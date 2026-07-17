/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import fin.services.model.FinAccount;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing FinAccount in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FinAccountCacheModel
	implements CacheModel<FinAccount>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FinAccountCacheModel)) {
			return false;
		}

		FinAccountCacheModel finAccountCacheModel =
			(FinAccountCacheModel)object;

		if (accountId == finAccountCacheModel.accountId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", ownerUserId=");
		sb.append(ownerUserId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountNumber=");
		sb.append(accountNumber);
		sb.append(", accountName=");
		sb.append(accountName);
		sb.append(", accountType=");
		sb.append(accountType);
		sb.append(", balance=");
		sb.append(balance);
		sb.append(", status=");
		sb.append(status);
		sb.append(", cardScheme=");
		sb.append(cardScheme);
		sb.append(", cardBrand=");
		sb.append(cardBrand);
		sb.append(", cardBankName=");
		sb.append(cardBankName);
		sb.append(", cardCountryName=");
		sb.append(cardCountryName);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FinAccount toEntityModel() {
		FinAccountImpl finAccountImpl = new FinAccountImpl();

		if (uuid == null) {
			finAccountImpl.setUuid("");
		}
		else {
			finAccountImpl.setUuid(uuid);
		}

		finAccountImpl.setAccountId(accountId);
		finAccountImpl.setGroupId(groupId);
		finAccountImpl.setCompanyId(companyId);
		finAccountImpl.setUserId(userId);

		if (userName == null) {
			finAccountImpl.setUserName("");
		}
		else {
			finAccountImpl.setUserName(userName);
		}

		finAccountImpl.setOwnerUserId(ownerUserId);

		if (createDate == Long.MIN_VALUE) {
			finAccountImpl.setCreateDate(null);
		}
		else {
			finAccountImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			finAccountImpl.setModifiedDate(null);
		}
		else {
			finAccountImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (accountNumber == null) {
			finAccountImpl.setAccountNumber("");
		}
		else {
			finAccountImpl.setAccountNumber(accountNumber);
		}

		if (accountName == null) {
			finAccountImpl.setAccountName("");
		}
		else {
			finAccountImpl.setAccountName(accountName);
		}

		if (accountType == null) {
			finAccountImpl.setAccountType("");
		}
		else {
			finAccountImpl.setAccountType(accountType);
		}

		finAccountImpl.setBalance(balance);
		finAccountImpl.setStatus(status);

		if (cardScheme == null) {
			finAccountImpl.setCardScheme("");
		}
		else {
			finAccountImpl.setCardScheme(cardScheme);
		}

		if (cardBrand == null) {
			finAccountImpl.setCardBrand("");
		}
		else {
			finAccountImpl.setCardBrand(cardBrand);
		}

		if (cardBankName == null) {
			finAccountImpl.setCardBankName("");
		}
		else {
			finAccountImpl.setCardBankName(cardBankName);
		}

		if (cardCountryName == null) {
			finAccountImpl.setCardCountryName("");
		}
		else {
			finAccountImpl.setCardCountryName(cardCountryName);
		}

		finAccountImpl.resetOriginalValues();

		return finAccountImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		accountId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();

		ownerUserId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		accountNumber = objectInput.readUTF();
		accountName = objectInput.readUTF();
		accountType = objectInput.readUTF();

		balance = objectInput.readDouble();

		status = objectInput.readInt();
		cardScheme = objectInput.readUTF();
		cardBrand = objectInput.readUTF();
		cardBankName = objectInput.readUTF();
		cardCountryName = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(accountId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(ownerUserId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (accountNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accountNumber);
		}

		if (accountName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accountName);
		}

		if (accountType == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(accountType);
		}

		objectOutput.writeDouble(balance);

		objectOutput.writeInt(status);

		if (cardScheme == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cardScheme);
		}

		if (cardBrand == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cardBrand);
		}

		if (cardBankName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cardBankName);
		}

		if (cardCountryName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(cardCountryName);
		}
	}

	public String uuid;
	public long accountId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long ownerUserId;
	public long createDate;
	public long modifiedDate;
	public String accountNumber;
	public String accountName;
	public String accountType;
	public double balance;
	public int status;
	public String cardScheme;
	public String cardBrand;
	public String cardBankName;
	public String cardCountryName;

}