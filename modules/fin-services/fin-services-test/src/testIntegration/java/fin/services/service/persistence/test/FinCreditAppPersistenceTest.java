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

import fin.services.exception.NoSuchFinCreditAppException;
import fin.services.model.FinCreditApp;
import fin.services.service.FinCreditAppLocalServiceUtil;
import fin.services.service.persistence.FinCreditAppPersistence;
import fin.services.service.persistence.FinCreditAppUtil;

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
public class FinCreditAppPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "fin.services.service"));

	@Before
	public void setUp() {
		_persistence = FinCreditAppUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FinCreditApp> iterator = _finCreditApps.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinCreditApp finCreditApp = _persistence.create(pk);

		Assert.assertNotNull(finCreditApp);

		Assert.assertEquals(finCreditApp.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FinCreditApp newFinCreditApp = addFinCreditApp();

		_persistence.remove(newFinCreditApp);

		FinCreditApp existingFinCreditApp = _persistence.fetchByPrimaryKey(
			newFinCreditApp.getPrimaryKey());

		Assert.assertNull(existingFinCreditApp);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFinCreditApp();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinCreditApp newFinCreditApp = _persistence.create(pk);

		newFinCreditApp.setUuid(RandomTestUtil.randomString());

		newFinCreditApp.setGroupId(RandomTestUtil.nextLong());

		newFinCreditApp.setCompanyId(RandomTestUtil.nextLong());

		newFinCreditApp.setUserId(RandomTestUtil.nextLong());

		newFinCreditApp.setUserName(RandomTestUtil.randomString());

		newFinCreditApp.setCreateDate(RandomTestUtil.nextDate());

		newFinCreditApp.setModifiedDate(RandomTestUtil.nextDate());

		newFinCreditApp.setAccountId(RandomTestUtil.nextLong());

		newFinCreditApp.setApplicantName(RandomTestUtil.randomString());

		newFinCreditApp.setRequestedAmount(RandomTestUtil.nextDouble());

		newFinCreditApp.setPurpose(RandomTestUtil.randomString());

		newFinCreditApp.setStatus(RandomTestUtil.nextInt());

		newFinCreditApp.setReviewNotes(RandomTestUtil.randomString());

		_finCreditApps.add(_persistence.update(newFinCreditApp));

		FinCreditApp existingFinCreditApp = _persistence.findByPrimaryKey(
			newFinCreditApp.getPrimaryKey());

		Assert.assertEquals(
			existingFinCreditApp.getUuid(), newFinCreditApp.getUuid());
		Assert.assertEquals(
			existingFinCreditApp.getCreditAppId(),
			newFinCreditApp.getCreditAppId());
		Assert.assertEquals(
			existingFinCreditApp.getGroupId(), newFinCreditApp.getGroupId());
		Assert.assertEquals(
			existingFinCreditApp.getCompanyId(),
			newFinCreditApp.getCompanyId());
		Assert.assertEquals(
			existingFinCreditApp.getUserId(), newFinCreditApp.getUserId());
		Assert.assertEquals(
			existingFinCreditApp.getUserName(), newFinCreditApp.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFinCreditApp.getCreateDate()),
			Time.getShortTimestamp(newFinCreditApp.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingFinCreditApp.getModifiedDate()),
			Time.getShortTimestamp(newFinCreditApp.getModifiedDate()));
		Assert.assertEquals(
			existingFinCreditApp.getAccountId(),
			newFinCreditApp.getAccountId());
		Assert.assertEquals(
			existingFinCreditApp.getApplicantName(),
			newFinCreditApp.getApplicantName());
		AssertUtils.assertEquals(
			existingFinCreditApp.getRequestedAmount(),
			newFinCreditApp.getRequestedAmount());
		Assert.assertEquals(
			existingFinCreditApp.getPurpose(), newFinCreditApp.getPurpose());
		Assert.assertEquals(
			existingFinCreditApp.getStatus(), newFinCreditApp.getStatus());
		Assert.assertEquals(
			existingFinCreditApp.getReviewNotes(),
			newFinCreditApp.getReviewNotes());
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
		FinCreditApp newFinCreditApp = addFinCreditApp();

		FinCreditApp existingFinCreditApp = _persistence.findByPrimaryKey(
			newFinCreditApp.getPrimaryKey());

		Assert.assertEquals(existingFinCreditApp, newFinCreditApp);
	}

	@Test(expected = NoSuchFinCreditAppException.class)
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

	protected OrderByComparator<FinCreditApp> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"FinServices_FinCreditApp", "uuid", true, "creditAppId", true,
			"groupId", true, "companyId", true, "userId", true, "userName",
			true, "createDate", true, "modifiedDate", true, "accountId", true,
			"applicantName", true, "requestedAmount", true, "purpose", true,
			"status", true, "reviewNotes", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FinCreditApp newFinCreditApp = addFinCreditApp();

		FinCreditApp existingFinCreditApp = _persistence.fetchByPrimaryKey(
			newFinCreditApp.getPrimaryKey());

		Assert.assertEquals(existingFinCreditApp, newFinCreditApp);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinCreditApp missingFinCreditApp = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFinCreditApp);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		FinCreditApp newFinCreditApp1 = addFinCreditApp();
		FinCreditApp newFinCreditApp2 = addFinCreditApp();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinCreditApp1.getPrimaryKey());
		primaryKeys.add(newFinCreditApp2.getPrimaryKey());

		Map<Serializable, FinCreditApp> finCreditApps =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, finCreditApps.size());
		Assert.assertEquals(
			newFinCreditApp1,
			finCreditApps.get(newFinCreditApp1.getPrimaryKey()));
		Assert.assertEquals(
			newFinCreditApp2,
			finCreditApps.get(newFinCreditApp2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FinCreditApp> finCreditApps =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(finCreditApps.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		FinCreditApp newFinCreditApp = addFinCreditApp();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinCreditApp.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FinCreditApp> finCreditApps =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, finCreditApps.size());
		Assert.assertEquals(
			newFinCreditApp,
			finCreditApps.get(newFinCreditApp.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FinCreditApp> finCreditApps =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(finCreditApps.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		FinCreditApp newFinCreditApp = addFinCreditApp();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFinCreditApp.getPrimaryKey());

		Map<Serializable, FinCreditApp> finCreditApps =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, finCreditApps.size());
		Assert.assertEquals(
			newFinCreditApp,
			finCreditApps.get(newFinCreditApp.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			FinCreditAppLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<FinCreditApp>() {

				@Override
				public void performAction(FinCreditApp finCreditApp) {
					Assert.assertNotNull(finCreditApp);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		FinCreditApp newFinCreditApp = addFinCreditApp();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinCreditApp.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"creditAppId", newFinCreditApp.getCreditAppId()));

		List<FinCreditApp> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		FinCreditApp existingFinCreditApp = result.get(0);

		Assert.assertEquals(existingFinCreditApp, newFinCreditApp);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinCreditApp.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"creditAppId", RandomTestUtil.nextLong()));

		List<FinCreditApp> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		FinCreditApp newFinCreditApp = addFinCreditApp();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinCreditApp.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("creditAppId"));

		Object newCreditAppId = newFinCreditApp.getCreditAppId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"creditAppId", new Object[] {newCreditAppId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCreditAppId = result.get(0);

		Assert.assertEquals(existingCreditAppId, newCreditAppId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinCreditApp.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("creditAppId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"creditAppId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		FinCreditApp newFinCreditApp = addFinCreditApp();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newFinCreditApp.getPrimaryKey()));
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

		FinCreditApp newFinCreditApp = addFinCreditApp();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FinCreditApp.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"creditAppId", newFinCreditApp.getCreditAppId()));

		List<FinCreditApp> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(FinCreditApp finCreditApp) {
		Assert.assertEquals(
			finCreditApp.getUuid(),
			ReflectionTestUtil.invoke(
				finCreditApp, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "uuid_"));
		Assert.assertEquals(
			Long.valueOf(finCreditApp.getGroupId()),
			ReflectionTestUtil.<Long>invoke(
				finCreditApp, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "groupId"));
	}

	protected FinCreditApp addFinCreditApp() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FinCreditApp finCreditApp = _persistence.create(pk);

		finCreditApp.setUuid(RandomTestUtil.randomString());

		finCreditApp.setGroupId(RandomTestUtil.nextLong());

		finCreditApp.setCompanyId(RandomTestUtil.nextLong());

		finCreditApp.setUserId(RandomTestUtil.nextLong());

		finCreditApp.setUserName(RandomTestUtil.randomString());

		finCreditApp.setCreateDate(RandomTestUtil.nextDate());

		finCreditApp.setModifiedDate(RandomTestUtil.nextDate());

		finCreditApp.setAccountId(RandomTestUtil.nextLong());

		finCreditApp.setApplicantName(RandomTestUtil.randomString());

		finCreditApp.setRequestedAmount(RandomTestUtil.nextDouble());

		finCreditApp.setPurpose(RandomTestUtil.randomString());

		finCreditApp.setStatus(RandomTestUtil.nextInt());

		finCreditApp.setReviewNotes(RandomTestUtil.randomString());

		_finCreditApps.add(_persistence.update(finCreditApp));

		return finCreditApp;
	}

	private List<FinCreditApp> _finCreditApps = new ArrayList<FinCreditApp>();
	private FinCreditAppPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}