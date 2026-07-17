/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import fin.services.exception.NoSuchFinTransactionException;
import fin.services.model.FinTransaction;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the fin transaction service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinTransactionUtil
 * @generated
 */
@ProviderType
public interface FinTransactionPersistence
	extends BasePersistence<FinTransaction> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FinTransactionUtil} to access the fin transaction persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the fin transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching fin transactions
	 */
	public java.util.List<FinTransaction> findByUuid(String uuid);

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
	public java.util.List<FinTransaction> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<FinTransaction> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

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
	public java.util.List<FinTransaction> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public FinTransaction findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public FinTransaction fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public FinTransaction findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public FinTransaction fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

	/**
	 * Returns the fin transactions before and after the current fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param transactionId the primary key of the current fin transaction
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public FinTransaction[] findByUuid_PrevAndNext(
			long transactionId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Removes all the fin transactions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of fin transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching fin transactions
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the fin transaction where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFinTransactionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public FinTransaction findByUUID_G(String uuid, long groupId)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the fin transaction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public FinTransaction fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the fin transaction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public FinTransaction fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the fin transaction where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the fin transaction that was removed
	 */
	public FinTransaction removeByUUID_G(String uuid, long groupId)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the number of fin transactions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching fin transactions
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the fin transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching fin transactions
	 */
	public java.util.List<FinTransaction> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<FinTransaction> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<FinTransaction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

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
	public java.util.List<FinTransaction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public FinTransaction findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public FinTransaction fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public FinTransaction findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public FinTransaction fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

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
	public FinTransaction[] findByUuid_C_PrevAndNext(
			long transactionId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Removes all the fin transactions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of fin transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching fin transactions
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the fin transactions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin transactions
	 */
	public java.util.List<FinTransaction> findByGroupId(long groupId);

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
	public java.util.List<FinTransaction> findByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<FinTransaction> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

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
	public java.util.List<FinTransaction> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public FinTransaction findByGroupId_First(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public FinTransaction fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public FinTransaction findByGroupId_Last(
			long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public FinTransaction fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

	/**
	 * Returns the fin transactions before and after the current fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param transactionId the primary key of the current fin transaction
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public FinTransaction[] findByGroupId_PrevAndNext(
			long transactionId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Returns all the fin transactions that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin transactions that the user has permission to view
	 */
	public java.util.List<FinTransaction> filterFindByGroupId(long groupId);

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
	public java.util.List<FinTransaction> filterFindByGroupId(
		long groupId, int start, int end);

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
	public java.util.List<FinTransaction> filterFindByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

	/**
	 * Returns the fin transactions before and after the current fin transaction in the ordered set of fin transactions that the user has permission to view where groupId = &#63;.
	 *
	 * @param transactionId the primary key of the current fin transaction
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public FinTransaction[] filterFindByGroupId_PrevAndNext(
			long transactionId, long groupId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Removes all the fin transactions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	public void removeByGroupId(long groupId);

	/**
	 * Returns the number of fin transactions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin transactions
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the number of fin transactions that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin transactions that the user has permission to view
	 */
	public int filterCountByGroupId(long groupId);

	/**
	 * Returns all the fin transactions where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the matching fin transactions
	 */
	public java.util.List<FinTransaction> findByG_A(
		long groupId, long accountId);

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
	public java.util.List<FinTransaction> findByG_A(
		long groupId, long accountId, int start, int end);

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
	public java.util.List<FinTransaction> findByG_A(
		long groupId, long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

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
	public java.util.List<FinTransaction> findByG_A(
		long groupId, long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public FinTransaction findByG_A_First(
			long groupId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public FinTransaction fetchByG_A_First(
		long groupId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	public FinTransaction findByG_A_Last(
			long groupId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	public FinTransaction fetchByG_A_Last(
		long groupId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

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
	public FinTransaction[] findByG_A_PrevAndNext(
			long transactionId, long groupId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Returns all the fin transactions that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the matching fin transactions that the user has permission to view
	 */
	public java.util.List<FinTransaction> filterFindByG_A(
		long groupId, long accountId);

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
	public java.util.List<FinTransaction> filterFindByG_A(
		long groupId, long accountId, int start, int end);

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
	public java.util.List<FinTransaction> filterFindByG_A(
		long groupId, long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

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
	public FinTransaction[] filterFindByG_A_PrevAndNext(
			long transactionId, long groupId, long accountId,
			com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
				orderByComparator)
		throws NoSuchFinTransactionException;

	/**
	 * Removes all the fin transactions where groupId = &#63; and accountId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 */
	public void removeByG_A(long groupId, long accountId);

	/**
	 * Returns the number of fin transactions where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the number of matching fin transactions
	 */
	public int countByG_A(long groupId, long accountId);

	/**
	 * Returns the number of fin transactions that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the number of matching fin transactions that the user has permission to view
	 */
	public int filterCountByG_A(long groupId, long accountId);

	/**
	 * Caches the fin transaction in the entity cache if it is enabled.
	 *
	 * @param finTransaction the fin transaction
	 */
	public void cacheResult(FinTransaction finTransaction);

	/**
	 * Caches the fin transactions in the entity cache if it is enabled.
	 *
	 * @param finTransactions the fin transactions
	 */
	public void cacheResult(java.util.List<FinTransaction> finTransactions);

	/**
	 * Creates a new fin transaction with the primary key. Does not add the fin transaction to the database.
	 *
	 * @param transactionId the primary key for the new fin transaction
	 * @return the new fin transaction
	 */
	public FinTransaction create(long transactionId);

	/**
	 * Removes the fin transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction that was removed
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public FinTransaction remove(long transactionId)
		throws NoSuchFinTransactionException;

	public FinTransaction updateImpl(FinTransaction finTransaction);

	/**
	 * Returns the fin transaction with the primary key or throws a <code>NoSuchFinTransactionException</code> if it could not be found.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	public FinTransaction findByPrimaryKey(long transactionId)
		throws NoSuchFinTransactionException;

	/**
	 * Returns the fin transaction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction, or <code>null</code> if a fin transaction with the primary key could not be found
	 */
	public FinTransaction fetchByPrimaryKey(long transactionId);

	/**
	 * Returns all the fin transactions.
	 *
	 * @return the fin transactions
	 */
	public java.util.List<FinTransaction> findAll();

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
	public java.util.List<FinTransaction> findAll(int start, int end);

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
	public java.util.List<FinTransaction> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator);

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
	public java.util.List<FinTransaction> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FinTransaction>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the fin transactions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of fin transactions.
	 *
	 * @return the number of fin transactions
	 */
	public int countAll();

}