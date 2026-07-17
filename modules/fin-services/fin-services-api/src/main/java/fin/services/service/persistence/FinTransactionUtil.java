/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import fin.services.model.FinTransaction;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the fin transaction service. This utility wraps <code>fin.services.service.persistence.impl.FinTransactionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinTransactionPersistence
 * @generated
 */
public class FinTransactionUtil {

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
	public static void clearCache(FinTransaction finTransaction) {
		getPersistence().clearCache(finTransaction);
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
	public static Map<Serializable, FinTransaction> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FinTransaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FinTransaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FinTransaction> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FinTransaction update(FinTransaction finTransaction) {
		return getPersistence().update(finTransaction);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FinTransaction update(
		FinTransaction finTransaction, ServiceContext serviceContext) {

		return getPersistence().update(finTransaction, serviceContext);
	}

	/**
	 * Returns all the fin transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching fin transactions
	 */
	public static List<FinTransaction> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the fin transactions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @return the range of matching fin transactions
	 */
	public static List<FinTransaction> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the fin transactions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin transactions
	 */
	public static List<FinTransaction> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin transactions where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin transactions
	 */
	public static List<FinTransaction> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public static FinTransaction findByUuid_First(
			String uuid, OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchByUuid_First(
		String uuid, OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public static FinTransaction findByUuid_Last(
			String uuid, OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchByUuid_Last(
		String uuid, OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the fin transactions before and after the current fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param transactionId the primary key of the current fin transaction
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction[] findByUuid_PrevAndNext(
			long transactionId, String uuid,
			OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByUuid_PrevAndNext(
			transactionId, uuid, orderByComparator);
	}

	/**
	 * Removes all the fin transactions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of fin transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching fin transactions
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the fin transaction where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFinTransactionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public static FinTransaction findByUUID_G(String uuid, long groupId)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the fin transaction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the fin transaction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the fin transaction where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the fin transaction that was removed
	 */
	public static FinTransaction removeByUUID_G(String uuid, long groupId)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of fin transactions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching fin transactions
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the fin transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching fin transactions
	 */
	public static List<FinTransaction> findByUuid_C(
		String uuid, long companyId) {

		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the fin transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @return the range of matching fin transactions
	 */
	public static List<FinTransaction> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin transactions
	 */
	public static List<FinTransaction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin transactions
	 */
	public static List<FinTransaction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public static FinTransaction findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public static FinTransaction findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the fin transactions before and after the current fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param transactionId the primary key of the current fin transaction
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction[] findByUuid_C_PrevAndNext(
			long transactionId, String uuid, long companyId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByUuid_C_PrevAndNext(
			transactionId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the fin transactions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of fin transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching fin transactions
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns all the fin transactions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin transactions
	 */
	public static List<FinTransaction> findByGroupId(long groupId) {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns a range of all the fin transactions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @return the range of matching fin transactions
	 */
	public static List<FinTransaction> findByGroupId(
		long groupId, int start, int end) {

		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin transactions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin transactions
	 */
	public static List<FinTransaction> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin transactions where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin transactions
	 */
	public static List<FinTransaction> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGroupId(
			groupId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public static FinTransaction findByGroupId_First(
			long groupId, OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchByGroupId_First(
		long groupId, OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().fetchByGroupId_First(
			groupId, orderByComparator);
	}

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public static FinTransaction findByGroupId_Last(
			long groupId, OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchByGroupId_Last(
		long groupId, OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	 * Returns the fin transactions before and after the current fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param transactionId the primary key of the current fin transaction
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction[] findByGroupId_PrevAndNext(
			long transactionId, long groupId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByGroupId_PrevAndNext(
			transactionId, groupId, orderByComparator);
	}

	/**
	 * Returns all the fin transactions that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin transactions that the user has permission to view
	 */
	public static List<FinTransaction> filterFindByGroupId(long groupId) {
		return getPersistence().filterFindByGroupId(groupId);
	}

	/**
	 * Returns a range of all the fin transactions that the user has permission to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @return the range of matching fin transactions that the user has permission to view
	 */
	public static List<FinTransaction> filterFindByGroupId(
		long groupId, int start, int end) {

		return getPersistence().filterFindByGroupId(groupId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin transactions that the user has permissions to view where groupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin transactions that the user has permission to view
	 */
	public static List<FinTransaction> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().filterFindByGroupId(
			groupId, start, end, orderByComparator);
	}

	/**
	 * Returns the fin transactions before and after the current fin transaction in the ordered set of fin transactions that the user has permission to view where groupId = &#63;.
	 *
	 * @param transactionId the primary key of the current fin transaction
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction[] filterFindByGroupId_PrevAndNext(
			long transactionId, long groupId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().filterFindByGroupId_PrevAndNext(
			transactionId, groupId, orderByComparator);
	}

	/**
	 * Removes all the fin transactions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public static void removeByGroupId(long groupId) {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of fin transactions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin transactions
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the number of fin transactions that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin transactions that the user has permission to view
	 */
	public static int filterCountByGroupId(long groupId) {
		return getPersistence().filterCountByGroupId(groupId);
	}

	/**
	 * Returns all the fin transactions where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the matching fin transactions
	 */
	public static List<FinTransaction> findByG_A(long groupId, long accountId) {
		return getPersistence().findByG_A(groupId, accountId);
	}

	/**
	 * Returns a range of all the fin transactions where groupId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @return the range of matching fin transactions
	 */
	public static List<FinTransaction> findByG_A(
		long groupId, long accountId, int start, int end) {

		return getPersistence().findByG_A(groupId, accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin transactions where groupId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin transactions
	 */
	public static List<FinTransaction> findByG_A(
		long groupId, long accountId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().findByG_A(
			groupId, accountId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin transactions where groupId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fin transactions
	 */
	public static List<FinTransaction> findByG_A(
		long groupId, long accountId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByG_A(
			groupId, accountId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public static FinTransaction findByG_A_First(
			long groupId, long accountId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByG_A_First(
			groupId, accountId, orderByComparator);
	}

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchByG_A_First(
		long groupId, long accountId,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().fetchByG_A_First(
			groupId, accountId, orderByComparator);
	}

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public static FinTransaction findByG_A_Last(
			long groupId, long accountId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByG_A_Last(
			groupId, accountId, orderByComparator);
	}

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public static FinTransaction fetchByG_A_Last(
		long groupId, long accountId,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().fetchByG_A_Last(
			groupId, accountId, orderByComparator);
	}

	/**
	 * Returns the fin transactions before and after the current fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param transactionId the primary key of the current fin transaction
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction[] findByG_A_PrevAndNext(
			long transactionId, long groupId, long accountId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByG_A_PrevAndNext(
			transactionId, groupId, accountId, orderByComparator);
	}

	/**
	 * Returns all the fin transactions that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the matching fin transactions that the user has permission to view
	 */
	public static List<FinTransaction> filterFindByG_A(
		long groupId, long accountId) {

		return getPersistence().filterFindByG_A(groupId, accountId);
	}

	/**
	 * Returns a range of all the fin transactions that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @return the range of matching fin transactions that the user has permission to view
	 */
	public static List<FinTransaction> filterFindByG_A(
		long groupId, long accountId, int start, int end) {

		return getPersistence().filterFindByG_A(groupId, accountId, start, end);
	}

	/**
	 * Returns an ordered range of all the fin transactions that the user has permissions to view where groupId = &#63; and accountId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fin transactions that the user has permission to view
	 */
	public static List<FinTransaction> filterFindByG_A(
		long groupId, long accountId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().filterFindByG_A(
			groupId, accountId, start, end, orderByComparator);
	}

	/**
	 * Returns the fin transactions before and after the current fin transaction in the ordered set of fin transactions that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param transactionId the primary key of the current fin transaction
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction[] filterFindByG_A_PrevAndNext(
			long transactionId, long groupId, long accountId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().filterFindByG_A_PrevAndNext(
			transactionId, groupId, accountId, orderByComparator);
	}

	/**
	 * Removes all the fin transactions where groupId = &#63; and accountId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 */
	public static void removeByG_A(long groupId, long accountId) {
		getPersistence().removeByG_A(groupId, accountId);
	}

	/**
	 * Returns the number of fin transactions where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the number of matching fin transactions
	 */
	public static int countByG_A(long groupId, long accountId) {
		return getPersistence().countByG_A(groupId, accountId);
	}

	/**
	 * Returns the number of fin transactions that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the number of matching fin transactions that the user has permission to view
	 */
	public static int filterCountByG_A(long groupId, long accountId) {
		return getPersistence().filterCountByG_A(groupId, accountId);
	}

	/**
	 * Caches the fin transaction in the entity cache if it is enabled.
	 *
	 * @param finTransaction the fin transaction
	 */
	public static void cacheResult(FinTransaction finTransaction) {
		getPersistence().cacheResult(finTransaction);
	}

	/**
	 * Caches the fin transactions in the entity cache if it is enabled.
	 *
	 * @param finTransactions the fin transactions
	 */
	public static void cacheResult(List<FinTransaction> finTransactions) {
		getPersistence().cacheResult(finTransactions);
	}

	/**
	 * Creates a new fin transaction with the primary key. Does not add the fin transaction to the database.
	 *
	 * @param transactionId the primary key for the new fin transaction
	 * @return the new fin transaction
	 */
	public static FinTransaction create(long transactionId) {
		return getPersistence().create(transactionId);
	}

	/**
	 * Removes the fin transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction that was removed
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction remove(long transactionId)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().remove(transactionId);
	}

	public static FinTransaction updateImpl(FinTransaction finTransaction) {
		return getPersistence().updateImpl(finTransaction);
	}

	/**
	 * Returns the fin transaction with the primary key or throws a <code>NoSuchFinTransactionException</code> if it could not be found.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction findByPrimaryKey(long transactionId)
		throws fin.services.exception.NoSuchFinTransactionException {

		return getPersistence().findByPrimaryKey(transactionId);
	}

	/**
	 * Returns the fin transaction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction, or <code>null</code> if a fin transaction with the primary key could not be found
	 */
	public static FinTransaction fetchByPrimaryKey(long transactionId) {
		return getPersistence().fetchByPrimaryKey(transactionId);
	}

	/**
	 * Returns all the fin transactions.
	 *
	 * @return the fin transactions
	 */
	public static List<FinTransaction> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the fin transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @return the range of fin transactions
	 */
	public static List<FinTransaction> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the fin transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fin transactions
	 */
	public static List<FinTransaction> findAll(
		int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fin transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of fin transactions
	 */
	public static List<FinTransaction> findAll(
		int start, int end, OrderByComparator<FinTransaction> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the fin transactions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of fin transactions.
	 *
	 * @return the number of fin transactions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FinTransactionPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(FinTransactionPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile FinTransactionPersistence _persistence;

}