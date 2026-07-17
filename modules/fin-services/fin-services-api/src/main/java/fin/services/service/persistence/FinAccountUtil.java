/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import fin.services.model.FinAccount;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the fin account service. This utility wraps <code>fin.services.service.persistence.impl.FinAccountPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinAccountPersistence
 * @generated
 */
public class FinAccountUtil {

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
	public static void clearCache(FinAccount finAccount) {
		getPersistence().clearCache(finAccount);
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
	public static Map<Serializable, FinAccount> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FinAccount> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FinAccount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FinAccount> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FinAccount update(FinAccount finAccount) {
		return getPersistence().update(finAccount);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FinAccount update(
		FinAccount finAccount, ServiceContext serviceContext) {

		return getPersistence().update(finAccount, serviceContext);
	}

	/**
	 * Returns all the fin accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching fin accounts
	 */
	public static List<FinAccount> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the fin accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @return the range of matching fin accounts
	 */
	public static List<FinAccount> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the fin accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin accounts
	 */
	public static List<FinAccount> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin accounts where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin accounts
	 */
	public static List<FinAccount> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FinAccount> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public static FinAccount findByUuid_First(
			String uuid, OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByUuid_First(
		String uuid, OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public static FinAccount findByUuid_Last(
			String uuid, OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByUuid_Last(
		String uuid, OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the fin accounts before and after the current fin account in the ordered set where uuid = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public static FinAccount[] findByUuid_PrevAndNext(
			long accountId, String uuid,
			OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByUuid_PrevAndNext(
			accountId, uuid, orderByComparator);
	}

	/**
	 * Removes all the fin accounts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of fin accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching fin accounts
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the fin account where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFinAccountException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public static FinAccount findByUUID_G(String uuid, long groupId)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the fin account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the fin account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the fin account where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the fin account that was removed
	 */
	public static FinAccount removeByUUID_G(String uuid, long groupId)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of fin accounts where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching fin accounts
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the fin accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching fin accounts
	 */
	public static List<FinAccount> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the fin accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @return the range of matching fin accounts
	 */
	public static List<FinAccount> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin accounts
	 */
	public static List<FinAccount> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin accounts
	 */
	public static List<FinAccount> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public static FinAccount findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public static FinAccount findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the fin accounts before and after the current fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public static FinAccount[] findByUuid_C_PrevAndNext(
			long accountId, String uuid, long companyId,
			OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByUuid_C_PrevAndNext(
			accountId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the fin accounts where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of fin accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching fin accounts
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the fin accounts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin accounts
	 */
	public static List<FinAccount> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the fin accounts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @return the range of matching fin accounts
	 */
	public static List<FinAccount> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin accounts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin accounts
	 */
	public static List<FinAccount> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin accounts where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin accounts
	 */
	public static List<FinAccount> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public static FinAccount findByGroupId_First(
			long groupId, OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByGroupId_First(
		long groupId, OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public static FinAccount findByGroupId_Last(
			long groupId, OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByGroupId_Last(
		long groupId, OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the fin accounts before and after the current fin account in the ordered set where groupId = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public static FinAccount[] findByGroupId_PrevAndNext(
			long accountId, long groupId,
			OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByGroupId_PrevAndNext(
			accountId, groupId, orderByComparator);
	}

	/**
	 * Returns all the fin accounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin accounts that the user has permission to view
	 */
	public static List<FinAccount> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	 * Returns a range of all the fin accounts that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @return the range of matching fin accounts that the user has permission to view
	 */
	public static List<FinAccount> filterFindByGroupId(
		long groupId, int start, int end) {

		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin accounts that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin accounts that the user has permission to view
	 */
	public static List<FinAccount> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the fin accounts before and after the current fin account in the ordered set of fin accounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public static FinAccount[] filterFindByGroupId_PrevAndNext(
			long accountId, long groupId,
			OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().filterFindByGroupId_PrevAndNext(
			accountId, groupId, orderByComparator);
	}

	/**
	 * Removes all the fin accounts where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of fin accounts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin accounts
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the number of fin accounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin accounts that the user has permission to view
	 */
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	 * Returns the fin account where groupId = &#63; and accountNumber = &#63; or throws a <code>NoSuchFinAccountException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public static FinAccount findByG_A(long groupId, String accountNumber)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByG_A(groupId, accountNumber);
	}

	/**
	 * Returns the fin account where groupId = &#63; and accountNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByG_A(long groupId, String accountNumber) {
		return getPersistence().fetchByG_A(groupId, accountNumber);
	}

	/**
	 * Returns the fin account where groupId = &#63; and accountNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByG_A(
		long groupId, String accountNumber, boolean useFinderCache) {

		return getPersistence().fetchByG_A(
			groupId, accountNumber, useFinderCache);
	}

	/**
	 * Removes the fin account where groupId = &#63; and accountNumber = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the fin account that was removed
	 */
	public static FinAccount removeByG_A(long groupId, String accountNumber)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().removeByG_A(groupId, accountNumber);
	}

	/**
	 * Returns the number of fin accounts where groupId = &#63; and accountNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the number of matching fin accounts
	 */
	public static int countByG_A(long groupId, String accountNumber) {
		return getPersistence().countByG_A(groupId, accountNumber);
	}

	/**
	 * Returns all the fin accounts where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the matching fin accounts
	 */
	public static List<FinAccount> findByG_O(long groupId, long ownerUserId) {
		return getPersistence().findByG_O(groupId, ownerUserId);
	}

	/**
	 * Returns a range of all the fin accounts where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @return the range of matching fin accounts
	 */
	public static List<FinAccount> findByG_O(
		long groupId, long ownerUserId, int start, int end) {

		return getPersistence().findByG_O(groupId, ownerUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin accounts where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin accounts
	 */
	public static List<FinAccount> findByG_O(
		long groupId, long ownerUserId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().findByG_O(
			groupId, ownerUserId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin accounts where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin accounts
	 */
	public static List<FinAccount> findByG_O(
		long groupId, long ownerUserId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_O(
			groupId, ownerUserId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public static FinAccount findByG_O_First(
			long groupId, long ownerUserId,
			OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByG_O_First(
			groupId, ownerUserId, orderByComparator);
	}

	/**
	 * Returns the first fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByG_O_First(
		long groupId, long ownerUserId,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().fetchByG_O_First(
			groupId, ownerUserId, orderByComparator);
	}

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public static FinAccount findByG_O_Last(
			long groupId, long ownerUserId,
			OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByG_O_Last(
			groupId, ownerUserId, orderByComparator);
	}

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public static FinAccount fetchByG_O_Last(
		long groupId, long ownerUserId,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().fetchByG_O_Last(
			groupId, ownerUserId, orderByComparator);
	}

	/**
	 * Returns the fin accounts before and after the current fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public static FinAccount[] findByG_O_PrevAndNext(
			long accountId, long groupId, long ownerUserId,
			OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByG_O_PrevAndNext(
			accountId, groupId, ownerUserId, orderByComparator);
	}

	/**
	 * Returns all the fin accounts that the user has permission to view where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the matching fin accounts that the user has permission to view
	 */
	public static List<FinAccount> filterFindByG_O(
		long groupId, long ownerUserId) {

		return getPersistence().filterFindByG_O(groupId, ownerUserId);
	}

	/**
	 * Returns a range of all the fin accounts that the user has permission to view where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @return the range of matching fin accounts that the user has permission to view
	 */
	public static List<FinAccount> filterFindByG_O(
		long groupId, long ownerUserId, int start, int end) {

		return getPersistence().filterFindByG_O(
			groupId, ownerUserId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin accounts that the user has permissions to view where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin accounts that the user has permission to view
	 */
	public static List<FinAccount> filterFindByG_O(
		long groupId, long ownerUserId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().filterFindByG_O(
			groupId, ownerUserId, start, end, orderByComparator);
	}

	/**
	 * Returns the fin accounts before and after the current fin account in the ordered set of fin accounts that the user has permission to view where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public static FinAccount[] filterFindByG_O_PrevAndNext(
			long accountId, long groupId, long ownerUserId,
			OrderByComparator<FinAccount> orderByComparator)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().filterFindByG_O_PrevAndNext(
			accountId, groupId, ownerUserId, orderByComparator);
	}

	/**
	 * Removes all the fin accounts where groupId = &#63; and ownerUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 */
	public static void removeByG_O(long groupId, long ownerUserId) {
		getPersistence().removeByG_O(groupId, ownerUserId);
	}

	/**
	 * Returns the number of fin accounts where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the number of matching fin accounts
	 */
	public static int countByG_O(long groupId, long ownerUserId) {
		return getPersistence().countByG_O(groupId, ownerUserId);
	}

	/**
	 * Returns the number of fin accounts that the user has permission to view where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the number of matching fin accounts that the user has permission to view
	 */
	public static int filterCountByG_O(long groupId, long ownerUserId) {
		return getPersistence().filterCountByG_O(groupId, ownerUserId);
	}

	/**
	 * Caches the fin account in the entity cache if it is enabled.
	 *
	 * @param finAccount the fin account
	 */
	public static void cacheResult(FinAccount finAccount) {
		getPersistence().cacheResult(finAccount);
	}

	/**
	 * Caches the fin accounts in the entity cache if it is enabled.
	 *
	 * @param finAccounts the fin accounts
	 */
	public static void cacheResult(List<FinAccount> finAccounts) {
		getPersistence().cacheResult(finAccounts);
	}

	/**
	 * Creates a new fin account with the primary key. Does not add the fin account to the database.
	 *
	 * @param accountId the primary key for the new fin account
	 * @return the new fin account
	 */
	public static FinAccount create(long accountId) {
		return getPersistence().create(accountId);
	}

	/**
	 * Removes the fin account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account that was removed
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public static FinAccount remove(long accountId)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().remove(accountId);
	}

	public static FinAccount updateImpl(FinAccount finAccount) {
		return getPersistence().updateImpl(finAccount);
	}

	/**
	 * Returns the fin account with the primary key or throws a <code>NoSuchFinAccountException</code> if it could not be found.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public static FinAccount findByPrimaryKey(long accountId)
		throws fin.services.exception.NoSuchFinAccountException {

		return getPersistence().findByPrimaryKey(accountId);
	}

	/**
	 * Returns the fin account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account, or <code>null</code> if a fin account with the primary key could not be found
	 */
	public static FinAccount fetchByPrimaryKey(long accountId) {
		return getPersistence().fetchByPrimaryKey(accountId);
	}

	/**
	 * Returns all the fin accounts.
	 *
	 * @return the fin accounts
	 */
	public static List<FinAccount> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the fin accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @return the range of fin accounts
	 */
	public static List<FinAccount> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the fin accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fin accounts
	 */
	public static List<FinAccount> findAll(
		int start, int end, OrderByComparator<FinAccount> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin accounts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinAccountModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin accounts
	 * @param end the upper bound of the range of fin accounts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of fin accounts
	 */
	public static List<FinAccount> findAll(
		int start, int end, OrderByComparator<FinAccount> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the fin accounts from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of fin accounts.
	 *
	 * @return the number of fin accounts
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FinAccountPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(FinAccountPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile FinAccountPersistence _persistence;

}