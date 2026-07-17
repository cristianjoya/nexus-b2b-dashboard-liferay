/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.service;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import fin.services.model.FinTransaction;

import java.io.Serializable;

import java.util.Collection;
import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for FinTransaction. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see FinTransactionLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface FinTransactionLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>fin.services.service.impl.FinTransactionLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the fin transaction local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link FinTransactionLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the fin transaction to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinTransactionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finTransaction the fin transaction
	 * @return the fin transaction that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public FinTransaction addFinTransaction(FinTransaction finTransaction);

	public int countSearchFinTransactionsByGroupId(
		long groupId, String keywords, Long accountId, String transactionType,
		Integer status, Collection<Long> restrictedAccountIds);

	/**
	 * Creates a new fin transaction with the primary key. Does not add the fin transaction to the database.
	 *
	 * @param transactionId the primary key for the new fin transaction
	 * @return the new fin transaction
	 */
	@Transactional(enabled = false)
	public FinTransaction createFinTransaction(long transactionId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the fin transaction from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinTransactionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finTransaction the fin transaction
	 * @return the fin transaction that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public FinTransaction deleteFinTransaction(FinTransaction finTransaction);

	/**
	 * Deletes the fin transaction with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinTransactionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction that was removed
	 * @throws PortalException if a fin transaction with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public FinTransaction deleteFinTransaction(long transactionId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> T dslQuery(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int dslQueryCount(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FinTransaction fetchFinTransaction(long transactionId);

	/**
	 * Returns the fin transaction matching the UUID and group.
	 *
	 * @param uuid the fin transaction's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin transaction, or <code>null</code> if a matching fin transaction could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FinTransaction fetchFinTransactionByUuidAndGroupId(
		String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	/**
	 * Returns the fin transaction with the primary key.
	 *
	 * @param transactionId the primary key of the fin transaction
	 * @return the fin transaction
	 * @throws PortalException if a fin transaction with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FinTransaction getFinTransaction(long transactionId)
		throws PortalException;

	/**
	 * Returns the fin transaction matching the UUID and group.
	 *
	 * @param uuid the fin transaction's UUID
	 * @param groupId the primary key of the group
	 * @return the matching fin transaction
	 * @throws PortalException if a matching fin transaction could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public FinTransaction getFinTransactionByUuidAndGroupId(
			String uuid, long groupId)
		throws PortalException;

	/**
	 * Returns a range of all the fin transactions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>fin.services.model.impl.FinTransactionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @return the range of fin transactions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FinTransaction> getFinTransactions(int start, int end);

	/**
	 * Returns all the fin transactions matching the UUID and company.
	 *
	 * @param uuid the UUID of the fin transactions
	 * @param companyId the primary key of the company
	 * @return the matching fin transactions, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FinTransaction> getFinTransactionsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	 * Returns a range of fin transactions matching the UUID and company.
	 *
	 * @param uuid the UUID of the fin transactions
	 * @param companyId the primary key of the company
	 * @param start the lower bound of the range of fin transactions
	 * @param end the upper bound of the range of fin transactions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the range of matching fin transactions, or an empty list if no matches were found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FinTransaction> getFinTransactionsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<FinTransaction> orderByComparator);

	/**
	 * Returns the number of fin transactions.
	 *
	 * @return the number of fin transactions
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getFinTransactionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<FinTransaction> searchFinTransactionsByGroupId(
		long groupId, String keywords, Long accountId, String transactionType,
		Integer status, Collection<Long> restrictedAccountIds, int start,
		int end);

	/**
	 * Updates the fin transaction in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FinTransactionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param finTransaction the fin transaction
	 * @return the fin transaction that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public FinTransaction updateFinTransaction(FinTransaction finTransaction);

}