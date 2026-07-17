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

import fin.services.exception.NoSuchFinAccountException;
import fin.services.model.FinAccount;
import fin.services.model.FinAccountTable;
import fin.services.model.impl.FinAccountImpl;
import fin.services.model.impl.FinAccountModelImpl;
import fin.services.service.persistence.FinAccountPersistence;
import fin.services.service.persistence.FinAccountUtil;
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
 * The persistence implementation for the fin account service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = FinAccountPersistence.class)
public class FinAccountPersistenceImpl
	extends BasePersistenceImpl<FinAccount> implements FinAccountPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FinAccountUtil</code> to access the fin account persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FinAccountImpl.class.getName();

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
	 * Returns all the fin accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching fin accounts
	 */
	@Override
	public List<FinAccount> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinAccount> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
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
	@Override
	public List<FinAccount> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
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
	@Override
	public List<FinAccount> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<FinAccount> orderByComparator,
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

		List<FinAccount> list = null;

		if (useFinderCache) {
			list = (List<FinAccount>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FinAccount finAccount : list) {
					if (!uuid.equals(finAccount.getUuid())) {
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

			sb.append(_SQL_SELECT_FINACCOUNT_WHERE);

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
				sb.append(FinAccountModelImpl.ORDER_BY_JPQL);
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

				list = (List<FinAccount>)QueryUtil.list(
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
	 * Returns the first fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	@Override
	public FinAccount findByUuid_First(
			String uuid, OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByUuid_First(uuid, orderByComparator);

		if (finAccount != null) {
			return finAccount;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFinAccountException(sb.toString());
	}

	/**
	 * Returns the first fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByUuid_First(
		String uuid, OrderByComparator<FinAccount> orderByComparator) {

		List<FinAccount> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	@Override
	public FinAccount findByUuid_Last(
			String uuid, OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByUuid_Last(uuid, orderByComparator);

		if (finAccount != null) {
			return finAccount;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchFinAccountException(sb.toString());
	}

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByUuid_Last(
		String uuid, OrderByComparator<FinAccount> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<FinAccount> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinAccount[] findByUuid_PrevAndNext(
			long accountId, String uuid,
			OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		uuid = Objects.toString(uuid, "");

		FinAccount finAccount = findByPrimaryKey(accountId);

		Session session = null;

		try {
			session = openSession();

			FinAccount[] array = new FinAccountImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, finAccount, uuid, orderByComparator, true);

			array[1] = finAccount;

			array[2] = getByUuid_PrevAndNext(
				session, finAccount, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinAccount getByUuid_PrevAndNext(
		Session session, FinAccount finAccount, String uuid,
		OrderByComparator<FinAccount> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FINACCOUNT_WHERE);

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
			sb.append(FinAccountModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(finAccount)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinAccount> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fin accounts where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (FinAccount finAccount :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(finAccount);
		}
	}

	/**
	 * Returns the number of fin accounts where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching fin accounts
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FINACCOUNT_WHERE);

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
		"finAccount.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(finAccount.uuid IS NULL OR finAccount.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;

	/**
	 * Returns the fin account where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchFinAccountException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	@Override
	public FinAccount findByUUID_G(String uuid, long groupId)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByUUID_G(uuid, groupId);

		if (finAccount == null) {
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

			throw new NoSuchFinAccountException(sb.toString());
		}

		return finAccount;
	}

	/**
	 * Returns the fin account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the fin account where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByUUID_G(
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

		if (result instanceof FinAccount) {
			FinAccount finAccount = (FinAccount)result;

			if (!Objects.equals(uuid, finAccount.getUuid()) ||
				(groupId != finAccount.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FINACCOUNT_WHERE);

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

				List<FinAccount> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					FinAccount finAccount = list.get(0);

					result = finAccount;

					cacheResult(finAccount);
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
			return (FinAccount)result;
		}
	}

	/**
	 * Removes the fin account where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the fin account that was removed
	 */
	@Override
	public FinAccount removeByUUID_G(String uuid, long groupId)
		throws NoSuchFinAccountException {

		FinAccount finAccount = findByUUID_G(uuid, groupId);

		return remove(finAccount);
	}

	/**
	 * Returns the number of fin accounts where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching fin accounts
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinAccount finAccount = fetchByUUID_G(uuid, groupId);

		if (finAccount == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"finAccount.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(finAccount.uuid IS NULL OR finAccount.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"finAccount.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the fin accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching fin accounts
	 */
	@Override
	public List<FinAccount> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinAccount> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
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
	@Override
	public List<FinAccount> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
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
	@Override
	public List<FinAccount> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator,
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

		List<FinAccount> list = null;

		if (useFinderCache) {
			list = (List<FinAccount>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FinAccount finAccount : list) {
					if (!uuid.equals(finAccount.getUuid()) ||
						(companyId != finAccount.getCompanyId())) {

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

			sb.append(_SQL_SELECT_FINACCOUNT_WHERE);

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
				sb.append(FinAccountModelImpl.ORDER_BY_JPQL);
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

				list = (List<FinAccount>)QueryUtil.list(
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
	 * Returns the first fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	@Override
	public FinAccount findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (finAccount != null) {
			return finAccount;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFinAccountException(sb.toString());
	}

	/**
	 * Returns the first fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<FinAccount> orderByComparator) {

		List<FinAccount> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinAccount findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (finAccount != null) {
			return finAccount;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchFinAccountException(sb.toString());
	}

	/**
	 * Returns the last fin account in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<FinAccount> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<FinAccount> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinAccount[] findByUuid_C_PrevAndNext(
			long accountId, String uuid, long companyId,
			OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		uuid = Objects.toString(uuid, "");

		FinAccount finAccount = findByPrimaryKey(accountId);

		Session session = null;

		try {
			session = openSession();

			FinAccount[] array = new FinAccountImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, finAccount, uuid, companyId, orderByComparator, true);

			array[1] = finAccount;

			array[2] = getByUuid_C_PrevAndNext(
				session, finAccount, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinAccount getByUuid_C_PrevAndNext(
		Session session, FinAccount finAccount, String uuid, long companyId,
		OrderByComparator<FinAccount> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_FINACCOUNT_WHERE);

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
			sb.append(FinAccountModelImpl.ORDER_BY_JPQL);
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
					orderByComparator.getOrderByConditionValues(finAccount)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinAccount> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fin accounts where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (FinAccount finAccount :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(finAccount);
		}
	}

	/**
	 * Returns the number of fin accounts where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching fin accounts
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FINACCOUNT_WHERE);

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
		"finAccount.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(finAccount.uuid IS NULL OR finAccount.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"finAccount.companyId = ?";

	private FinderPath _finderPathWithPaginationFindByGroupId;
	private FinderPath _finderPathWithoutPaginationFindByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns all the fin accounts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin accounts
	 */
	@Override
	public List<FinAccount> findByGroupId(long groupId) {
		return findByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinAccount> findByGroupId(long groupId, int start, int end) {
		return findByGroupId(groupId, start, end, null);
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
	@Override
	public List<FinAccount> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return findByGroupId(groupId, start, end, orderByComparator, true);
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
	@Override
	public List<FinAccount> findByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator,
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

		List<FinAccount> list = null;

		if (useFinderCache) {
			list = (List<FinAccount>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FinAccount finAccount : list) {
					if (groupId != finAccount.getGroupId()) {
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

			sb.append(_SQL_SELECT_FINACCOUNT_WHERE);

			sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FinAccountModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				list = (List<FinAccount>)QueryUtil.list(
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
	 * Returns the first fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	@Override
	public FinAccount findByGroupId_First(
			long groupId, OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByGroupId_First(
			groupId, orderByComparator);

		if (finAccount != null) {
			return finAccount;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchFinAccountException(sb.toString());
	}

	/**
	 * Returns the first fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByGroupId_First(
		long groupId, OrderByComparator<FinAccount> orderByComparator) {

		List<FinAccount> list = findByGroupId(groupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	@Override
	public FinAccount findByGroupId_Last(
			long groupId, OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByGroupId_Last(groupId, orderByComparator);

		if (finAccount != null) {
			return finAccount;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append("}");

		throw new NoSuchFinAccountException(sb.toString());
	}

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByGroupId_Last(
		long groupId, OrderByComparator<FinAccount> orderByComparator) {

		int count = countByGroupId(groupId);

		if (count == 0) {
			return null;
		}

		List<FinAccount> list = findByGroupId(
			groupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinAccount[] findByGroupId_PrevAndNext(
			long accountId, long groupId,
			OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		FinAccount finAccount = findByPrimaryKey(accountId);

		Session session = null;

		try {
			session = openSession();

			FinAccount[] array = new FinAccountImpl[3];

			array[0] = getByGroupId_PrevAndNext(
				session, finAccount, groupId, orderByComparator, true);

			array[1] = finAccount;

			array[2] = getByGroupId_PrevAndNext(
				session, finAccount, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinAccount getByGroupId_PrevAndNext(
		Session session, FinAccount finAccount, long groupId,
		OrderByComparator<FinAccount> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FINACCOUNT_WHERE);

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
			sb.append(FinAccountModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(finAccount)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinAccount> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the fin accounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the matching fin accounts that the user has permission to view
	 */
	@Override
	public List<FinAccount> filterFindByGroupId(long groupId) {
		return filterFindByGroupId(
			groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinAccount> filterFindByGroupId(
		long groupId, int start, int end) {

		return filterFindByGroupId(groupId, start, end, null);
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
	@Override
	public List<FinAccount> filterFindByGroupId(
		long groupId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

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
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_WHERE);
		}
		else {
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(FinAccountModelImpl.ORDER_BY_SQL_INLINE_DISTINCT);
			}
			else {
				sb.append(FinAccountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinAccount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, FinAccountImpl.class);
			}
			else {
				sqlQuery.addEntity(_FILTER_ENTITY_TABLE, FinAccountImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);

			return (List<FinAccount>)QueryUtil.list(
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
	 * Returns the fin accounts before and after the current fin account in the ordered set of fin accounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param groupId the group ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	@Override
	public FinAccount[] filterFindByGroupId_PrevAndNext(
			long accountId, long groupId,
			OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByGroupId_PrevAndNext(
				accountId, groupId, orderByComparator);
		}

		FinAccount finAccount = findByPrimaryKey(accountId);

		Session session = null;

		try {
			session = openSession();

			FinAccount[] array = new FinAccountImpl[3];

			array[0] = filterGetByGroupId_PrevAndNext(
				session, finAccount, groupId, orderByComparator, true);

			array[1] = finAccount;

			array[2] = filterGetByGroupId_PrevAndNext(
				session, finAccount, groupId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FinAccount filterGetByGroupId_PrevAndNext(
		Session session, FinAccount finAccount, long groupId,
		OrderByComparator<FinAccount> orderByComparator, boolean previous) {

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
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_WHERE);
		}
		else {
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(FinAccountModelImpl.ORDER_BY_SQL_INLINE_DISTINCT);
			}
			else {
				sb.append(FinAccountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinAccount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, FinAccountImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, FinAccountImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(groupId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(finAccount)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinAccount> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fin accounts where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 */
	@Override
	public void removeByGroupId(long groupId) {
		for (FinAccount finAccount :
				findByGroupId(
					groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(finAccount);
		}
	}

	/**
	 * Returns the number of fin accounts where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin accounts
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FINACCOUNT_WHERE);

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
	 * Returns the number of fin accounts that the user has permission to view where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching fin accounts that the user has permission to view
	 */
	@Override
	public int filterCountByGroupId(long groupId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByGroupId(groupId);
		}

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_FINACCOUNT_WHERE);

		sb.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinAccount.class.getName(),
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
		"finAccount.groupId = ?";

	private FinderPath _finderPathFetchByG_A;

	/**
	 * Returns the fin account where groupId = &#63; and accountNumber = &#63; or throws a <code>NoSuchFinAccountException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	@Override
	public FinAccount findByG_A(long groupId, String accountNumber)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByG_A(groupId, accountNumber);

		if (finAccount == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("groupId=");
			sb.append(groupId);

			sb.append(", accountNumber=");
			sb.append(accountNumber);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFinAccountException(sb.toString());
		}

		return finAccount;
	}

	/**
	 * Returns the fin account where groupId = &#63; and accountNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByG_A(long groupId, String accountNumber) {
		return fetchByG_A(groupId, accountNumber, true);
	}

	/**
	 * Returns the fin account where groupId = &#63; and accountNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByG_A(
		long groupId, String accountNumber, boolean useFinderCache) {

		accountNumber = Objects.toString(accountNumber, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {groupId, accountNumber};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByG_A, finderArgs, this);
		}

		if (result instanceof FinAccount) {
			FinAccount finAccount = (FinAccount)result;

			if ((groupId != finAccount.getGroupId()) ||
				!Objects.equals(accountNumber, finAccount.getAccountNumber())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FINACCOUNT_WHERE);

			sb.append(_FINDER_COLUMN_G_A_GROUPID_2);

			boolean bindAccountNumber = false;

			if (accountNumber.isEmpty()) {
				sb.append(_FINDER_COLUMN_G_A_ACCOUNTNUMBER_3);
			}
			else {
				bindAccountNumber = true;

				sb.append(_FINDER_COLUMN_G_A_ACCOUNTNUMBER_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				if (bindAccountNumber) {
					queryPos.add(accountNumber);
				}

				List<FinAccount> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByG_A, finderArgs, list);
					}
				}
				else {
					FinAccount finAccount = list.get(0);

					result = finAccount;

					cacheResult(finAccount);
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
			return (FinAccount)result;
		}
	}

	/**
	 * Removes the fin account where groupId = &#63; and accountNumber = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the fin account that was removed
	 */
	@Override
	public FinAccount removeByG_A(long groupId, String accountNumber)
		throws NoSuchFinAccountException {

		FinAccount finAccount = findByG_A(groupId, accountNumber);

		return remove(finAccount);
	}

	/**
	 * Returns the number of fin accounts where groupId = &#63; and accountNumber = &#63;.
	 *
	 * @param groupId the group ID
	 * @param accountNumber the account number
	 * @return the number of matching fin accounts
	 */
	@Override
	public int countByG_A(long groupId, String accountNumber) {
		FinAccount finAccount = fetchByG_A(groupId, accountNumber);

		if (finAccount == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_G_A_GROUPID_2 =
		"finAccount.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_A_ACCOUNTNUMBER_2 =
		"finAccount.accountNumber = ?";

	private static final String _FINDER_COLUMN_G_A_ACCOUNTNUMBER_3 =
		"(finAccount.accountNumber IS NULL OR finAccount.accountNumber = '')";

	private FinderPath _finderPathWithPaginationFindByG_O;
	private FinderPath _finderPathWithoutPaginationFindByG_O;
	private FinderPath _finderPathCountByG_O;

	/**
	 * Returns all the fin accounts where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the matching fin accounts
	 */
	@Override
	public List<FinAccount> findByG_O(long groupId, long ownerUserId) {
		return findByG_O(
			groupId, ownerUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinAccount> findByG_O(
		long groupId, long ownerUserId, int start, int end) {

		return findByG_O(groupId, ownerUserId, start, end, null);
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
	@Override
	public List<FinAccount> findByG_O(
		long groupId, long ownerUserId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		return findByG_O(
			groupId, ownerUserId, start, end, orderByComparator, true);
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
	@Override
	public List<FinAccount> findByG_O(
		long groupId, long ownerUserId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByG_O;
				finderArgs = new Object[] {groupId, ownerUserId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByG_O;
			finderArgs = new Object[] {
				groupId, ownerUserId, start, end, orderByComparator
			};
		}

		List<FinAccount> list = null;

		if (useFinderCache) {
			list = (List<FinAccount>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FinAccount finAccount : list) {
					if ((groupId != finAccount.getGroupId()) ||
						(ownerUserId != finAccount.getOwnerUserId())) {

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

			sb.append(_SQL_SELECT_FINACCOUNT_WHERE);

			sb.append(_FINDER_COLUMN_G_O_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_O_OWNERUSERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FinAccountModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(ownerUserId);

				list = (List<FinAccount>)QueryUtil.list(
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
	 * Returns the first fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account
	 * @throws NoSuchFinAccountException if a matching fin account could not be found
	 */
	@Override
	public FinAccount findByG_O_First(
			long groupId, long ownerUserId,
			OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByG_O_First(
			groupId, ownerUserId, orderByComparator);

		if (finAccount != null) {
			return finAccount;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", ownerUserId=");
		sb.append(ownerUserId);

		sb.append("}");

		throw new NoSuchFinAccountException(sb.toString());
	}

	/**
	 * Returns the first fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByG_O_First(
		long groupId, long ownerUserId,
		OrderByComparator<FinAccount> orderByComparator) {

		List<FinAccount> list = findByG_O(
			groupId, ownerUserId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinAccount findByG_O_Last(
			long groupId, long ownerUserId,
			OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByG_O_Last(
			groupId, ownerUserId, orderByComparator);

		if (finAccount != null) {
			return finAccount;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("groupId=");
		sb.append(groupId);

		sb.append(", ownerUserId=");
		sb.append(ownerUserId);

		sb.append("}");

		throw new NoSuchFinAccountException(sb.toString());
	}

	/**
	 * Returns the last fin account in the ordered set where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fin account, or <code>null</code> if a matching fin account could not be found
	 */
	@Override
	public FinAccount fetchByG_O_Last(
		long groupId, long ownerUserId,
		OrderByComparator<FinAccount> orderByComparator) {

		int count = countByG_O(groupId, ownerUserId);

		if (count == 0) {
			return null;
		}

		List<FinAccount> list = findByG_O(
			groupId, ownerUserId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FinAccount[] findByG_O_PrevAndNext(
			long accountId, long groupId, long ownerUserId,
			OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		FinAccount finAccount = findByPrimaryKey(accountId);

		Session session = null;

		try {
			session = openSession();

			FinAccount[] array = new FinAccountImpl[3];

			array[0] = getByG_O_PrevAndNext(
				session, finAccount, groupId, ownerUserId, orderByComparator,
				true);

			array[1] = finAccount;

			array[2] = getByG_O_PrevAndNext(
				session, finAccount, groupId, ownerUserId, orderByComparator,
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

	protected FinAccount getByG_O_PrevAndNext(
		Session session, FinAccount finAccount, long groupId, long ownerUserId,
		OrderByComparator<FinAccount> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		sb.append(_SQL_SELECT_FINACCOUNT_WHERE);

		sb.append(_FINDER_COLUMN_G_O_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_O_OWNERUSERID_2);

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
			sb.append(FinAccountModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(groupId);

		queryPos.add(ownerUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(finAccount)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinAccount> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the fin accounts that the user has permission to view where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the matching fin accounts that the user has permission to view
	 */
	@Override
	public List<FinAccount> filterFindByG_O(long groupId, long ownerUserId) {
		return filterFindByG_O(
			groupId, ownerUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinAccount> filterFindByG_O(
		long groupId, long ownerUserId, int start, int end) {

		return filterFindByG_O(groupId, ownerUserId, start, end, null);
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
	@Override
	public List<FinAccount> filterFindByG_O(
		long groupId, long ownerUserId, int start, int end,
		OrderByComparator<FinAccount> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_O(
				groupId, ownerUserId, start, end, orderByComparator);
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
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_WHERE);
		}
		else {
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_G_O_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_O_OWNERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(FinAccountModelImpl.ORDER_BY_SQL_INLINE_DISTINCT);
			}
			else {
				sb.append(FinAccountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinAccount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, FinAccountImpl.class);
			}
			else {
				sqlQuery.addEntity(_FILTER_ENTITY_TABLE, FinAccountImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);

			queryPos.add(ownerUserId);

			return (List<FinAccount>)QueryUtil.list(
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
	 * Returns the fin accounts before and after the current fin account in the ordered set of fin accounts that the user has permission to view where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param accountId the primary key of the current fin account
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	@Override
	public FinAccount[] filterFindByG_O_PrevAndNext(
			long accountId, long groupId, long ownerUserId,
			OrderByComparator<FinAccount> orderByComparator)
		throws NoSuchFinAccountException {

		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_O_PrevAndNext(
				accountId, groupId, ownerUserId, orderByComparator);
		}

		FinAccount finAccount = findByPrimaryKey(accountId);

		Session session = null;

		try {
			session = openSession();

			FinAccount[] array = new FinAccountImpl[3];

			array[0] = filterGetByG_O_PrevAndNext(
				session, finAccount, groupId, ownerUserId, orderByComparator,
				true);

			array[1] = finAccount;

			array[2] = filterGetByG_O_PrevAndNext(
				session, finAccount, groupId, ownerUserId, orderByComparator,
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

	protected FinAccount filterGetByG_O_PrevAndNext(
		Session session, FinAccount finAccount, long groupId, long ownerUserId,
		OrderByComparator<FinAccount> orderByComparator, boolean previous) {

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
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_WHERE);
		}
		else {
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_G_O_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_O_OWNERUSERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_FINACCOUNT_NO_INLINE_DISTINCT_WHERE_2);
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
				sb.append(FinAccountModelImpl.ORDER_BY_SQL_INLINE_DISTINCT);
			}
			else {
				sb.append(FinAccountModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinAccount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(_FILTER_ENTITY_ALIAS, FinAccountImpl.class);
		}
		else {
			sqlQuery.addEntity(_FILTER_ENTITY_TABLE, FinAccountImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(groupId);

		queryPos.add(ownerUserId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(finAccount)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FinAccount> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fin accounts where groupId = &#63; and ownerUserId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 */
	@Override
	public void removeByG_O(long groupId, long ownerUserId) {
		for (FinAccount finAccount :
				findByG_O(
					groupId, ownerUserId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(finAccount);
		}
	}

	/**
	 * Returns the number of fin accounts where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the number of matching fin accounts
	 */
	@Override
	public int countByG_O(long groupId, long ownerUserId) {
		FinderPath finderPath = _finderPathCountByG_O;

		Object[] finderArgs = new Object[] {groupId, ownerUserId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FINACCOUNT_WHERE);

			sb.append(_FINDER_COLUMN_G_O_GROUPID_2);

			sb.append(_FINDER_COLUMN_G_O_OWNERUSERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(groupId);

				queryPos.add(ownerUserId);

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
	 * Returns the number of fin accounts that the user has permission to view where groupId = &#63; and ownerUserId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param ownerUserId the owner user ID
	 * @return the number of matching fin accounts that the user has permission to view
	 */
	@Override
	public int filterCountByG_O(long groupId, long ownerUserId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_O(groupId, ownerUserId);
		}

		StringBundler sb = new StringBundler(3);

		sb.append(_FILTER_SQL_COUNT_FINACCOUNT_WHERE);

		sb.append(_FINDER_COLUMN_G_O_GROUPID_2);

		sb.append(_FINDER_COLUMN_G_O_OWNERUSERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FinAccount.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);

			queryPos.add(ownerUserId);

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

	private static final String _FINDER_COLUMN_G_O_GROUPID_2 =
		"finAccount.groupId = ? AND ";

	private static final String _FINDER_COLUMN_G_O_OWNERUSERID_2 =
		"finAccount.ownerUserId = ?";

	public FinAccountPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");

		setDBColumnNames(dbColumnNames);

		setModelClass(FinAccount.class);

		setModelImplClass(FinAccountImpl.class);
		setModelPKClass(long.class);

		setTable(FinAccountTable.INSTANCE);
	}

	/**
	 * Caches the fin account in the entity cache if it is enabled.
	 *
	 * @param finAccount the fin account
	 */
	@Override
	public void cacheResult(FinAccount finAccount) {
		entityCache.putResult(
			FinAccountImpl.class, finAccount.getPrimaryKey(), finAccount);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {finAccount.getUuid(), finAccount.getGroupId()},
			finAccount);

		finderCache.putResult(
			_finderPathFetchByG_A,
			new Object[] {
				finAccount.getGroupId(), finAccount.getAccountNumber()
			},
			finAccount);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the fin accounts in the entity cache if it is enabled.
	 *
	 * @param finAccounts the fin accounts
	 */
	@Override
	public void cacheResult(List<FinAccount> finAccounts) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (finAccounts.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FinAccount finAccount : finAccounts) {
			if (entityCache.getResult(
					FinAccountImpl.class, finAccount.getPrimaryKey()) == null) {

				cacheResult(finAccount);
			}
		}
	}

	/**
	 * Clears the cache for all fin accounts.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FinAccountImpl.class);

		finderCache.clearCache(FinAccountImpl.class);
	}

	/**
	 * Clears the cache for the fin account.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FinAccount finAccount) {
		entityCache.removeResult(FinAccountImpl.class, finAccount);
	}

	@Override
	public void clearCache(List<FinAccount> finAccounts) {
		for (FinAccount finAccount : finAccounts) {
			entityCache.removeResult(FinAccountImpl.class, finAccount);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FinAccountImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FinAccountImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		FinAccountModelImpl finAccountModelImpl) {

		Object[] args = new Object[] {
			finAccountModelImpl.getUuid(), finAccountModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathFetchByUUID_G, args, finAccountModelImpl);

		args = new Object[] {
			finAccountModelImpl.getGroupId(),
			finAccountModelImpl.getAccountNumber()
		};

		finderCache.putResult(_finderPathFetchByG_A, args, finAccountModelImpl);
	}

	/**
	 * Creates a new fin account with the primary key. Does not add the fin account to the database.
	 *
	 * @param accountId the primary key for the new fin account
	 * @return the new fin account
	 */
	@Override
	public FinAccount create(long accountId) {
		FinAccount finAccount = new FinAccountImpl();

		finAccount.setNew(true);
		finAccount.setPrimaryKey(accountId);

		String uuid = PortalUUIDUtil.generate();

		finAccount.setUuid(uuid);

		finAccount.setCompanyId(CompanyThreadLocal.getCompanyId());

		return finAccount;
	}

	/**
	 * Removes the fin account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account that was removed
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	@Override
	public FinAccount remove(long accountId) throws NoSuchFinAccountException {
		return remove((Serializable)accountId);
	}

	/**
	 * Removes the fin account with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the fin account
	 * @return the fin account that was removed
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	@Override
	public FinAccount remove(Serializable primaryKey)
		throws NoSuchFinAccountException {

		Session session = null;

		try {
			session = openSession();

			FinAccount finAccount = (FinAccount)session.get(
				FinAccountImpl.class, primaryKey);

			if (finAccount == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFinAccountException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(finAccount);
		}
		catch (NoSuchFinAccountException noSuchEntityException) {
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
	protected FinAccount removeImpl(FinAccount finAccount) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(finAccount)) {
				finAccount = (FinAccount)session.get(
					FinAccountImpl.class, finAccount.getPrimaryKeyObj());
			}

			if (finAccount != null) {
				session.delete(finAccount);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (finAccount != null) {
			clearCache(finAccount);
		}

		return finAccount;
	}

	@Override
	public FinAccount updateImpl(FinAccount finAccount) {
		boolean isNew = finAccount.isNew();

		if (!(finAccount instanceof FinAccountModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(finAccount.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(finAccount);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in finAccount proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FinAccount implementation " +
					finAccount.getClass());
		}

		FinAccountModelImpl finAccountModelImpl =
			(FinAccountModelImpl)finAccount;

		if (Validator.isNull(finAccount.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			finAccount.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (finAccount.getCreateDate() == null)) {
			if (serviceContext == null) {
				finAccount.setCreateDate(date);
			}
			else {
				finAccount.setCreateDate(serviceContext.getCreateDate(date));
			}
		}

		if (!finAccountModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				finAccount.setModifiedDate(date);
			}
			else {
				finAccount.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(finAccount);
			}
			else {
				finAccount = (FinAccount)session.merge(finAccount);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FinAccountImpl.class, finAccountModelImpl, false, true);

		cacheUniqueFindersCache(finAccountModelImpl);

		if (isNew) {
			finAccount.setNew(false);
		}

		finAccount.resetOriginalValues();

		return finAccount;
	}

	/**
	 * Returns the fin account with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the fin account
	 * @return the fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	@Override
	public FinAccount findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFinAccountException {

		FinAccount finAccount = fetchByPrimaryKey(primaryKey);

		if (finAccount == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFinAccountException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return finAccount;
	}

	/**
	 * Returns the fin account with the primary key or throws a <code>NoSuchFinAccountException</code> if it could not be found.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account
	 * @throws NoSuchFinAccountException if a fin account with the primary key could not be found
	 */
	@Override
	public FinAccount findByPrimaryKey(long accountId)
		throws NoSuchFinAccountException {

		return findByPrimaryKey((Serializable)accountId);
	}

	/**
	 * Returns the fin account with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountId the primary key of the fin account
	 * @return the fin account, or <code>null</code> if a fin account with the primary key could not be found
	 */
	@Override
	public FinAccount fetchByPrimaryKey(long accountId) {
		return fetchByPrimaryKey((Serializable)accountId);
	}

	/**
	 * Returns all the fin accounts.
	 *
	 * @return the fin accounts
	 */
	@Override
	public List<FinAccount> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FinAccount> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<FinAccount> findAll(
		int start, int end, OrderByComparator<FinAccount> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<FinAccount> findAll(
		int start, int end, OrderByComparator<FinAccount> orderByComparator,
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

		List<FinAccount> list = null;

		if (useFinderCache) {
			list = (List<FinAccount>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FINACCOUNT);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FINACCOUNT;

				sql = sql.concat(FinAccountModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FinAccount>)QueryUtil.list(
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
	 * Removes all the fin accounts from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FinAccount finAccount : findAll()) {
			remove(finAccount);
		}
	}

	/**
	 * Returns the number of fin accounts.
	 *
	 * @return the number of fin accounts
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FINACCOUNT);

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
		return "accountId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FINACCOUNT;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FinAccountModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the fin account persistence.
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

		_finderPathFetchByG_A = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByG_A",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"groupId", "accountNumber"}, true);

		_finderPathWithPaginationFindByG_O = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_O",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"groupId", "ownerUserId"}, true);

		_finderPathWithoutPaginationFindByG_O = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_O",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "ownerUserId"}, true);

		_finderPathCountByG_O = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_O",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"groupId", "ownerUserId"}, false);

		FinAccountUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		FinAccountUtil.setPersistence(null);

		entityCache.removeCache(FinAccountImpl.class.getName());
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

	private static final String _SQL_SELECT_FINACCOUNT =
		"SELECT finAccount FROM FinAccount finAccount";

	private static final String _SQL_SELECT_FINACCOUNT_WHERE =
		"SELECT finAccount FROM FinAccount finAccount WHERE ";

	private static final String _SQL_COUNT_FINACCOUNT =
		"SELECT COUNT(finAccount) FROM FinAccount finAccount";

	private static final String _SQL_COUNT_FINACCOUNT_WHERE =
		"SELECT COUNT(finAccount) FROM FinAccount finAccount WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"finAccount.accountId";

	private static final String _FILTER_SQL_SELECT_FINACCOUNT_WHERE =
		"SELECT DISTINCT {finAccount.*} FROM FinServices_FinAccount finAccount WHERE ";

	private static final String
		_FILTER_SQL_SELECT_FINACCOUNT_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {FinServices_FinAccount.*} FROM (SELECT DISTINCT finAccount.accountId FROM FinServices_FinAccount finAccount WHERE ";

	private static final String
		_FILTER_SQL_SELECT_FINACCOUNT_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN FinServices_FinAccount ON TEMP_TABLE.accountId = FinServices_FinAccount.accountId";

	private static final String _FILTER_SQL_COUNT_FINACCOUNT_WHERE =
		"SELECT COUNT(DISTINCT finAccount.accountId) AS COUNT_VALUE FROM FinServices_FinAccount finAccount WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "finAccount";

	private static final String _FILTER_ENTITY_TABLE = "FinServices_FinAccount";

	private static final String _ORDER_BY_ENTITY_ALIAS = "finAccount.";

	private static final String _ORDER_BY_ENTITY_TABLE =
		"FinServices_FinAccount.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FinAccount exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FinAccount exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FinAccountPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}