/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import fin.services.model.FinTransaction;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FinTransaction in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FinTransactionCacheModel
	implements CacheModel<FinTransaction>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FinTransactionCacheModel)) {
			return false;
		}

		FinTransactionCacheModel finTransactionCacheModel =
			(FinTransactionCacheModel)object;

		if (transactionId == finTransactionCacheModel.transactionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, transactionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", transactionId=");
		sb.append(transactionId);
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
		sb.append(", transactionType=");
		sb.append(transactionType);
		sb.append(", amount=");
		sb.append(amount);
		sb.append(", description=");
		sb.append(description);
		sb.append(", transactionDate=");
		sb.append(transactionDate);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FinTransaction toEntityModel() {
		FinTransactionImpl finTransactionImpl = new FinTransactionImpl();

		if (uuid == null) {
			finTransactionImpl.setUuid("");
		}
		else {
			finTransactionImpl.setUuid(uuid);
		}

		finTransactionImpl.setTransactionId(transactionId);
		finTransactionImpl.setGroupId(groupId);
		finTransactionImpl.setCompanyId(companyId);
		finTransactionImpl.setUserId(userId);

		if (userName == null) {
			finTransactionImpl.setUserName("");
		}
		else {
			finTransactionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			finTransactionImpl.setCreateDate(null);
		}
		else {
			finTransactionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			finTransactionImpl.setModifiedDate(null);
		}
		else {
			finTransactionImpl.setModifiedDate(new Date(modifiedDate));
		}

		finTransactionImpl.setAccountId(accountId);

		if (transactionType == null) {
			finTransactionImpl.setTransactionType("");
		}
		else {
			finTransactionImpl.setTransactionType(transactionType);
		}

		finTransactionImpl.setAmount(amount);

		if (description == null) {
			finTransactionImpl.setDescription("");
		}
		else {
			finTransactionImpl.setDescription(description);
		}

		if (transactionDate == Long.MIN_VALUE) {
			finTransactionImpl.setTransactionDate(null);
		}
		else {
			finTransactionImpl.setTransactionDate(new Date(transactionDate));
		}

		finTransactionImpl.setStatus(status);

		finTransactionImpl.resetOriginalValues();

		return finTransactionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		transactionId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		accountId = objectInput.readLong();
		transactionType = objectInput.readUTF();

		amount = objectInput.readDouble();
		description = objectInput.readUTF();
		transactionDate = objectInput.readLong();

		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(transactionId);

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

		if (transactionType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(transactionType);
		}

		objectOutput.writeDouble(amount);

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeLong(transactionDate);

		objectOutput.writeInt(status);
	}

	public String uuid;
	public long transactionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountId;
	public String transactionType;
	public double amount;
	public String description;
	public long transactionDate;
	public int status;

}