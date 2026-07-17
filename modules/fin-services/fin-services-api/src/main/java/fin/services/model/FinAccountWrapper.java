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
 * This class is a wrapper for {@link FinAccount}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinAccount
 * @generated
 */
public class FinAccountWrapper
	extends BaseModelWrapper<FinAccount>
	implements FinAccount, ModelWrapper<FinAccount> {

	public FinAccountWrapper(FinAccount finAccount) {
		super(finAccount);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("accountId", getAccountId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("ownerUserId", getOwnerUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountNumber", getAccountNumber());
		attributes.put("accountName", getAccountName());
		attributes.put("accountType", getAccountType());
		attributes.put("balance", getBalance());
		attributes.put("status", getStatus());
		attributes.put("cardScheme", getCardScheme());
		attributes.put("cardBrand", getCardBrand());
		attributes.put("cardBankName", getCardBankName());
		attributes.put("cardCountryName", getCardCountryName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
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

		Long ownerUserId = (Long)attributes.get("ownerUserId");

		if (ownerUserId != null) {
			setOwnerUserId(ownerUserId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String accountNumber = (String)attributes.get("accountNumber");

		if (accountNumber != null) {
			setAccountNumber(accountNumber);
		}

		String accountName = (String)attributes.get("accountName");

		if (accountName != null) {
			setAccountName(accountName);
		}

		String accountType = (String)attributes.get("accountType");

		if (accountType != null) {
			setAccountType(accountType);
		}

		Double balance = (Double)attributes.get("balance");

		if (balance != null) {
			setBalance(balance);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String cardScheme = (String)attributes.get("cardScheme");

		if (cardScheme != null) {
			setCardScheme(cardScheme);
		}

		String cardBrand = (String)attributes.get("cardBrand");

		if (cardBrand != null) {
			setCardBrand(cardBrand);
		}

		String cardBankName = (String)attributes.get("cardBankName");

		if (cardBankName != null) {
			setCardBankName(cardBankName);
		}

		String cardCountryName = (String)attributes.get("cardCountryName");

		if (cardCountryName != null) {
			setCardCountryName(cardCountryName);
		}
	}

	@Override
	public FinAccount cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the account ID of this fin account.
	 *
	 * @return the account ID of this fin account
	 */
	@Override
	public long getAccountId() {
		return model.getAccountId();
	}

	/**
	 * Returns the account name of this fin account.
	 *
	 * @return the account name of this fin account
	 */
	@Override
	public String getAccountName() {
		return model.getAccountName();
	}

	/**
	 * Returns the account number of this fin account.
	 *
	 * @return the account number of this fin account
	 */
	@Override
	public String getAccountNumber() {
		return model.getAccountNumber();
	}

	/**
	 * Returns the account type of this fin account.
	 *
	 * @return the account type of this fin account
	 */
	@Override
	public String getAccountType() {
		return model.getAccountType();
	}

	/**
	 * Returns the balance of this fin account.
	 *
	 * @return the balance of this fin account
	 */
	@Override
	public double getBalance() {
		return model.getBalance();
	}

	/**
	 * Returns the card bank name of this fin account.
	 *
	 * @return the card bank name of this fin account
	 */
	@Override
	public String getCardBankName() {
		return model.getCardBankName();
	}

	/**
	 * Returns the card brand of this fin account.
	 *
	 * @return the card brand of this fin account
	 */
	@Override
	public String getCardBrand() {
		return model.getCardBrand();
	}

	/**
	 * Returns the card country name of this fin account.
	 *
	 * @return the card country name of this fin account
	 */
	@Override
	public String getCardCountryName() {
		return model.getCardCountryName();
	}

	/**
	 * Returns the card scheme of this fin account.
	 *
	 * @return the card scheme of this fin account
	 */
	@Override
	public String getCardScheme() {
		return model.getCardScheme();
	}

	/**
	 * Returns the company ID of this fin account.
	 *
	 * @return the company ID of this fin account
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this fin account.
	 *
	 * @return the create date of this fin account
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the group ID of this fin account.
	 *
	 * @return the group ID of this fin account
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this fin account.
	 *
	 * @return the modified date of this fin account
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the owner user ID of this fin account.
	 *
	 * @return the owner user ID of this fin account
	 */
	@Override
	public long getOwnerUserId() {
		return model.getOwnerUserId();
	}

	/**
	 * Returns the owner user uuid of this fin account.
	 *
	 * @return the owner user uuid of this fin account
	 */
	@Override
	public String getOwnerUserUuid() {
		return model.getOwnerUserUuid();
	}

	/**
	 * Returns the primary key of this fin account.
	 *
	 * @return the primary key of this fin account
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this fin account.
	 *
	 * @return the status of this fin account
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the user ID of this fin account.
	 *
	 * @return the user ID of this fin account
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this fin account.
	 *
	 * @return the user name of this fin account
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this fin account.
	 *
	 * @return the user uuid of this fin account
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this fin account.
	 *
	 * @return the uuid of this fin account
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
	 * Sets the account ID of this fin account.
	 *
	 * @param accountId the account ID of this fin account
	 */
	@Override
	public void setAccountId(long accountId) {
		model.setAccountId(accountId);
	}

	/**
	 * Sets the account name of this fin account.
	 *
	 * @param accountName the account name of this fin account
	 */
	@Override
	public void setAccountName(String accountName) {
		model.setAccountName(accountName);
	}

	/**
	 * Sets the account number of this fin account.
	 *
	 * @param accountNumber the account number of this fin account
	 */
	@Override
	public void setAccountNumber(String accountNumber) {
		model.setAccountNumber(accountNumber);
	}

	/**
	 * Sets the account type of this fin account.
	 *
	 * @param accountType the account type of this fin account
	 */
	@Override
	public void setAccountType(String accountType) {
		model.setAccountType(accountType);
	}

	/**
	 * Sets the balance of this fin account.
	 *
	 * @param balance the balance of this fin account
	 */
	@Override
	public void setBalance(double balance) {
		model.setBalance(balance);
	}

	/**
	 * Sets the card bank name of this fin account.
	 *
	 * @param cardBankName the card bank name of this fin account
	 */
	@Override
	public void setCardBankName(String cardBankName) {
		model.setCardBankName(cardBankName);
	}

	/**
	 * Sets the card brand of this fin account.
	 *
	 * @param cardBrand the card brand of this fin account
	 */
	@Override
	public void setCardBrand(String cardBrand) {
		model.setCardBrand(cardBrand);
	}

	/**
	 * Sets the card country name of this fin account.
	 *
	 * @param cardCountryName the card country name of this fin account
	 */
	@Override
	public void setCardCountryName(String cardCountryName) {
		model.setCardCountryName(cardCountryName);
	}

	/**
	 * Sets the card scheme of this fin account.
	 *
	 * @param cardScheme the card scheme of this fin account
	 */
	@Override
	public void setCardScheme(String cardScheme) {
		model.setCardScheme(cardScheme);
	}

	/**
	 * Sets the company ID of this fin account.
	 *
	 * @param companyId the company ID of this fin account
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this fin account.
	 *
	 * @param createDate the create date of this fin account
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the group ID of this fin account.
	 *
	 * @param groupId the group ID of this fin account
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this fin account.
	 *
	 * @param modifiedDate the modified date of this fin account
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the owner user ID of this fin account.
	 *
	 * @param ownerUserId the owner user ID of this fin account
	 */
	@Override
	public void setOwnerUserId(long ownerUserId) {
		model.setOwnerUserId(ownerUserId);
	}

	/**
	 * Sets the owner user uuid of this fin account.
	 *
	 * @param ownerUserUuid the owner user uuid of this fin account
	 */
	@Override
	public void setOwnerUserUuid(String ownerUserUuid) {
		model.setOwnerUserUuid(ownerUserUuid);
	}

	/**
	 * Sets the primary key of this fin account.
	 *
	 * @param primaryKey the primary key of this fin account
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this fin account.
	 *
	 * @param status the status of this fin account
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the user ID of this fin account.
	 *
	 * @param userId the user ID of this fin account
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this fin account.
	 *
	 * @param userName the user name of this fin account
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this fin account.
	 *
	 * @param userUuid the user uuid of this fin account
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this fin account.
	 *
	 * @param uuid the uuid of this fin account
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
	protected FinAccountWrapper wrap(FinAccount finAccount) {
		return new FinAccountWrapper(finAccount);
	}

}