package fin.services.rest.internal.resource.support;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;

import java.util.Map;

/**
 * Enforces B2B role permissions on REST resources.
 *
 * @author krism
 */
public class FinB2BPermissionEnforcer {

	public static final String ACTION_ADD = "add";

	public static final String ACTION_DELETE = "delete";

	public static final String ACTION_UPDATE = "update";

	public static final String ACTION_VIEW = "view";

	public static final String RESOURCE_ACCOUNTS = "accounts";

	public static final String RESOURCE_CREDIT_APPLICATIONS =
		"creditApplications";

	public static final String RESOURCE_TRANSACTIONS = "transactions";

	public static void require(
			User user, long groupId, String resource, String action,
			RoleLocalService roleLocalService,
			UserGroupRoleLocalService userGroupRoleLocalService)
		throws PortalException {

		if ((user == null) || user.isGuestUser()) {
			throw new PrincipalException(
				"Authentication required for " + resource + "." + action);
		}

		if (groupId <= 0) {
			throw new PrincipalException(
				"Site scope is required for " + resource + "." + action);
		}

		Map<String, Object> response =
			FinB2BPermissionSupport.resolvePermissionsResponse(
				user, groupId, roleLocalService,
				userGroupRoleLocalService);

		if (!hasAction(response, resource, action)) {
			throw new PrincipalException(
				"Missing B2B permission: " + resource + "." + action);
		}
	}

	private static boolean hasAction(
		Map<String, Object> response, String resource, String action) {

		Object resourcePermissions = response.get(resource);

		if (!(resourcePermissions instanceof Map<?, ?> permissionsMap)) {
			return false;
		}

		Object allowed = permissionsMap.get(action);

		return Boolean.TRUE.equals(allowed);
	}

	private FinB2BPermissionEnforcer() {
	}

}
