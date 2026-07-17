/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link FinTransactionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FinTransactionLocalService
 * @generated
 */
public class FinTransactionLocalServiceWrapper
	implements FinTransactionLocalService,
			   ServiceWrapper<FinTransactionLocalService> {

	public FinTransactionLocalServiceWrapper() {
		this(null);
	}

	public FinTransactionLocalServiceWrapper(
		FinTransactionLocalService finTransactionLocalService) {

		_finTransactionLocalService = finTransactionLocalService;
	}

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
	@Override
	public fin.services.model.FinTransaction addFinTransaction(
		fin.services.model.FinTransaction finTransaction) {

		return _finTransactionLocalService.addFinTransaction(finTransaction);
	}

	@Override
	public int countSearchFinTransactionsByGroupId(
		long groupId, String keywords, Long accountId, String transactionType,
		Integer status, java.util.Collection<Long> restrictedAccountIds) {

		return _finTransactionLocalService.countSearchFinTransactionsByGroupId(
			groupId, keywords, accountId, transactionType, status,
			restrictedAccountIds);
	}

	/**
	 * Creates a new fin transaction with the primary key. Does not add the fin transaction to the database.
	 *
	 * @param transactionId the primary key for the new fin transaction
	 * @return the new fin transaction
	 */
	@Override
	public fin.services.model.FinTransaction createFinTransaction(
		long transactionId) {

		return _finTransactionLocalService.createFinTransaction(transactionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finTransactionLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public fin.services.model.FinTransaction deleteFinTransaction(
		fin.services.model.FinTransaction finTransaction) {

		return _finTransactionLocalService.deleteFinTransaction(finTransaction);
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
	@Override
	public fin.services.model.FinTransaction deleteFinTransaction(
			long transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finTransactionLocalService.deleteFinTransaction(transactionId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finTransactionLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _finTransactionLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _finTransactionLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _finTransactionLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _finTransactionLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _finTransactionLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _finTransactionLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _finTransactionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _finTransactionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public fin.services.model.FinTransaction fetchFinTransaction(
		long transactionId) {

		return _finTransactionLocalService.fetchFinTransaction(transactionId);
	}

	/**
	 * Returns the fin transaction matching the UUID and group.
	 *
	 * @param uuid the fin transaction's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public fin.services.model.FinTransaction
		fetchFinTransactionByUuidAndGroupId(String uuid, long groupId) {

		return _finTransactionLocalService.fetchFinTransactionByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _finTransactionLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _finTransactionLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	/**
	 * Returns the fin transaction with the primary key.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction
	 * @throws PortalException if a fin transaction with the primary key could not be found
	 */
	@Override
	public fin.services.model.FinTransaction getFinTransaction(
			long transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finTransactionLocalService.getFinTransaction(transactionId);
	}

	/**
	 * Returns the fin transaction matching the UUID and group.
	 *
	 * @param uuid the fin transaction's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin transaction
	 * @throws PortalException if a matching fin transaction could not be found
	 */
	@Override
	public fin.services.model.FinTransaction getFinTransactionByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finTransactionLocalService.getFinTransactionByUuidAndGroupId(
			uuid, groupId);
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
	@Override
	public java.util.List<fin.services.model.FinTransaction> getFinTransactions(
		int start, int end) {

		return _finTransactionLocalService.getFinTransactions(start, end);
	}

	/**
	 * Returns all the fin transactions matching the UUID and company.
	 *
	 * @param uuid the UUID of the fin transactions
	 * @param companyId the primary key of the company
	 * @return the matching fin transactions, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<fin.services.model.FinTransaction>
		getFinTransactionsByUuidAndCompanyId(String uuid, long companyId) {

		return _finTransactionLocalService.getFinTransactionsByUuidAndCompanyId(
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
	@Override
	public java.util.List<fin.services.model.FinTransaction>
		getFinTransactionsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<fin.services.model.FinTransaction> orderByComparator) {

		return _finTransactionLocalService.getFinTransactionsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of fin transactions.
	 *
	 * @return the number of fin transactions
	 */
	@Override
	public int getFinTransactionsCount() {
		return _finTransactionLocalService.getFinTransactionsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _finTransactionLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _finTransactionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finTransactionLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<fin.services.model.FinTransaction>
		searchFinTransactionsByGroupId(
			long groupId, String keywords, Long accountId,
			String transactionType, Integer status,
			java.util.Collection<Long> restrictedAccountIds, int start,
			int end) {

		return _finTransactionLocalService.searchFinTransactionsByGroupId(
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
	@Override
	public fin.services.model.FinTransaction updateFinTransaction(
		fin.services.model.FinTransaction finTransaction) {

		return _finTransactionLocalService.updateFinTransaction(finTransaction);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _finTransactionLocalService.getBasePersistence();
	}

	@Override
	public FinTransactionLocalService getWrappedService() {
		return _finTransactionLocalService;
	}

	@Override
	public void setWrappedService(
		FinTransactionLocalService finTransactionLocalService) {

		_finTransactionLocalService = finTransactionLocalService;
	}

	private FinTransactionLocalService _finTransactionLocalService;

}