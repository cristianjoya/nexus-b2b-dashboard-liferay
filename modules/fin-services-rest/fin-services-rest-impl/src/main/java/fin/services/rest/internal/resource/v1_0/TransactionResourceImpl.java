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
import fin.services.model.FinTransaction;
import fin.services.rest.dto.v1_0.Transaction;
import fin.services.rest.internal.resource.support.FinB2BAccountAccess;
import fin.services.rest.internal.resource.support.FinB2BPermissionEnforcer;
import fin.services.rest.internal.resource.support.SiteScopeResolver;
import fin.services.rest.resource.v1_0.TransactionResource;
import fin.services.service.FinAccountLocalService;
import fin.services.service.FinTransactionLocalService;

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
	properties = "OSGI-INF/liferay/rest/v1_0/transaction.properties",
	scope = ServiceScope.PROTOTYPE, service = TransactionResource.class
)
public class TransactionResourceImpl extends BaseTransactionResourceImpl {

	@Override
	public Response deleteTransaction(Long transactionId) throws Exception {
		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_TRANSACTIONS,
			FinB2BPermissionEnforcer.ACTION_DELETE);

		FinTransaction finTransaction = _getFinTransactionForDelete(
			transactionId);

		_finTransactionLocalService.deleteFinTransaction(finTransaction);

		return Response.noContent(
		).build();
	}

	@Override
	public Transaction getTransaction(Long transactionId) throws Exception {
		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_TRANSACTIONS,
			FinB2BPermissionEnforcer.ACTION_VIEW);

		return _toTransaction(_getFinTransaction(transactionId));
	}

	@Override
	public Page<Transaction> getTransactionsPage(
			String search, Long accountId, Long ownerUserId,
			String transactionType, Integer status, Pagination pagination)
		throws Exception {

		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_TRANSACTIONS,
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

		List<FinTransaction> finTransactions =
			_finTransactionLocalService.searchFinTransactionsByGroupId(
				groupId, search, accountId, transactionType, status,
				restrictedAccountIds, pagination.getStartPosition(),
				pagination.getEndPosition());

		int totalCount =
			_finTransactionLocalService.countSearchFinTransactionsByGroupId(
				groupId, search, accountId, transactionType, status,
				restrictedAccountIds);

		return Page.of(
			TransformUtil.transform(
				finTransactions, finTransaction -> _toTransaction(finTransaction)),
			pagination, totalCount);
	}

	@Override
	public Transaction postTransaction(Transaction transaction)
		throws Exception {

		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_TRANSACTIONS,
			FinB2BPermissionEnforcer.ACTION_ADD);

		long groupId = _getGroupId();

		_validateAccount(groupId, transaction.getAccountId());
		_validateTransactionBalance(groupId, transaction);

		FinTransaction finTransaction =
			_finTransactionLocalService.createFinTransaction(
				_counterLocalService.increment(
					FinTransaction.class.getName()));

		finTransaction.setGroupId(groupId);
		finTransaction.setCompanyId(contextCompany.getCompanyId());
		finTransaction.setUserId(contextUser.getUserId());
		finTransaction.setUserName(contextUser.getFullName());
		finTransaction.setCreateDate(new Date());
		finTransaction.setModifiedDate(new Date());

		_updateFinTransactionFields(finTransaction, transaction);

		if (finTransaction.getStatus() == 0) {
			finTransaction.setStatus(1);
		}

		return _toTransaction(
			_finTransactionLocalService.addFinTransaction(finTransaction));
	}

	@Override
	public Transaction putTransaction(
			Long transactionId, Transaction transaction)
		throws Exception {

		_requirePermission(
			FinB2BPermissionEnforcer.RESOURCE_TRANSACTIONS,
			FinB2BPermissionEnforcer.ACTION_UPDATE);

		FinTransaction finTransaction = _getFinTransaction(transactionId);

		if (transaction.getAccountId() != null) {
			_validateAccount(
				finTransaction.getGroupId(), transaction.getAccountId());
		}

		finTransaction.setModifiedDate(new Date());

		_updateFinTransactionFields(finTransaction, transaction);

		return _toTransaction(
			_finTransactionLocalService.updateFinTransaction(finTransaction));
	}

	private void _requirePermission(String resource, String action)
		throws PortalException {

		FinB2BPermissionEnforcer.require(
			contextUser, _getGroupId(), resource, action, _roleLocalService,
			_userGroupRoleLocalService);
	}

	private FinTransaction _getFinTransaction(long transactionId)
		throws PortalException {

		FinTransaction finTransaction =
			_finTransactionLocalService.getFinTransaction(transactionId);

		if (finTransaction.getGroupId() != _getGroupId()) {
			throw new PrincipalException();
		}

		_validateAccount(
			finTransaction.getGroupId(), finTransaction.getAccountId());

		return finTransaction;
	}

	/**
	 * Delete must work even if the linked account was already removed
	 * (orphan cleanup). When the account still exists, ownership is enforced.
	 */
	private FinTransaction _getFinTransactionForDelete(long transactionId)
		throws PortalException {

		FinTransaction finTransaction =
			_finTransactionLocalService.getFinTransaction(transactionId);

		long groupId = _getGroupId();

		if (finTransaction.getGroupId() != groupId) {
			throw new PrincipalException();
		}

		FinAccount finAccount = _finAccountLocalService.fetchFinAccount(
			finTransaction.getAccountId());

		if (finAccount != null) {
			FinB2BAccountAccess.requireAccountAccess(
				contextUser, groupId, finAccount, _roleLocalService,
				_userGroupRoleLocalService);
		}

		return finTransaction;
	}

	private long _getGroupId() throws PortalException {
		return new SiteScopeResolver(
			contextCompany, _groupLocalService, contextHttpServletRequest
		).getGroupId();
	}

	private Transaction _toTransaction(FinTransaction finTransaction) {
		Transaction transaction = new Transaction();

		transaction.setAccountId(finTransaction.getAccountId());
		transaction.setAmount(finTransaction.getAmount());
		transaction.setCreateDate(finTransaction.getCreateDate());
		transaction.setDescription(finTransaction.getDescription());
		transaction.setModifiedDate(finTransaction.getModifiedDate());
		transaction.setStatus(finTransaction.getStatus());
		transaction.setTransactionDate(finTransaction.getTransactionDate());
		transaction.setTransactionId(finTransaction.getTransactionId());
		transaction.setTransactionType(finTransaction.getTransactionType());
		transaction.setUuid(finTransaction.getUuid());

		return transaction;
	}

	private void _updateFinTransactionFields(
		FinTransaction finTransaction, Transaction transaction) {

		if (transaction.getAccountId() != null) {
			finTransaction.setAccountId(transaction.getAccountId());
		}

		if (transaction.getAmount() != null) {
			finTransaction.setAmount(transaction.getAmount());
		}

		if (Validator.isNotNull(transaction.getDescription())) {
			finTransaction.setDescription(transaction.getDescription());
		}

		if (transaction.getStatus() != null) {
			finTransaction.setStatus(transaction.getStatus());
		}

		if (transaction.getTransactionDate() != null) {
			finTransaction.setTransactionDate(transaction.getTransactionDate());
		}

		if (Validator.isNotNull(transaction.getTransactionType())) {
			finTransaction.setTransactionType(transaction.getTransactionType());
		}
	}

	private void _validateAccount(long groupId, Long accountId)
		throws PortalException {

		if ((accountId == null) || (accountId <= 0)) {
			throw new PortalException("Account is required for transaction");
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

	private void _validateTransactionBalance(
			long groupId, Transaction transaction)
		throws PortalException {

		if ((transaction.getAmount() == null) ||
			(transaction.getAmount() <= 0)) {

			throw new PortalException("Amount must be greater than zero");
		}

		String transactionType = transaction.getTransactionType();

		if (!"WITHDRAWAL".equals(transactionType) &&
			!"PAYMENT".equals(transactionType)) {

			return;
		}

		FinAccount finAccount = _finAccountLocalService.fetchFinAccount(
			transaction.getAccountId());

		if ((finAccount == null) || (finAccount.getGroupId() != groupId)) {
			throw new PortalException(
				"Account not found: " + transaction.getAccountId());
		}

		if (finAccount.getBalance() < transaction.getAmount()) {
			throw new PortalException(
				"Insufficient balance for account: " +
					finAccount.getAccountNumber());
		}
	}

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private FinAccountLocalService _finAccountLocalService;

	@Reference
	private FinTransactionLocalService _finTransactionLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

}
