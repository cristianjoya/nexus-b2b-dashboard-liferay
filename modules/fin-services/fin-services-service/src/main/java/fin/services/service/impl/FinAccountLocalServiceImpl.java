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
import com.liferay.portal.kernel.util.Validator;

import fin.services.model.FinAccount;
import fin.services.service.base.FinAccountLocalServiceBaseImpl;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author krism
 */
@Component(
	property = "model.class.name=fin.services.model.FinAccount",
	service = AopService.class
)
public class FinAccountLocalServiceImpl extends FinAccountLocalServiceBaseImpl {

	public int countSearchFinAccountsByGroupId(
		long groupId, String keywords, String accountType, Integer status,
		Long ownerUserId) {

		return (int)dynamicQueryCount(
			_buildSearchDynamicQuery(
				groupId, keywords, accountType, status, ownerUserId));
	}

	public FinAccount fetchFinAccountByGroupIdAndAccountNumber(
		long groupId, String accountNumber) {

		return finAccountPersistence.fetchByG_A(groupId, accountNumber);
	}

	public List<FinAccount> getFinAccountsByGroupId(long groupId) {
		return finAccountPersistence.findByGroupId(groupId);
	}

	public List<FinAccount> getFinAccountsByGroupId(
		long groupId, int start, int end) {

		return finAccountPersistence.findByGroupId(groupId, start, end);
	}

	public int getFinAccountsCountByGroupId(long groupId) {
		return finAccountPersistence.countByGroupId(groupId);
	}

	public FinAccount getUniqueFinAccountByGroupIdAndAccountNumber(
			long groupId, String accountNumber)
		throws PortalException {

		return finAccountPersistence.findByG_A(groupId, accountNumber);
	}

	public List<FinAccount> searchFinAccountsByGroupId(
		long groupId, String keywords, String accountType, Integer status,
		Long ownerUserId, int start, int end) {

		return dynamicQuery(
			_buildSearchDynamicQuery(
				groupId, keywords, accountType, status, ownerUserId),
			start, end);
	}

	private DynamicQuery _buildSearchDynamicQuery(
		long groupId, String keywords, String accountType, Integer status,
		Long ownerUserId) {

		DynamicQuery dynamicQuery = dynamicQuery();

		dynamicQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));

		if ((ownerUserId != null) && (ownerUserId > 0)) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.eq("ownerUserId", ownerUserId));
		}

		if (Validator.isNotNull(keywords)) {
			String pattern = "%" + keywords.trim() + "%";
			Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

			disjunction.add(
				RestrictionsFactoryUtil.ilike("accountName", pattern));
			disjunction.add(
				RestrictionsFactoryUtil.ilike("accountNumber", pattern));

			dynamicQuery.add(disjunction);
		}

		if (Validator.isNotNull(accountType) && !accountType.isBlank()) {
			dynamicQuery.add(
				RestrictionsFactoryUtil.eq("accountType", accountType));
		}

		if (status != null) {
			dynamicQuery.add(RestrictionsFactoryUtil.eq("status", status));
		}

		return dynamicQuery;
	}

}
