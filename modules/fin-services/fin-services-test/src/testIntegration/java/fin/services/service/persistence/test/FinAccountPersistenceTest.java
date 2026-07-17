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

import fin.services.exception.NoSuchFinAccountException;
import fin.services.model.FinAccount;
import fin.services.service.FinAccountLocalServiceUtil;
import fin.services.service.persistence.FinAccountPersistence;
import fin.services.service.persistence.FinAccountUtil;

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
public class FinAccountPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "fin.services.service"));

	@Before
	public void setUp() {
		_persistence = FinAccountUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FinAccount> iterator = _finAccounts.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinAccount finAccount = _persistence.create(pk);

		Assert.assertNotNull(finAccount);

		Assert.assertEquals(finAccount.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FinAccount newFinAccount = addFinAccount();

		_persistence.remove(newFinAccount);

		FinAccount existingFinAccount = _persistence.fetchByPrimaryKey(
			newFinAccount.getPrimaryKey());

		Assert.assertNull(existingFinAccount);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFinAccount();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinAccount newFinAccount = _persistence.create(pk);

		newFinAccount.setUuid(RandomTestUtil.randomString());

		newFinAccount.setGroupId(RandomTestUtil.nextLong());

		newFinAccount.setCompanyId(RandomTestUtil.nextLong());

		newFinAccount.setUserId(RandomTestUtil.nextLong());

		newFinAccount.setUserName(RandomTestUtil.randomString());

		newFinAccount.setOwnerUserId(RandomTestUtil.nextLong());

		newFinAccount.setCreateDate(RandomTestUtil.nextDate());

		newFinAccount.setModifiedDate(RandomTestUtil.nextDate());

		newFinAccount.setAccountNumber(RandomTestUtil.randomString());

		newFinAccount.setAccountName(RandomTestUtil.randomString());

		newFinAccount.setAccountType(RandomTestUtil.randomString());

		newFinAccount.setBalance(RandomTestUtil.nextDouble());

		newFinAccount.setStatus(RandomTestUtil.nextInt());

		newFinAccount.setCardScheme(RandomTestUtil.randomString());

		newFinAccount.setCardBrand(RandomTestUtil.randomString());

		newFinAccount.setCardBankName(RandomTestUtil.randomString());

		newFinAccount.setCardCountryName(RandomTestUtil.randomString());

		_finAccounts.add(_persistence.update(newFinAccount));

		FinAccount existingFinAccount = _persistence.findByPrimaryKey(
			newFinAccount.getPrimaryKey());

		Assert.assertEquals(
			existingFinAccount.getUuid(), newFinAccount.getUuid());
		Assert.assertEquals(
			existingFinAccount.getAccountId(), newFinAccount.getAccountId());
		Assert.assertEquals(
			existingFinAccount.getGroupId(), newFinAccount.getGroupId());
		Assert.assertEquals(
			existingFinAccount.getCompanyId(), newFinAccount.getCompanyId());
		Assert.assertEquals(
			existingFinAccount.getUserId(), newFinAccount.getUserId());
		Assert.assertEquals(
			existingFinAccount.getUserName(), newFinAccount.getUserName());
		Assert.assertEquals(
			existingFinAccount.getOwnerUserId(),
			newFinAccount.getOwnerUserId());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFinAccount.getCreateDate()),
			Time.getShortTimestamp(newFinAccount.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingFinAccount.getModifiedDate()),
			Time.getShortTimestamp(newFinAccount.getModifiedDate()));
		Assert.assertEquals(
			existingFinAccount.getAccountNumber(),
			newFinAccount.getAccountNumber());
		Assert.assertEquals(
			existingFinAccount.getAccountName(),
			newFinAccount.getAccountName());
		Assert.assertEquals(
			existingFinAccount.getAccountType(),
			newFinAccount.getAccountType());
		AssertUtils.assertEquals(
			existingFinAccount.getBalance(), newFinAccount.getBalance());
		Assert.assertEquals(
			existingFinAccount.getStatus(), newFinAccount.getStatus());
		Assert.assertEquals(
			existingFinAccount.getCardScheme(), newFinAccount.getCardScheme());
		Assert.assertEquals(
			existingFinAccount.getCardBrand(), newFinAccount.getCardBrand());
		Assert.assertEquals(
			existingFinAccount.getCardBankName(),
			newFinAccount.getCardBankName());
		Assert.assertEquals(
			existingFinAccount.getCardCountryName(),
			newFinAccount.getCardCountryName());
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
		_persistence.countByG_A(RandomTestUtil.nextLong(), "");

		_persistence.countByG_A(0L, "null");

		_persistence.countByG_A(0L, (String)null);
	}

	@Test
	public void testCountByG_O() throws Exception {
		_persistence.countByG_O(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByG_O(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FinAccount newFinAccount = addFinAccount();

		FinAccount existingFinAccount = _persistence.findByPrimaryKey(
			newFinAccount.getPrimaryKey());

		Assert.assertEquals(existingFinAccount, newFinAccount);
	}

	@Test(expected = NoSuchFinAccountException.class)
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

	protected OrderByComparator<FinAccount> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"FinServices_FinAccount", "uuid", true, "accountId", true,
			"groupId", true, "companyId", true, "userId", true, "userName",
			true, "ownerUserId", true, "createDate", true, "modifiedDate", true,
			"accountNumber", true, "accountName", true, "accountType", true,
			"balance", true, "status", true, "cardScheme", true, "cardBrand",
			true, "cardBankName", true, "cardCountryName", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FinAccount newFinAccount = addFinAccount();

		FinAccount existingFinAccount = _persistence.fetchByPrimaryKey(
			newFinAccount.getPrimaryKey());

		Assert.assertEquals(existingFinAccount, newFinAccount);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinAccount missingFinAccount = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFinAccount);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		FinAccount newFinAccount1 = addFinAccount();
		FinAccount newFinAccount2 = addFinAccount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinAccount1.getPrimaryKey());
		primaryKeys.add(newFinAccount2.getPrimaryKey());

		Map<Serializable, FinAccount> finAccounts =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, finAccounts.size());
		Assert.assertEquals(
			newFinAccount1, finAccounts.get(newFinAccount1.getPrimaryKey()));
		Assert.assertEquals(
			newFinAccount2, finAccounts.get(newFinAccount2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FinAccount> finAccounts =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(finAccounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		FinAccount newFinAccount = addFinAccount();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinAccount.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FinAccount> finAccounts =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, finAccounts.size());
		Assert.assertEquals(
			newFinAccount, finAccounts.get(newFinAccount.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FinAccount> finAccounts =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(finAccounts.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		FinAccount newFinAccount = addFinAccount();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinAccount.getPrimaryKey());

		Map<Serializable, FinAccount> finAccounts =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, finAccounts.size());
		Assert.assertEquals(
			newFinAccount, finAccounts.get(newFinAccount.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			FinAccountLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<FinAccount>() {

				@Override
				public void performAction(FinAccount finAccount) {
					Assert.assertNotNull(finAccount);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		FinAccount newFinAccount = addFinAccount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinAccount.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"accountId", newFinAccount.getAccountId()));

		List<FinAccount> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		FinAccount existingFinAccount = result.get(0);

		Assert.assertEquals(existingFinAccount, newFinAccount);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinAccount.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq("accountId", RandomTestUtil.nextLong()));

		List<FinAccount> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		FinAccount newFinAccount = addFinAccount();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinAccount.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("accountId"));

		Object newAccountId = newFinAccount.getAccountId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"accountId", new Object[] {newAccountId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingAccountId = result.get(0);

		Assert.assertEquals(existingAccountId, newAccountId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinAccount.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property("accountId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"accountId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		FinAccount newFinAccount = addFinAccount();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newFinAccount.getPrimaryKey()));
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

		FinAccount newFinAccount = addFinAccount();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinAccount.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"accountId", newFinAccount.getAccountId()));

		List<FinAccount> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(FinAccount finAccount) {
		Assert.assertEquals(
			finAccount.getUuid(),
			ReflectionTestUtil.invoke(
				finAccount, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(finAccount.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				finAccount, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));

		Assert.assertEquals(
			Long.valueOf(finAccount.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				finAccount, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
		Assert.assertEquals(
			finAccount.getAccountNumber(),
			ReflectionTestUtil.invoke(
				finAccount, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "accountNumber"));
	}

	protected FinAccount addFinAccount() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinAccount finAccount = _persistence.create(pk);

		finAccount.setUuid(RandomTestUtil.randomString());

		finAccount.setGroupId(RandomTestUtil.nextLong());

		finAccount.setCompanyId(RandomTestUtil.nextLong());

		finAccount.setUserId(RandomTestUtil.nextLong());

		finAccount.setUserName(RandomTestUtil.randomString());

		finAccount.setOwnerUserId(RandomTestUtil.nextLong());

		finAccount.setCreateDate(RandomTestUtil.nextDate());

		finAccount.setModifiedDate(RandomTestUtil.nextDate());

		finAccount.setAccountNumber(RandomTestUtil.randomString());

		finAccount.setAccountName(RandomTestUtil.randomString());

		finAccount.setAccountType(RandomTestUtil.randomString());

		finAccount.setBalance(RandomTestUtil.nextDouble());

		finAccount.setStatus(RandomTestUtil.nextInt());

		finAccount.setCardScheme(RandomTestUtil.randomString());

		finAccount.setCardBrand(RandomTestUtil.randomString());

		finAccount.setCardBankName(RandomTestUtil.randomString());

		finAccount.setCardCountryName(RandomTestUtil.randomString());

		_finAccounts.add(_persistence.update(finAccount));

		return finAccount;
	}

	private List<FinAccount> _finAccounts = new ArrayList<FinAccount>();
	private FinAccountPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}