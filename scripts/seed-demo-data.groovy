import com.liferay.object.constants.ObjectDefinitionConstants
import com.liferay.object.model.ObjectDefinition
import com.liferay.object.model.ObjectEntry
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil
import com.liferay.object.service.ObjectEntryLocalServiceUtil
import com.liferay.portal.kernel.model.Group
import com.liferay.portal.kernel.model.User
import com.liferay.portal.kernel.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil
import com.liferay.portal.kernel.service.ServiceContext
import com.liferay.portal.kernel.service.UserLocalServiceUtil
import com.liferay.portal.kernel.util.PortalUtil

import java.io.Serializable
import java.util.Date
import java.util.HashMap

// Seed demo sobre Liferay Objects (Fin Account / Transaction / Credit).
// Control Panel → Configuration → Server Administration → Script (Groovy)
// Ajusta emails y pageFriendlyUrl antes de ejecutar.

def pageFriendlyUrl = "/nexus-b2b"
def managerEmail = "manager@example.com"
def b2bUserEmail = "b2b.user@example.com"

long companyId = PortalUtil.getDefaultCompanyId()
Group siteGroup = null

for (Group group : GroupLocalServiceUtil.getCompanyGroups(companyId, -1, -1)) {
	if (!group.isSite()) {
		continue
	}

	try {
		LayoutLocalServiceUtil.getFriendlyURLLayout(
			group.getGroupId(), false, pageFriendlyUrl)
		siteGroup = group
		break
	}
	catch (Exception ignored) {
	}
}

if (siteGroup == null) {
	return "No se encontro un sitio con la pagina ${pageFriendlyUrl}"
}

User manager = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, managerEmail)
User b2bUser = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, b2bUserEmail)

if (manager == null) {
	return "Manager no encontrado: ${managerEmail}"
}

if (b2bUser == null) {
	return "B2B User no encontrado: ${b2bUserEmail}"
}

long groupId = siteGroup.getGroupId()
long userId = manager.getUserId()
ServiceContext sc = new ServiceContext()
sc.setCompanyId(companyId)
sc.setScopeGroupId(groupId)
sc.setUserId(userId)

ObjectDefinition accountDef =
	ObjectDefinitionLocalServiceUtil.fetchObjectDefinitionByExternalReferenceCode(
		"FIN_ACCOUNT", companyId)
ObjectDefinition txDef =
	ObjectDefinitionLocalServiceUtil.fetchObjectDefinitionByExternalReferenceCode(
		"FIN_TRANSACTION", companyId)
ObjectDefinition creditDef =
	ObjectDefinitionLocalServiceUtil.fetchObjectDefinitionByExternalReferenceCode(
		"FIN_CREDIT_APPLICATION", companyId)

if (accountDef == null || txDef == null || creditDef == null) {
	return "Faltan Object Definitions FIN_ACCOUNT / FIN_TRANSACTION / FIN_CREDIT_APPLICATION"
}

StringBuilder log = new StringBuilder()

def findAccountByNumber = { String accountNumber ->
	def page = ObjectEntryLocalServiceUtil.getObjectEntries(
		groupId, accountDef.getObjectDefinitionId(), true, null, -1, -1)

	for (ObjectEntry entry : page) {
		Serializable value = entry.getValues().get("accountNumber")

		if (accountNumber.equals(String.valueOf(value))) {
			return entry
		}
	}

	return null
}

def createAccount = { String number, String name, String type, long ownerId, String scheme ->
	ObjectEntry existing = findAccountByNumber(number)

	if (existing != null) {
		log.append("SKIP cuenta ${number} (id=${existing.getObjectEntryId()})\n")
		return existing
	}

	Map<String, Serializable> values = new HashMap<>()
	values.put("accountNumber", number)
	values.put("accountName", name)
	values.put("accountType", type)
	values.put("balance", Double.valueOf(0d))
	values.put("nexusStatus", "1")
	values.put("ownerUserId", Long.valueOf(ownerId))
	values.put("cardScheme", scheme)
	values.put("cardBrand", scheme)

	ObjectEntry created = ObjectEntryLocalServiceUtil.addObjectEntry(
		userId, groupId, accountDef.getObjectDefinitionId(), values, sc)

	log.append("OK cuenta ${number} id=${created.getObjectEntryId()}\n")
	return created
}

ObjectEntry checking = createAccount(
	"4111111111111111", "Cuenta Corriente Demo", "CHECKING",
	b2bUser.getUserId(), "visa")
ObjectEntry savings = createAccount(
	"5555555555554444", "Cuenta Ahorros Demo", "SAVINGS",
	manager.getUserId(), "mastercard")

def addDeposit = { ObjectEntry account, double amount, String description ->
	Map<String, Serializable> values = new HashMap<>()
	values.put("transactionType", "DEPOSIT")
	values.put("amount", Double.valueOf(amount))
	values.put("transactionDate", new Date())
	values.put("nexusTransactionStatus", "1")
	values.put("description", description)
	values.put("r_finAccount_c_finAccountId", Long.valueOf(account.getObjectEntryId()))

	ObjectEntry created = ObjectEntryLocalServiceUtil.addObjectEntry(
		userId, groupId, txDef.getObjectDefinitionId(), values, sc)

	log.append("OK tx deposit ${amount} on ${account.getObjectEntryId()} -> ${created.getObjectEntryId()}\n")
}

addDeposit(checking, 1500d, "Deposito inicial demo")
addDeposit(savings, 800d, "Deposito inicial demo")

Map<String, Serializable> creditValues = new HashMap<>()
creditValues.put("applicantName", b2bUser.getFullName())
creditValues.put("requestedAmount", Double.valueOf(500d))
creditValues.put("purpose", "Capital de trabajo demo")
creditValues.put("nexusCreditStatus", "0")
creditValues.put("reviewNotes", "")
creditValues.put(
	"r_finAccounts_c_finAccountId", Long.valueOf(checking.getObjectEntryId()))

ObjectEntry credit = ObjectEntryLocalServiceUtil.addObjectEntry(
	userId, groupId, creditDef.getObjectDefinitionId(), creditValues, sc)

log.append("OK credit pendiente id=${credit.getObjectEntryId()}\n")
log.append("Sitio groupId=${groupId}. Recarga el dashboard.\n")

return log.toString()
