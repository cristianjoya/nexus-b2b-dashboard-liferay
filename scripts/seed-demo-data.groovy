import com.liferay.counter.kernel.service.CounterLocalServiceUtil
import com.liferay.portal.kernel.model.Group
import com.liferay.portal.kernel.model.User
import com.liferay.portal.kernel.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil
import com.liferay.portal.kernel.service.UserLocalServiceUtil
import com.liferay.portal.kernel.util.PortalUtil

import fin.services.model.FinAccount
import fin.services.model.FinCreditApp
import fin.services.model.FinTransaction
import fin.services.service.FinAccountLocalServiceUtil
import fin.services.service.FinCreditAppLocalServiceUtil
import fin.services.service.FinTransactionLocalServiceUtil

import java.util.Date

// =============================================================================
// Seed de datos de demo — Server Administration → Script (Groovy)
// Ajusta emails y pageFriendlyUrl antes de ejecutar.
// =============================================================================

def pageFriendlyUrl = "/nexus-b2b"
def managerEmail = "manager@example.com"   // usuario que ejecuta / crea registros
def b2bUserEmail = "b2b.user@example.com"  // owner de la cuenta demo B2B

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
	return "B2B User no encontrado: ${b2bUserEmail}. Crea el usuario y asigna rol B2B User primero."
}

long groupId = siteGroup.getGroupId()
Date now = new Date()
StringBuilder log = new StringBuilder()

def ensureAccount = { String number, String name, String type, double balance, long ownerId, String scheme ->
	FinAccount existing =
		FinAccountLocalServiceUtil.fetchFinAccountByGroupIdAndAccountNumber(
			groupId, number)

	if (existing != null) {
		log.append("SKIP cuenta ${number} (ya existe id=${existing.getAccountId()})\n")
		return existing
	}

	long accountId = CounterLocalServiceUtil.increment(FinAccount.class.getName())
	FinAccount account = FinAccountLocalServiceUtil.createFinAccount(accountId)

	account.setGroupId(groupId)
	account.setCompanyId(companyId)
	account.setUserId(manager.getUserId())
	account.setUserName(manager.getFullName())
	account.setCreateDate(now)
	account.setModifiedDate(now)
	account.setAccountNumber(number)
	account.setAccountName(name)
	account.setAccountType(type)
	account.setBalance(balance)
	account.setStatus(1)
	account.setOwnerUserId(ownerId)
	account.setCardScheme(scheme)
	account.setCardBrand(scheme.substring(0, 1).toUpperCase() + scheme.substring(1))

	FinAccount saved = FinAccountLocalServiceUtil.addFinAccount(account)
	log.append("OK cuenta ${number} id=${saved.getAccountId()} owner=${ownerId}\n")
	return saved
}

// Números de prueba (Luhn OK) — solo para demo, no son PAN reales de clientes
FinAccount managerAccount = ensureAccount(
	"4111111111111111", "Cuenta Demo Manager", "CHECKING", 5_000_000d,
	manager.getUserId(), "visa")

FinAccount userAccount = ensureAccount(
	"5555555555554444", "Cuenta Demo B2B User", "CHECKING", 1_300_000d,
	b2bUser.getUserId(), "mastercard")

def ensureTransaction = { FinAccount account, String type, double amount, String description ->
	List existingTx = FinTransactionLocalServiceUtil.getFinTransactions(-1, -1).findAll {
		it.getGroupId() == groupId &&
			it.getAccountId() == account.getAccountId() &&
			it.getDescription() == description
	}

	if (!existingTx.isEmpty()) {
		log.append("SKIP tx '${description}'\n")
		return
	}

	long txId = CounterLocalServiceUtil.increment(FinTransaction.class.getName())
	FinTransaction tx = FinTransactionLocalServiceUtil.createFinTransaction(txId)

	tx.setGroupId(groupId)
	tx.setCompanyId(companyId)
	tx.setUserId(manager.getUserId())
	tx.setUserName(manager.getFullName())
	tx.setCreateDate(now)
	tx.setModifiedDate(now)
	tx.setAccountId(account.getAccountId())
	tx.setTransactionType(type)
	tx.setAmount(amount)
	tx.setDescription(description)
	tx.setStatus(1)
	tx.setTransactionDate(now)

	FinTransactionLocalServiceUtil.addFinTransaction(tx)
	log.append("OK tx '${description}' (${type}) en accountId=${account.getAccountId()}\n")
}

// Tipos válidos: DEPOSIT / WITHDRAWAL / PAYMENT (addFinTransaction ajusta el saldo)
ensureTransaction(userAccount, "DEPOSIT", 250_000d, "Abono demo seed")
ensureTransaction(userAccount, "WITHDRAWAL", 50_000d, "Retiro demo seed")

def ensureCredit = { FinAccount account, String applicant, double amount, int status ->
	List existing = FinCreditAppLocalServiceUtil.getFinCreditApps(-1, -1).findAll {
		it.getGroupId() == groupId &&
			it.getAccountId() == account.getAccountId() &&
			it.getApplicantName() == applicant
	}

	if (!existing.isEmpty()) {
		log.append("SKIP credito '${applicant}'\n")
		return
	}

	long creditId = CounterLocalServiceUtil.increment(FinCreditApp.class.getName())
	FinCreditApp credit = FinCreditAppLocalServiceUtil.createFinCreditApp(creditId)

	credit.setGroupId(groupId)
	credit.setCompanyId(companyId)
	credit.setUserId(b2bUser.getUserId())
	credit.setUserName(b2bUser.getFullName())
	credit.setCreateDate(now)
	credit.setModifiedDate(now)
	credit.setAccountId(account.getAccountId())
	credit.setApplicantName(applicant)
	credit.setRequestedAmount(amount)
	credit.setPurpose("Capital de trabajo (seed demo)")
	credit.setStatus(status)
	credit.setReviewNotes(status == 0 ? "" : "Seed demo")

	FinCreditAppLocalServiceUtil.addFinCreditApp(credit)
	log.append("OK credito '${applicant}' status=${status}\n")
}

// status 0 = pendiente (no abona saldo al crear)
ensureCredit(userAccount, "Demo Applicant B2B", 800_000d, 0)

log.append(
	"\nListo. Sitio='${siteGroup.getName()}' groupId=${groupId}. " +
	"Recarga el dashboard. Manager=${managerEmail}, B2B User=${b2bUserEmail}.")

log.toString()
