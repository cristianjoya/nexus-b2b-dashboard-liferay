package fin.services.rest.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import fin.services.rest.dto.v1_0.Account;
import fin.services.rest.dto.v1_0.CreditApplication;
import fin.services.rest.dto.v1_0.Transaction;
import fin.services.rest.resource.v1_0.AccountResource;
import fin.services.rest.resource.v1_0.CreditApplicationResource;
import fin.services.rest.resource.v1_0.TransactionResource;

import jakarta.annotation.Generated;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.ws.rs.core.UriInfo;

import java.util.Map;
import java.util.function.BiFunction;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author krism
 * @generated
 */
@Generated("")
public class Query {

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

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {account(accountId: ___){accountId, accountName, accountNumber, accountType, balance, createDate, modifiedDate, status, uuid, cardScheme, cardBrand, cardBankName, cardCountryName, ownerUserId, ownerUserName}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Account account(@GraphQLName("accountId") Long accountId)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> accountResource.getAccount(accountId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {accounts(accountType: ___, page: ___, pageSize: ___, search: ___, status: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountPage accounts(
			@GraphQLName("search") String search,
			@GraphQLName("accountType") String accountType,
			@GraphQLName("status") Integer status,
			@GraphQLName("ownerUserId") Long ownerUserId,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getAccountsPage(
					search, accountType, status, ownerUserId,
					Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {assignableB2BUsers{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public AccountPage assignableB2BUsers(
			@GraphQLName("search") String search,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_accountResourceComponentServiceObjects,
			this::_populateResourceContext,
			accountResource -> new AccountPage(
				accountResource.getAssignableB2BUsersPage(
					search, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {creditApplication(creditAppId: ___){accountId, applicantName, createDate, creditAppId, modifiedDate, purpose, requestedAmount, reviewNotes, status, uuid}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public CreditApplication creditApplication(
			@GraphQLName("creditAppId") Long creditAppId)
		throws Exception {

		return _applyComponentServiceObjects(
			_creditApplicationResourceComponentServiceObjects,
			this::_populateResourceContext,
			creditApplicationResource ->
				creditApplicationResource.getCreditApplication(creditAppId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {creditApplications(accountId: ___, page: ___, pageSize: ___, search: ___, status: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public CreditApplicationPage creditApplications(
			@GraphQLName("search") String search,
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("ownerUserId") Long ownerUserId,
			@GraphQLName("status") Integer status,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_creditApplicationResourceComponentServiceObjects,
			this::_populateResourceContext,
			creditApplicationResource -> new CreditApplicationPage(
				creditApplicationResource.getCreditApplicationsPage(
					search, accountId, ownerUserId, status,
					Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {transaction(transactionId: ___){accountId, amount, createDate, description, modifiedDate, status, transactionDate, transactionId, transactionType, uuid}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Transaction transaction(
			@GraphQLName("transactionId") Long transactionId)
		throws Exception {

		return _applyComponentServiceObjects(
			_transactionResourceComponentServiceObjects,
			this::_populateResourceContext,
			transactionResource -> transactionResource.getTransaction(
				transactionId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {transactions(accountId: ___, page: ___, pageSize: ___, search: ___, status: ___, transactionType: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TransactionPage transactions(
			@GraphQLName("search") String search,
			@GraphQLName("accountId") Long accountId,
			@GraphQLName("ownerUserId") Long ownerUserId,
			@GraphQLName("transactionType") String transactionType,
			@GraphQLName("status") Integer status,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_transactionResourceComponentServiceObjects,
			this::_populateResourceContext,
			transactionResource -> new TransactionPage(
				transactionResource.getTransactionsPage(
					search, accountId, ownerUserId, transactionType, status,
					Pagination.of(page, pageSize))));
	}

	@GraphQLTypeExtension(Transaction.class)
	public class GetAccountTypeExtension {

		public GetAccountTypeExtension(Transaction transaction) {
			_transaction = transaction;
		}

		@GraphQLField
		public Account account() throws Exception {
			return _applyComponentServiceObjects(
				_accountResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				accountResource -> accountResource.getAccount(
					_transaction.getAccountId()));
		}

		private Transaction _transaction;

	}

	@GraphQLName("AccountPage")
	public class AccountPage {

		public AccountPage(Page accountPage) {
			actions = accountPage.getActions();

			items = accountPage.getItems();
			lastPage = accountPage.getLastPage();
			page = accountPage.getPage();
			pageSize = accountPage.getPageSize();
			totalCount = accountPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<Account> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("CreditApplicationPage")
	public class CreditApplicationPage {

		public CreditApplicationPage(Page creditApplicationPage) {
			actions = creditApplicationPage.getActions();

			items = creditApplicationPage.getItems();
			lastPage = creditApplicationPage.getLastPage();
			page = creditApplicationPage.getPage();
			pageSize = creditApplicationPage.getPageSize();
			totalCount = creditApplicationPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<CreditApplication> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("TransactionPage")
	public class TransactionPage {

		public TransactionPage(Page transactionPage) {
			actions = transactionPage.getActions();

			items = transactionPage.getItems();
			lastPage = transactionPage.getLastPage();
			page = transactionPage.getPage();
			pageSize = transactionPage.getPageSize();
			totalCount = transactionPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<Transaction> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

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
	}

	private static ComponentServiceObjects<AccountResource>
		_accountResourceComponentServiceObjects;
	private static ComponentServiceObjects<CreditApplicationResource>
		_creditApplicationResourceComponentServiceObjects;
	private static ComponentServiceObjects<TransactionResource>
		_transactionResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction
		<Object, String, com.liferay.portal.kernel.search.filter.Filter>
			_filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, com.liferay.portal.kernel.search.Sort[]>
		_sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}