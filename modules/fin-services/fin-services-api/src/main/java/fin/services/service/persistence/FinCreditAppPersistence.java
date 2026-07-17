/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import fin.services.exception.NoSuchFinCreditAppException;
import fin.services.model.FinCreditApp;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the fin credit app service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinCreditAppUtil
 * @generated
 */
@ProviderType
public interface FinCreditAppPersistence extends BasePersistence<FinCreditApp> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FinCreditAppUtil} to access the fin credit app persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the fin credit apps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching fin credit apps
	 */
	public java.util.List<FinCreditApp> findByUuid(String uuid);

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
	public java.util.List<FinCreditApp> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<FinCreditApp> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

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
	public java.util.List<FinCreditApp> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin credit app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public FinCreditApp findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the first fin credit app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public FinCreditApp fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

	/**
	 * Returns the last fin credit app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public FinCreditApp findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the last fin credit app in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public FinCreditApp fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

	/**
	 * Returns the fin credit apps before and after the current fin credit app in the ordered set where uuid = &#63;.
	 *
	 * @param creditAppId the primary key of the current fin credit app
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public FinCreditApp[] findByUuid_PrevAndNext(
			long creditAppId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Removes all the fin credit apps where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of fin credit apps where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching fin credit apps
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the fin credit app where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFinCreditAppException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public FinCreditApp findByUUID_G(String uuid, long groupId)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the fin credit app where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public FinCreditApp fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the fin credit app where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public FinCreditApp fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the fin credit app where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the fin credit app that was removed
	 */
	public FinCreditApp removeByUUID_G(String uuid, long groupId)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the number of fin credit apps where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching fin credit apps
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the fin credit apps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching fin credit apps
	 */
	public java.util.List<FinCreditApp> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<FinCreditApp> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<FinCreditApp> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

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
	public java.util.List<FinCreditApp> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin credit app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public FinCreditApp findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the first fin credit app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public FinCreditApp fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

	/**
	 * Returns the last fin credit app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public FinCreditApp findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the last fin credit app in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public FinCreditApp fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

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
	public FinCreditApp[] findByUuid_C_PrevAndNext(
			long creditAppId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Removes all the fin credit apps where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of fin credit apps where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching fin credit apps
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the fin credit apps where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin credit apps
	 */
	public java.util.List<FinCreditApp> findByGroupId(long groupId);

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
	public java.util.List<FinCreditApp> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<FinCreditApp> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

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
	public java.util.List<FinCreditApp> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin credit app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public FinCreditApp findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the first fin credit app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public FinCreditApp fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

	/**
	 * Returns the last fin credit app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public FinCreditApp findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the last fin credit app in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public FinCreditApp fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

	/**
	 * Returns the fin credit apps before and after the current fin credit app in the ordered set where groupId = &#63;.
	 *
	 * @param creditAppId the primary key of the current fin credit app
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public FinCreditApp[] findByGroupId_PrevAndNext(
			long creditAppId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns all the fin credit apps that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin credit apps that the user has permission to view
	 */
	public java.util.List<FinCreditApp> filterFindByGroupId(long groupId);

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
	public java.util.List<FinCreditApp> filterFindByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<FinCreditApp> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

	/**
	 * Returns the fin credit apps before and after the current fin credit app in the ordered set of fin credit apps that the user has permission to view where groupId = &#63;.
	 *
	 * @param creditAppId the primary key of the current fin credit app
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public FinCreditApp[] filterFindByGroupId_PrevAndNext(
			long creditAppId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Removes all the fin credit apps where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of fin credit apps where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin credit apps
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the number of fin credit apps that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin credit apps that the user has permission to view
	 */
	public int filterCountByGroupId(long groupId);

	/**
	 * Returns all the fin credit apps where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the matching fin credit apps
	 */
	public java.util.List<FinCreditApp> findByG_A(long groupId, long accountId);

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
	public java.util.List<FinCreditApp> findByG_A(
		long groupId, long accountId, int start, int end);

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
	public java.util.List<FinCreditApp> findByG_A(
		long groupId, long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

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
	public java.util.List<FinCreditApp> findByG_A(
		long groupId, long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin credit app in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public FinCreditApp findByG_A_First(
			long groupId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the first fin credit app in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public FinCreditApp fetchByG_A_First(
		long groupId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

	/**
	 * Returns the last fin credit app in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app
	 * @throws NoSuchFinCreditAppException if a matching fin credit app could not be found
	 */
	public FinCreditApp findByG_A_Last(
			long groupId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the last fin credit app in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin credit app, or <code>null</code> if a matching fin credit app could not be found
	 */
	public FinCreditApp fetchByG_A_Last(
		long groupId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

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
	public FinCreditApp[] findByG_A_PrevAndNext(
			long creditAppId, long groupId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns all the fin credit apps that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the matching fin credit apps that the user has permission to view
	 */
	public java.util.List<FinCreditApp> filterFindByG_A(
		long groupId, long accountId);

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
	public java.util.List<FinCreditApp> filterFindByG_A(
		long groupId, long accountId, int start, int end);

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
	public java.util.List<FinCreditApp> filterFindByG_A(
		long groupId, long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

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
	public FinCreditApp[] filterFindByG_A_PrevAndNext(
			long creditAppId, long groupId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
				orderByComparator)
		throws NoSuchFinCreditAppException;

	/**
	 * Removes all the fin credit apps where groupId = &#63; and accountId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 */
	public void removeByG_A(long groupId, long accountId);

	/**
	 * Returns the number of fin credit apps where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the number of matching fin credit apps
	 */
	public int countByG_A(long groupId, long accountId);

	/**
	 * Returns the number of fin credit apps that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the number of matching fin credit apps that the user has permission to view
	 */
	public int filterCountByG_A(long groupId, long accountId);

	/**
	 * Caches the fin credit app in the entity cache if it is enabled.
	 *
	 * @param finCreditApp the fin credit app
	 */
	public void cacheResult(FinCreditApp finCreditApp);

	/**
	 * Caches the fin credit apps in the entity cache if it is enabled.
	 *
	 * @param finCreditApps the fin credit apps
	 */
	public void cacheResult(java.util.List<FinCreditApp> finCreditApps);

	/**
	 * Creates a new fin credit app with the primary key. Does not add the fin credit app to the database.
	 *
	 * @param creditAppId the primary key for the new fin credit app
	 * @return the new fin credit app
	 */
	public FinCreditApp create(long creditAppId);

	/**
	 * Removes the fin credit app with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param creditAppId the primary key of the fin credit app
	 * @return the fin credit app that was removed
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public FinCreditApp remove(long creditAppId)
		throws NoSuchFinCreditAppException;

	public FinCreditApp updateImpl(FinCreditApp finCreditApp);

	/**
	 * Returns the fin credit app with the primary key or throws a <code>NoSuchFinCreditAppException</code> if it could not be found.
	 *
	 * @param creditAppId the primary key of the fin credit app
	 * @return the fin credit app
	 * @throws NoSuchFinCreditAppException if a fin credit app with the primary key could not be found
	 */
	public FinCreditApp findByPrimaryKey(long creditAppId)
		throws NoSuchFinCreditAppException;

	/**
	 * Returns the fin credit app with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param creditAppId the primary key of the fin credit app
	 * @return the fin credit app, or <code>null</code> if a fin credit app with the primary key could not be found
	 */
	public FinCreditApp fetchByPrimaryKey(long creditAppId);

	/**
	 * Returns all the fin credit apps.
	 *
	 * @return the fin credit apps
	 */
	public java.util.List<FinCreditApp> findAll();

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
	public java.util.List<FinCreditApp> findAll(int start, int end);

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
	public java.util.List<FinCreditApp> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator);

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
	public java.util.List<FinCreditApp> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinCreditApp>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the fin credit apps from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of fin credit apps.
	 *
	 * @return the number of fin credit apps
	 */
	public int countAll();

}