/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import fin.services.exception.NoSuchFinAccountException;
import fin.services.model.FinAccount;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the fin account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinAccountUtil
 * @generated
 */
@ProviderType
public interface FinAccountPersistence extends BasePersistence<FinAccount> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FinAccountUtil} to access the fin account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the fin accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching fin accounts
	 */
	public java.util.List<FinAccount> findByUuid(String uuid);

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
	public java.util.List<FinAccount> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<FinAccount> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

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
	public java.util.List<FinAccount> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public FinAccount findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Returns the first fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public FinAccount findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

	/**
	 * Returns the fin accounts before and after the current fin account in the ordered set where uuid = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public FinAccount[] findByUuid_PrevAndNext(
			long accountId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Removes all the fin accounts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of fin accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching fin accounts
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the fin account where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFinAccountException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public FinAccount findByUUID_G(String uuid, long groupId)
		throws NoSuchFinAccountException;

	/**
	 * Returns the fin account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the fin account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the fin account where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the fin account that was removed
	 */
	public FinAccount removeByUUID_G(String uuid, long groupId)
		throws NoSuchFinAccountException;

	/**
	 * Returns the number of fin accounts where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching fin accounts
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the fin accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching fin accounts
	 */
	public java.util.List<FinAccount> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<FinAccount> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<FinAccount> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

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
	public java.util.List<FinAccount> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public FinAccount findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Returns the first fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public FinAccount findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

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
	public FinAccount[] findByUuid_C_PrevAndNext(
			long accountId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Removes all the fin accounts where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of fin accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching fin accounts
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the fin accounts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin accounts
	 */
	public java.util.List<FinAccount> findByGroupId(long groupId);

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
	public java.util.List<FinAccount> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<FinAccount> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

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
	public java.util.List<FinAccount> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public FinAccount findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Returns the first fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public FinAccount findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

	/**
	 * Returns the fin accounts before and after the current fin account in the ordered set where groupId = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public FinAccount[] findByGroupId_PrevAndNext(
			long accountId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Returns all the fin accounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin accounts that the user has permission to view
	 */
	public java.util.List<FinAccount> filterFindByGroupId(long groupId);

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
	public java.util.List<FinAccount> filterFindByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<FinAccount> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

	/**
	 * Returns the fin accounts before and after the current fin account in the ordered set of fin accounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public FinAccount[] filterFindByGroupId_PrevAndNext(
			long accountId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Removes all the fin accounts where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of fin accounts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin accounts
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the number of fin accounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin accounts that the user has permission to view
	 */
	public int filterCountByGroupId(long groupId);

	/**
	 * Returns the fin account where groupId = &#63; and accountNumber = &#63; or throws a <code>NoSuchFinAccountException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public FinAccount findByG_A(long groupId, String accountNumber)
		throws NoSuchFinAccountException;

	/**
	 * Returns the fin account where groupId = &#63; and accountNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByG_A(long groupId, String accountNumber);

	/**
	 * Returns the fin account where groupId = &#63; and accountNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByG_A(
		long groupId, String accountNumber, boolean useFinderCache);

	/**
	 * Removes the fin account where groupId = &#63; and accountNumber = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the fin account that was removed
	 */
	public FinAccount removeByG_A(long groupId, String accountNumber)
		throws NoSuchFinAccountException;

	/**
	 * Returns the number of fin accounts where groupId = &#63; and accountNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the number of matching fin accounts
	 */
	public int countByG_A(long groupId, String accountNumber);

	/**
	 * Returns all the fin accounts where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the matching fin accounts
	 */
	public java.util.List<FinAccount> findByG_O(long groupId, long ownerUserId);

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
	public java.util.List<FinAccount> findByG_O(
		long groupId, long ownerUserId, int start, int end);

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
	public java.util.List<FinAccount> findByG_O(
		long groupId, long ownerUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

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
	public java.util.List<FinAccount> findByG_O(
		long groupId, long ownerUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public FinAccount findByG_O_First(
			long groupId, long ownerUserId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Returns the first fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByG_O_First(
		long groupId, long ownerUserId,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	public FinAccount findByG_O_Last(
			long groupId, long ownerUserId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	public FinAccount fetchByG_O_Last(
		long groupId, long ownerUserId,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

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
	public FinAccount[] findByG_O_PrevAndNext(
			long accountId, long groupId, long ownerUserId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Returns all the fin accounts that the user has permission to view where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the matching fin accounts that the user has permission to view
	 */
	public java.util.List<FinAccount> filterFindByG_O(
		long groupId, long ownerUserId);

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
	public java.util.List<FinAccount> filterFindByG_O(
		long groupId, long ownerUserId, int start, int end);

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
	public java.util.List<FinAccount> filterFindByG_O(
		long groupId, long ownerUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

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
	public FinAccount[] filterFindByG_O_PrevAndNext(
			long accountId, long groupId, long ownerUserId,
			com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
				orderByComparator)
		throws NoSuchFinAccountException;

	/**
	 * Removes all the fin accounts where groupId = &#63; and ownerUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 */
	public void removeByG_O(long groupId, long ownerUserId);

	/**
	 * Returns the number of fin accounts where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the number of matching fin accounts
	 */
	public int countByG_O(long groupId, long ownerUserId);

	/**
	 * Returns the number of fin accounts that the user has permission to view where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the number of matching fin accounts that the user has permission to view
	 */
	public int filterCountByG_O(long groupId, long ownerUserId);

	/**
	 * Caches the fin account in the entity cache if it is enabled.
	 *
	 * @param finAccount the fin account
	 */
	public void cacheResult(FinAccount finAccount);

	/**
	 * Caches the fin accounts in the entity cache if it is enabled.
	 *
	 * @param finAccounts the fin accounts
	 */
	public void cacheResult(java.util.List<FinAccount> finAccounts);

	/**
	 * Creates a new fin account with the primary key. Does not add the fin account to the database.
	 *
	 * @param accountId the primary key for the new fin account
	 * @return the new fin account
	 */
	public FinAccount create(long accountId);

	/**
	 * Removes the fin account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account that was removed
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public FinAccount remove(long accountId) throws NoSuchFinAccountException;

	public FinAccount updateImpl(FinAccount finAccount);

	/**
	 * Returns the fin account with the primary key or throws a <code>NoSuchFinAccountException</code> if it could not be found.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	public FinAccount findByPrimaryKey(long accountId)
		throws NoSuchFinAccountException;

	/**
	 * Returns the fin account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account, or <code>null</code> if a fin account with the primary key could not be found
	 */
	public FinAccount fetchByPrimaryKey(long accountId);

	/**
	 * Returns all the fin accounts.
	 *
	 * @return the fin accounts
	 */
	public java.util.List<FinAccount> findAll();

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
	public java.util.List<FinAccount> findAll(int start, int end);

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
	public java.util.List<FinAccount> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator);

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
	public java.util.List<FinAccount> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinAccount>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the fin accounts from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of fin accounts.
	 *
	 * @return the number of fin accounts
	 */
	public int countAll();

}