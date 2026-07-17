import com.liferay.portal.kernel.model.Group
import com.liferay.portal.kernel.model.Role
import com.liferay.portal.kernel.model.User
import com.liferay.portal.kernel.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil
import com.liferay.portal.kernel.service.RoleLocalServiceUtil
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil
import com.liferay.portal.kernel.service.UserLocalServiceUtil
import com.liferay.portal.kernel.util.PortalUtil

// Asigna rol "B2B User" en el sitio que contiene la pagina /nexus-b2b
// Ejecutar en: Control Panel > Configuration > Server Administration > Script

def pageFriendlyUrl = "/nexus-b2b"
def userEmail = "nuevo.b2b@example.com"  // <-- cambia por el email del usuario creado
def roleName = "B2B User"

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

User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, userEmail)

if (user == null) {
	return "Usuario no encontrado: ${userEmail}"
}

long groupId = siteGroup.getGroupId()
long userId = user.getUserId()

if (!UserLocalServiceUtil.hasGroupUser(groupId, userId)) {
	UserLocalServiceUtil.addGroupUser(groupId, userId)
}

Role role = RoleLocalServiceUtil.getRole(companyId, roleName)

if (!UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, groupId, role.getRoleId())) {
	UserGroupRoleLocalServiceUtil.addUserGroupRoles(userId, groupId, [role.getRoleId()] as long[])
}

"OK: ${userEmail} (userId=${userId}) es miembro del sitio '${siteGroup.getName()}' (groupId=${groupId}) con rol '${roleName}'. Recarga el formulario de cuenta en el dashboard."
