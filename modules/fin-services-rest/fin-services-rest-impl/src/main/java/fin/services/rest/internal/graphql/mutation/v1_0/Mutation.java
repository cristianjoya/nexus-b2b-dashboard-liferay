package fin.services.rest.internal.graphql.mutation.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.batch.engine.resource.VulcanBatchEngineImportTaskResource;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;

import fin.services.rest.dto.v1_0.Account;
import fin.services.rest.dto.v1_0.CreditApplication;
import fin.services.rest.dto.v1_0.Transaction;
import fin.services.rest.resource.v1_0.AccountResource;
import fin.services.rest.resource.v1_0.CreditApplicationResource;
import fin.services.rest.resource.v1_0.TransactionResource;

import jakarta.annotation.Generated;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.function.BiFunction;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author krism
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setAccountResourceComponentServiceObjects(
		ComponentServiceObjects<AccountResource>
			accountResourceComponentServiceObjects) {

		_accountResourceComponentServiceObjects =
			accountResourceComponentServiceObjects;
	}

	public static void setCreditApplicationResourceComponentServiceObjects(
		ComponentServiceObjects<CreditApplicationResource>
			creditApplicationResourceComponentServiceObjects) {

		_creditApplicationResourceComponentServiceObjects =
			creditApplicationResourceComponentServiceObjects;
	}

	public static void setTransactionResourceComponentServiceObjects(
		ComponentServiceObjects<TransactionResource>
			transactionResourceComponentServiceObjects) {

		_transactionResourceComponentServiceObjects =
			transactionResourceComponentServiceObjects;
	}

	@GraphQLField
	public Response deleteAccount(@GraphQLName("accountId") Long accountId)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccount(accountId));
	}

	@GraphQLField
	public Response deleteAccountBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.deleteAccountBatch(
				callbackURL, object));
	}

	@GraphQLField
	public Account createAccount(@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.postAccount(account));
	}

	@GraphQLField
	public Response createAccountBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.postAccountBatch(
				callbackURL, object));
	}

	@GraphQLField
	public Account updateAccount(
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("account") Account account)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccount(accountId, account));
	}

	@GraphQLField
	public Response updateAccountBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.putAccountBatch(
				callbackURL, object));
	}

	@GraphQLField
	public Response deleteCreditApplication(
			@GraphQLName("creditAppId") Long creditAppId)
		throws Exception {

		return _applyComponentServiceObjects(
			_creditApplicationResourceComponentServiceObjects,
			this::_populateResourceContext,
			creditApplicationResource ->
				creditApplicationResource.deleteCreditApplication(creditAppId));
	}

	@GraphQLField
	public Response deleteCreditApplicationBatch(
			@GraphQLName("creditAppId") Long creditAppId,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_creditApplicationResourceComponentServiceObjects,
			this::_populateResourceContext,
			creditApplicationResource ->
				creditApplicationResource.deleteCreditApplicationBatch(
					creditAppId, callbackURL, object));
	}

	@GraphQLField
	public CreditApplication createCreditApplication(
			@GraphQLName("creditApplication") CreditApplication
				creditApplication)
		throws Exception {

		return _applyComponentServiceObjects(
			_creditApplicationResourceComponentServiceObjects,
			this::_populateResourceContext,
			creditApplicationResource ->
				creditApplicationResource.postCreditApplication(
					creditApplication));
	}

	@GraphQLField
	public Response createCreditApplicationBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_creditApplicationResourceComponentServiceObjects,
			this::_populateResourceContext,
			creditApplicationResource ->
				creditApplicationResource.postCreditApplicationBatch(
					callbackURL, object));
	}

	@GraphQLField
	public CreditApplication updateCreditApplication(
			@GraphQLName("creditAppId") Long creditAppId,
			@GraphQLName("creditApplication") CreditApplication
				creditApplication)
		throws Exception {

		return _applyComponentServiceObjects(
			_creditApplicationResourceComponentServiceObjects,
			this::_populateResourceContext,
			creditApplicationResource ->
				creditApplicationResource.putCreditApplication(
					creditAppId, creditApplication));
	}

	@GraphQLField
	public Response updateCreditApplicationBatch(
			@GraphQLName("creditAppId") Long creditAppId,
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_creditApplicationResourceComponentServiceObjects,
			this::_populateResourceContext,
			creditApplicationResource ->
				creditApplicationResource.putCreditApplicationBatch(
					creditAppId, callbackURL, object));
	}

	@GraphQLField
	public Response deleteTransaction(
			@GraphQLName("transactionId") Long transactionId)
		throws Exception {

		return _applyComponentServiceObjects(
			_transactionResourceComponentServiceObjects,
			this::_populateResourceContext,
			transactionResource -> transactionResource.deleteTransaction(
				transactionId));
	}

	@GraphQLField
	public Response deleteTransactionBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_transactionResourceComponentServiceObjects,
			this::_populateResourceContext,
			transactionResource -> transactionResource.deleteTransactionBatch(
				callbackURL, object));
	}

	@GraphQLField
	public Transaction createTransaction(
			@GraphQLName("transaction") Transaction transaction)
		throws Exception {

		return _applyComponentServiceObjects(
			_transactionResourceComponentServiceObjects,
			this::_populateResourceContext,
			transactionResource -> transactionResource.postTransaction(
				transaction));
	}

	@GraphQLField
	public Response createTransactionBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_transactionResourceComponentServiceObjects,
			this::_populateResourceContext,
			transactionResource -> transactionResource.postTransactionBatch(
				callbackURL, object));
	}

	@GraphQLField
	public Transaction updateTransaction(
			@GraphQLName("transactionId") Long transactionId,
			@GraphQLName("transaction") Transaction transaction)
		throws Exception {

		return _applyComponentServiceObjects(
			_transactionResourceComponentServiceObjects,
			this::_populateResourceContext,
			transactionResource -> transactionResource.putTransaction(
				transactionId, transaction));
	}

	@GraphQLField
	public Response updateTransactionBatch(
			@GraphQLName("callbackURL") String callbackURL,
			@GraphQLName("object") Object object)
		throws Exception {

		return _applyComponentServiceObjects(
			_transactionResourceComponentServiceObjects,
			this::_populateResourceContext,
			transactionResource -> transactionResource.putTransactionBatch(
				callbackURL, object));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(AccountResource accountResource)
		throws Exception {

		accountResource.setContextAcceptLanguage(_acceptLanguage);
		accountResource.setContextCompany(_company);
		accountResource.setContextHttpServletRequest(_httpServletRequest);
		accountResource.setContextHttpServletResponse(_httpServletResponse);
		accountResource.setContextUriInfo(_uriInfo);
		accountResource.setContextUser(_user);
		accountResource.setGroupLocalService(_groupLocalService);
		accountResource.setRoleLocalService(_roleLocalService);

		accountResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
	}

	private void _populateResourceContext(
			CreditApplicationResource creditApplicationResource)
		throws Exception {

		creditApplicationResource.setContextAcceptLanguage(_acceptLanguage);
		creditApplicationResource.setContextCompany(_company);
		creditApplicationResource.setContextHttpServletRequest(
			_httpServletRequest);
		creditApplicationResource.setContextHttpServletResponse(
			_httpServletResponse);
		creditApplicationResource.setContextUriInfo(_uriInfo);
		creditApplicationResource.setContextUser(_user);
		creditApplicationResource.setGroupLocalService(_groupLocalService);
		creditApplicationResource.setRoleLocalService(_roleLocalService);

		creditApplicationResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
	}

	private void _populateResourceContext(
			TransactionResource transactionResource)
		throws Exception {

		transactionResource.setContextAcceptLanguage(_acceptLanguage);
		transactionResource.setContextCompany(_company);
		transactionResource.setContextHttpServletRequest(_httpServletRequest);
		transactionResource.setContextHttpServletResponse(_httpServletResponse);
		transactionResource.setContextUriInfo(_uriInfo);
		transactionResource.setContextUser(_user);
		transactionResource.setGroupLocalService(_groupLocalService);
		transactionResource.setRoleLocalService(_roleLocalService);

		transactionResource.setVulcanBatchEngineImportTaskResource(
			_vulcanBatchEngineImportTaskResource);
	}

	private static ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;
	private static ComponentServiceObjects<CreditApplicationResource>
		_creditApplicationResourceComponentServiceObjects;
	private static ComponentServiceObjects<TransactionResource>
		_transactionResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, com.liferay.portal.kernel.search.Sort[]>
		_sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;
	private VulcanBatchEngineImportTaskResource
		_vulcanBatchEngineImportTaskResource;

}