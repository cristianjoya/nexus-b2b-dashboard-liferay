/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import fin.services.exception.NoSuchFinTransactionException;
import fin.services.model.FinTransaction;
import fin.services.model.FinTransactionTable;
import fin.services.model.impl.FinTransactionImpl;
import fin.services.model.impl.FinTransactionModelImpl;
import fin.services.service.persistence.FinTransactionPersistence;
import fin.services.service.persistence.FinTransactionUtil;
import fin.services.service.persistence.impl.constants.FinServicesPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the fin transaction service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = FinTransactionPersistence.class)
public class FinTransactionPersistenceImpl
	extends BasePersistenceImpl<FinTransaction>
	implements FinTransactionPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FinTransactionUtil</code> to access the fin transaction persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FinTransactionImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the fin transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching fin transactions
	 */
	@Override
	public List<FinTransaction> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinTransaction> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<FinTransaction> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<FinTransaction> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<FinTransaction> list = null;

		if (useFinderCache) {
			list = (List<FinTransaction>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FinTransaction finTransaction : list) {
					if (!uuid.equals(finTransaction.getUuid())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_FINTRANSACTION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FinTransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list = (List<FinTransaction>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction findByUuid_First(
			String uuid, OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = fetchByUuid_First(
			uuid, orderByComparator);

		if (finTransaction != null) {
			return finTransaction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFinTransactionException(sb.toString());
	}

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction fetchByUuid_First(
		String uuid, OrderByComparator<FinTransaction> orderByComparator) {

		List<FinTransaction> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction findByUuid_Last(
			String uuid, OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = fetchByUuid_Last(
			uuid, orderByComparator);

		if (finTransaction != null) {
			return finTransaction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFinTransactionException(sb.toString());
	}

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction fetchByUuid_Last(
		String uuid, OrderByComparator<FinTransaction> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<FinTransaction> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinTransaction[] findByUuid_PrevAndNext(
			long transactionId, String uuid,
			OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		uuid = Objects.toString(uuid, "");

		FinTransaction finTransaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			FinTransaction[] array = new FinTransactionImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, finTransaction, uuid, orderByComparator, true);

			array[1] = finTransaction;

			array[2] = getByUuid_PrevAndNext(
				session, finTransaction, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinTransaction getByUuid_PrevAndNext(
		Session session, FinTransaction finTransaction, String uuid,
		OrderByComparator<FinTransaction> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FINTRANSACTION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(FinTransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						finTransaction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinTransaction> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fin transactions where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (FinTransaction finTransaction :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(finTransaction);
		}
	}

	/**
	 * Returns the number of fin transactions where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching fin transactions
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FINTRANSACTION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"finTransaction.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(finTransaction.uuid IS NULL OR finTransaction.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;

	/**
	 * Returns the fin transaction where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFinTransactionException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction findByUUID_G(String uuid, long groupId)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = fetchByUUID_G(uuid, groupId);

		if (finTransaction == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("uuid=");
			sb.append(uuid);

			sb.append(", groupId=");
			sb.append(groupId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFinTransactionException(sb.toString());
		}

		return finTransaction;
	}

	/**
	 * Returns the fin transaction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the fin transaction where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof FinTransaction) {
			FinTransaction finTransaction = (FinTransaction)result;

			if (!Objects.equals(uuid, finTransaction.getUuid()) ||
				(groupId != finTransaction.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FINTRANSACTION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(groupId);

				List<FinTransaction> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					FinTransaction finTransaction = list.get(0);

					result = finTransaction;

					cacheResult(finTransaction);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (FinTransaction)result;
		}
	}

	/**
	 * Removes the fin transaction where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the fin transaction that was removed
	 */
	@Override
	public FinTransaction removeByUUID_G(String uuid, long groupId)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = findByUUID_G(uuid, groupId);

		return remove(finTransaction);
	}

	/**
	 * Returns the number of fin transactions where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching fin transactions
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinTransaction finTransaction = fetchByUUID_G(uuid, groupId);

		if (finTransaction == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"finTransaction.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(finTransaction.uuid IS NULL OR finTransaction.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"finTransaction.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the fin transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching fin transactions
	 */
	@Override
	public List<FinTransaction> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinTransaction> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<FinTransaction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<FinTransaction> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<FinTransaction> list = null;

		if (useFinderCache) {
			list = (List<FinTransaction>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FinTransaction finTransaction : list) {
					if (!uuid.equals(finTransaction.getUuid()) ||
						(companyId != finTransaction.getCompanyId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_FINTRANSACTION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FinTransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list = (List<FinTransaction>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public FinTransaction findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (finTransaction != null) {
			return finTransaction;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFinTransactionException(sb.toString());
	}

	/**
	 * Returns the first fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<FinTransaction> orderByComparator) {

		List<FinTransaction> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinTransaction findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (finTransaction != null) {
			return finTransaction;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFinTransactionException(sb.toString());
	}

	/**
	 * Returns the last fin transaction in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<FinTransaction> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<FinTransaction> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinTransaction[] findByUuid_C_PrevAndNext(
			long transactionId, String uuid, long companyId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		uuid = Objects.toString(uuid, "");

		FinTransaction finTransaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			FinTransaction[] array = new FinTransactionImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, finTransaction, uuid, companyId, orderByComparator,
				true);

			array[1] = finTransaction;

			array[2] = getByUuid_C_PrevAndNext(
				session, finTransaction, uuid, companyId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinTransaction getByUuid_C_PrevAndNext(
		Session session, FinTransaction finTransaction, String uuid,
		long companyId, OrderByComparator<FinTransaction> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_FINTRANSACTION_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(FinTransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						finTransaction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinTransaction> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fin transactions where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (FinTransaction finTransaction :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(finTransaction);
		}
	}

	/**
	 * Returns the number of fin transactions where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching fin transactions
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FINTRANSACTION_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"finTransaction.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(finTransaction.uuid IS NULL OR finTransaction.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"finTransaction.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the fin transactions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin transactions
	 */
	@Override
	public List<FinTransaction> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinTransaction> findByGroupId(
		long groupId, int start, int end) {

		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<FinTransaction> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<FinTransaction> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByGroupId;
				finderArgs = new Object[] {groupId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByGroupId;
			finderArgs = new Object[] {groupId, start, end, orderByComparator};
		}

		List<FinTransaction> list = null;

		if (useFinderCache) {
			list = (List<FinTransaction>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FinTransaction finTransaction : list) {
					if (groupId != finTransaction.getGroupId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_FINTRANSACTION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FinTransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<FinTransaction>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction findByGroupId_First(
			long groupId, OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = fetchByGroupId_First(
			groupId, orderByComparator);

		if (finTransaction != null) {
			return finTransaction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchFinTransactionException(sb.toString());
	}

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction fetchByGroupId_First(
		long groupId, OrderByComparator<FinTransaction> orderByComparator) {

		List<FinTransaction> list = findByGroupId(
			groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction
	 * @throws NoSuchFinTransactionException if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction findByGroupId_Last(
			long groupId, OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = fetchByGroupId_Last(
			groupId, orderByComparator);

		if (finTransaction != null) {
			return finTransaction;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchFinTransactionException(sb.toString());
	}

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction fetchByGroupId_Last(
		long groupId, OrderByComparator<FinTransaction> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<FinTransaction> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinTransaction[] findByGroupId_PrevAndNext(
			long transactionId, long groupId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			FinTransaction[] array = new FinTransactionImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, finTransaction, groupId, orderByComparator, true);

			array[1] = finTransaction;

			array[2] = getByGroupId_PrevAndNext(
				session, finTransaction, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinTransaction getByGroupId_PrevAndNext(
		Session session, FinTransaction finTransaction, long groupId,
		OrderByComparator<FinTransaction> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FINTRANSACTION_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(FinTransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						finTransaction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinTransaction> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the fin transactions that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin transactions that the user has permission to view
	 */
	@Override
	public List<FinTransaction> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinTransaction> filterFindByGroupId(
		long groupId, int start, int end) {

		return filterFindByGroupId(groupId, start, end, null);
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
	@Override
	public List<FinTransaction> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId(groupId, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_FINTRANSACTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_FINTRANSACTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_FINTRANSACTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(FinTransactionModelImpl.ORDER_BY_SQL_INLINE_DISTINCT);
			}
			else {
				sb.append(FinTransactionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinTransaction.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, FinTransactionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, FinTransactionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);

			return (List<FinTransaction>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public FinTransaction[] filterFindByGroupId_PrevAndNext(
			long transactionId, long groupId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(
				transactionId, groupId, orderByComparator);
		}

		FinTransaction finTransaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			FinTransaction[] array = new FinTransactionImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(
				session, finTransaction, groupId, orderByComparator, true);

			array[1] = finTransaction;

			array[2] = filterGetByGroupId_PrevAndNext(
				session, finTransaction, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinTransaction filterGetByGroupId_PrevAndNext(
		Session session, FinTransaction finTransaction, long groupId,
		OrderByComparator<FinTransaction> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_FINTRANSACTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_FINTRANSACTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_FINTRANSACTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(FinTransactionModelImpl.ORDER_BY_SQL_INLINE_DISTINCT);
			}
			else {
				sb.append(FinTransactionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinTransaction.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, FinTransactionImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, FinTransactionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						finTransaction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinTransaction> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fin transactions where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (FinTransaction finTransaction :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(finTransaction);
		}
	}

	/**
	 * Returns the number of fin transactions where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin transactions
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FINTRANSACTION_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of fin transactions that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin transactions that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_FINTRANSACTION_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinTransaction.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"finTransaction.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByG_A;
	private FinderPath _finderPathWithoutPaginationFindByG_A;
	private FinderPath _finderPathCountByG_A;

	/**
	 * Returns all the fin transactions where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the matching fin transactions
	 */
	@Override
	public List<FinTransaction> findByG_A(long groupId, long accountId) {
		return findByG_A(
			groupId, accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinTransaction> findByG_A(
		long groupId, long accountId, int start, int end) {

		return findByG_A(groupId, accountId, start, end, null);
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
	@Override
	public List<FinTransaction> findByG_A(
		long groupId, long accountId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return findByG_A(
			groupId, accountId, start, end, orderByComparator, true);
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
	@Override
	public List<FinTransaction> findByG_A(
		long groupId, long accountId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_A;
				finderArgs = new Object[] {groupId, accountId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_A;
			finderArgs = new Object[] {
				groupId, accountId, start, end, orderByComparator
			};
		}

		List<FinTransaction> list = null;

		if (useFinderCache) {
			list = (List<FinTransaction>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FinTransaction finTransaction : list) {
					if ((groupId != finTransaction.getGroupId()) ||
						(accountId != finTransaction.getAccountId())) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_FINTRANSACTION_WHERE);

			sb.append(_FINDER_COLUMN_G_A_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_A_ACCOUNTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FinTransactionModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(accountId);

				list = (List<FinTransaction>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
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
	@Override
	public FinTransaction findByG_A_First(
			long groupId, long accountId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = fetchByG_A_First(
			groupId, accountId, orderByComparator);

		if (finTransaction != null) {
			return finTransaction;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchFinTransactionException(sb.toString());
	}

	/**
	 * Returns the first fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction fetchByG_A_First(
		long groupId, long accountId,
		OrderByComparator<FinTransaction> orderByComparator) {

		List<FinTransaction> list = findByG_A(
			groupId, accountId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinTransaction findByG_A_Last(
			long groupId, long accountId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = fetchByG_A_Last(
			groupId, accountId, orderByComparator);

		if (finTransaction != null) {
			return finTransaction;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", accountId=");
		sb.append(accountId);

		sb.append("}");

		throw new NoSuchFinTransactionException(sb.toString());
	}

	/**
	 * Returns the last fin transaction in the ordered set where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Override
	public FinTransaction fetchByG_A_Last(
		long groupId, long accountId,
		OrderByComparator<FinTransaction> orderByComparator) {

		int count = countByG_A(groupId, accountId);

		if (count == 0) {
			return null;
		}

		List<FinTransaction> list = findByG_A(
			groupId, accountId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinTransaction[] findByG_A_PrevAndNext(
			long transactionId, long groupId, long accountId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			FinTransaction[] array = new FinTransactionImpl[3];

			array[0] = getByG_A_PrevAndNext(
				session, finTransaction, groupId, accountId, orderByComparator,
				true);

			array[1] = finTransaction;

			array[2] = getByG_A_PrevAndNext(
				session, finTransaction, groupId, accountId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinTransaction getByG_A_PrevAndNext(
		Session session, FinTransaction finTransaction, long groupId,
		long accountId, OrderByComparator<FinTransaction> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_FINTRANSACTION_WHERE);

		sb.append(_FINDER_COLUMN_G_A_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_A_ACCOUNTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(FinTransactionModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(accountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						finTransaction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinTransaction> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the fin transactions that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the matching fin transactions that the user has permission to view
	 */
	@Override
	public List<FinTransaction> filterFindByG_A(long groupId, long accountId) {
		return filterFindByG_A(
			groupId, accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinTransaction> filterFindByG_A(
		long groupId, long accountId, int start, int end) {

		return filterFindByG_A(groupId, accountId, start, end, null);
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
	@Override
	public List<FinTransaction> filterFindByG_A(
		long groupId, long accountId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A(groupId, accountId, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_FINTRANSACTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_FINTRANSACTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_G_A_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_A_ACCOUNTID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_FINTRANSACTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(FinTransactionModelImpl.ORDER_BY_SQL_INLINE_DISTINCT);
			}
			else {
				sb.append(FinTransactionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinTransaction.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, FinTransactionImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, FinTransactionImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);

			queryPos.add(accountId);

			return (List<FinTransaction>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
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
	@Override
	public FinTransaction[] filterFindByG_A_PrevAndNext(
			long transactionId, long groupId, long accountId,
			OrderByComparator<FinTransaction> orderByComparator)
		throws NoSuchFinTransactionException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_A_PrevAndNext(
				transactionId, groupId, accountId, orderByComparator);
		}

		FinTransaction finTransaction = findByPrimaryKey(transactionId);

		Session session = null;

		try {
			session = openSession();

			FinTransaction[] array = new FinTransactionImpl[3];

			array[0] = filterGetByG_A_PrevAndNext(
				session, finTransaction, groupId, accountId, orderByComparator,
				true);

			array[1] = finTransaction;

			array[2] = filterGetByG_A_PrevAndNext(
				session, finTransaction, groupId, accountId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinTransaction filterGetByG_A_PrevAndNext(
		Session session, FinTransaction finTransaction, long groupId,
		long accountId, OrderByComparator<FinTransaction> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_FINTRANSACTION_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_FINTRANSACTION_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_G_A_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_A_ACCOUNTID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_FINTRANSACTION_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(FinTransactionModelImpl.ORDER_BY_SQL_INLINE_DISTINCT);
			}
			else {
				sb.append(FinTransactionModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinTransaction.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, FinTransactionImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, FinTransactionImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(groupId);

		queryPos.add(accountId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						finTransaction)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinTransaction> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fin transactions where groupId = &#63; and accountId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 */
	@Override
	public void removeByG_A(long groupId, long accountId) {
		for (FinTransaction finTransaction :
				findByG_A(
					groupId, accountId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(finTransaction);
		}
	}

	/**
	 * Returns the number of fin transactions where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the number of matching fin transactions
	 */
	@Override
	public int countByG_A(long groupId, long accountId) {
		FinderPath finderPath = _finderPathCountByG_A;

		Object[] finderArgs = new Object[] {groupId, accountId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FINTRANSACTION_WHERE);

			sb.append(_FINDER_COLUMN_G_A_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_A_ACCOUNTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(accountId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of fin transactions that the user has permission to view where groupId = &#63; and accountId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountId the account ID
	 * @return the number of matching fin transactions that the user has permission to view
	 */
	@Override
	public int filterCountByG_A(long groupId, long accountId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_A(groupId, accountId);
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_FINTRANSACTION_WHERE);

		sb.append(_FINDER_COLUMN_G_A_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_A_ACCOUNTID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinTransaction.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);

			queryPos.add(accountId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_G_A_GROUPID_2 =
		"finTransaction.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_A_ACCOUNTID_2 =
		"finTransaction.accountId = ?";

	public FinTransactionPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(FinTransaction.class);

		setModelImplClass(FinTransactionImpl.class);
		setModelPKClass(long.class);

		setTable(FinTransactionTable.INSTANCE);
	}

	/**
	 * Caches the fin transaction in the entity cache if it is enabled.
	 *
	 * @param finTransaction the fin transaction
	 */
	@Override
	public void cacheResult(FinTransaction finTransaction) {
		entityCache.putResult(
			FinTransactionImpl.class, finTransaction.getPrimaryKey(),
			finTransaction);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {
				finTransaction.getUuid(), finTransaction.getGroupId()
			},
			finTransaction);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the fin transactions in the entity cache if it is enabled.
	 *
	 * @param finTransactions the fin transactions
	 */
	@Override
	public void cacheResult(List<FinTransaction> finTransactions) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (finTransactions.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FinTransaction finTransaction : finTransactions) {
			if (entityCache.getResult(
					FinTransactionImpl.class, finTransaction.getPrimaryKey()) ==
						null) {

				cacheResult(finTransaction);
			}
		}
	}

	/**
	 * Clears the cache for all fin transactions.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FinTransactionImpl.class);

		finderCache.clearCache(FinTransactionImpl.class);
	}

	/**
	 * Clears the cache for the fin transaction.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FinTransaction finTransaction) {
		entityCache.removeResult(FinTransactionImpl.class, finTransaction);
	}

	@Override
	public void clearCache(List<FinTransaction> finTransactions) {
		for (FinTransaction finTransaction : finTransactions) {
			entityCache.removeResult(FinTransactionImpl.class, finTransaction);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FinTransactionImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FinTransactionImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		FinTransactionModelImpl finTransactionModelImpl) {

		Object[] args = new Object[] {
			finTransactionModelImpl.getUuid(),
			finTransactionModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathFetchByUUID_G, args, finTransactionModelImpl);
	}

	/**
	 * Creates a new fin transaction with the primary key. Does not add the fin transaction to the database.
	 *
	 * @param transactionId the primary key for the new fin transaction
	 * @return the new fin transaction
	 */
	@Override
	public FinTransaction create(long transactionId) {
		FinTransaction finTransaction = new FinTransactionImpl();

		finTransaction.setNew(true);
		finTransaction.setPrimaryKey(transactionId);

		String uuid = PortalUUIDUtil.generate();

		finTransaction.setUuid(uuid);

		finTransaction.setCompanyId(CompanyThreadLocal.getCompanyId());

		return finTransaction;
	}

	/**
	 * Removes the fin transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction that was removed
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	@Override
	public FinTransaction remove(long transactionId)
		throws NoSuchFinTransactionException {

		return remove((Serializable)transactionId);
	}

	/**
	 * Removes the fin transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the fin transaction
	 * @return the fin transaction that was removed
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	@Override
	public FinTransaction remove(Serializable primaryKey)
		throws NoSuchFinTransactionException {

		Session session = null;

		try {
			session = openSession();

			FinTransaction finTransaction = (FinTransaction)session.get(
				FinTransactionImpl.class, primaryKey);

			if (finTransaction == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFinTransactionException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(finTransaction);
		}
		catch (NoSuchFinTransactionException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected FinTransaction removeImpl(FinTransaction finTransaction) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(finTransaction)) {
				finTransaction = (FinTransaction)session.get(
					FinTransactionImpl.class,
					finTransaction.getPrimaryKeyObj());
			}

			if (finTransaction != null) {
				session.delete(finTransaction);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (finTransaction != null) {
			clearCache(finTransaction);
		}

		return finTransaction;
	}

	@Override
	public FinTransaction updateImpl(FinTransaction finTransaction) {
		boolean isNew = finTransaction.isNew();

		if (!(finTransaction instanceof FinTransactionModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(finTransaction.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					finTransaction);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in finTransaction proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FinTransaction implementation " +
					finTransaction.getClass());
		}

		FinTransactionModelImpl finTransactionModelImpl =
			(FinTransactionModelImpl)finTransaction;

		if (Validator.isNull(finTransaction.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			finTransaction.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (finTransaction.getCreateDate() == null)) {
			if (serviceContext == null) {
				finTransaction.setCreateDate(date);
			}
			else {
				finTransaction.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!finTransactionModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				finTransaction.setModifiedDate(date);
			}
			else {
				finTransaction.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(finTransaction);
			}
			else {
				finTransaction = (FinTransaction)session.merge(finTransaction);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FinTransactionImpl.class, finTransactionModelImpl, false, true);

		cacheUniqueFindersCache(finTransactionModelImpl);

		if (isNew) {
			finTransaction.setNew(false);
		}

		finTransaction.resetOriginalValues();

		return finTransaction;
	}

	/**
	 * Returns the fin transaction with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the fin transaction
	 * @return the fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	@Override
	public FinTransaction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFinTransactionException {

		FinTransaction finTransaction = fetchByPrimaryKey(primaryKey);

		if (finTransaction == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFinTransactionException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return finTransaction;
	}

	/**
	 * Returns the fin transaction with the primary key or throws a <code>NoSuchFinTransactionException</code> if it could not be found.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction
	 * @throws NoSuchFinTransactionException if a fin transaction with the primary key could not be found
	 */
	@Override
	public FinTransaction findByPrimaryKey(long transactionId)
		throws NoSuchFinTransactionException {

		return findByPrimaryKey((Serializable)transactionId);
	}

	/**
	 * Returns the fin transaction with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction, or <code>null</code> if a fin transaction with the primary key could not be found
	 */
	@Override
	public FinTransaction fetchByPrimaryKey(long transactionId) {
		return fetchByPrimaryKey((Serializable)transactionId);
	}

	/**
	 * Returns all the fin transactions.
	 *
	 * @return the fin transactions
	 */
	@Override
	public List<FinTransaction> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinTransaction> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<FinTransaction> findAll(
		int start, int end,
		OrderByComparator<FinTransaction> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<FinTransaction> findAll(
		int start, int end, OrderByComparator<FinTransaction> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<FinTransaction> list = null;

		if (useFinderCache) {
			list = (List<FinTransaction>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FINTRANSACTION);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FINTRANSACTION;

				sql = sql.concat(FinTransactionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FinTransaction>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the fin transactions from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FinTransaction finTransaction : findAll()) {
			remove(finTransaction);
		}
	}

	/**
	 * Returns the number of fin transactions.
	 *
	 * @return the number of fin transactions
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FINTRANSACTION);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "transactionId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FINTRANSACTION;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FinTransactionModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the fin transaction persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathFetchByUUID_G = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "groupId"}, true);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByGroupId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"groupId"}, true);

		_finderPathWithoutPaginationFindByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			true);

		_finderPathCountByGroupId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()}, new String[] {"groupId"},
			false);

		_finderPathWithPaginationFindByG_A = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_A",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "accountId"}, true);

		_finderPathWithoutPaginationFindByG_A = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_A",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "accountId"}, true);

		_finderPathCountByG_A = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_A",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "accountId"}, false);

		FinTransactionUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		FinTransactionUtil.setPersistence(null);

		entityCache.removeCache(FinTransactionImpl.class.getName());
	}

	@Override
	@Reference(
		target = FinServicesPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = FinServicesPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = FinServicesPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_FINTRANSACTION =
		"SELECT finTransaction FROM FinTransaction finTransaction";

	private static final String _SQL_SELECT_FINTRANSACTION_WHERE =
		"SELECT finTransaction FROM FinTransaction finTransaction WHERE ";

	private static final String _SQL_COUNT_FINTRANSACTION =
		"SELECT COUNT(finTransaction) FROM FinTransaction finTransaction";

	private static final String _SQL_COUNT_FINTRANSACTION_WHERE =
		"SELECT COUNT(finTransaction) FROM FinTransaction finTransaction WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"finTransaction.transactionId";

	private static final String _FILTER_SQL_SELECT_FINTRANSACTION_WHERE =
		"SELECT DISTINCT {finTransaction.*} FROM FinServices_FinTransaction finTransaction WHERE ";

	private static final String
		_FILTER_SQL_SELECT_FINTRANSACTION_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {FinServices_FinTransaction.*} FROM (SELECT DISTINCT finTransaction.transactionId FROM FinServices_FinTransaction finTransaction WHERE ";

	private static final String
		_FILTER_SQL_SELECT_FINTRANSACTION_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN FinServices_FinTransaction ON TEMP_TABLE.transactionId = FinServices_FinTransaction.transactionId";

	private static final String _FILTER_SQL_COUNT_FINTRANSACTION_WHERE =
		"SELECT COUNT(DISTINCT finTransaction.transactionId) AS COUNT_VALUE FROM FinServices_FinTransaction finTransaction WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "finTransaction";

	private static final String _FILTER_ENTITY_TABLE =
		"FinServices_FinTransaction";

	private static final String _ORDER_BY_ENTITY_ALIAS = "finTransaction.";

	private static final String _ORDER_BY_ENTITY_TABLE =
		"FinServices_FinTransaction.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FinTransaction exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FinTransaction exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FinTransactionPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}