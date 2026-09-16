import com.liferay.object.constants.ObjectActionKeys
import com.liferay.object.model.ObjectDefinition
import com.liferay.object.service.ObjectDefinitionLocalServiceUtil
import com.liferay.portal.kernel.model.Group
import com.liferay.portal.kernel.model.ResourceConstants
import com.liferay.portal.kernel.model.Role
import com.liferay.portal.kernel.model.RoleConstants
import com.liferay.portal.kernel.model.User
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal
import com.liferay.portal.kernel.security.permission.ActionKeys
import com.liferay.portal.kernel.security.permission.PermissionChecker
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal
import com.liferay.portal.kernel.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil
import com.liferay.portal.kernel.service.RoleLocalServiceUtil
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil
import com.liferay.portal.kernel.service.UserLocalServiceUtil
import com.liferay.portal.kernel.util.PortalUtil

/**
 * Permisos Objects → roles B2B Manager / B2B User.
 * ADD_OBJECT_ENTRY en getResourceName(); VIEW/UPDATE/DELETE en getClassName().
 * Matriz: User solo VIEW en cuentas; ADD en txs/crédito. Manager CRUD completo.
 *
 * Server Administration → Script → Execute. El log debe incluir VERIFY OK.
 */

def pageFriendlyUrl = "/nexus-b2b"
def verifyUserEmail = "" // opcional: "user@example.com" para VERIFY como ese usuario

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
	return "ERROR: no hay sitio con página ${pageFriendlyUrl}. Ajusta pageFriendlyUrl."
}

long siteGroupId = siteGroup.getGroupId()

def objectErcs = [
	"FIN_ACCOUNT",
	"FIN_TRANSACTION",
	"FIN_CREDIT_APPLICATION",
]

Role managerRole = RoleLocalServiceUtil.getRole(companyId, "B2B Manager")
Role userRole = RoleLocalServiceUtil.getRole(companyId, "B2B User")

def log = new StringBuilder()

log.append("siteGroupId=${siteGroupId} site=${siteGroup.getFriendlyURL()}\n")
log.append("managerRoleId=${managerRole.getRoleId()} type=${managerRole.getType()} (site=${RoleConstants.TYPE_SITE})\n")
log.append("userRoleId=${userRole.getRoleId()} type=${userRole.getType()} (site=${RoleConstants.TYPE_SITE})\n")

if (managerRole.getType() != RoleConstants.TYPE_SITE ||
		userRole.getType() != RoleConstants.TYPE_SITE) {
	log.append("WARN: los roles B2B deben ser de tipo SITIO (type=2). Si son Regular, re-créalos como Site Role.\n")
}

log.append("\n")

def availableActions = { String resourceName ->
	ResourceActionLocalServiceUtil.getResourceActions(resourceName).collect {
		it.getActionId()
	} as Set
}

def filterActions = { List desired, Set available ->
	desired.findAll { available.contains(it) } as String[]
}

def tryEnsure = { String resourceName, List desired ->
	try {
		ResourceActionLocalServiceUtil.checkResourceActions(resourceName, desired as List)
	}
	catch (Exception ignored) {
	}
}

def grant = { String resourceName, int scope, String primKey, long roleId,
		String[] actionIds, String label ->
	if (actionIds == null || actionIds.length == 0) {
		log.append("SKIP ${label} — sin acciones válidas en ${resourceName}\n")

		return
	}

	ResourcePermissionLocalServiceUtil.setResourcePermissions(
		companyId, resourceName, scope, primKey, roleId, actionIds)

	log.append("OK ${label} → ${actionIds.join(', ')}\n")
}

def grantAllScopes = { String resourceName, String[] managerActions,
		String[] userActions, String labelPrefix ->
	[
		[ResourceConstants.SCOPE_GROUP_TEMPLATE, "0", "GROUP_TEMPLATE"],
		[ResourceConstants.SCOPE_COMPANY, String.valueOf(companyId), "COMPANY"],
		[ResourceConstants.SCOPE_GROUP, String.valueOf(siteGroupId), "GROUP ${siteGroupId}"],
	].each { row ->
		grant(resourceName, row[0] as int, row[1] as String, managerRole.getRoleId(),
			managerActions, "${labelPrefix} Manager ${row[2]}")
		grant(resourceName, row[0] as int, row[1] as String, userRole.getRoleId(),
			userActions, "${labelPrefix} User ${row[2]}")
	}
}

/**
 * Matriz de acciones por objeto (Manager vs User).
 */
def matrix = [
	FIN_ACCOUNT: [
		managerEntry: [ActionKeys.VIEW, ActionKeys.UPDATE, ActionKeys.DELETE, ActionKeys.PERMISSIONS],
		userEntry: [ActionKeys.VIEW],
		managerAdd: true,
		userAdd: false,
	],
	FIN_TRANSACTION: [
		managerEntry: [ActionKeys.VIEW, ActionKeys.UPDATE, ActionKeys.DELETE, ActionKeys.PERMISSIONS],
		userEntry: [ActionKeys.VIEW, ActionKeys.UPDATE],
		managerAdd: true,
		userAdd: true,
	],
	FIN_CREDIT_APPLICATION: [
		managerEntry: [ActionKeys.VIEW, ActionKeys.UPDATE, ActionKeys.DELETE, ActionKeys.PERMISSIONS],
		userEntry: [ActionKeys.VIEW, ActionKeys.UPDATE],
		managerAdd: true,
		userAdd: true,
	],
]

for (String erc : objectErcs) {
	ObjectDefinition od =
		ObjectDefinitionLocalServiceUtil.getObjectDefinitionByExternalReferenceCode(
			erc, companyId)

	String className = od.getClassName()
	String resourceName = od.getResourceName()
	String portletId = od.getPortletId()
	def spec = matrix[erc]

	log.append("--- ${erc} ---\n")
	log.append("className=${className}\n")
	log.append("resourceName=${resourceName}\n")
	log.append("portletId=${portletId}\n")
	log.append("restContextPath=${od.getRESTContextPath()}\n")
	log.append("scope=${od.getScope()}\n")
	log.append("userAdd=${spec.userAdd} managerAdd=${spec.managerAdd}\n")

	def entryDesiredManager = spec.managerEntry as List
	def entryDesiredUser = spec.userEntry as List

	tryEnsure(className, entryDesiredManager + [ObjectActionKeys.ADD_OBJECT_ENTRY])

	def entryAvailable = availableActions(className)

	log.append("acciones className: ${entryAvailable}\n")

	grantAllScopes(
		className,
		filterActions(entryDesiredManager, entryAvailable),
		filterActions(entryDesiredUser, entryAvailable),
		"${erc} entry")

	tryEnsure(resourceName, entryDesiredManager + [ObjectActionKeys.ADD_OBJECT_ENTRY])

	def resourceAvailable = availableActions(resourceName)

	log.append("acciones resourceName: ${resourceAvailable}\n")

	def managerResourceDesired = new ArrayList(entryDesiredManager)

	if (spec.managerAdd) {
		managerResourceDesired.add(ObjectActionKeys.ADD_OBJECT_ENTRY)
	}

	def userResourceDesired = new ArrayList(entryDesiredUser)

	if (spec.userAdd) {
		userResourceDesired.add(ObjectActionKeys.ADD_OBJECT_ENTRY)
	}

	grantAllScopes(
		resourceName,
		filterActions(managerResourceDesired, resourceAvailable),
		filterActions(userResourceDesired, resourceAvailable),
		"${erc} resource")

	if (portletId) {
		def managerPortlet = [
			ActionKeys.ACCESS,
			ActionKeys.VIEW,
		]
		def userPortlet = [
			ActionKeys.ACCESS,
			ActionKeys.VIEW,
		]

		if (spec.managerAdd) {
			managerPortlet.add(ObjectActionKeys.ADD_OBJECT_ENTRY)
		}

		if (spec.userAdd) {
			userPortlet.add(ObjectActionKeys.ADD_OBJECT_ENTRY)
		}

		tryEnsure(portletId, managerPortlet)

		def portletAvailable = availableActions(portletId)

		log.append("acciones portlet: ${portletAvailable}\n")

		grantAllScopes(
			portletId,
			filterActions(managerPortlet, portletAvailable),
			filterActions(userPortlet, portletAvailable),
			"${erc} portlet")
	}

	log.append("\n")
}

// VERIFY separación Manager vs User en cuentas y txs
try {
	ObjectDefinition account =
		ObjectDefinitionLocalServiceUtil.getObjectDefinitionByExternalReferenceCode(
			"FIN_ACCOUNT", companyId)
	ObjectDefinition tx =
		ObjectDefinitionLocalServiceUtil.getObjectDefinitionByExternalReferenceCode(
			"FIN_TRANSACTION", companyId)

	boolean userAccountAdd = ResourcePermissionLocalServiceUtil.hasResourcePermission(
		companyId, account.getResourceName(), ResourceConstants.SCOPE_GROUP,
		String.valueOf(siteGroupId), userRole.getRoleId(), ObjectActionKeys.ADD_OBJECT_ENTRY)
	boolean userAccountView = ResourcePermissionLocalServiceUtil.hasResourcePermission(
		companyId, account.getClassName(), ResourceConstants.SCOPE_GROUP,
		String.valueOf(siteGroupId), userRole.getRoleId(), ActionKeys.VIEW)
	boolean managerAccountAdd = ResourcePermissionLocalServiceUtil.hasResourcePermission(
		companyId, account.getResourceName(), ResourceConstants.SCOPE_GROUP,
		String.valueOf(siteGroupId), managerRole.getRoleId(), ObjectActionKeys.ADD_OBJECT_ENTRY)
	boolean userTxAdd = ResourcePermissionLocalServiceUtil.hasResourcePermission(
		companyId, tx.getResourceName(), ResourceConstants.SCOPE_GROUP,
		String.valueOf(siteGroupId), userRole.getRoleId(), ObjectActionKeys.ADD_OBJECT_ENTRY)
	boolean userTxView = ResourcePermissionLocalServiceUtil.hasResourcePermission(
		companyId, tx.getClassName(), ResourceConstants.SCOPE_GROUP,
		String.valueOf(siteGroupId), userRole.getRoleId(), ActionKeys.VIEW)

	log.append("VERIFY B2B User FIN_ACCOUNT: VIEW=${userAccountView} ADD=${userAccountAdd} (ADD debe ser false)\n")
	log.append("VERIFY B2B Manager FIN_ACCOUNT: ADD=${managerAccountAdd} (debe ser true)\n")
	log.append("VERIFY B2B User FIN_TRANSACTION: VIEW=${userTxView} ADD=${userTxAdd} (ambos true)\n")

	if (userAccountView && !userAccountAdd && managerAccountAdd && userTxView && userTxAdd) {
		log.append("VERIFY OK (matriz Manager/User separada).\n")
	}
	else {
		log.append("VERIFY FAIL: matriz incorrecta.\n")
	}
}
catch (Exception e) {
	log.append("VERIFY error: ${e.getMessage()}\n")
}

if (verifyUserEmail) {
	try {
		User user = UserLocalServiceUtil.getUserByEmailAddress(companyId, verifyUserEmail)
		def siteRoles = UserGroupRoleLocalServiceUtil.getUserGroupRoles(
			user.getUserId(), siteGroupId)

		log.append("\nUSER ${verifyUserEmail} id=${user.getUserId()} siteRoles=")
		log.append(siteRoles.collect { RoleLocalServiceUtil.getRole(it.getRoleId()).getName() }.join(", "))
		log.append("\n")

		PermissionChecker checker = PermissionCheckerFactoryUtil.create(user)
		PermissionChecker previous = PermissionThreadLocal.getPermissionChecker()
		String previousName = PrincipalThreadLocal.getName()

		try {
			PermissionThreadLocal.setPermissionChecker(checker)
			PrincipalThreadLocal.setName(String.valueOf(user.getUserId()))

			ObjectDefinition tx =
				ObjectDefinitionLocalServiceUtil.getObjectDefinitionByExternalReferenceCode(
					"FIN_TRANSACTION", companyId)

			boolean canView = checker.hasPermission(
				siteGroupId, tx.getClassName(), siteGroupId, ActionKeys.VIEW)
			boolean canAdd = checker.hasPermission(
				siteGroupId, tx.getResourceName(), siteGroupId,
				ObjectActionKeys.ADD_OBJECT_ENTRY)

			log.append("VERIFY user permissionChecker: VIEW=${canView} ADD=${canAdd}\n")

			if (!canView) {
				log.append("→ Asigna rol B2B al usuario EN ESTE SITIO (assign-b2b-user-role.groovy).\n")
			}
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(previous)
			PrincipalThreadLocal.setName(previousName)
		}
	}
	catch (Exception e) {
		log.append("VERIFY user error: ${e.getMessage()}\n")
	}
}

log.append(
	"\nListo. Ctrl+F5 en /es/nexus-b2b. Si VERIFY OK pero API sigue 403, ")
log.append("el usuario no tiene el rol B2B en el sitio ${siteGroupId}.")

return log.toString()
