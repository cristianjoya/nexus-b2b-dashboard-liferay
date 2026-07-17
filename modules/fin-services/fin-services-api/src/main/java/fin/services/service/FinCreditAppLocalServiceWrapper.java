/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link FinCreditAppLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FinCreditAppLocalService
 * @generated
 */
public class FinCreditAppLocalServiceWrapper
	implements FinCreditAppLocalService,
			   ServiceWrapper<FinCreditAppLocalService> {

	public FinCreditAppLocalServiceWrapper() {
		this(null);
	}

	public FinCreditAppLocalServiceWrapper(
		FinCreditAppLocalService finCreditAppLocalService) {

		_finCreditAppLocalService = finCreditAppLocalService;
	}

	/**
	 * Adds the fin credit app to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinCreditAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finCreditApp the fin credit app
	 * @return the fin credit app that was added
	 */
	@Override
	public fin.services.model.FinCreditApp addFinCreditApp(
		fin.services.model.FinCreditApp finCreditApp) {

		return _finCreditAppLocalService.addFinCreditApp(finCreditApp);
	}

	@Override
	public int countSearchFinCreditAppsByGroupId(
		long groupId, String keywords, Long accountId, Integer status,
		java.util.Collection<Long> restrictedAccountIds) {

		return _finCreditAppLocalService.countSearchFinCreditAppsByGroupId(
			groupId, keywords, accountId, status, restrictedAccountIds);
	}

	/**
	 * Creates a new fin credit app with the primary key. Does not add the fin credit app to the database.
	 *
	 * @param creditAppId the primary key for the new fin credit app
	 * @return the new fin credit app
	 */
	@Override
	public fin.services.model.FinCreditApp createFinCreditApp(
		long creditAppId) {

		return _finCreditAppLocalService.createFinCreditApp(creditAppId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finCreditAppLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the fin credit app from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinCreditAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finCreditApp the fin credit app
	 * @return the fin credit app that was removed
	 */
	@Override
	public fin.services.model.FinCreditApp deleteFinCreditApp(
		fin.services.model.FinCreditApp finCreditApp) {

		return _finCreditAppLocalService.deleteFinCreditApp(finCreditApp);
	}

	/**
	 * Deletes the fin credit app with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinCreditAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param creditAppId the primary key of the fin credit app
	 * @return the fin credit app that was removed
	 * @throws PortalException if a fin credit app with the primary key could not be found
	 */
	@Override
	public fin.services.model.FinCreditApp deleteFinCreditApp(long creditAppId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finCreditAppLocalService.deleteFinCreditApp(creditAppId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finCreditAppLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _finCreditAppLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _finCreditAppLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _finCreditAppLocalService.dynamicQuery();
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

		return _finCreditAppLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinCreditAppModelImpl</code>.
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

		return _finCreditAppLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinCreditAppModelImpl</code>.
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

		return _finCreditAppLocalService.dynamicQuery(
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

		return _finCreditAppLocalService.dynamicQueryCount(dynamicQuery);
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

		return _finCreditAppLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public fin.services.model.FinCreditApp fetchFinCreditApp(long creditAppId) {
		return _finCreditAppLocalService.fetchFinCreditApp(creditAppId);
	}

	/**
	 * Returns the fin credit app matching the UUID and group.
	 *
	 * @param uuid the fin credit app's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	@Override
	public fin.services.model.FinCreditApp fetchFinCreditAppByUuidAndGroupId(
		String uuid, long groupId) {

		return _finCreditAppLocalService.fetchFinCreditAppByUuidAndGroupId(
			uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _finCreditAppLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _finCreditAppLocalService.getExportActionableDynamicQuery(
			portletDataContext);
	}

	/**
	 * Returns the fin credit app with the primary key.
	 *
	 * @param creditAppId the primary key of the fin credit app
	 * @return the fin credit app
	 * @throws PortalException if a fin credit app with the primary key could not be found
	 */
	@Override
	public fin.services.model.FinCreditApp getFinCreditApp(long creditAppId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finCreditAppLocalService.getFinCreditApp(creditAppId);
	}

	/**
	 * Returns the fin credit app matching the UUID and group.
	 *
	 * @param uuid the fin credit app's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin credit app
	 * @throws PortalException if a matching fin credit app could not be found
	 */
	@Override
	public fin.services.model.FinCreditApp getFinCreditAppByUuidAndGroupId(
			String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finCreditAppLocalService.getFinCreditAppByUuidAndGroupId(
			uuid, groupId);
	}

	/**
	 * Returns a range of all the fin credit apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @return the range of fin credit apps
	 */
	@Override
	public java.util.List<fin.services.model.FinCreditApp> getFinCreditApps(
		int start, int end) {

		return _finCreditAppLocalService.getFinCreditApps(start, end);
	}

	/**
	 * Returns all the fin credit apps matching the UUID and company.
	 *
	 * @param uuid the UUID of the fin credit apps
	 * @param companyId the primary key of the company
	 * @return the matching fin credit apps, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<fin.services.model.FinCreditApp>
		getFinCreditAppsByUuidAndCompanyId(String uuid, long companyId) {

		return _finCreditAppLocalService.getFinCreditAppsByUuidAndCompanyId(
			uuid, companyId);
	}

	/**
	 * Returns a range of fin credit apps matching the UUID and company.
	 *
	 * @param uuid the UUID of the fin credit apps
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching fin credit apps, or an empty list if no matches were found
	 */
	@Override
	public java.util.List<fin.services.model.FinCreditApp>
		getFinCreditAppsByUuidAndCompanyId(
			String uuid, long companyId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<fin.services.model.FinCreditApp> orderByComparator) {

		return _finCreditAppLocalService.getFinCreditAppsByUuidAndCompanyId(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of fin credit apps.
	 *
	 * @return the number of fin credit apps
	 */
	@Override
	public int getFinCreditAppsCount() {
		return _finCreditAppLocalService.getFinCreditAppsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _finCreditAppLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _finCreditAppLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _finCreditAppLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<fin.services.model.FinCreditApp>
		searchFinCreditAppsByGroupId(
			long groupId, String keywords, Long accountId, Integer status,
			java.util.Collection<Long> restrictedAccountIds, int start,
			int end) {

		return _finCreditAppLocalService.searchFinCreditAppsByGroupId(
			groupId, keywords, accountId, status, restrictedAccountIds, start,
			end);
	}

	/**
	 * Updates the fin credit app in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinCreditAppLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finCreditApp the fin credit app
	 * @return the fin credit app that was updated
	 */
	@Override
	public fin.services.model.FinCreditApp updateFinCreditApp(
		fin.services.model.FinCreditApp finCreditApp) {

		return _finCreditAppLocalService.updateFinCreditApp(finCreditApp);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _finCreditAppLocalService.getBasePersistence();
	}

	@Override
	public FinCreditAppLocalService getWrappedService() {
		return _finCreditAppLocalService;
	}

	@Override
	public void setWrappedService(
		FinCreditAppLocalService finCreditAppLocalService) {

		_finCreditAppLocalService = finCreditAppLocalService;
	}

	private FinCreditAppLocalService _finCreditAppLocalService;

}