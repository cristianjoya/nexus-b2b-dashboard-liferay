import com.liferay.portal.kernel.dao.jdbc.DataAccess
import com.liferay.portal.kernel.model.Group
import com.liferay.portal.kernel.model.User
import com.liferay.portal.kernel.service.GroupLocalServiceUtil
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil
import com.liferay.portal.kernel.service.UserLocalServiceUtil
import com.liferay.portal.kernel.util.PortalUtil

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

// Ejecutar en: Control Panel > Configuration > Server Administration > Script
//
// Nota: /nexus-b2b es la URL de una PÁGINA dentro del sitio Guest,
// no el friendlyURL del sitio. Este script localiza el groupId del sitio
// que contiene esa página.

def pageFriendlyUrl = "/nexus-b2b"
def ownerEmail = "b2b.user@example.com"  // <-- cambia por el email del B2B User

// Opcional: fuerza un groupId concreto (ej. 20126) y deja null para autodetectar.
Long forcedSiteGroupId = null

long companyId = PortalUtil.getDefaultCompanyId()
long siteGroupId = forcedSiteGroupId != null ? forcedSiteGroupId.longValue() : -1L
String resolvedFrom = forcedSiteGroupId != null ? "groupId forzado" : null

if (siteGroupId <= 0) {
	for (Group group : GroupLocalServiceUtil.getCompanyGroups(companyId, -1, -1)) {
		if (!group.isSite()) {
			continue
		}

		try {
			LayoutLocalServiceUtil.getFriendlyURLLayout(
				group.getGroupId(), false, pageFriendlyUrl)

			siteGroupId = group.getGroupId()
			resolvedFrom = "pagina ${pageFriendlyUrl} en sitio '${group.getName()}'"
			break
		}
		catch (Exception ignored) {
		}
	}
}

User owner = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, ownerEmail)

if (owner == null) {
	return "Usuario no encontrado: ${ownerEmail}"
}

long ownerUserId = owner.getUserId()
int updated = 0
List<Long> groupIds = []

Connection connection = DataAccess.getConnection()

try {
	Statement statement = connection.createStatement()

	try {
		statement.executeUpdate(
			"alter table FinServices_FinAccount add column ownerUserId BIGINT null")
	}
	catch (Throwable ignored) {
	}

	try {
		statement.executeUpdate(
			"create index IX_3623D7FD on FinServices_FinAccount " +
				"(groupId, ownerUserId)")
	}
	catch (Throwable ignored) {
	}

	if (siteGroupId <= 0) {
		ResultSet groupIdsResult = statement.executeQuery(
			"select distinct groupId from FinServices_FinAccount " +
				"where groupId > 0 order by groupId")

		while (groupIdsResult.next()) {
			groupIds.add(groupIdsResult.getLong(1))
		}

		groupIdsResult.close()

		if (groupIds.isEmpty()) {
			return "No se encontro el sitio con pagina ${pageFriendlyUrl} " +
				"y tampoco hay cuentas con groupId > 0 en FinServices_FinAccount."
		}

		resolvedFrom = "groupIds detectados en cuentas existentes: ${groupIds}"
	}
	else {
		groupIds.add(siteGroupId)
	}

	PreparedStatement preparedStatement = connection.prepareStatement(
		"update FinServices_FinAccount set ownerUserId = ? " +
			"where groupId = ? and (ownerUserId is null or ownerUserId <> ?)")

	for (Long groupId : groupIds) {
		preparedStatement.setLong(1, ownerUserId)
		preparedStatement.setLong(2, groupId)
		preparedStatement.setLong(3, ownerUserId)
		updated += preparedStatement.executeUpdate()
	}

	preparedStatement.close()
	statement.close()
}
finally {
	DataAccess.cleanUp(connection)
}

"OK: ${updated} cuenta(s) asignadas a ${ownerEmail} (userId=${ownerUserId}). " +
	"Origen: ${resolvedFrom}"
