package fin.services.rest.internal.resource.v1_0;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import fin.services.model.FinAccount;
import fin.services.model.FinCreditApp;
import fin.services.rest.dto.v1_0.CreditApplication;
import fin.services.rest.internal.resource.support.FinB2BAccountAccess;
import fin.services.rest.internal.resource.support.FinB2BPermissionEnforcer;
import fin.services.rest.internal.resource.support.SiteScopeResolver;
import fin.services.rest.resource.v1_0.CreditApplicationResource;
import fin.services.service.FinAccountLocalService;
import fin.services.service.FinCreditAppLocalService;

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
	properties = "OSGI-INF/liferay/rest/v1_0/credit-application.properties",
	scope = ServiceScope.PROTOTYPE, service = CreditApplicationResource.class
)
public class CreditApplicationResourceImpl
	extends BaseCreditApplicationResourceImpl {

	@Override
	public Response deleteCreditApplication(Long creditAppId) throws Exception {
		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_CREDIT_APPLICATIONS,
			FinB2BPermissionEnforcer.ACTION_DELETE);

		FinCreditApp finCreditApp = _getFinCreditAppForDelete(creditAppId);

		_finCreditAppLocalService.deleteFinCreditApp(finCreditApp);

		return Response.noContent(
		).build();
	}

	@Override
	public CreditApplication getCreditApplication(Long creditAppId)
		throws Exception {

		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_CREDIT_APPLICATIONS,
			FinB2BPermissionEnforcer.ACTION_VIEW);

		return _toCreditApplication(_getFinCreditApp(creditAppId));
	}

	@Override
	public Page<CreditApplication> getCreditApplicationsPage(
			String search, Long accountId, Long ownerUserId, Integer status,
			Pagination pagination)
		throws Exception {

		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_CREDIT_APPLICATIONS,
			FinB2BPermissionEnforcer.ACTION_VIEW);

		long groupId = _getGroupId();
		List<Long> restrictedAccountIds =
			FinB2BAccountAccess.applyOwnerUserIdFilter(
				groupId, ownerUserId,
				FinB2BAccountAccess.getAccessibleAccountIds(
					contextUser, groupId, _finAccountLocalService,
					_roleLocalService, _userGroupRoleLocalService),
				_finAccountLocalService);

		if ((accountId != null) && (restrictedAccountIds != null) &&
			!restrictedAccountIds.contains(accountId)) {

			accountId = null;
			restrictedAccountIds = List.of();
		}

		List<FinCreditApp> finCreditApps =
			_finCreditAppLocalService.searchFinCreditAppsByGroupId(
				groupId, search, accountId, status, restrictedAccountIds,
				pagination.getStartPosition(), pagination.getEndPosition());

		int totalCount =
			_finCreditAppLocalService.countSearchFinCreditAppsByGroupId(
				groupId, search, accountId, status, restrictedAccountIds);

		return Page.of(
			TransformUtil.transform(
				finCreditApps,
				finCreditApp -> _toCreditApplication(finCreditApp)),
			pagination, totalCount);
	}

	@Override
	public CreditApplication postCreditApplication(
			CreditApplication creditApplication)
		throws Exception {

		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_CREDIT_APPLICATIONS,
			FinB2BPermissionEnforcer.ACTION_ADD);

		long groupId = _getGroupId();

		_validateAccount(groupId, creditApplication.getAccountId());

		FinCreditApp finCreditApp = _finCreditAppLocalService.createFinCreditApp(
			_counterLocalService.increment(FinCreditApp.class.getName()));

		finCreditApp.setGroupId(groupId);
		finCreditApp.setCompanyId(contextCompany.getCompanyId());
		finCreditApp.setUserId(contextUser.getUserId());
		finCreditApp.setUserName(contextUser.getFullName());
		finCreditApp.setCreateDate(new Date());
		finCreditApp.setModifiedDate(new Date());
		finCreditApp.setStatus(0);

		_updateFinCreditAppFields(finCreditApp, creditApplication);

		return _toCreditApplication(
			_finCreditAppLocalService.addFinCreditApp(finCreditApp));
	}

	@Override
	public CreditApplication putCreditApplication(
			Long creditAppId, CreditApplication creditApplication)
		throws Exception {

		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_CREDIT_APPLICATIONS,
			FinB2BPermissionEnforcer.ACTION_UPDATE);

		FinCreditApp finCreditApp = _getFinCreditApp(creditAppId);

		if (creditApplication.getAccountId() != null) {
			_validateAccount(
				finCreditApp.getGroupId(), creditApplication.getAccountId());
		}

		finCreditApp.setModifiedDate(new Date());

		_updateFinCreditAppFields(finCreditApp, creditApplication);

		return _toCreditApplication(
			_finCreditAppLocalService.updateFinCreditApp(finCreditApp));
	}

	private void _requirePermission(String resource, String action)
		throws PortalException {

		FinB2BPermissionEnforcer.require(
			contextUser, _getGroupId(), resource, action, _roleLocalService,
			_userGroupRoleLocalService);
	}

	private FinCreditApp _getFinCreditApp(long creditAppId)
		throws PortalException {

		FinCreditApp finCreditApp = _finCreditAppLocalService.getFinCreditApp(
			creditAppId);

		if (finCreditApp.getGroupId() != _getGroupId()) {
			throw new PrincipalException();
		}

		_validateAccount(
			finCreditApp.getGroupId(), finCreditApp.getAccountId());

		return finCreditApp;
	}

	/**
	 * Delete must work even if the linked account was already removed.
	 * When the account still exists, ownership is enforced.
	 */
	private FinCreditApp _getFinCreditAppForDelete(long creditAppId)
		throws PortalException {

		FinCreditApp finCreditApp = _finCreditAppLocalService.getFinCreditApp(
			creditAppId);

		long groupId = _getGroupId();

		if (finCreditApp.getGroupId() != groupId) {
			throw new PrincipalException();
		}

		FinAccount finAccount = _finAccountLocalService.fetchFinAccount(
			finCreditApp.getAccountId());

		if (finAccount != null) {
			FinB2BAccountAccess.requireAccountAccess(
				contextUser, groupId, finAccount, _roleLocalService,
				_userGroupRoleLocalService);
		}

		return finCreditApp;
	}

	private long _getGroupId() throws PortalException {
		return new SiteScopeResolver(
			contextCompany, _groupLocalService, contextHttpServletRequest
		).getGroupId();
	}

	private CreditApplication _toCreditApplication(FinCreditApp finCreditApp) {
		CreditApplication creditApplication = new CreditApplication();

		creditApplication.setAccountId(finCreditApp.getAccountId());
		creditApplication.setApplicantName(finCreditApp.getApplicantName());
		creditApplication.setCreateDate(finCreditApp.getCreateDate());
		creditApplication.setCreditAppId(finCreditApp.getCreditAppId());
		creditApplication.setModifiedDate(finCreditApp.getModifiedDate());
		creditApplication.setPurpose(finCreditApp.getPurpose());
		creditApplication.setRequestedAmount(finCreditApp.getRequestedAmount());
		creditApplication.setReviewNotes(finCreditApp.getReviewNotes());
		creditApplication.setStatus(finCreditApp.getStatus());
		creditApplication.setUuid(finCreditApp.getUuid());

		return creditApplication;
	}

	private void _updateFinCreditAppFields(
		FinCreditApp finCreditApp, CreditApplication creditApplication) {

		if (creditApplication.getAccountId() != null) {
			finCreditApp.setAccountId(creditApplication.getAccountId());
		}

		if (Validator.isNotNull(creditApplication.getApplicantName())) {
			finCreditApp.setApplicantName(
				creditApplication.getApplicantName());
		}

		if (Validator.isNotNull(creditApplication.getPurpose())) {
			finCreditApp.setPurpose(creditApplication.getPurpose());
		}

		if (creditApplication.getRequestedAmount() != null) {
			finCreditApp.setRequestedAmount(
				creditApplication.getRequestedAmount());
		}

		if (Validator.isNotNull(creditApplication.getReviewNotes())) {
			finCreditApp.setReviewNotes(creditApplication.getReviewNotes());
		}

		if (creditApplication.getStatus() != null) {
			finCreditApp.setStatus(creditApplication.getStatus());
		}
	}

	private void _validateAccount(long groupId, Long accountId)
		throws PortalException {

		if ((accountId == null) || (accountId <= 0)) {
			throw new PortalException(
				"Account is required for credit application");
		}

		FinAccount finAccount = _finAccountLocalService.fetchFinAccount(
			accountId);

		if ((finAccount == null) || (finAccount.getGroupId() != groupId)) {
			throw new PortalException("Account not found: " + accountId);
		}

		FinB2BAccountAccess.requireAccountAccess(
			contextUser, groupId, finAccount, _roleLocalService,
			_userGroupRoleLocalService);
	}

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private FinAccountLocalService _finAccountLocalService;

	@Reference
	private FinCreditAppLocalService _finCreditAppLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

}
