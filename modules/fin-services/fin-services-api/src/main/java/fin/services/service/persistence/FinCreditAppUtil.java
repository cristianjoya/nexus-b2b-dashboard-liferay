/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import fin.services.model.FinCreditApp;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the fin credit app service. This utility wraps <code>fin.services.service.persistence.impl.FinCreditAppPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinCreditAppPersistence
 * @generated
 */
public class FinCreditAppUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(FinCreditApp finCreditApp) {
		getPersistence().clearCache(finCreditApp);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, FinCreditApp> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FinCreditApp> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FinCreditApp> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FinCreditApp> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FinCreditApp update(FinCreditApp finCreditApp) {
		return getPersistence().update(finCreditApp);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FinCreditApp update(
		FinCreditApp finCreditApp, ServiceContext serviceContext) {

		return getPersistence().update(finCreditApp, serviceContext);
	}

	/**
	 * Returns all the fin credit apps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching fin credit apps
	 */
	public static List<FinCreditApp> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the fin credit apps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @return the range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the fin credit apps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin credit apps where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin credit app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public static FinCreditApp findByUuid_First(
			String uuid, OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first fin credit app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public static FinCreditApp fetchByUuid_First(
		String uuid, OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last fin credit app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public static FinCreditApp findByUuid_Last(
			String uuid, OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last fin credit app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public static FinCreditApp fetchByUuid_Last(
		String uuid, OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the fin credit apps before and after the current fin credit app in the ordered set where uuid = &#63;.
	 *
	 * @param creditAppId the primary key of the current fin credit app
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public static FinCreditApp[] findByUuid_PrevAndNext(
			long creditAppId, String uuid,
			OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByUuid_PrevAndNext(
			creditAppId, uuid, orderByComparator);
	}

	/**
	 * Removes all the fin credit apps where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of fin credit apps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching fin credit apps
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the fin credit app where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFinCreditAppException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public static FinCreditApp findByUUID_G(String uuid, long groupId)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the fin credit app where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public static FinCreditApp fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the fin credit app where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public static FinCreditApp fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the fin credit app where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the fin credit app that was removed
	 */
	public static FinCreditApp removeByUUID_G(String uuid, long groupId)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of fin credit apps where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching fin credit apps
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the fin credit apps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching fin credit apps
	 */
	public static List<FinCreditApp> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the fin credit apps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @return the range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin credit apps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin credit apps where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin credit app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public static FinCreditApp findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first fin credit app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public static FinCreditApp fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last fin credit app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public static FinCreditApp findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last fin credit app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public static FinCreditApp fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the fin credit apps before and after the current fin credit app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param creditAppId the primary key of the current fin credit app
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public static FinCreditApp[] findByUuid_C_PrevAndNext(
			long creditAppId, String uuid, long companyId,
			OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByUuid_C_PrevAndNext(
			creditAppId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the fin credit apps where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of fin credit apps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching fin credit apps
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the fin credit apps where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin credit apps
	 */
	public static List<FinCreditApp> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the fin credit apps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @return the range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin credit apps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin credit apps where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin credit app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public static FinCreditApp findByGroupId_First(
			long groupId, OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first fin credit app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public static FinCreditApp fetchByGroupId_First(
		long groupId, OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last fin credit app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public static FinCreditApp findByGroupId_Last(
			long groupId, OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last fin credit app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public static FinCreditApp fetchByGroupId_Last(
		long groupId, OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the fin credit apps before and after the current fin credit app in the ordered set where groupId = &#63;.
	 *
	 * @param creditAppId the primary key of the current fin credit app
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public static FinCreditApp[] findByGroupId_PrevAndNext(
			long creditAppId, long groupId,
			OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByGroupId_PrevAndNext(
			creditAppId, groupId, orderByComparator);
	}

	/**
	 * Returns all the fin credit apps that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin credit apps that the user has permission to view
	 */
	public static List<FinCreditApp> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	 * Returns a range of all the fin credit apps that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @return the range of matching fin credit apps that the user has permission to view
	 */
	public static List<FinCreditApp> filterFindByGroupId(
		long groupId, int start, int end) {

		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin credit apps that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin credit apps that the user has permission to view
	 */
	public static List<FinCreditApp> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the fin credit apps before and after the current fin credit app in the ordered set of fin credit apps that the user has permission to view where groupId = &#63;.
	 *
	 * @param creditAppId the primary key of the current fin credit app
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public static FinCreditApp[] filterFindByGroupId_PrevAndNext(
			long creditAppId, long groupId,
			OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().filterFindByGroupId_PrevAndNext(
			creditAppId, groupId, orderByComparator);
	}

	/**
	 * Removes all the fin credit apps where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of fin credit apps where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin credit apps
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the number of fin credit apps that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin credit apps that the user has permission to view
	 */
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	 * Returns all the fin credit apps where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the matching fin credit apps
	 */
	public static List<FinCreditApp> findByG_A(long groupId, long accountId) {
		return getPersistence().findByG_A(groupId, accountId);
	}

	/**
	 * Returns a range of all the fin credit apps where groupId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @return the range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByG_A(
		long groupId, long accountId, int start, int end) {

		return getPersistence().findByG_A(groupId, accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin credit apps where groupId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByG_A(
		long groupId, long accountId, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().findByG_A(
			groupId, accountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin credit apps where groupId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin credit apps
	 */
	public static List<FinCreditApp> findByG_A(
		long groupId, long accountId, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_A(
			groupId, accountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin credit app in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public static FinCreditApp findByG_A_First(
			long groupId, long accountId,
			OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByG_A_First(
			groupId, accountId, orderByComparator);
	}

	/**
	 * Returns the first fin credit app in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public static FinCreditApp fetchByG_A_First(
		long groupId, long accountId,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().fetchByG_A_First(
			groupId, accountId, orderByComparator);
	}

	/**
	 * Returns the last fin credit app in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public static FinCreditApp findByG_A_Last(
			long groupId, long accountId,
			OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByG_A_Last(
			groupId, accountId, orderByComparator);
	}

	/**
	 * Returns the last fin credit app in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public static FinCreditApp fetchByG_A_Last(
		long groupId, long accountId,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().fetchByG_A_Last(
			groupId, accountId, orderByComparator);
	}

	/**
	 * Returns the fin credit apps before and after the current fin credit app in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param creditAppId the primary key of the current fin credit app
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public static FinCreditApp[] findByG_A_PrevAndNext(
			long creditAppId, long groupId, long accountId,
			OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByG_A_PrevAndNext(
			creditAppId, groupId, accountId, orderByComparator);
	}

	/**
	 * Returns all the fin credit apps that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the matching fin credit apps that the user has permission to view
	 */
	public static List<FinCreditApp> filterFindByG_A(
		long groupId, long accountId) {

		return getPersistence().filterFindByG_A(groupId, accountId);
	}

	/**
	 * Returns a range of all the fin credit apps that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @return the range of matching fin credit apps that the user has permission to view
	 */
	public static List<FinCreditApp> filterFindByG_A(
		long groupId, long accountId, int start, int end) {

		return getPersistence().filterFindByG_A(groupId, accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin credit apps that the user has permissions to view where groupId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin credit apps that the user has permission to view
	 */
	public static List<FinCreditApp> filterFindByG_A(
		long groupId, long accountId, int start, int end,
		OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().filterFindByG_A(
			groupId, accountId, start, end, orderByComparator);
	}

	/**
	 * Returns the fin credit apps before and after the current fin credit app in the ordered set of fin credit apps that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param creditAppId the primary key of the current fin credit app
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public static FinCreditApp[] filterFindByG_A_PrevAndNext(
			long creditAppId, long groupId, long accountId,
			OrderByComparator<FinCreditApp> orderByComparator)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().filterFindByG_A_PrevAndNext(
			creditAppId, groupId, accountId, orderByComparator);
	}

	/**
	 * Removes all the fin credit apps where groupId = &#63; and accountId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 */
	public static void removeByG_A(long groupId, long accountId) {
		getPersistence().removeByG_A(groupId, accountId);
	}

	/**
	 * Returns the number of fin credit apps where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the number of matching fin credit apps
	 */
	public static int countByG_A(long groupId, long accountId) {
		return getPersistence().countByG_A(groupId, accountId);
	}

	/**
	 * Returns the number of fin credit apps that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the number of matching fin credit apps that the user has permission to view
	 */
	public static int filterCountByG_A(long groupId, long accountId) {
		return getPersistence().filterCountByG_A(groupId, accountId);
	}

	/**
	 * Caches the fin credit app in the entity cache if it is enabled.
	 *
	 * @param finCreditApp the fin credit app
	 */
	public static void cacheResult(FinCreditApp finCreditApp) {
		getPersistence().cacheResult(finCreditApp);
	}

	/**
	 * Caches the fin credit apps in the entity cache if it is enabled.
	 *
	 * @param finCreditApps the fin credit apps
	 */
	public static void cacheResult(List<FinCreditApp> finCreditApps) {
		getPersistence().cacheResult(finCreditApps);
	}

	/**
	 * Creates a new fin credit app with the primary key. Does not add the fin credit app to the database.
	 *
	 * @param creditAppId the primary key for the new fin credit app
	 * @return the new fin credit app
	 */
	public static FinCreditApp create(long creditAppId) {
		return getPersistence().create(creditAppId);
	}

	/**
	 * Removes the fin credit app with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param creditAppId the primary key of the fin credit app
	 * @return the fin credit app that was removed
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public static FinCreditApp remove(long creditAppId)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().remove(creditAppId);
	}

	public static FinCreditApp updateImpl(FinCreditApp finCreditApp) {
		return getPersistence().updateImpl(finCreditApp);
	}

	/**
	 * Returns the fin credit app with the primary key or throws a <code>NoSuchFinCreditAppException</code> if it could not be found.
	 *
	 * @param creditAppId the primary key of the fin credit app
	 * @return the fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public static FinCreditApp findByPrimaryKey(long creditAppId)
		throws fin.services.exception.NoSuchFinCreditAppException {

		return getPersistence().findByPrimaryKey(creditAppId);
	}

	/**
	 * Returns the fin credit app with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param creditAppId the primary key of the fin credit app
	 * @return the fin credit app, or <code>null</code> if a fin credit app with the primary key could not be found
	 */
	public static FinCreditApp fetchByPrimaryKey(long creditAppId) {
		return getPersistence().fetchByPrimaryKey(creditAppId);
	}

	/**
	 * Returns all the fin credit apps.
	 *
	 * @return the fin credit apps
	 */
	public static List<FinCreditApp> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the fin credit apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @return the range of fin credit apps
	 */
	public static List<FinCreditApp> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the fin credit apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fin credit apps
	 */
	public static List<FinCreditApp> findAll(
		int start, int end, OrderByComparator<FinCreditApp> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin credit apps.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinCreditAppModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin credit apps
	 * @param end the upper bound of the range of fin credit apps (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of fin credit apps
	 */
	public static List<FinCreditApp> findAll(
		int start, int end, OrderByComparator<FinCreditApp> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the fin credit apps from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of fin credit apps.
	 *
	 * @return the number of fin credit apps
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FinCreditAppPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(FinCreditAppPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile FinCreditAppPersistence _persistence;

}