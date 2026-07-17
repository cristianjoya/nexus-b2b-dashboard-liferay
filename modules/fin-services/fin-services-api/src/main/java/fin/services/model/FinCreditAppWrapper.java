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
 * This class is a wrapper for {@link FinCreditApp}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FinCreditApp
 * @generated
 */
public class FinCreditAppWrapper
	extends BaseModelWrapper<FinCreditApp>
	implements FinCreditApp, ModelWrapper<FinCreditApp> {

	public FinCreditAppWrapper(FinCreditApp finCreditApp) {
		super(finCreditApp);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("creditAppId", getCreditAppId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountId", getAccountId());
		attributes.put("applicantName", getApplicantName());
		attributes.put("requestedAmount", getRequestedAmount());
		attributes.put("purpose", getPurpose());
		attributes.put("status", getStatus());
		attributes.put("reviewNotes", getReviewNotes());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long creditAppId = (Long)attributes.get("creditAppId");

		if (creditAppId != null) {
			setCreditAppId(creditAppId);
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

		String applicantName = (String)attributes.get("applicantName");

		if (applicantName != null) {
			setApplicantName(applicantName);
		}

		Double requestedAmount = (Double)attributes.get("requestedAmount");

		if (requestedAmount != null) {
			setRequestedAmount(requestedAmount);
		}

		String purpose = (String)attributes.get("purpose");

		if (purpose != null) {
			setPurpose(purpose);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		String reviewNotes = (String)attributes.get("reviewNotes");

		if (reviewNotes != null) {
			setReviewNotes(reviewNotes);
		}
	}

	@Override
	public FinCreditApp cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the account ID of this fin credit app.
	 *
	 * @return the account ID of this fin credit app
	 */
	@Override
	public long getAccountId() {
		return model.getAccountId();
	}

	/**
	 * Returns the applicant name of this fin credit app.
	 *
	 * @return the applicant name of this fin credit app
	 */
	@Override
	public String getApplicantName() {
		return model.getApplicantName();
	}

	/**
	 * Returns the company ID of this fin credit app.
	 *
	 * @return the company ID of this fin credit app
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this fin credit app.
	 *
	 * @return the create date of this fin credit app
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the credit app ID of this fin credit app.
	 *
	 * @return the credit app ID of this fin credit app
	 */
	@Override
	public long getCreditAppId() {
		return model.getCreditAppId();
	}

	/**
	 * Returns the group ID of this fin credit app.
	 *
	 * @return the group ID of this fin credit app
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this fin credit app.
	 *
	 * @return the modified date of this fin credit app
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the primary key of this fin credit app.
	 *
	 * @return the primary key of this fin credit app
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the purpose of this fin credit app.
	 *
	 * @return the purpose of this fin credit app
	 */
	@Override
	public String getPurpose() {
		return model.getPurpose();
	}

	/**
	 * Returns the requested amount of this fin credit app.
	 *
	 * @return the requested amount of this fin credit app
	 */
	@Override
	public double getRequestedAmount() {
		return model.getRequestedAmount();
	}

	/**
	 * Returns the review notes of this fin credit app.
	 *
	 * @return the review notes of this fin credit app
	 */
	@Override
	public String getReviewNotes() {
		return model.getReviewNotes();
	}

	/**
	 * Returns the status of this fin credit app.
	 *
	 * @return the status of this fin credit app
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the user ID of this fin credit app.
	 *
	 * @return the user ID of this fin credit app
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this fin credit app.
	 *
	 * @return the user name of this fin credit app
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this fin credit app.
	 *
	 * @return the user uuid of this fin credit app
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this fin credit app.
	 *
	 * @return the uuid of this fin credit app
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
	 * Sets the account ID of this fin credit app.
	 *
	 * @param accountId the account ID of this fin credit app
	 */
	@Override
	public void setAccountId(long accountId) {
		model.setAccountId(accountId);
	}

	/**
	 * Sets the applicant name of this fin credit app.
	 *
	 * @param applicantName the applicant name of this fin credit app
	 */
	@Override
	public void setApplicantName(String applicantName) {
		model.setApplicantName(applicantName);
	}

	/**
	 * Sets the company ID of this fin credit app.
	 *
	 * @param companyId the company ID of this fin credit app
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this fin credit app.
	 *
	 * @param createDate the create date of this fin credit app
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the credit app ID of this fin credit app.
	 *
	 * @param creditAppId the credit app ID of this fin credit app
	 */
	@Override
	public void setCreditAppId(long creditAppId) {
		model.setCreditAppId(creditAppId);
	}

	/**
	 * Sets the group ID of this fin credit app.
	 *
	 * @param groupId the group ID of this fin credit app
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this fin credit app.
	 *
	 * @param modifiedDate the modified date of this fin credit app
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the primary key of this fin credit app.
	 *
	 * @param primaryKey the primary key of this fin credit app
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the purpose of this fin credit app.
	 *
	 * @param purpose the purpose of this fin credit app
	 */
	@Override
	public void setPurpose(String purpose) {
		model.setPurpose(purpose);
	}

	/**
	 * Sets the requested amount of this fin credit app.
	 *
	 * @param requestedAmount the requested amount of this fin credit app
	 */
	@Override
	public void setRequestedAmount(double requestedAmount) {
		model.setRequestedAmount(requestedAmount);
	}

	/**
	 * Sets the review notes of this fin credit app.
	 *
	 * @param reviewNotes the review notes of this fin credit app
	 */
	@Override
	public void setReviewNotes(String reviewNotes) {
		model.setReviewNotes(reviewNotes);
	}

	/**
	 * Sets the status of this fin credit app.
	 *
	 * @param status the status of this fin credit app
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the user ID of this fin credit app.
	 *
	 * @param userId the user ID of this fin credit app
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this fin credit app.
	 *
	 * @param userName the user name of this fin credit app
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this fin credit app.
	 *
	 * @param userUuid the user uuid of this fin credit app
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this fin credit app.
	 *
	 * @param uuid the uuid of this fin credit app
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
	protected FinCreditAppWrapper wrap(FinCreditApp finCreditApp) {
		return new FinCreditAppWrapper(finCreditApp);
	}

}