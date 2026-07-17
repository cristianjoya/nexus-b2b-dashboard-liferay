package fin.services.rest.internal.resource.v1_0;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import fin.services.model.FinAccount;
import fin.services.model.FinCreditApp;
import fin.services.model.FinTransaction;
import fin.services.rest.dto.v1_0.Account;
import fin.services.rest.dto.v1_0.B2bAssignableUser;
import fin.services.rest.dto.v1_0.CardBin;
import fin.services.rest.internal.constants.FinB2BConstants;
import fin.services.rest.internal.integration.BinlistClient;
import fin.services.rest.internal.resource.support.FinB2BAccountAccess;
import fin.services.rest.internal.resource.support.FinB2BPermissionEnforcer;
import fin.services.rest.internal.resource.support.FinB2BPermissionSupport;
import fin.services.rest.resource.v1_0.AccountResource;
import fin.services.service.FinAccountLocalService;
import fin.services.service.FinCreditAppLocalService;
import fin.services.service.FinTransactionLocalService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import jakarta.ws.rs.core.Response;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author krism
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/account.properties",
	scope = ServiceScope.PROTOTYPE, service = AccountResource.class
)
public class AccountResourceImpl extends BaseAccountResourceImpl {

	@Override
	public Page<B2bAssignableUser> getAssignableB2BUsersPage(
			String search, Pagination pagination)
		throws Exception {

		long groupId = _getGroupId();

		if (!FinB2BAccountAccess.canAssignOwners(
				contextUser, groupId, _roleLocalService,
				_userGroupRoleLocalService)) {

			throw new PrincipalException(
				"Only B2B managers can list assignable users");
		}

		List<User> groupUsers = _userLocalService.getGroupUsers(groupId);
		List<B2bAssignableUser> assignableUsers = new ArrayList<>();
		String searchTerm =
			Validator.isNotNull(search) ? search.trim().toLowerCase() : null;

		for (User user : groupUsers) {
			if (user.isGuestUser() || !user.isActive()) {
				continue;
			}

			if (!FinB2BPermissionSupport.hasGroupRole(
					contextCompany.getCompanyId(), user.getUserId(), groupId,
					FinB2BConstants.ROLE_B2B_USER, _roleLocalService,
					_userGroupRoleLocalService)) {

				continue;
			}

			String fullName = GetterUtil.getString(user.getFullName());
			String emailAddress = GetterUtil.getString(user.getEmailAddress());

			if ((searchTerm != null) && !searchTerm.isEmpty() &&
				!fullName.toLowerCase().contains(searchTerm) &&
				!emailAddress.toLowerCase().contains(searchTerm)) {

				continue;
			}

			B2bAssignableUser assignableUser = new B2bAssignableUser();

			assignableUser.setEmailAddress(emailAddress);
			assignableUser.setFullName(fullName);
			assignableUser.setUserId(user.getUserId());

			assignableUsers.add(assignableUser);
		}

		int totalCount = assignableUsers.size();
		int start = pagination.getStartPosition();
		int end = Math.min(pagination.getEndPosition(), totalCount);

		if (start >= totalCount) {
			return Page.of(Collections.emptyList(), pagination, totalCount);
		}

		return Page.of(
			assignableUsers.subList(start, end), pagination, totalCount);
	}

	@Override
	public Response deleteAccount(Long accountId) throws Exception {
		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_ACCOUNTS,
			FinB2BPermissionEnforcer.ACTION_DELETE);

		FinAccount finAccount = _getFinAccount(accountId);

		_deleteRelatedRecords(finAccount);

		_finAccountLocalService.deleteFinAccount(finAccount);

		return Response.noContent(
		).build();
	}

	@Override
	public Account getAccount(Long accountId) throws Exception {
		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_ACCOUNTS,
			FinB2BPermissionEnforcer.ACTION_VIEW);

		return _toAccount(_getFinAccount(accountId));
	}

	@Override
	public Page<Account> getAccountsPage(
			String search, String accountType, Integer status, Long ownerUserId,
			Pagination pagination)
		throws Exception {

		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_ACCOUNTS,
			FinB2BPermissionEnforcer.ACTION_VIEW);

		long groupId = _getGroupId();
		Long scopedOwnerUserId =
			FinB2BAccountAccess.resolveListOwnerUserIdFilter(
				contextUser, groupId, _roleLocalService,
				_userGroupRoleLocalService);
		Long filterOwnerUserId = scopedOwnerUserId;

		if (filterOwnerUserId == null) {
			if ((ownerUserId != null) && (ownerUserId > 0)) {
				filterOwnerUserId = ownerUserId;
			}
		}

		List<FinAccount> finAccounts =
			_finAccountLocalService.searchFinAccountsByGroupId(
				groupId, search, accountType, status, filterOwnerUserId,
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalCount =
			_finAccountLocalService.countSearchFinAccountsByGroupId(
				groupId, search, accountType, status, filterOwnerUserId);

		return Page.of(
			TransformUtil.transform(
				finAccounts, finAccount -> _toAccount(finAccount)),
			pagination, totalCount);
	}

	@Override
	public Account postAccount(Account account) throws Exception {
		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_ACCOUNTS,
			FinB2BPermissionEnforcer.ACTION_ADD);

		long groupId = _getGroupId();

		_validateAccountNumber(groupId, account.getAccountNumber(), 0);

		FinAccount finAccount = _finAccountLocalService.createFinAccount(
			_counterLocalService.increment(FinAccount.class.getName()));

		finAccount.setGroupId(groupId);
		finAccount.setCompanyId(contextCompany.getCompanyId());
		finAccount.setUserId(contextUser.getUserId());
		finAccount.setUserName(contextUser.getFullName());
		finAccount.setCreateDate(new Date());
		finAccount.setModifiedDate(new Date());

		_applyOwnerUserId(finAccount, account, true);

		_updateFinAccountFields(finAccount, account);

		return _toAccount(
			_finAccountLocalService.addFinAccount(finAccount));
	}

	@Override
	public Account putAccount(Long accountId, Account account)
		throws Exception {

		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_ACCOUNTS,
			FinB2BPermissionEnforcer.ACTION_UPDATE);

		FinAccount finAccount = _getFinAccount(accountId);

		_validateAccountNumber(
			finAccount.getGroupId(), account.getAccountNumber(),
			finAccount.getAccountId());

		finAccount.setModifiedDate(new Date());

		_applyOwnerUserId(finAccount, account, false);

		_updateFinAccountFields(finAccount, account);

		return _toAccount(
			_finAccountLocalService.updateFinAccount(finAccount));
	}

	private void _requirePermission(String resource, String action)
		throws PortalException {

		FinB2BPermissionEnforcer.require(
			contextUser, _getGroupId(), resource, action, _roleLocalService,
			_userGroupRoleLocalService);
	}

	private FinAccount _getFinAccount(long accountId) throws PortalException {
		FinAccount finAccount = _finAccountLocalService.getFinAccount(
			accountId);

		if (finAccount.getGroupId() != _getGroupId()) {
			throw new PrincipalException();
		}

		FinB2BAccountAccess.requireAccountAccess(
			contextUser, finAccount.getGroupId(), finAccount,
			_roleLocalService, _userGroupRoleLocalService);

		return finAccount;
	}

	private long _getGroupId() throws PortalException {
		long scopeGroupId = 0;

		String friendlyUrl = contextHttpServletRequest.getHeader(
			"X-Liferay-Site-Friendly-Url");

		if (Validator.isNull(friendlyUrl)) {
			friendlyUrl = contextHttpServletRequest.getParameter(
				"siteFriendlyUrl");
		}

		scopeGroupId = _resolveGroupIdFromFriendlyUrl(friendlyUrl);

		if (scopeGroupId > 0) {
			return scopeGroupId;
		}

		String currentUrl = contextHttpServletRequest.getHeader(
			"X-Liferay-Current-Url");

		scopeGroupId = _resolveGroupIdFromPageUrl(currentUrl);

		if (scopeGroupId > 0) {
			return scopeGroupId;
		}

		scopeGroupId = _resolveGroupIdFromPageUrl(
			contextHttpServletRequest.getHeader("Referer"));

		if (scopeGroupId > 0) {
			return scopeGroupId;
		}

		scopeGroupId = GetterUtil.getLong(
			contextHttpServletRequest.getHeader("X-Liferay-Scope-Group-Id"),
			0);

		if (scopeGroupId == 0) {
			scopeGroupId = GetterUtil.getLong(
				contextHttpServletRequest.getParameter("scopeGroupId"), 0);
		}

		if (scopeGroupId > 0) {
			return _validateGroupId(scopeGroupId);
		}

		return PortalUtil.getScopeGroupId(contextHttpServletRequest);
	}

	private long _resolveGroupIdFromFriendlyUrl(String friendlyUrl) {
		if (Validator.isNull(friendlyUrl)) {
			return 0;
		}

		if (!friendlyUrl.startsWith("/")) {
			friendlyUrl = "/" + friendlyUrl;
		}

		Group group = _groupLocalService.fetchFriendlyURLGroup(
			contextCompany.getCompanyId(), friendlyUrl);

		if ((group == null) ||
			(group.getCompanyId() != contextCompany.getCompanyId())) {

			return 0;
		}

		return group.getGroupId();
	}

	private long _resolveGroupIdFromPageUrl(String url) {
		if (Validator.isNull(url)) {
			return 0;
		}

		int pathStart = url.indexOf("://");

		if (pathStart > 0) {
			pathStart = url.indexOf('/', pathStart + 3);

			if (pathStart > 0) {
				url = url.substring(pathStart);
			}
		}

		int queryIndex = url.indexOf('?');

		if (queryIndex > 0) {
			url = url.substring(0, queryIndex);
		}

		return _resolveGroupIdFromPath(url);
	}

	private long _resolveGroupIdFromPath(String path) {
		String[] segments = path.split("/");
		int index = 0;

		if ((segments.length > index + 1) && _isLocaleSegment(segments[index])) {
			index++;
		}

		if ((segments.length > index) && segments[index].equals("web") &&
			(segments.length > index + 1)) {

			return _resolveGroupIdFromFriendlyUrl("/" + segments[index + 1]);
		}

		if ((segments.length > index) && segments[index].equals("group") &&
			(segments.length > index + 1)) {

			return _resolveGroupIdFromFriendlyUrl("/" + segments[index + 1]);
		}

		if ((segments.length > index) &&
			!_isReservedPathSegment(segments[index])) {

			return _resolveGroupIdFromFriendlyUrl("/" + segments[index]);
		}

		return 0;
	}

	private boolean _isLocaleSegment(String segment) {
		if (Validator.isNull(segment)) {
			return false;
		}

		return segment.matches("[a-z]{2}") ||
			segment.matches("[a-z]{2}_[A-Z]{2}");
	}

	private boolean _isReservedPathSegment(String segment) {
		return segment.equals("api") || segment.equals("c") ||
			segment.equals("documents") || segment.equals("group") ||
			segment.equals("image") || segment.equals("o") ||
			segment.equals("web");
	}

	private long _validateGroupId(long scopeGroupId) throws PortalException {
		Group group = _groupLocalService.getGroup(scopeGroupId);

		if (group.getCompanyId() != contextCompany.getCompanyId()) {
			throw new PrincipalException();
		}

		return scopeGroupId;
	}

	private Account _toAccount(FinAccount finAccount) {
		Account account = new Account();

		account.setAccountId(finAccount.getAccountId());
		account.setAccountName(finAccount.getAccountName());
		account.setAccountNumber(finAccount.getAccountNumber());
		account.setAccountType(finAccount.getAccountType());
		account.setBalance(finAccount.getBalance());
		account.setCardBankName(finAccount.getCardBankName());
		account.setCardBrand(finAccount.getCardBrand());
		account.setCardCountryName(finAccount.getCardCountryName());
		account.setCardScheme(finAccount.getCardScheme());
		account.setCreateDate(finAccount.getCreateDate());
		account.setModifiedDate(finAccount.getModifiedDate());
		account.setOwnerUserId(finAccount.getOwnerUserId());
		account.setOwnerUserName(_resolveOwnerUserName(finAccount.getOwnerUserId()));
		account.setStatus(finAccount.getStatus());
		account.setUuid(finAccount.getUuid());

		return account;
	}

	private void _applyOwnerUserId(
			FinAccount finAccount, Account account, boolean creating)
		throws PortalException {

		long groupId = finAccount.getGroupId();

		if (!FinB2BAccountAccess.canAssignOwners(
				contextUser, groupId, _roleLocalService,
				_userGroupRoleLocalService)) {

			return;
		}

		Long ownerUserId = account.getOwnerUserId();

		if (creating || (ownerUserId != null)) {
			if ((ownerUserId == null) || (ownerUserId <= 0)) {
				throw new PortalException("Assigned B2B user is required");
			}

			FinB2BAccountAccess.requireAssignableOwner(
				contextCompany.getCompanyId(), groupId, ownerUserId,
				_roleLocalService, _userGroupRoleLocalService,
				_userLocalService);

			finAccount.setOwnerUserId(ownerUserId);
		}
	}

	private String _resolveOwnerUserName(long ownerUserId) {
		if (ownerUserId <= 0) {
			return null;
		}

		User ownerUser = _userLocalService.fetchUser(ownerUserId);

		if (ownerUser == null) {
			return null;
		}

		return ownerUser.getFullName();
	}

	private void _updateFinAccountFields(
		FinAccount finAccount, Account account) {

		if (Validator.isNotNull(account.getAccountName())) {
			finAccount.setAccountName(account.getAccountName());
		}

		if (Validator.isNotNull(account.getAccountNumber())) {
			finAccount.setAccountNumber(account.getAccountNumber());
		}

		if (Validator.isNotNull(account.getAccountType())) {
			finAccount.setAccountType(account.getAccountType());
		}

		if (account.getBalance() != null) {
			finAccount.setBalance(account.getBalance());
		}

		if (account.getStatus() != null) {
			finAccount.setStatus(account.getStatus());
		}

		_applyCardMetadata(finAccount, account);
	}

	private void _applyCardMetadata(FinAccount finAccount, Account account) {
		if (Validator.isNotNull(account.getCardScheme())) {
			finAccount.setCardScheme(account.getCardScheme());
			finAccount.setCardBrand(account.getCardBrand());
			finAccount.setCardBankName(account.getCardBankName());
			finAccount.setCardCountryName(account.getCardCountryName());

			return;
		}

		String accountNumber = account.getAccountNumber();

		if (Validator.isNull(accountNumber)) {
			accountNumber = finAccount.getAccountNumber();
		}

		String digits = _normalizeDigits(accountNumber);

		if ((digits == null) || (digits.length() < 6)) {
			return;
		}

		String bin = digits.substring(0, Math.min(8, digits.length()));
		CardBin cardBin = _binlistClient.lookup(bin);

		if (Validator.isNotNull(cardBin.getScheme())) {
			finAccount.setCardScheme(cardBin.getScheme());
			finAccount.setCardBrand(cardBin.getBrand());
			finAccount.setCardBankName(cardBin.getBankName());
			finAccount.setCardCountryName(cardBin.getCountryName());
		}
	}

	private String _normalizeDigits(String value) {
		if (Validator.isNull(value)) {
			return null;
		}

		String digits = value.replaceAll("\\D", "");

		if (digits.isEmpty()) {
			return null;
		}

		return digits;
	}

	private void _deleteRelatedRecords(FinAccount finAccount) {
		long groupId = finAccount.getGroupId();
		long accountId = finAccount.getAccountId();

		DynamicQuery creditQuery = _finCreditAppLocalService.dynamicQuery();

		creditQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		creditQuery.add(RestrictionsFactoryUtil.eq("accountId", accountId));

		List<FinCreditApp> creditApps = _finCreditAppLocalService.dynamicQuery(
			creditQuery);

		for (FinCreditApp creditApp : creditApps) {
			_finCreditAppLocalService.deleteFinCreditApp(creditApp);
		}

		DynamicQuery transactionQuery =
			_finTransactionLocalService.dynamicQuery();

		transactionQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		transactionQuery.add(
			RestrictionsFactoryUtil.eq("accountId", accountId));

		List<FinTransaction> transactions =
			_finTransactionLocalService.dynamicQuery(transactionQuery);

		for (FinTransaction transaction : transactions) {
			_finTransactionLocalService.deleteFinTransaction(transaction);
		}
	}

	private void _validateAccountNumber(
			long groupId, String accountNumber, long accountId)
		throws PortalException {

		if (Validator.isNull(accountNumber)) {
			throw new PortalException("Account number is required");
		}

		FinAccount existingFinAccount =
			_finAccountLocalService.fetchFinAccountByGroupIdAndAccountNumber(
				groupId, accountNumber);

		if ((existingFinAccount != null) &&
			(existingFinAccount.getAccountId() != accountId)) {

			throw new PortalException(
				"Account number already exists: " + accountNumber);
		}
	}

	@Reference
	private BinlistClient _binlistClient;

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private FinAccountLocalService _finAccountLocalService;

	@Reference
	private FinCreditAppLocalService _finCreditAppLocalService;

	@Reference
	private FinTransactionLocalService _finTransactionLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}
