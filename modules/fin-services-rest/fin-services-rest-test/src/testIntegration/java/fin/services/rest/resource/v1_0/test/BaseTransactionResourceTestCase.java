package fin.services.rest.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.TransformUtil;

import fin.services.rest.client.dto.v1_0.Transaction;
import fin.services.rest.client.http.HttpInvoker;
import fin.services.rest.client.pagination.Page;
import fin.services.rest.client.pagination.Pagination;
import fin.services.rest.client.resource.v1_0.TransactionResource;
import fin.services.rest.client.serdes.v1_0.TransactionSerDes;

import jakarta.annotation.Generated;

import jakarta.ws.rs.core.MultivaluedHashMap;

import java.lang.reflect.Method;

import java.text.Format;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author krism
 * @generated
 */
@Generated("")
public abstract class BaseTransactionResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_format = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_transactionResource.setContextCompany(testCompany);

		_testCompanyAdminUser = UserTestUtil.getAdminUser(
			testCompany.getCompanyId());

		transactionResource = TransactionResource.builder(
		).authentication(
			_testCompanyAdminUser.getEmailAddress(),
			PropsValues.DEFAULT_ADMIN_PASSWORD
		).endpoint(
			testCompany.getVirtualHostname(), 8080, "http"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		Transaction transaction1 = randomTransaction();

		String json = objectMapper.writeValueAsString(transaction1);

		Transaction transaction2 = TransactionSerDes.toDTO(json);

		Assert.assertTrue(equals(transaction1, transaction2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		Transaction transaction = randomTransaction();

		String json1 = objectMapper.writeValueAsString(transaction);
		String json2 = TransactionSerDes.toJSON(transaction);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	protected ObjectMapper getClientSerDesObjectMapper() {
		return new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Transaction transaction = randomTransaction();

		transaction.setDescription(regex);
		transaction.setTransactionType(regex);
		transaction.setUuid(regex);

		String json = TransactionSerDes.toJSON(transaction);

		Assert.assertFalse(json.contains(regex));

		transaction = TransactionSerDes.toDTO(json);

		Assert.assertEquals(regex, transaction.getDescription());
		Assert.assertEquals(regex, transaction.getTransactionType());
		Assert.assertEquals(regex, transaction.getUuid());
	}

	@Test
	public void testDeleteTransaction() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		Transaction transaction = testDeleteTransaction_addTransaction();

		assertHttpResponseStatusCode(
			204,
			transactionResource.deleteTransactionHttpResponse(
				transaction.getTransactionId()));

		assertHttpResponseStatusCode(
			404,
			transactionResource.getTransactionHttpResponse(
				transaction.getTransactionId()));
		assertHttpResponseStatusCode(
			404, transactionResource.getTransactionHttpResponse(0L));
	}

	protected Transaction testDeleteTransaction_addTransaction()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteTransaction() throws Exception {

		// No namespace

		Transaction transaction1 =
			testGraphQLDeleteTransaction_addTransaction();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteTransaction",
						new HashMap<String, Object>() {
							{
								put(
									"transactionId",
									transaction1.getTransactionId());
							}
						})),
				"JSONObject/data", "Object/deleteTransaction"));

		JSONArray errorsJSONArray1 = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"transaction",
					new HashMap<String, Object>() {
						{
							put(
								"transactionId",
								transaction1.getTransactionId());
						}
					},
					new GraphQLField("transactionId"))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray1.length() > 0);
	}

	protected Transaction testGraphQLDeleteTransaction_addTransaction()
		throws Exception {

		return testGraphQLTransaction_addTransaction();
	}

	@Test
	public void testGetTransaction() throws Exception {
		Transaction postTransaction = testGetTransaction_addTransaction();

		Transaction getTransaction = transactionResource.getTransaction(
			postTransaction.getTransactionId());

		assertEquals(postTransaction, getTransaction);
		assertValid(getTransaction);
	}

	protected Transaction testGetTransaction_addTransaction() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetTransaction() throws Exception {
		Transaction transaction = testGraphQLGetTransaction_addTransaction();

		// No namespace

		Assert.assertTrue(
			equals(
				transaction,
				TransactionSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"transaction",
								new HashMap<String, Object>() {
									{
										put(
											"transactionId",
											transaction.getTransactionId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/transaction"))));
	}

	@Test
	public void testGraphQLGetTransactionNotFound() throws Exception {
		Long irrelevantTransactionId = RandomTestUtil.randomLong();

		// No namespace

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"transaction",
						new HashMap<String, Object>() {
							{
								put("transactionId", irrelevantTransactionId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	protected Transaction testGraphQLGetTransaction_addTransaction()
		throws Exception {

		return testGraphQLTransaction_addTransaction();
	}

	@Test
	public void testGetTransactionsPage() throws Exception {
		Page<Transaction> page = transactionResource.getTransactionsPage(
			null, null, RandomTestUtil.randomString(), null,
			Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		Transaction transaction1 = testGetTransactionsPage_addTransaction(
			randomTransaction());

		Transaction transaction2 = testGetTransactionsPage_addTransaction(
			randomTransaction());

		page = transactionResource.getTransactionsPage(
			null, null, null, null, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(transaction1, (List<Transaction>)page.getItems());
		assertContains(transaction2, (List<Transaction>)page.getItems());
		assertValid(page, testGetTransactionsPage_getExpectedActions());

		transactionResource.deleteTransaction(transaction1.getTransactionId());

		transactionResource.deleteTransaction(transaction2.getTransactionId());
	}

	protected Map<String, Map<String, String>>
			testGetTransactionsPage_getExpectedActions()
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetTransactionsPageWithPagination() throws Exception {
		Page<Transaction> transactionsPage =
			transactionResource.getTransactionsPage(
				null, null, null, null, null);

		int totalCount = GetterUtil.getInteger(
			transactionsPage.getTotalCount());

		Transaction transaction1 = testGetTransactionsPage_addTransaction(
			randomTransaction());

		Transaction transaction2 = testGetTransactionsPage_addTransaction(
			randomTransaction());

		Transaction transaction3 = testGetTransactionsPage_addTransaction(
			randomTransaction());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<Transaction> page1 = transactionResource.getTransactionsPage(
				null, null, null, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
					pageSizeLimit));

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(transaction1, (List<Transaction>)page1.getItems());

			Page<Transaction> page2 = transactionResource.getTransactionsPage(
				null, null, null, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
					pageSizeLimit));

			assertContains(transaction2, (List<Transaction>)page2.getItems());

			Page<Transaction> page3 = transactionResource.getTransactionsPage(
				null, null, null, null,
				Pagination.of(
					(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
					pageSizeLimit));

			assertContains(transaction3, (List<Transaction>)page3.getItems());
		}
		else {
			Page<Transaction> page1 = transactionResource.getTransactionsPage(
				null, null, null, null, Pagination.of(1, totalCount + 2));

			List<Transaction> transactions1 =
				(List<Transaction>)page1.getItems();

			Assert.assertEquals(
				transactions1.toString(), totalCount + 2, transactions1.size());

			Page<Transaction> page2 = transactionResource.getTransactionsPage(
				null, null, null, null, Pagination.of(2, totalCount + 2));

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<Transaction> transactions2 =
				(List<Transaction>)page2.getItems();

			Assert.assertEquals(
				transactions2.toString(), 1, transactions2.size());

			Page<Transaction> page3 = transactionResource.getTransactionsPage(
				null, null, null, null, Pagination.of(1, (int)totalCount + 3));

			assertContains(transaction1, (List<Transaction>)page3.getItems());
			assertContains(transaction2, (List<Transaction>)page3.getItems());
			assertContains(transaction3, (List<Transaction>)page3.getItems());
		}
	}

	protected Transaction testGetTransactionsPage_addTransaction(
			Transaction transaction)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetTransactionsPage() throws Exception {
		GraphQLField graphQLField = new GraphQLField(
			"transactions",
			new HashMap<String, Object>() {
				{
					put("page", 1);
					put("pageSize", 10);
				}
			},
			new GraphQLField("items", getGraphQLFields()),
			new GraphQLField("page"), new GraphQLField("totalCount"));

		// No namespace

		JSONObject transactionsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/transactions");

		long totalCount = transactionsJSONObject.getLong("totalCount");

		Transaction transaction1 =
			testGraphQLGetTransactionsPage_addTransaction();
		Transaction transaction2 =
			testGraphQLGetTransactionsPage_addTransaction();

		transactionsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/transactions");

		Assert.assertEquals(
			totalCount + 2, transactionsJSONObject.getLong("totalCount"));

		assertContains(
			transaction1,
			Arrays.asList(
				TransactionSerDes.toDTOs(
					transactionsJSONObject.getString("items"))));
		assertContains(
			transaction2,
			Arrays.asList(
				TransactionSerDes.toDTOs(
					transactionsJSONObject.getString("items"))));
	}

	protected Transaction testGraphQLGetTransactionsPage_addTransaction()
		throws Exception {

		return testGraphQLTransaction_addTransaction();
	}

	@Test
	public void testPostTransaction() throws Exception {
		Transaction randomTransaction = randomTransaction();

		Transaction postTransaction = testPostTransaction_addTransaction(
			randomTransaction);

		assertEquals(randomTransaction, postTransaction);
		assertValid(postTransaction);
	}

	protected Transaction testPostTransaction_addTransaction(
			Transaction transaction)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutTransaction() throws Exception {
		Transaction postTransaction = testPutTransaction_addTransaction();

		Transaction randomTransaction = randomTransaction();

		Transaction putTransaction = transactionResource.putTransaction(
			postTransaction.getTransactionId(), randomTransaction);

		assertEquals(randomTransaction, putTransaction);
		assertValid(putTransaction);

		Transaction getTransaction = transactionResource.getTransaction(
			putTransaction.getTransactionId());

		assertEquals(randomTransaction, getTransaction);
		assertValid(getTransaction);
	}

	protected Transaction testPutTransaction_addTransaction() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Transaction testGraphQLTransaction_addTransaction()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(
		Transaction transaction, List<Transaction> transactions) {

		boolean contains = false;

		for (Transaction item : transactions) {
			if (equals(transaction, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			transactions + " does not contain " + transaction, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		Transaction transaction1, Transaction transaction2) {

		Assert.assertTrue(
			transaction1 + " does not equal " + transaction2,
			equals(transaction1, transaction2));
	}

	protected void assertEquals(
		List<Transaction> transactions1, List<Transaction> transactions2) {

		Assert.assertEquals(transactions1.size(), transactions2.size());

		for (int i = 0; i < transactions1.size(); i++) {
			Transaction transaction1 = transactions1.get(i);
			Transaction transaction2 = transactions2.get(i);

			assertEquals(transaction1, transaction2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Transaction> transactions1, List<Transaction> transactions2) {

		Assert.assertEquals(transactions1.size(), transactions2.size());

		for (Transaction transaction1 : transactions1) {
			boolean contains = false;

			for (Transaction transaction2 : transactions2) {
				if (equals(transaction1, transaction2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				transactions2 + " does not contain " + transaction1, contains);
		}
	}

	protected void assertValid(Transaction transaction) throws Exception {
		boolean valid = true;

		if (transaction.getTransactionId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accountId", additionalAssertFieldName)) {
				if (transaction.getAccountId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("amount", additionalAssertFieldName)) {
				if (transaction.getAmount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (transaction.getCreateDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (transaction.getDescription() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (transaction.getModifiedDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (transaction.getStatus() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("transactionDate", additionalAssertFieldName)) {
				if (transaction.getTransactionDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("transactionId", additionalAssertFieldName)) {
				if (transaction.getTransactionId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("transactionType", additionalAssertFieldName)) {
				if (transaction.getTransactionType() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("uuid", additionalAssertFieldName)) {
				if (transaction.getUuid() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<Transaction> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<Transaction> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<Transaction> transactions = page.getItems();

		int size = transactions.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);

		assertValid(page.getActions(), expectedActions);
	}

	protected void assertValid(
		Map<String, Map<String, String>> actions1,
		Map<String, Map<String, String>> actions2) {

		for (String key : actions2.keySet()) {
			Map action = actions1.get(key);

			Assert.assertNotNull(key + " does not contain an action", action);

			Map<String, String> expectedAction = actions2.get(key);

			Assert.assertEquals(
				expectedAction.get("method"), action.get("method"));
			Assert.assertEquals(expectedAction.get("href"), action.get("href"));
		}
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					fin.services.rest.dto.v1_0.Transaction.class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(
			java.lang.reflect.Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(
		Transaction transaction1, Transaction transaction2) {

		if (transaction1 == transaction2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accountId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						transaction1.getAccountId(),
						transaction2.getAccountId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("amount", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						transaction1.getAmount(), transaction2.getAmount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						transaction1.getCreateDate(),
						transaction2.getCreateDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("description", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						transaction1.getDescription(),
						transaction2.getDescription())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						transaction1.getModifiedDate(),
						transaction2.getModifiedDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						transaction1.getStatus(), transaction2.getStatus())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("transactionDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						transaction1.getTransactionDate(),
						transaction2.getTransactionDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("transactionId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						transaction1.getTransactionId(),
						transaction2.getTransactionId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("transactionType", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						transaction1.getTransactionType(),
						transaction2.getTransactionType())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("uuid", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						transaction1.getUuid(), transaction2.getUuid())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected java.lang.reflect.Field[] getDeclaredFields(Class clazz)
		throws Exception {

		if (clazz.getClassLoader() == null) {
			return new java.lang.reflect.Field[0];
		}

		return TransformUtil.transform(
			ReflectionUtil.getDeclaredFields(clazz),
			field -> {
				if (field.isSynthetic()) {
					return null;
				}

				return field;
			},
			java.lang.reflect.Field.class);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_transactionResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_transactionResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		if (entityModel == null) {
			return Collections.emptyList();
		}

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		return TransformUtil.transform(
			getEntityFields(),
			entityField -> {
				if (!Objects.equals(entityField.getType(), type) ||
					ArrayUtil.contains(
						getIgnoredEntityFieldNames(), entityField.getName())) {

					return null;
				}

				return entityField;
			});
	}

	protected String getFilterString(
		EntityField entityField, String operator, Transaction transaction) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("accountId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("amount")) {
			sb.append(String.valueOf(transaction.getAmount()));

			return sb.toString();
		}

		if (entityFieldName.equals("createDate")) {
			if (operator.equals("between")) {
				Date date = transaction.getCreateDate();

				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(_format.format(date.getTime() - (2 * Time.SECOND)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(_format.format(date.getTime() + (2 * Time.SECOND)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_format.format(transaction.getCreateDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("description")) {
			Object object = transaction.getDescription();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("modifiedDate")) {
			if (operator.equals("between")) {
				Date date = transaction.getModifiedDate();

				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(_format.format(date.getTime() - (2 * Time.SECOND)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(_format.format(date.getTime() + (2 * Time.SECOND)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_format.format(transaction.getModifiedDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("status")) {
			sb.append(String.valueOf(transaction.getStatus()));

			return sb.toString();
		}

		if (entityFieldName.equals("transactionDate")) {
			if (operator.equals("between")) {
				Date date = transaction.getTransactionDate();

				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(_format.format(date.getTime() - (2 * Time.SECOND)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(_format.format(date.getTime() + (2 * Time.SECOND)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_format.format(transaction.getTransactionDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("transactionId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("transactionType")) {
			Object object = transaction.getTransactionType();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("uuid")) {
			Object object = transaction.getUuid();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword(
			"test@liferay.com:" + PropsValues.DEFAULT_ADMIN_PASSWORD);

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected Transaction randomTransaction() throws Exception {
		return new Transaction() {
			{
				accountId = RandomTestUtil.randomLong();
				amount = RandomTestUtil.randomDouble();
				createDate = RandomTestUtil.nextDate();
				description = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				modifiedDate = RandomTestUtil.nextDate();
				status = RandomTestUtil.randomInt();
				transactionDate = RandomTestUtil.nextDate();
				transactionId = RandomTestUtil.randomLong();
				transactionType = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				uuid = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected Transaction randomIrrelevantTransaction() throws Exception {
		Transaction randomIrrelevantTransaction = randomTransaction();

		return randomIrrelevantTransaction;
	}

	protected Transaction randomPatchTransaction() throws Exception {
		return randomTransaction();
	}

	protected TransactionResource transactionResource;
	protected com.liferay.portal.kernel.model.Group irrelevantGroup;
	protected com.liferay.portal.kernel.model.Company testCompany;
	protected com.liferay.portal.kernel.model.Group testGroup;

	protected static class BeanTestUtil {

		public static void copyProperties(Object source, Object target)
			throws Exception {

			Class<?> sourceClass = source.getClass();

			Class<?> targetClass = target.getClass();

			for (java.lang.reflect.Field field :
					_getAllDeclaredFields(sourceClass)) {

				if (field.isSynthetic()) {
					continue;
				}

				Method getMethod = _getMethod(
					sourceClass, field.getName(), "get");

				try {
					Method setMethod = _getMethod(
						targetClass, field.getName(), "set",
						getMethod.getReturnType());

					setMethod.invoke(target, getMethod.invoke(source));
				}
				catch (Exception e) {
					continue;
				}
			}
		}

		public static boolean hasProperty(Object bean, String name) {
			Method setMethod = _getMethod(
				bean.getClass(), "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod != null) {
				return true;
			}

			return false;
		}

		public static void setProperty(Object bean, String name, Object value)
			throws Exception {

			Class<?> clazz = bean.getClass();

			Method setMethod = _getMethod(
				clazz, "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod == null) {
				throw new NoSuchMethodException();
			}

			Class<?>[] parameterTypes = setMethod.getParameterTypes();

			setMethod.invoke(bean, _translateValue(parameterTypes[0], value));
		}

		private static List<java.lang.reflect.Field> _getAllDeclaredFields(
			Class<?> clazz) {

			List<java.lang.reflect.Field> fields = new ArrayList<>();

			while ((clazz != null) && (clazz != Object.class)) {
				for (java.lang.reflect.Field field :
						clazz.getDeclaredFields()) {

					fields.add(field);
				}

				clazz = clazz.getSuperclass();
			}

			return fields;
		}

		private static Method _getMethod(Class<?> clazz, String name) {
			for (Method method : clazz.getMethods()) {
				if (name.equals(method.getName()) &&
					(method.getParameterCount() == 1) &&
					_parameterTypes.contains(method.getParameterTypes()[0])) {

					return method;
				}
			}

			return null;
		}

		private static Method _getMethod(
				Class<?> clazz, String fieldName, String prefix,
				Class<?>... parameterTypes)
			throws Exception {

			return clazz.getMethod(
				prefix + StringUtil.upperCaseFirstLetter(fieldName),
				parameterTypes);
		}

		private static Object _translateValue(
			Class<?> parameterType, Object value) {

			if ((value instanceof Integer) &&
				parameterType.equals(Long.class)) {

				Integer intValue = (Integer)value;

				return intValue.longValue();
			}

			return value;
		}

		private static final Set<Class<?>> _parameterTypes = new HashSet<>(
			Arrays.asList(
				Boolean.class, Date.class, Double.class, Integer.class,
				Long.class, Map.class, String.class));

	}

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseTransactionResourceTestCase.class);

	private static Format _format;

	private com.liferay.portal.kernel.model.User _testCompanyAdminUser;

	@Inject
	private fin.services.rest.resource.v1_0.TransactionResource
		_transactionResource;

}