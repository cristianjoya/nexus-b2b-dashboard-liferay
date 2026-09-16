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
 * Lista membresía y roles de sitio de un usuario en el sitio de /nexus-b2b.
 * Control Panel → Server Administration → Script (Groovy)
 */

def pageFriendlyUrl = "/nexus-b2b"
def userEmail = "user@example.com"

long companyId = PortalUtil.getDefaultCompanyId()
User user = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, userEmail)

if (user == null) {
	return "Usuario no encontrado: ${userEmail}"
}

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

def log = new StringBuilder()

log.append("userId=${user.getUserId()} email=${user.getEmailAddress()}\n")

if (siteGroup == null) {
	log.append("No hay sitio con página ${pageFriendlyUrl}\n")
}
else {
	long groupId = siteGroup.getGroupId()

	log.append("site=${siteGroup.getName()} groupId=${groupId}\n")
	log.append("miembro=${UserLocalServiceUtil.hasGroupUser(groupId, user.getUserId())}\n")

	for (String roleName : ["B2B User", "B2B Manager"]) {
		Role role = RoleLocalServiceUtil.fetchRole(companyId, roleName)

		if (role == null) {
			log.append("${roleName}: (rol no existe)\n")
		}
		else {
			boolean has = UserGroupRoleLocalServiceUtil.hasUserGroupRole(
				user.getUserId(), groupId, role.getRoleId())

			log.append("${roleName}: hasUserGroupRole=${has} (roleId=${role.getRoleId()}, type=${role.getType()})\n")
		}
	}
}

log.append("\nTodos los UserGroupRole del usuario:\n")

for (UserGroupRole ugr : UserGroupRoleLocalServiceUtil.getUserGroupRoles(user.getUserId())) {
	Role r = RoleLocalServiceUtil.getRole(ugr.getRoleId())
	Group g = GroupLocalServiceUtil.getGroup(ugr.getGroupId())

	log.append("- ${r.getName()} @ ${g.getName()} (groupId=${g.getGroupId()})\n")
}

return log.toString()
