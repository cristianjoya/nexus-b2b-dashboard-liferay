/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link FinAccountLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FinAccountLocalService
 * @generated
 */
public class FinAccountLocalServiceWrapper
	implements FinAccountLocalService, ServiceWrapper<FinAccountLocalService> {

	public FinAccountLocalServiceWrapper() {
		this(null);
	}

	public FinAccountLocalServiceWrapper(
		FinAccountLocalService finAccountLocalService) {

		_finAccountLocalService = finAccountLocalService;
	}

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
	@Override
	public fin.services.model.FinAccount addFinAccount(
		fin.services.model.FinAccount finAccount) {

		return _finAccountLocalService.addFinAccount(finAccount);
	}

	@Override
	public int countSearchFinAccountsByGroupId(
		long groupId, String keywords, String accountType, Integer status,
		Long ownerUserId) {

		return _finAccountLocalService.countSearchFinAccountsByGroupId(
			groupId, keywords, accountType, status, ownerUserId);
	}

	/**
	 * Creates a new fin account with the primary key. Does not add the fin account to the database.
	 *
	 * @param accountId the primary key for the new fin account
	 * @return the new fin account
	 */
	@Override
	public fin.services.model.FinAccount createFinAccount(long accountId) {
		return _finAccountLocalService.createFinAccount(accountId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finAccountLocalService.createPersistedModel(primaryKeyObj);
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
	@Override
	public fin.services.model.FinAccount deleteFinAccount(
		fin.services.model.FinAccount finAccount) {

		return _finAccountLocalService.deleteFinAccount(finAccount);
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
	@Override
	public fin.services.model.FinAccount deleteFinAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finAccountLocalService.deleteFinAccount(accountId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finAccountLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _finAccountLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _finAccountLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _finAccountLocalService.dynamicQuery();
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

		return _finAccountLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _finAccountLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _finAccountLocalService.dynamicQuery(
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

		return _finAccountLocalService.dynamicQueryCount(dynamicQuery);
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

		return _finAccountLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public fin.services.model.FinAccount fetchFinAccount(long accountId) {
		return _finAccountLocalService.fetchFinAccount(accountId);
	}

	@Override
	public fin.services.model.FinAccount
		fetchFinAccountByGroupIdAndAccountNumber(
			long groupId, String accountNumber) {

		return _finAccountLocalService.fetchFinAccountByGroupIdAndAccountNumber(
			groupId, accountNumber);
	}

	/**
	 * Returns the fin account matching the UUID and group.
	 *
	 * @param uuid the fin account's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public fin.services.model.FinAccount fetchFinAccountByUuidAndGroupId(
		String uuid, long groupId) {

		return _finAccountLocalService.fetchFinAccountByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _finAccountLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _finAccountLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	/**
	 * Returns the fin account with the primary key.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account
	 * @throws PortalException if a fin account with the primary key could not be found
	 */
	@Override
	public fin.services.model.FinAccount getFinAccount(long accountId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finAccountLocalService.getFinAccount(accountId);
	}

	/**
	 * Returns the fin account matching the UUID and group.
	 *
	 * @param uuid the fin account's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin account
	 * @throws PortalException if a matching fin account could not be found
	 */
	@Override
	public fin.services.model.FinAccount getFinAccountByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finAccountLocalService.getFinAccountByUuidAndGroupId(
			uuid, groupId);
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
	@Override
	public java.util.List<fin.services.model.FinAccount> getFinAccounts(
		int start, int end) {

		return _finAccountLocalService.getFinAccounts(start, end);
	}

	@Override
	public java.util.List<fin.services.model.FinAccount>
		getFinAccountsByGroupId(long groupId) {

		return _finAccountLocalService.getFinAccountsByGroupId(groupId);
	}

	@Override
	public java.util.List<fin.services.model.FinAccount>
		getFinAccountsByGroupId(long groupId, int start, int end) {

		return _finAccountLocalService.getFinAccountsByGroupId(
			groupId, start, end);
	}

	/**
	 * Returns all the fin accounts matching the UUID and company.
	 *
	 * @param uuid the UUID of the fin accounts
	 * @param companyId the primary key of the company
	 * @return the matching fin accounts, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<fin.services.model.FinAccount>
		getFinAccountsByUuidAndCompanyId(String uuid, long companyId) {

		return _finAccountLocalService.getFinAccountsByUuidAndCompanyId(
			uuid, companyId);
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
	@Override
	public java.util.List<fin.services.model.FinAccount>
		getFinAccountsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<fin.services.model.FinAccount> orderByComparator) {

		return _finAccountLocalService.getFinAccountsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of fin accounts.
	 *
	 * @return the number of fin accounts
	 */
	@Override
	public int getFinAccountsCount() {
		return _finAccountLocalService.getFinAccountsCount();
	}

	@Override
	public int getFinAccountsCountByGroupId(long groupId) {
		return _finAccountLocalService.getFinAccountsCountByGroupId(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _finAccountLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _finAccountLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finAccountLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public fin.services.model.FinAccount
			getUniqueFinAccountByGroupIdAndAccountNumber(
				long groupId, String accountNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finAccountLocalService.
			getUniqueFinAccountByGroupIdAndAccountNumber(
				groupId, accountNumber);
	}

	@Override
	public java.util.List<fin.services.model.FinAccount>
		searchFinAccountsByGroupId(
			long groupId, String keywords, String accountType, Integer status,
			Long ownerUserId, int start, int end) {

		return _finAccountLocalService.searchFinAccountsByGroupId(
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
	@Override
	public fin.services.model.FinAccount updateFinAccount(
		fin.services.model.FinAccount finAccount) {

		return _finAccountLocalService.updateFinAccount(finAccount);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _finAccountLocalService.getBasePersistence();
	}

	@Override
	public FinAccountLocalService getWrappedService() {
		return _finAccountLocalService;
	}

	@Override
	public void setWrappedService(
		FinAccountLocalService finAccountLocalService) {

		_finAccountLocalService = finAccountLocalService;
	}

	private FinAccountLocalService _finAccountLocalService;

}