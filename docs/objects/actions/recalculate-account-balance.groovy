import com.liferay.list.type.service.ListTypeEntryLocalServiceUtil
import com.liferay.object.model.ObjectDefinition
import com.liferay.object.model.ObjectEntry
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil
import com.liferay.object.service.ObjectEntryLocalServiceUtil
import com.liferay.portal.kernel.log.Log
import com.liferay.portal.kernel.log.LogFactoryUtil
import com.liferay.portal.kernel.service.ServiceContext

import java.io.Serializable

/**
 * Object Action Groovy: recalcula FinAccount.balance
 * a partir de transacciones Completadas + créditos Aprobados.
 *
 * Triggers: On After Add / Update / Delete
 * Objects: Fin Transaction y Fin Credit Application
 *
 * Variables de contexto Liferay: entryDTO, currentUserId
 */

Log log = LogFactoryUtil.getLog("nexus.fin.ledger")

String ACCOUNT_REL_TX = "r_finAccount_c_finAccountId"
String ACCOUNT_REL_CREDIT = "r_finAccounts_c_finAccountId"

try {
	Map properties = _properties(entryDTO)

	if (properties == null) {
		log.error("entryDTO without properties")
		return
	}

	Long accountEntryId = _accountIdFrom(properties)

	if (accountEntryId == null || accountEntryId <= 0) {
		log.warn("No related Fin Account on entry; skip ledger. keys=" + properties.keySet())
		return
	}

	ObjectEntry account = ObjectEntryLocalServiceUtil.getObjectEntry(accountEntryId)
	long companyId = account.getCompanyId()
	long groupId = account.getGroupId()

	ObjectDefinition txDef =
		ObjectDefinitionLocalServiceUtil.getObjectDefinitionByExternalReferenceCode(
			"FIN_TRANSACTION", companyId)
	ObjectDefinition creditDef =
		ObjectDefinitionLocalServiceUtil.getObjectDefinitionByExternalReferenceCode(
			"FIN_CREDIT_APPLICATION", companyId)

	double balance = 0d

	for (ObjectEntry tx : ObjectEntryLocalServiceUtil.getObjectEntries(
			groupId, txDef.getObjectDefinitionId(), -1, -1)) {

		Map values = tx.getValues()

		if (!_sameId(_accountIdFrom(values), accountEntryId)) {
			continue
		}

		if (_picklistKey(values.get("nexusTransactionStatus")) != "1") {
			continue
		}

		double amount = _toDouble(values.get("amount"))
		String type = _picklistKey(values.get("transactionType"))

		if (type == "DEPOSIT") {
			balance += amount
		}
		else if (type == "WITHDRAWAL" || type == "PAYMENT") {
			balance -= amount
		}
	}

	for (ObjectEntry credit : ObjectEntryLocalServiceUtil.getObjectEntries(
			groupId, creditDef.getObjectDefinitionId(), -1, -1)) {

		Map values = credit.getValues()

		if (!_sameId(_accountIdFrom(values), accountEntryId)) {
			continue
		}

		if (_picklistKey(values.get("nexusCreditStatus")) != "1") {
			continue
		}

		balance += _toDouble(values.get("requestedAmount"))
	}

	Map<String, Serializable> updates = new HashMap<>()
	updates.put("balance", Double.valueOf(balance))

	ServiceContext serviceContext = new ServiceContext()
	serviceContext.setCompanyId(companyId)
	serviceContext.setScopeGroupId(groupId)

	long objectEntryFolderId = account.getObjectEntryFolderId()

	ObjectEntryLocalServiceUtil.partialUpdateObjectEntry(
		(long) currentUserId, accountEntryId, objectEntryFolderId, updates,
		serviceContext)

	log.info("Fin Account " + accountEntryId + " balance=" + balance)
}
catch (Exception exception) {
	log.error("Ledger recalc failed: " + exception.getMessage(), exception)
	throw exception
}

Map _properties(def dto) {
	if (dto == null) {
		return null
	}

	if (dto instanceof Map) {
		def props = dto.get("properties")

		return (props instanceof Map) ? (Map) props : (Map) dto
	}

	try {
		def props = dto.properties

		if (props instanceof Map) {
			return (Map) props
		}
	}
	catch (Exception ignored) {
	}

	return null
}

Long _accountIdFrom(Map values) {
	if (values == null) {
		return null
	}

	Long id = _toLong(values.get("r_finAccount_c_finAccountId"))

	if (id != null) {
		return id
	}

	id = _toLong(values.get("r_finAccounts_c_finAccountId"))

	if (id != null) {
		return id
	}

	id = _relatedId(values.get("finAccount"))

	if (id != null) {
		return id
	}

	return _relatedId(values.get("finAccounts"))
}

Long _relatedId(def value) {
	if (value == null) {
		return null
	}

	if (value instanceof Number) {
		return ((Number) value).longValue()
	}

	if (value instanceof Map) {
		return _toLong(value.get("id") ?: value.get("objectEntryId"))
	}

	return _toLong(value)
}

boolean _sameId(Long left, Long right) {
	if (left == null || right == null) {
		return false
	}

	return left.longValue() == right.longValue()
}

String _picklistKey(def value) {
	if (value == null) {
		return ""
	}

	if (value instanceof Map) {
		def key = value.get("key")

		if (key != null) {
			return String.valueOf(key)
		}

		def nestedId = value.get("listTypeEntryId") ?: value.get("id")

		if (nestedId != null) {
			return _picklistKey(nestedId)
		}

		return ""
	}

	if (value instanceof Number) {
		try {
			def entry = ListTypeEntryLocalServiceUtil.fetchListTypeEntry(
				((Number) value).longValue())

			if (entry != null && entry.getKey() != null) {
				return entry.getKey()
			}
		}
		catch (Exception ignored) {
		}

		return String.valueOf(((Number) value).longValue())
	}

	return String.valueOf(value).trim()
}

Long _toLong(def value) {
	if (value == null || value == "null") {
		return null
	}

	if (value instanceof Number) {
		return ((Number) value).longValue()
	}

	if (value instanceof Map) {
		return _toLong(value.get("id") ?: value.get("key") ?: value.get("value"))
	}

	String text = String.valueOf(value).trim()

	if (text.isEmpty()) {
		return null
	}

	return Long.parseLong(text)
}

double _toDouble(def value) {
	if (value == null) {
		return 0d
	}

	if (value instanceof Number) {
		return ((Number) value).doubleValue()
	}

	String text = String.valueOf(value).trim()

	if (text.isEmpty() || text == "null") {
		return 0d
	}

	return Double.parseDouble(text)
}
