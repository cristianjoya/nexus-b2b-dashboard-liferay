/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import fin.services.model.FinCreditApp;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FinCreditApp in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FinCreditAppCacheModel
	implements CacheModel<FinCreditApp>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FinCreditAppCacheModel)) {
			return false;
		}

		FinCreditAppCacheModel finCreditAppCacheModel =
			(FinCreditAppCacheModel)object;

		if (creditAppId == finCreditAppCacheModel.creditAppId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, creditAppId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", creditAppId=");
		sb.append(creditAppId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", applicantName=");
		sb.append(applicantName);
		sb.append(", requestedAmount=");
		sb.append(requestedAmount);
		sb.append(", purpose=");
		sb.append(purpose);
		sb.append(", status=");
		sb.append(status);
		sb.append(", reviewNotes=");
		sb.append(reviewNotes);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FinCreditApp toEntityModel() {
		FinCreditAppImpl finCreditAppImpl = new FinCreditAppImpl();

		if (uuid == null) {
			finCreditAppImpl.setUuid("");
		}
		else {
			finCreditAppImpl.setUuid(uuid);
		}

		finCreditAppImpl.setCreditAppId(creditAppId);
		finCreditAppImpl.setGroupId(groupId);
		finCreditAppImpl.setCompanyId(companyId);
		finCreditAppImpl.setUserId(userId);

		if (userName == null) {
			finCreditAppImpl.setUserName("");
		}
		else {
			finCreditAppImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			finCreditAppImpl.setCreateDate(null);
		}
		else {
			finCreditAppImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			finCreditAppImpl.setModifiedDate(null);
		}
		else {
			finCreditAppImpl.setModifiedDate(new Date(modifiedDate));
		}

		finCreditAppImpl.setAccountId(accountId);

		if (applicantName == null) {
			finCreditAppImpl.setApplicantName("");
		}
		else {
			finCreditAppImpl.setApplicantName(applicantName);
		}

		finCreditAppImpl.setRequestedAmount(requestedAmount);

		if (purpose == null) {
			finCreditAppImpl.setPurpose("");
		}
		else {
			finCreditAppImpl.setPurpose(purpose);
		}

		finCreditAppImpl.setStatus(status);

		if (reviewNotes == null) {
			finCreditAppImpl.setReviewNotes("");
		}
		else {
			finCreditAppImpl.setReviewNotes(reviewNotes);
		}

		finCreditAppImpl.resetOriginalValues();

		return finCreditAppImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		creditAppId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		accountId = objectInput.readLong();
		applicantName = objectInput.readUTF();

		requestedAmount = objectInput.readDouble();
		purpose = objectInput.readUTF();

		status = objectInput.readInt();
		reviewNotes = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(creditAppId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(accountId);

		if (applicantName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(applicantName);
		}

		objectOutput.writeDouble(requestedAmount);

		if (purpose == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(purpose);
		}

		objectOutput.writeInt(status);

		if (reviewNotes == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(reviewNotes);
		}
	}

	public String uuid;
	public long creditAppId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountId;
	public String applicantName;
	public double requestedAmount;
	public String purpose;
	public int status;
	public String reviewNotes;

}