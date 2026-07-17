package fin.services.rest.internal.graphql.servlet.v1_0;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import fin.services.rest.internal.graphql.mutation.v1_0.Mutation;
import fin.services.rest.internal.graphql.query.v1_0.Query;
import fin.services.rest.internal.resource.v1_0.AccountResourceImpl;
import fin.services.rest.internal.resource.v1_0.CreditApplicationResourceImpl;
import fin.services.rest.internal.resource.v1_0.TransactionResourceImpl;
import fin.services.rest.resource.v1_0.AccountResource;
import fin.services.rest.resource.v1_0.CreditApplicationResource;
import fin.services.rest.resource.v1_0.TransactionResource;

import jakarta.annotation.Generated;

import java.util.HashMap;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author krism
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setAccountResourceComponentServiceObjects(
			_accountResourceComponentServiceObjects);
		Mutation.setCreditApplicationResourceComponentServiceObjects(
			_creditApplicationResourceComponentServiceObjects);
		Mutation.setTransactionResourceComponentServiceObjects(
			_transactionResourceComponentServiceObjects);

		Query.setAccountResourceComponentServiceObjects(
			_accountResourceComponentServiceObjects);
		Query.setCreditApplicationResourceComponentServiceObjects(
			_creditApplicationResourceComponentServiceObjects);
		Query.setTransactionResourceComponentServiceObjects(
			_transactionResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "FinServicesRest";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/fin-services-rest-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#deleteAccount",
						new ObjectValuePair<>(
							AccountResourceImpl.class, "deleteAccount"));
					put(
						"mutation#deleteAccountBatch",
						new ObjectValuePair<>(
							AccountResourceImpl.class, "deleteAccountBatch"));
					put(
						"mutation#createAccount",
						new ObjectValuePair<>(
							AccountResourceImpl.class, "postAccount"));
					put(
						"mutation#createAccountBatch",
						new ObjectValuePair<>(
							AccountResourceImpl.class, "postAccountBatch"));
					put(
						"mutation#updateAccount",
						new ObjectValuePair<>(
							AccountResourceImpl.class, "putAccount"));
					put(
						"mutation#updateAccountBatch",
						new ObjectValuePair<>(
							AccountResourceImpl.class, "putAccountBatch"));
					put(
						"mutation#deleteCreditApplication",
						new ObjectValuePair<>(
							CreditApplicationResourceImpl.class,
							"deleteCreditApplication"));
					put(
						"mutation#deleteCreditApplicationBatch",
						new ObjectValuePair<>(
							CreditApplicationResourceImpl.class,
							"deleteCreditApplicationBatch"));
					put(
						"mutation#createCreditApplication",
						new ObjectValuePair<>(
							CreditApplicationResourceImpl.class,
							"postCreditApplication"));
					put(
						"mutation#createCreditApplicationBatch",
						new ObjectValuePair<>(
							CreditApplicationResourceImpl.class,
							"postCreditApplicationBatch"));
					put(
						"mutation#updateCreditApplication",
						new ObjectValuePair<>(
							CreditApplicationResourceImpl.class,
							"putCreditApplication"));
					put(
						"mutation#updateCreditApplicationBatch",
						new ObjectValuePair<>(
							CreditApplicationResourceImpl.class,
							"putCreditApplicationBatch"));
					put(
						"mutation#deleteTransaction",
						new ObjectValuePair<>(
							TransactionResourceImpl.class,
							"deleteTransaction"));
					put(
						"mutation#deleteTransactionBatch",
						new ObjectValuePair<>(
							TransactionResourceImpl.class,
							"deleteTransactionBatch"));
					put(
						"mutation#createTransaction",
						new ObjectValuePair<>(
							TransactionResourceImpl.class, "postTransaction"));
					put(
						"mutation#createTransactionBatch",
						new ObjectValuePair<>(
							TransactionResourceImpl.class,
							"postTransactionBatch"));
					put(
						"mutation#updateTransaction",
						new ObjectValuePair<>(
							TransactionResourceImpl.class, "putTransaction"));
					put(
						"mutation#updateTransactionBatch",
						new ObjectValuePair<>(
							TransactionResourceImpl.class,
							"putTransactionBatch"));

					put(
						"query#account",
						new ObjectValuePair<>(
							AccountResourceImpl.class, "getAccount"));
					put(
						"query#accounts",
						new ObjectValuePair<>(
							AccountResourceImpl.class, "getAccountsPage"));
					put(
						"query#assignableB2BUsers",
						new ObjectValuePair<>(
							AccountResourceImpl.class,
							"getAssignableB2BUsersPage"));
					put(
						"query#creditApplication",
						new ObjectValuePair<>(
							CreditApplicationResourceImpl.class,
							"getCreditApplication"));
					put(
						"query#creditApplications",
						new ObjectValuePair<>(
							CreditApplicationResourceImpl.class,
							"getCreditApplicationsPage"));
					put(
						"query#transaction",
						new ObjectValuePair<>(
							TransactionResourceImpl.class, "getTransaction"));
					put(
						"query#transactions",
						new ObjectValuePair<>(
							TransactionResourceImpl.class,
							"getTransactionsPage"));

					put(
						"query#Transaction.account",
						new ObjectValuePair<>(
							AccountResourceImpl.class, "getAccount"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<CreditApplicationResource>
		_creditApplicationResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<TransactionResource>
		_transactionResourceComponentServiceObjects;

}