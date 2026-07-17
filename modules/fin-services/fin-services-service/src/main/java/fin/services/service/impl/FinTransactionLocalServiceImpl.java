/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

import fin.services.model.FinAccount;
import fin.services.model.FinTransaction;
import fin.services.service.FinAccountLocalService;
import fin.services.service.base.FinTransactionLocalServiceBaseImpl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author krism
 */
@Component(
	property = "model.class.name=fin.services.model.FinTransaction",
	service = AopService.class
)
public class FinTransactionLocalServiceImpl
	extends FinTransactionLocalServiceBaseImpl {

	public static final int STATUS_COMPLETED = 1;

	public static final String TYPE_DEPOSIT = "DEPOSIT";
	public static final String TYPE_PAYMENT = "PAYMENT";
	public static final String TYPE_WITHDRAWAL = "WITHDRAWAL";

	public int countSearchFinTransactionsByGroupId(
		long groupId, String keywords, Long accountId, String transactionType,
		Integer status, Collection<Long> restrictedAccountIds) {

		if ((restrictedAccountIds != null) && restrictedAccountIds.isEmpty()) {
			return 0;
		}

		return (int)dynamicQueryCount(
			_buildSearchDynamicQuery(
				groupId, keywords, accountId, transactionType, status,
				restrictedAccountIds));
	}

	@Override
	public FinTransaction addFinTransaction(FinTransaction finTransaction) {
		if (finTransaction.getTransactionDate() == null) {
			finTransaction.setTransactionDate(new Date());
		}

		try {
			_precheckBalanceChange(finTransaction, 1);
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}

		FinTransaction added = super.addFinTransaction(finTransaction);

		try {
			_applyBalanceChange(added, 1);
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}

		return added;
	}

	@Override
	public FinTransaction deleteFinTransaction(FinTransaction finTransaction) {
		try {
			_applyBalanceChange(finTransaction, -1);
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}

		return super.deleteFinTransaction(finTransaction);
	}

	@Override
	public FinTransaction updateFinTransaction(FinTransaction finTransaction) {
		try {
			FinTransaction existingFinTransaction = getFinTransaction(
				finTransaction.getTransactionId());

			_applyBalanceChange(existingFinTransaction, -1);

			FinTransaction updatedFinTransaction = super.updateFinTransaction(
				finTransaction);

			_applyBalanceChange(updatedFinTransaction, 1);

			return updatedFinTransaction;
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}
	}

	public List<FinTransaction> searchFinTransactionsByGroupId(
		long groupId, String keywords, Long accountId, String transactionType,
		Integer status, Collection<Long> restrictedAccountIds, int start,
		int end) {

		if ((restrictedAccountIds != null) && restrictedAccountIds.isEmpty()) {
			return List.of();
		}

		return dynamicQuery(
			_buildSearchDynamicQuery(
				groupId, keywords, accountId, transactionType, status,
				restrictedAccountIds),
			start, end);
	}

	private void _precheckBalanceChange(
			FinTransaction finTransaction, int sign)
		throws PortalException {

		if (finTransaction.getStatus() != STATUS_COMPLETED) {
			return;
		}

		String transactionType = finTransaction.getTransactionType();

		if (!TYPE_WITHDRAWAL.equals(transactionType) &&
			!TYPE_PAYMENT.equals(transactionType)) {

			return;
		}

		if (sign <= 0) {
			return;
		}

		FinAccount finAccount = _finAccountLocalService.fetchFinAccount(
			finTransaction.getAccountId());

		if ((finAccount == null) ||
			(finAccount.getGroupId() != finTransaction.getGroupId())) {

			throw new PortalException(
				"Account not found for transaction: " +
					finTransaction.getAccountId());
		}

		if (finAccount.getBalance() < finTransaction.getAmount()) {
			throw new PortalException(
				"Insufficient balance for account: " +
					finAccount.getAccountNumber());
		}
	}

	private void _applyBalanceChange(FinTransaction finTransaction, int sign)
		throws PortalException {

		if (finTransaction.getStatus() != STATUS_COMPLETED) {
			return;
		}

		FinAccount finAccount = _finAccountLocalService.fetchFinAccount(
			finTransaction.getAccountId());

		if ((finAccount == null) ||
			(finAccount.getGroupId() != finTransaction.getGroupId())) {

			// Orphan transaction (account already deleted): skip ledger adjust.
			return;
		}

		double amount = finTransaction.getAmount() * sign;
		String transactionType = finTransaction.getTransactionType();

		if (TYPE_DEPOSIT.equals(transactionType)) {
			finAccount.setBalance(finAccount.getBalance() + amount);
		}
		else if (
			TYPE_WITHDRAWAL.equals(transactionType) ||
				TYPE_PAYMENT.equals(transactionType)) {

			if ((sign > 0) &&
				(finAccount.getBalance() < finTransaction.getAmount())) {

				throw new PortalException(
					"Insufficient balance for account: " +
						finAccount.getAccountNumber());
			}

			finAccount.setBalance(finAccount.getBalance() - amount);
		}

		finAccount.setModifiedDate(new Date());

		_finAccountLocalService.updateFinAccount(finAccount);
	}

	private DynamicQuery _buildSearchDynamicQuery(
		long groupId, String keywords, Long accountId, String transactionType,
		Integer status, Collection<Long> restrictedAccountIds) {

		DynamicQuery dynamicQuery = dynamicQuery();

		dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));

		if (accountId != null) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("accountId", accountId));
		}
		else if (restrictedAccountIds != null) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.in(
					"accountId", restrictedAccountIds.toArray(new Long[0])));
		}

		if (Validator.isNotNull(keywords)) {
			String pattern = "%" + keywords.trim() + "%";
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			disjunction.add(
				RestrictionsFactoryUtil.ilike("description", pattern));
			disjunction.add(
				RestrictionsFactoryUtil.ilike("transactionType", pattern));

			dynamicQuery.add(disjunction);
		}

		if (Validator.isNotNull(transactionType) && !transactionType.isBlank()) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.eq("transactionType", transactionType));
		}

		if (status != null) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("status", status));
		}

		return dynamicQuery;
	}

	@Reference
	private FinAccountLocalService _finAccountLocalService;

}
