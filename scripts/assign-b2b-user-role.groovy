import com.liferay.portal.kernel.model.Group
import com.liferay.portal.kernel.model.Role
import com.liferay.portal.kernel.model.User
import com.liferay.portal.kernel.model.UserGroupRole
import com.liferay.portal.kernel.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil
import com.liferay.portal.kernel.service.RoleLocalServiceUtil
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil
import com.liferay.portal.kernel.service.UserLocalServiceUtil
import com.liferay.portal.kernel.util.PortalUtil

/**
 * Membresía de sitio + rol B2B (Manager o User) en el sitio de la página del dashboard.
 *
 * Control Panel → Server Administration → Script (Groovy)
 * Edita las tres variables de abajo antes de Execute.
 */

def pageFriendlyUrl = "/nexus-b2b"
def userEmail = "user@example.com"
def roleName = "B2B User" // o "B2B Manager"

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
	return "No se encontró un sitio con la página ${pageFriendlyUrl}"
}

User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, userEmail)

if (user == null) {
	return "Usuario no encontrado: ${userEmail}"
}

Role role = RoleLocalServiceUtil.fetchRole(companyId, roleName)

if (role == null) {
	return "Rol '${roleName}' no existe. Créalo como rol de sitio web con ese nombre exacto."
}

long groupId = siteGroup.getGroupId()
long userId = user.getUserId()
def log = new StringBuilder()

log.append("user=${userEmail} userId=${userId}\n")
log.append("site=${siteGroup.getName()} groupId=${groupId}\n")
log.append("role=${roleName} roleId=${role.getRoleId()} type=${role.getType()}\n")

if (!UserLocalServiceUtil.hasGroupUser(groupId, userId)) {
	UserLocalServiceUtil.addGroupUser(groupId, userId)
	log.append("Añadido como miembro del sitio\n")
}
else {
	log.append("Ya era miembro del sitio\n")
}

if (!UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, groupId, role.getRoleId())) {
	UserGroupRoleLocalServiceUtil.addUserGroupRoles(
		userId, groupId, [role.getRoleId()] as long[])
	log.append("Asignado rol '${roleName}'\n")
}
else {
	log.append("Ya tenía el rol '${roleName}' en este sitio\n")
}

log.append("\nRoles de sitio del usuario:\n")

for (UserGroupRole ugr : UserGroupRoleLocalServiceUtil.getUserGroupRoles(userId)) {
	Role r = RoleLocalServiceUtil.getRole(ugr.getRoleId())
	Group g = GroupLocalServiceUtil.getGroup(ugr.getGroupId())

	log.append("- ${r.getName()} @ ${g.getName()} (${ugr.getGroupId()})\n")
}

log.append("\nRecarga el dashboard (Ctrl+F5) con este usuario.")

return log.toString()
