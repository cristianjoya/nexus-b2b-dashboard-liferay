/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.security.permission.SimplePermissionChecker;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import fin.services.exception.NoSuchFinTransactionException;
import fin.services.model.FinTransaction;
import fin.services.service.FinTransactionLocalServiceUtil;
import fin.services.service.persistence.FinTransactionPersistence;
import fin.services.service.persistence.FinTransactionUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class FinTransactionPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "fin.services.service"));

	@Before
	public void setUp() {
		_persistence = FinTransactionUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FinTransaction> iterator = _finTransactions.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinTransaction finTransaction = _persistence.create(pk);

		Assert.assertNotNull(finTransaction);

		Assert.assertEquals(finTransaction.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FinTransaction newFinTransaction = addFinTransaction();

		_persistence.remove(newFinTransaction);

		FinTransaction existingFinTransaction = _persistence.fetchByPrimaryKey(
			newFinTransaction.getPrimaryKey());

		Assert.assertNull(existingFinTransaction);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFinTransaction();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinTransaction newFinTransaction = _persistence.create(pk);

		newFinTransaction.setUuid(RandomTestUtil.randomString());

		newFinTransaction.setGroupId(RandomTestUtil.nextLong());

		newFinTransaction.setCompanyId(RandomTestUtil.nextLong());

		newFinTransaction.setUserId(RandomTestUtil.nextLong());

		newFinTransaction.setUserName(RandomTestUtil.randomString());

		newFinTransaction.setCreateDate(RandomTestUtil.nextDate());

		newFinTransaction.setModifiedDate(RandomTestUtil.nextDate());

		newFinTransaction.setAccountId(RandomTestUtil.nextLong());

		newFinTransaction.setTransactionType(RandomTestUtil.randomString());

		newFinTransaction.setAmount(RandomTestUtil.nextDouble());

		newFinTransaction.setDescription(RandomTestUtil.randomString());

		newFinTransaction.setTransactionDate(RandomTestUtil.nextDate());

		newFinTransaction.setStatus(RandomTestUtil.nextInt());

		_finTransactions.add(_persistence.update(newFinTransaction));

		FinTransaction existingFinTransaction = _persistence.findByPrimaryKey(
			newFinTransaction.getPrimaryKey());

		Assert.assertEquals(
			existingFinTransaction.getUuid(), newFinTransaction.getUuid());
		Assert.assertEquals(
			existingFinTransaction.getTransactionId(),
			newFinTransaction.getTransactionId());
		Assert.assertEquals(
			existingFinTransaction.getGroupId(),
			newFinTransaction.getGroupId());
		Assert.assertEquals(
			existingFinTransaction.getCompanyId(),
			newFinTransaction.getCompanyId());
		Assert.assertEquals(
			existingFinTransaction.getUserId(), newFinTransaction.getUserId());
		Assert.assertEquals(
			existingFinTransaction.getUserName(),
			newFinTransaction.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFinTransaction.getCreateDate()),
			Time.getShortTimestamp(newFinTransaction.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingFinTransaction.getModifiedDate()),
			Time.getShortTimestamp(newFinTransaction.getModifiedDate()));
		Assert.assertEquals(
			existingFinTransaction.getAccountId(),
			newFinTransaction.getAccountId());
		Assert.assertEquals(
			existingFinTransaction.getTransactionType(),
			newFinTransaction.getTransactionType());
		AssertUtils.assertEquals(
			existingFinTransaction.getAmount(), newFinTransaction.getAmount());
		Assert.assertEquals(
			existingFinTransaction.getDescription(),
			newFinTransaction.getDescription());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFinTransaction.getTransactionDate()),
			Time.getShortTimestamp(newFinTransaction.getTransactionDate()));
		Assert.assertEquals(
			existingFinTransaction.getStatus(), newFinTransaction.getStatus());
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUUID_G() throws Exception {
		_persistence.countByUUID_G("", RandomTestUtil.nextLong());

		_persistence.countByUUID_G("null", 0L);

		_persistence.countByUUID_G((String)null, 0L);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByGroupId() throws Exception {
		_persistence.countByGroupId(RandomTestUtil.nextLong());

		_persistence.countByGroupId(0L);
	}

	@Test
	public void testCountByG_A() throws Exception {
		_persistence.countByG_A(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByG_A(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FinTransaction newFinTransaction = addFinTransaction();

		FinTransaction existingFinTransaction = _persistence.findByPrimaryKey(
			newFinTransaction.getPrimaryKey());

		Assert.assertEquals(existingFinTransaction, newFinTransaction);
	}

	@Test(expected = NoSuchFinTransactionException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	@Test
	public void testFilterFindByGroupId() throws Exception {
		PermissionThreadLocal.setPermissionChecker(
			new SimplePermissionChecker() {
				{
					init(TestPropsValues.getUser());
				}

				@Override
				public boolean isCompanyAdmin(long companyId) {
					return false;
				}

			});

		Assert.assertTrue(InlineSQLHelperUtil.isEnabled(0));

		_persistence.filterFindByGroupId(
			0, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		_persistence.filterFindByGroupId(
			0, QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<FinTransaction> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"FinServices_FinTransaction", "uuid", true, "transactionId", true,
			"groupId", true, "companyId", true, "userId", true, "userName",
			true, "createDate", true, "modifiedDate", true, "accountId", true,
			"transactionType", true, "amount", true, "description", true,
			"transactionDate", true, "status", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FinTransaction newFinTransaction = addFinTransaction();

		FinTransaction existingFinTransaction = _persistence.fetchByPrimaryKey(
			newFinTransaction.getPrimaryKey());

		Assert.assertEquals(existingFinTransaction, newFinTransaction);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinTransaction missingFinTransaction = _persistence.fetchByPrimaryKey(
			pk);

		Assert.assertNull(missingFinTransaction);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		FinTransaction newFinTransaction1 = addFinTransaction();
		FinTransaction newFinTransaction2 = addFinTransaction();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinTransaction1.getPrimaryKey());
		primaryKeys.add(newFinTransaction2.getPrimaryKey());

		Map<Serializable, FinTransaction> finTransactions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, finTransactions.size());
		Assert.assertEquals(
			newFinTransaction1,
			finTransactions.get(newFinTransaction1.getPrimaryKey()));
		Assert.assertEquals(
			newFinTransaction2,
			finTransactions.get(newFinTransaction2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FinTransaction> finTransactions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(finTransactions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		FinTransaction newFinTransaction = addFinTransaction();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinTransaction.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FinTransaction> finTransactions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, finTransactions.size());
		Assert.assertEquals(
			newFinTransaction,
			finTransactions.get(newFinTransaction.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FinTransaction> finTransactions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(finTransactions.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		FinTransaction newFinTransaction = addFinTransaction();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinTransaction.getPrimaryKey());

		Map<Serializable, FinTransaction> finTransactions =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, finTransactions.size());
		Assert.assertEquals(
			newFinTransaction,
			finTransactions.get(newFinTransaction.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			FinTransactionLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<FinTransaction>() {

				@Override
				public void performAction(FinTransaction finTransaction) {
					Assert.assertNotNull(finTransaction);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		FinTransaction newFinTransaction = addFinTransaction();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinTransaction.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"transactionId", newFinTransaction.getTransactionId()));

		List<FinTransaction> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		FinTransaction existingFinTransaction = result.get(0);

		Assert.assertEquals(existingFinTransaction, newFinTransaction);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinTransaction.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"transactionId", RandomTestUtil.nextLong()));

		List<FinTransaction> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		FinTransaction newFinTransaction = addFinTransaction();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinTransaction.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("transactionId"));

		Object newTransactionId = newFinTransaction.getTransactionId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"transactionId", new Object[] {newTransactionId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingTransactionId = result.get(0);

		Assert.assertEquals(existingTransactionId, newTransactionId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinTransaction.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("transactionId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"transactionId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		FinTransaction newFinTransaction = addFinTransaction();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newFinTransaction.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		FinTransaction newFinTransaction = addFinTransaction();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinTransaction.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"transactionId", newFinTransaction.getTransactionId()));

		List<FinTransaction> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(FinTransaction finTransaction) {
		Assert.assertEquals(
			finTransaction.getUuid(),
			ReflectionTestUtil.invoke(
				finTransaction, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(finTransaction.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				finTransaction, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
	}

	protected FinTransaction addFinTransaction() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinTransaction finTransaction = _persistence.create(pk);

		finTransaction.setUuid(RandomTestUtil.randomString());

		finTransaction.setGroupId(RandomTestUtil.nextLong());

		finTransaction.setCompanyId(RandomTestUtil.nextLong());

		finTransaction.setUserId(RandomTestUtil.nextLong());

		finTransaction.setUserName(RandomTestUtil.randomString());

		finTransaction.setCreateDate(RandomTestUtil.nextDate());

		finTransaction.setModifiedDate(RandomTestUtil.nextDate());

		finTransaction.setAccountId(RandomTestUtil.nextLong());

		finTransaction.setTransactionType(RandomTestUtil.randomString());

		finTransaction.setAmount(RandomTestUtil.nextDouble());

		finTransaction.setDescription(RandomTestUtil.randomString());

		finTransaction.setTransactionDate(RandomTestUtil.nextDate());

		finTransaction.setStatus(RandomTestUtil.nextInt());

		_finTransactions.add(_persistence.update(finTransaction));

		return finTransaction;
	}

	private List<FinTransaction> _finTransactions =
		new ArrayList<FinTransaction>();
	private FinTransactionPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}