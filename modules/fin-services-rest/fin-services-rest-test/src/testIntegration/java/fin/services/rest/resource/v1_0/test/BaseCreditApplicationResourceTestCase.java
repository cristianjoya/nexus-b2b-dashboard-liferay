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

import fin.services.rest.client.dto.v1_0.CreditApplication;
import fin.services.rest.client.http.HttpInvoker;
import fin.services.rest.client.pagination.Page;
import fin.services.rest.client.pagination.Pagination;
import fin.services.rest.client.resource.v1_0.CreditApplicationResource;
import fin.services.rest.client.serdes.v1_0.CreditApplicationSerDes;

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
public abstract class BaseCreditApplicationResourceTestCase {

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

		_creditApplicationResource.setContextCompany(testCompany);

		_testCompanyAdminUser = UserTestUtil.getAdminUser(
			testCompany.getCompanyId());

		creditApplicationResource = CreditApplicationResource.builder(
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

		CreditApplication creditApplication1 = randomCreditApplication();

		String json = objectMapper.writeValueAsString(creditApplication1);

		CreditApplication creditApplication2 = CreditApplicationSerDes.toDTO(
			json);

		Assert.assertTrue(equals(creditApplication1, creditApplication2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = getClientSerDesObjectMapper();

		CreditApplication creditApplication = randomCreditApplication();

		String json1 = objectMapper.writeValueAsString(creditApplication);
		String json2 = CreditApplicationSerDes.toJSON(creditApplication);

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

		CreditApplication creditApplication = randomCreditApplication();

		creditApplication.setApplicantName(regex);
		creditApplication.setPurpose(regex);
		creditApplication.setReviewNotes(regex);
		creditApplication.setUuid(regex);

		String json = CreditApplicationSerDes.toJSON(creditApplication);

		Assert.assertFalse(json.contains(regex));

		creditApplication = CreditApplicationSerDes.toDTO(json);

		Assert.assertEquals(regex, creditApplication.getApplicantName());
		Assert.assertEquals(regex, creditApplication.getPurpose());
		Assert.assertEquals(regex, creditApplication.getReviewNotes());
		Assert.assertEquals(regex, creditApplication.getUuid());
	}

	@Test
	public void testDeleteCreditApplication() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLDeleteCreditApplication() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetCreditApplication() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetCreditApplication() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetCreditApplicationNotFound() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGetCreditApplicationsPage() throws Exception {
		Page<CreditApplication> page =
			creditApplicationResource.getCreditApplicationsPage(
				null, null, null, Pagination.of(1, 10));

		long totalCount = page.getTotalCount();

		CreditApplication creditApplication1 =
			testGetCreditApplicationsPage_addCreditApplication(
				randomCreditApplication());

		CreditApplication creditApplication2 =
			testGetCreditApplicationsPage_addCreditApplication(
				randomCreditApplication());

		page = creditApplicationResource.getCreditApplicationsPage(
			null, null, null, Pagination.of(1, 10));

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(
			creditApplication1, (List<CreditApplication>)page.getItems());
		assertContains(
			creditApplication2, (List<CreditApplication>)page.getItems());
		assertValid(page, testGetCreditApplicationsPage_getExpectedActions());
	}

	protected Map<String, Map<String, String>>
			testGetCreditApplicationsPage_getExpectedActions()
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	@Test
	public void testGetCreditApplicationsPageWithPagination() throws Exception {
		Page<CreditApplication> creditApplicationsPage =
			creditApplicationResource.getCreditApplicationsPage(
				null, null, null, null);

		int totalCount = GetterUtil.getInteger(
			creditApplicationsPage.getTotalCount());

		CreditApplication creditApplication1 =
			testGetCreditApplicationsPage_addCreditApplication(
				randomCreditApplication());

		CreditApplication creditApplication2 =
			testGetCreditApplicationsPage_addCreditApplication(
				randomCreditApplication());

		CreditApplication creditApplication3 =
			testGetCreditApplicationsPage_addCreditApplication(
				randomCreditApplication());

		// See com.liferay.portal.vulcan.internal.configuration.HeadlessAPICompanyConfiguration#pageSizeLimit

		int pageSizeLimit = 500;

		if (totalCount >= (pageSizeLimit - 2)) {
			Page<CreditApplication> page1 =
				creditApplicationResource.getCreditApplicationsPage(
					null, null, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 1.0) / pageSizeLimit),
						pageSizeLimit));

			Assert.assertEquals(totalCount + 3, page1.getTotalCount());

			assertContains(
				creditApplication1, (List<CreditApplication>)page1.getItems());

			Page<CreditApplication> page2 =
				creditApplicationResource.getCreditApplicationsPage(
					null, null, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 2.0) / pageSizeLimit),
						pageSizeLimit));

			assertContains(
				creditApplication2, (List<CreditApplication>)page2.getItems());

			Page<CreditApplication> page3 =
				creditApplicationResource.getCreditApplicationsPage(
					null, null, null,
					Pagination.of(
						(int)Math.ceil((totalCount + 3.0) / pageSizeLimit),
						pageSizeLimit));

			assertContains(
				creditApplication3, (List<CreditApplication>)page3.getItems());
		}
		else {
			Page<CreditApplication> page1 =
				creditApplicationResource.getCreditApplicationsPage(
					null, null, null, Pagination.of(1, totalCount + 2));

			List<CreditApplication> creditApplications1 =
				(List<CreditApplication>)page1.getItems();

			Assert.assertEquals(
				creditApplications1.toString(), totalCount + 2,
				creditApplications1.size());

			Page<CreditApplication> page2 =
				creditApplicationResource.getCreditApplicationsPage(
					null, null, null, Pagination.of(2, totalCount + 2));

			Assert.assertEquals(totalCount + 3, page2.getTotalCount());

			List<CreditApplication> creditApplications2 =
				(List<CreditApplication>)page2.getItems();

			Assert.assertEquals(
				creditApplications2.toString(), 1, creditApplications2.size());

			Page<CreditApplication> page3 =
				creditApplicationResource.getCreditApplicationsPage(
					null, null, null, Pagination.of(1, (int)totalCount + 3));

			assertContains(
				creditApplication1, (List<CreditApplication>)page3.getItems());
			assertContains(
				creditApplication2, (List<CreditApplication>)page3.getItems());
			assertContains(
				creditApplication3, (List<CreditApplication>)page3.getItems());
		}
	}

	protected CreditApplication
			testGetCreditApplicationsPage_addCreditApplication(
				CreditApplication creditApplication)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetCreditApplicationsPage() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPostCreditApplication() throws Exception {
		CreditApplication randomCreditApplication = randomCreditApplication();

		CreditApplication postCreditApplication =
			testPostCreditApplication_addCreditApplication(
				randomCreditApplication);

		assertEquals(randomCreditApplication, postCreditApplication);
		assertValid(postCreditApplication);
	}

	protected CreditApplication testPostCreditApplication_addCreditApplication(
			CreditApplication creditApplication)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPutCreditApplication() throws Exception {
		Assert.assertTrue(false);
	}

	protected void assertContains(
		CreditApplication creditApplication,
		List<CreditApplication> creditApplications) {

		boolean contains = false;

		for (CreditApplication item : creditApplications) {
			if (equals(creditApplication, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			creditApplications + " does not contain " + creditApplication,
			contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(
		CreditApplication creditApplication1,
		CreditApplication creditApplication2) {

		Assert.assertTrue(
			creditApplication1 + " does not equal " + creditApplication2,
			equals(creditApplication1, creditApplication2));
	}

	protected void assertEquals(
		List<CreditApplication> creditApplications1,
		List<CreditApplication> creditApplications2) {

		Assert.assertEquals(
			creditApplications1.size(), creditApplications2.size());

		for (int i = 0; i < creditApplications1.size(); i++) {
			CreditApplication creditApplication1 = creditApplications1.get(i);
			CreditApplication creditApplication2 = creditApplications2.get(i);

			assertEquals(creditApplication1, creditApplication2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<CreditApplication> creditApplications1,
		List<CreditApplication> creditApplications2) {

		Assert.assertEquals(
			creditApplications1.size(), creditApplications2.size());

		for (CreditApplication creditApplication1 : creditApplications1) {
			boolean contains = false;

			for (CreditApplication creditApplication2 : creditApplications2) {
				if (equals(creditApplication1, creditApplication2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				creditApplications2 + " does not contain " + creditApplication1,
				contains);
		}
	}

	protected void assertValid(CreditApplication creditApplication)
		throws Exception {

		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accountId", additionalAssertFieldName)) {
				if (creditApplication.getAccountId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("applicantName", additionalAssertFieldName)) {
				if (creditApplication.getApplicantName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (creditApplication.getCreateDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("creditAppId", additionalAssertFieldName)) {
				if (creditApplication.getCreditAppId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (creditApplication.getModifiedDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("purpose", additionalAssertFieldName)) {
				if (creditApplication.getPurpose() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("requestedAmount", additionalAssertFieldName)) {
				if (creditApplication.getRequestedAmount() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("reviewNotes", additionalAssertFieldName)) {
				if (creditApplication.getReviewNotes() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (creditApplication.getStatus() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("uuid", additionalAssertFieldName)) {
				if (creditApplication.getUuid() == null) {
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

	protected void assertValid(Page<CreditApplication> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<CreditApplication> page,
		Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<CreditApplication> creditApplications =
			page.getItems();

		int size = creditApplications.size();

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
					fin.services.rest.dto.v1_0.CreditApplication.class)) {

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
		CreditApplication creditApplication1,
		CreditApplication creditApplication2) {

		if (creditApplication1 == creditApplication2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("accountId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						creditApplication1.getAccountId(),
						creditApplication2.getAccountId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("applicantName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						creditApplication1.getApplicantName(),
						creditApplication2.getApplicantName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						creditApplication1.getCreateDate(),
						creditApplication2.getCreateDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("creditAppId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						creditApplication1.getCreditAppId(),
						creditApplication2.getCreditAppId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("modifiedDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						creditApplication1.getModifiedDate(),
						creditApplication2.getModifiedDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("purpose", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						creditApplication1.getPurpose(),
						creditApplication2.getPurpose())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("requestedAmount", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						creditApplication1.getRequestedAmount(),
						creditApplication2.getRequestedAmount())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("reviewNotes", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						creditApplication1.getReviewNotes(),
						creditApplication2.getReviewNotes())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						creditApplication1.getStatus(),
						creditApplication2.getStatus())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("uuid", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						creditApplication1.getUuid(),
						creditApplication2.getUuid())) {

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

		if (!(_creditApplicationResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_creditApplicationResource;

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
		EntityField entityField, String operator,
		CreditApplication creditApplication) {

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

		if (entityFieldName.equals("applicantName")) {
			Object object = creditApplication.getApplicantName();

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

		if (entityFieldName.equals("createDate")) {
			if (operator.equals("between")) {
				Date date = creditApplication.getCreateDate();

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

				sb.append(_format.format(creditApplication.getCreateDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("creditAppId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("modifiedDate")) {
			if (operator.equals("between")) {
				Date date = creditApplication.getModifiedDate();

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

				sb.append(_format.format(creditApplication.getModifiedDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("purpose")) {
			Object object = creditApplication.getPurpose();

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

		if (entityFieldName.equals("requestedAmount")) {
			sb.append(String.valueOf(creditApplication.getRequestedAmount()));

			return sb.toString();
		}

		if (entityFieldName.equals("reviewNotes")) {
			Object object = creditApplication.getReviewNotes();

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

		if (entityFieldName.equals("status")) {
			sb.append(String.valueOf(creditApplication.getStatus()));

			return sb.toString();
		}

		if (entityFieldName.equals("uuid")) {
			Object object = creditApplication.getUuid();

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

	protected CreditApplication randomCreditApplication() throws Exception {
		return new CreditApplication() {
			{
				accountId = RandomTestUtil.randomLong();
				applicantName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				createDate = RandomTestUtil.nextDate();
				creditAppId = RandomTestUtil.randomLong();
				modifiedDate = RandomTestUtil.nextDate();
				purpose = StringUtil.toLowerCase(RandomTestUtil.randomString());
				requestedAmount = RandomTestUtil.randomDouble();
				reviewNotes = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				status = RandomTestUtil.randomInt();
				uuid = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected CreditApplication randomIrrelevantCreditApplication()
		throws Exception {

		CreditApplication randomIrrelevantCreditApplication =
			randomCreditApplication();

		return randomIrrelevantCreditApplication;
	}

	protected CreditApplication randomPatchCreditApplication()
		throws Exception {

		return randomCreditApplication();
	}

	protected CreditApplicationResource creditApplicationResource;
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
		LogFactoryUtil.getLog(BaseCreditApplicationResourceTestCase.class);

	private static Format _format;

	private com.liferay.portal.kernel.model.User _testCompanyAdminUser;

	@Inject
	private fin.services.rest.resource.v1_0.CreditApplicationResource
		_creditApplicationResource;

}