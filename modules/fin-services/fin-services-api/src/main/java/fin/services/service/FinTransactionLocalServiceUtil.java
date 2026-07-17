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

import fin.services.model.FinTransaction;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for FinTransaction. This utility wraps
 * <code>fin.services.service.impl.FinTransactionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see FinTransactionLocalService
 * @generated
 */
public class FinTransactionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>fin.services.service.impl.FinTransactionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the fin transaction to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinTransactionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finTransaction the fin transaction
	 * @return the fin transaction that was added
	 */
	public static FinTransaction addFinTransaction(
		FinTransaction finTransaction) {

		return getService().addFinTransaction(finTransaction);
	}

	public static int countSearchFinTransactionsByGroupId(
		long groupId, String keywords, Long accountId, String transactionType,
		Integer status, java.util.Collection<Long> restrictedAccountIds) {

		return getService().countSearchFinTransactionsByGroupId(
			groupId, keywords, accountId, transactionType, status,
			restrictedAccountIds);
	}

	/**
	 * Creates a new fin transaction with the primary key. Does not add the fin transaction to the database.
	 *
	 * @param transactionId the primary key for the new fin transaction
	 * @return the new fin transaction
	 */
	public static FinTransaction createFinTransaction(long transactionId) {
		return getService().createFinTransaction(transactionId);
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
	 * Deletes the fin transaction from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinTransactionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finTransaction the fin transaction
	 * @return the fin transaction that was removed
	 */
	public static FinTransaction deleteFinTransaction(
		FinTransaction finTransaction) {

		return getService().deleteFinTransaction(finTransaction);
	}

	/**
	 * Deletes the fin transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinTransactionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction that was removed
	 * @throws PortalException if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction deleteFinTransaction(long transactionId)
		throws PortalException {

		return getService().deleteFinTransaction(transactionId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinTransactionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinTransactionModelImpl</code>.
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

	public static FinTransaction fetchFinTransaction(long transactionId) {
		return getService().fetchFinTransaction(transactionId);
	}

	/**
	 * Returns the fin transaction matching the UUID and group.
	 *
	 * @param uuid the fin transaction's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchFinTransactionByUuidAndGroupId(
		String uuid, long groupId) {

		return getService().fetchFinTransactionByUuidAndGroupId(uuid, groupId);
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
	 * Returns the fin transaction with the primary key.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction
	 * @throws PortalException if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction getFinTransaction(long transactionId)
		throws PortalException {

		return getService().getFinTransaction(transactionId);
	}

	/**
	 * Returns the fin transaction matching the UUID and group.
	 *
	 * @param uuid the fin transaction's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin transaction
	 * @throws PortalException if a matching fin transaction could not be found
	 */
	public static FinTransaction getFinTransactionByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException {

		return getService().getFinTransactionByUuidAndGroupId(uuid, groupId);
	}

	/**
	 * Returns a range of all the fin transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @return the range of fin transactions
	 */
	public static List<FinTransaction> getFinTransactions(int start, int end) {
		return getService().getFinTransactions(start, end);
	}

	/**
	 * Returns all the fin transactions matching the UUID and company.
	 *
	 * @param uuid the UUID of the fin transactions
	 * @param companyId the primary key of the company
	 * @return the matching fin transactions, or an empty list if no matches were found
	 */
	public static List<FinTransaction> getFinTransactionsByUuidAndCompanyId(
		String uuid, long companyId) {

		return getService().getFinTransactionsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of fin transactions matching the UUID and company.
	 *
	 * @param uuid the UUID of the fin transactions
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching fin transactions, or an empty list if no matches were found
	 */
	public static List<FinTransaction> getFinTransactionsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getService().getFinTransactionsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of fin transactions.
	 *
	 * @return the number of fin transactions
	 */
	public static int getFinTransactionsCount() {
		return getService().getFinTransactionsCount();
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

	public static List<FinTransaction> searchFinTransactionsByGroupId(
		long groupId, String keywords, Long accountId, String transactionType,
		Integer status, java.util.Collection<Long> restrictedAccountIds,
		int start, int end) {

		return getService().searchFinTransactionsByGroupId(
			groupId, keywords, accountId, transactionType, status,
			restrictedAccountIds, start, end);
	}

	/**
	 * Updates the fin transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinTransactionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finTransaction the fin transaction
	 * @return the fin transaction that was updated
	 */
	public static FinTransaction updateFinTransaction(
		FinTransaction finTransaction) {

		return getService().updateFinTransaction(finTransaction);
	}

	public static FinTransactionLocalService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<FinTransactionLocalService> _serviceSnapshot =
		new Snapshot<>(
			FinTransactionLocalServiceUtil.class,
			FinTransactionLocalService.class);

}