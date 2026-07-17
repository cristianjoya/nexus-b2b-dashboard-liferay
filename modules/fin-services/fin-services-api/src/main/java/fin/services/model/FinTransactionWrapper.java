/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FinTransaction}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinTransaction
 * @generated
 */
public class FinTransactionWrapper
	extends BaseModelWrapper<FinTransaction>
	implements FinTransaction, ModelWrapper<FinTransaction> {

	public FinTransactionWrapper(FinTransaction finTransaction) {
		super(finTransaction);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("transactionId", getTransactionId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountId", getAccountId());
		attributes.put("transactionType", getTransactionType());
		attributes.put("amount", getAmount());
		attributes.put("description", getDescription());
		attributes.put("transactionDate", getTransactionDate());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long transactionId = (Long)attributes.get("transactionId");

		if (transactionId != null) {
			setTransactionId(transactionId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		String transactionType = (String)attributes.get("transactionType");

		if (transactionType != null) {
			setTransactionType(transactionType);
		}

		Double amount = (Double)attributes.get("amount");

		if (amount != null) {
			setAmount(amount);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date transactionDate = (Date)attributes.get("transactionDate");

		if (transactionDate != null) {
			setTransactionDate(transactionDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public FinTransaction cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the account ID of this fin transaction.
	 *
	 * @return the account ID of this fin transaction
	 */
	@Override
	public long getAccountId() {
		return model.getAccountId();
	}

	/**
	 * Returns the amount of this fin transaction.
	 *
	 * @return the amount of this fin transaction
	 */
	@Override
	public double getAmount() {
		return model.getAmount();
	}

	/**
	 * Returns the company ID of this fin transaction.
	 *
	 * @return the company ID of this fin transaction
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this fin transaction.
	 *
	 * @return the create date of this fin transaction
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this fin transaction.
	 *
	 * @return the description of this fin transaction
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the group ID of this fin transaction.
	 *
	 * @return the group ID of this fin transaction
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this fin transaction.
	 *
	 * @return the modified date of this fin transaction
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this fin transaction.
	 *
	 * @return the primary key of this fin transaction
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this fin transaction.
	 *
	 * @return the status of this fin transaction
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the transaction date of this fin transaction.
	 *
	 * @return the transaction date of this fin transaction
	 */
	@Override
	public Date getTransactionDate() {
		return model.getTransactionDate();
	}

	/**
	 * Returns the transaction ID of this fin transaction.
	 *
	 * @return the transaction ID of this fin transaction
	 */
	@Override
	public long getTransactionId() {
		return model.getTransactionId();
	}

	/**
	 * Returns the transaction type of this fin transaction.
	 *
	 * @return the transaction type of this fin transaction
	 */
	@Override
	public String getTransactionType() {
		return model.getTransactionType();
	}

	/**
	 * Returns the user ID of this fin transaction.
	 *
	 * @return the user ID of this fin transaction
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this fin transaction.
	 *
	 * @return the user name of this fin transaction
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this fin transaction.
	 *
	 * @return the user uuid of this fin transaction
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this fin transaction.
	 *
	 * @return the uuid of this fin transaction
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the account ID of this fin transaction.
	 *
	 * @param accountId the account ID of this fin transaction
	 */
	@Override
	public void setAccountId(long accountId) {
		model.setAccountId(accountId);
	}

	/**
	 * Sets the amount of this fin transaction.
	 *
	 * @param amount the amount of this fin transaction
	 */
	@Override
	public void setAmount(double amount) {
		model.setAmount(amount);
	}

	/**
	 * Sets the company ID of this fin transaction.
	 *
	 * @param companyId the company ID of this fin transaction
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this fin transaction.
	 *
	 * @param createDate the create date of this fin transaction
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this fin transaction.
	 *
	 * @param description the description of this fin transaction
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the group ID of this fin transaction.
	 *
	 * @param groupId the group ID of this fin transaction
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this fin transaction.
	 *
	 * @param modifiedDate the modified date of this fin transaction
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this fin transaction.
	 *
	 * @param primaryKey the primary key of this fin transaction
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this fin transaction.
	 *
	 * @param status the status of this fin transaction
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the transaction date of this fin transaction.
	 *
	 * @param transactionDate the transaction date of this fin transaction
	 */
	@Override
	public void setTransactionDate(Date transactionDate) {
		model.setTransactionDate(transactionDate);
	}

	/**
	 * Sets the transaction ID of this fin transaction.
	 *
	 * @param transactionId the transaction ID of this fin transaction
	 */
	@Override
	public void setTransactionId(long transactionId) {
		model.setTransactionId(transactionId);
	}

	/**
	 * Sets the transaction type of this fin transaction.
	 *
	 * @param transactionType the transaction type of this fin transaction
	 */
	@Override
	public void setTransactionType(String transactionType) {
		model.setTransactionType(transactionType);
	}

	/**
	 * Sets the user ID of this fin transaction.
	 *
	 * @param userId the user ID of this fin transaction
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this fin transaction.
	 *
	 * @param userName the user name of this fin transaction
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this fin transaction.
	 *
	 * @param userUuid the user uuid of this fin transaction
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this fin transaction.
	 *
	 * @param uuid the uuid of this fin transaction
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected FinTransactionWrapper wrap(FinTransaction finTransaction) {
		return new FinTransactionWrapper(finTransaction);
	}

}