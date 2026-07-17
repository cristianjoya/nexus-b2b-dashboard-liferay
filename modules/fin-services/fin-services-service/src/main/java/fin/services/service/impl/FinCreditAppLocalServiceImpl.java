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
import fin.services.model.FinCreditApp;
import fin.services.service.FinAccountLocalService;
import fin.services.service.base.FinCreditAppLocalServiceBaseImpl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author krism
 */
@Component(
	property = "model.class.name=fin.services.model.FinCreditApp",
	service = AopService.class
)
public class FinCreditAppLocalServiceImpl
	extends FinCreditAppLocalServiceBaseImpl {

	public static final int STATUS_APPROVED = 1;

	@Override
	public FinCreditApp deleteFinCreditApp(FinCreditApp finCreditApp) {
		try {
			if (finCreditApp.getStatus() == STATUS_APPROVED) {
				FinAccount finAccount = _finAccountLocalService.fetchFinAccount(
					finCreditApp.getAccountId());

				if ((finAccount != null) &&
					(finAccount.getGroupId() == finCreditApp.getGroupId())) {

					_adjustAccountBalance(
						finCreditApp.getGroupId(), finCreditApp.getAccountId(),
						-finCreditApp.getRequestedAmount());
				}
			}
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}

		return super.deleteFinCreditApp(finCreditApp);
	}

	public int countSearchFinCreditAppsByGroupId(
		long groupId, String keywords, Long accountId, Integer status,
		Collection<Long> restrictedAccountIds) {

		if ((restrictedAccountIds != null) && restrictedAccountIds.isEmpty()) {
			return 0;
		}

		return (int)dynamicQueryCount(
			_buildSearchDynamicQuery(
				groupId, keywords, accountId, status, restrictedAccountIds));
	}

	@Override
	public FinCreditApp updateFinCreditApp(FinCreditApp finCreditApp) {
		try {
			FinCreditApp existingFinCreditApp = getFinCreditApp(
				finCreditApp.getCreditAppId());

			int previousStatus = existingFinCreditApp.getStatus();
			long previousAccountId = existingFinCreditApp.getAccountId();
			double previousAmount = existingFinCreditApp.getRequestedAmount();
			long groupId = existingFinCreditApp.getGroupId();

			FinCreditApp updatedFinCreditApp = super.updateFinCreditApp(
				finCreditApp);

			boolean wasApproved = previousStatus == STATUS_APPROVED;
			boolean isApproved =
				updatedFinCreditApp.getStatus() == STATUS_APPROVED;

			if (wasApproved && !isApproved) {
				_adjustAccountBalance(
					groupId, previousAccountId, -previousAmount);
			}
			else if (!wasApproved && isApproved) {
				_applyCreditApproval(updatedFinCreditApp);
			}
			else if (wasApproved && isApproved) {
				boolean accountChanged =
					previousAccountId != updatedFinCreditApp.getAccountId();
				boolean amountChanged =
					Double.compare(
						previousAmount,
						updatedFinCreditApp.getRequestedAmount()) != 0;

				if (accountChanged || amountChanged) {
					_adjustAccountBalance(
						groupId, previousAccountId, -previousAmount);
					_applyCreditApproval(updatedFinCreditApp);
				}
			}

			return updatedFinCreditApp;
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}
	}

	public List<FinCreditApp> searchFinCreditAppsByGroupId(
		long groupId, String keywords, Long accountId, Integer status,
		Collection<Long> restrictedAccountIds, int start, int end) {

		if ((restrictedAccountIds != null) && restrictedAccountIds.isEmpty()) {
			return List.of();
		}

		return dynamicQuery(
			_buildSearchDynamicQuery(
				groupId, keywords, accountId, status, restrictedAccountIds),
			start, end);
	}

	private void _adjustAccountBalance(
			long groupId, long accountId, double delta)
		throws PortalException {

		FinAccount finAccount = _finAccountLocalService.fetchFinAccount(
			accountId);

		if ((finAccount == null) || (finAccount.getGroupId() != groupId)) {
			throw new PortalException(
				"Account not found for credit balance adjustment: " +
					accountId);
		}

		finAccount.setBalance(finAccount.getBalance() + delta);
		finAccount.setModifiedDate(new Date());

		_finAccountLocalService.updateFinAccount(finAccount);
	}

	private void _applyCreditApproval(FinCreditApp finCreditApp)
		throws PortalException {

		_adjustAccountBalance(
			finCreditApp.getGroupId(), finCreditApp.getAccountId(),
			finCreditApp.getRequestedAmount());
	}

	private DynamicQuery _buildSearchDynamicQuery(
		long groupId, String keywords, Long accountId, Integer status,
		Collection<Long> restrictedAccountIds) {

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
				RestrictionsFactoryUtil.ilike("applicantName", pattern));
			disjunction.add(RestrictionsFactoryUtil.ilike("purpose", pattern));

			dynamicQuery.add(disjunction);
		}

		if (status != null) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("status", status));
		}

		return dynamicQuery;
	}

	@Reference
	private FinAccountLocalService _finAccountLocalService;

}
