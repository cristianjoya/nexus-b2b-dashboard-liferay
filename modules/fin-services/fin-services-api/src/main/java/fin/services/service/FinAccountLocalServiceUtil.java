/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import fin.services.model.FinAccount;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for FinAccount. This utility wraps
 * <code>fin.services.service.impl.FinAccountLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see FinAccountLocalService
 * @generated
 */
public class FinAccountLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>fin.services.service.impl.FinAccountLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the fin account to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinAccountLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finAccount the fin account
	 * @return the fin account that was added
	 */
	public static FinAccount addFinAccount(FinAccount finAccount) {
		return getService().addFinAccount(finAccount);
	}

	public static int countSearchFinAccountsByGroupId(
		long groupId, String keywords, String accountType, Integer status,
		Long ownerUserId) {

		return getService().countSearchFinAccountsByGroupId(
			groupId, keywords, accountType, status, ownerUserId);
	}

	/**
	 * Creates a new fin account with the primary key. Does not add the fin account to the database.
	 *
	 * @param accountId the primary key for the new fin account
	 * @return the new fin account
	 */
	public static FinAccount createFinAccount(long accountId) {
		return getService().createFinAccount(accountId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the fin account from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinAccountLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finAccount the fin account
	 * @return the fin account that was removed
	 */
	public static FinAccount deleteFinAccount(FinAccount finAccount) {
		return getService().deleteFinAccount(finAccount);
	}

	/**
	 * Deletes the fin account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinAccountLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account that was removed
	 * @throws PortalException if a fin account with the primary key could not be found
	 */
	public static FinAccount deleteFinAccount(long accountId)
		throws PortalException {

		return getService().deleteFinAccount(accountId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static FinAccount fetchFinAccount(long accountId) {
		return getService().fetchFinAccount(accountId);
	}

	public static FinAccount fetchFinAccountByGroupIdAndAccountNumber(
		long groupId, String accountNumber) {

		return getService().fetchFinAccountByGroupIdAndAccountNumber(
			groupId, accountNumber);
	}

	/**
	 * Returns the fin account matching the UUID and group.
	 *
	 * @param uuid the fin account's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchFinAccountByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchFinAccountByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	/**
	 * Returns the fin account with the primary key.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account
	 * @throws PortalException if a fin account with the primary key could not be found
	 */
	public static FinAccount getFinAccount(long accountId)
		throws PortalException {

		return getService().getFinAccount(accountId);
	}

	/**
	 * Returns the fin account matching the UUID and group.
	 *
	 * @param uuid the fin account's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin account
	 * @throws PortalException if a matching fin account could not be found
	 */
	public static FinAccount getFinAccountByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return getService().getFinAccountByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the fin accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @return the range of fin accounts
	 */
	public static List<FinAccount> getFinAccounts(int start, int end) {
		return getService().getFinAccounts(start, end);
	}

	public static List<FinAccount> getFinAccountsByGroupId(long groupId) {
		return getService().getFinAccountsByGroupId(groupId);
	}

	public static List<FinAccount> getFinAccountsByGroupId(
		long groupId, int start, int end) {

		return getService().getFinAccountsByGroupId(groupId, start, end);
	}

	/**
	 * Returns all the fin accounts matching the UUID and company.
	 *
	 * @param uuid the UUID of the fin accounts
	 * @param companyId the primary key of the company
	 * @return the matching fin accounts, or an empty list if no matches were found
	 */
	public static List<FinAccount> getFinAccountsByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getFinAccountsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	 * Returns a range of fin accounts matching the UUID and company.
	 *
	 * @param uuid the UUID of the fin accounts
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching fin accounts, or an empty list if no matches were found
	 */
	public static List<FinAccount> getFinAccountsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return getService().getFinAccountsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of fin accounts.
	 *
	 * @return the number of fin accounts
	 */
	public static int getFinAccountsCount() {
		return getService().getFinAccountsCount();
	}

	public static int getFinAccountsCountByGroupId(long groupId) {
		return getService().getFinAccountsCountByGroupId(groupId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static FinAccount getUniqueFinAccountByGroupIdAndAccountNumber(
			long groupId, String accountNumber)
		throws PortalException {

		return getService().getUniqueFinAccountByGroupIdAndAccountNumber(
			groupId, accountNumber);
	}

	public static List<FinAccount> searchFinAccountsByGroupId(
		long groupId, String keywords, String accountType, Integer status,
		Long ownerUserId, int start, int end) {

		return getService().searchFinAccountsByGroupId(
			groupId, keywords, accountType, status, ownerUserId, start, end);
	}

	/**
	 * Updates the fin account in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinAccountLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finAccount the fin account
	 * @return the fin account that was updated
	 */
	public static FinAccount updateFinAccount(FinAccount finAccount) {
		return getService().updateFinAccount(finAccount);
	}

	public static FinAccountLocalService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<FinAccountLocalService> _serviceSnapshot =
		new Snapshot<>(
			FinAccountLocalServiceUtil.class, FinAccountLocalService.class);

}