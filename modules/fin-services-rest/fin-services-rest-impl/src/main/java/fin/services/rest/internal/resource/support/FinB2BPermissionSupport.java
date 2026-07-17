package fin.services.rest.internal.resource.support;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroupRole;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;

import java.util.List;

import fin.services.rest.internal.constants.FinB2BConstants;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author krism
 */
public class FinB2BPermissionSupport {

	public static boolean canEvaluatePermissions(
			User user, long groupId, RoleLocalService roleLocalService,
			UserGroupRoleLocalService userGroupRoleLocalService)
		throws PortalException {

		if (isElevatedAdmin(
				user, groupId, roleLocalService,
				userGroupRoleLocalService)) {

			return true;
		}

		return hasGroupRole(
			user.getCompanyId(), user.getUserId(), groupId,
			FinB2BConstants.ROLE_B2B_MANAGER, roleLocalService,
			userGroupRoleLocalService) ||
			hasGroupRole(
				user.getCompanyId(), user.getUserId(), groupId,
				FinB2BConstants.ROLE_B2B_USER, roleLocalService,
				userGroupRoleLocalService);
	}

	public static Map<String, Object> resolvePermissionsResponse(
			User user, long groupId, RoleLocalService roleLocalService,
			UserGroupRoleLocalService userGroupRoleLocalService)
		throws PortalException {

		String profile = resolveProfile(
			user, groupId, roleLocalService, userGroupRoleLocalService);

		Map<String, Object> response;

		if ("ADMIN".equals(profile) || "B2B_MANAGER".equals(profile)) {
			response = fullPermissionsResponse();
		}
		else if ("B2B_USER".equals(profile)) {
			response = b2bUserPermissionsResponse();
		}
		else {
			response = emptyPermissionsResponse();
		}

		response.put("profile", profile);

		return response;
	}

	public static String resolveProfile(
			User user, long groupId, RoleLocalService roleLocalService,
			UserGroupRoleLocalService userGroupRoleLocalService)
		throws PortalException {

		if (isElevatedAdmin(
				user, groupId, roleLocalService,
				userGroupRoleLocalService)) {

			return "ADMIN";
		}

		if (hasGroupRole(
				user.getCompanyId(), user.getUserId(), groupId,
				FinB2BConstants.ROLE_B2B_MANAGER, roleLocalService,
				userGroupRoleLocalService)) {

			return "B2B_MANAGER";
		}

		if (hasGroupRole(
				user.getCompanyId(), user.getUserId(), groupId,
				FinB2BConstants.ROLE_B2B_USER, roleLocalService,
				userGroupRoleLocalService)) {

			return "B2B_USER";
		}

		return "NONE";
	}

	public static boolean hasGroupRole(
		long companyId, long userId, long groupId, String roleName,
		RoleLocalService roleLocalService,
		UserGroupRoleLocalService userGroupRoleLocalService) {

		if (groupId <= 0) {
			return false;
		}

		Role role = roleLocalService.fetchRole(companyId, roleName);

		if (role != null) {
			if (userGroupRoleLocalService.hasUserGroupRole(
					userId, groupId, role.getRoleId())) {

				return true;
			}
		}

		List<UserGroupRole> userGroupRoles =
			userGroupRoleLocalService.getUserGroupRoles(userId, groupId);

		for (UserGroupRole userGroupRole : userGroupRoles) {
			Role assignedRole = roleLocalService.fetchRole(
				userGroupRole.getRoleId());

			if ((assignedRole != null) &&
				roleName.equals(assignedRole.getName())) {

				return true;
			}
		}

		return false;
	}

	public static boolean isElevatedAdmin(
			User user, long groupId, RoleLocalService roleLocalService,
			UserGroupRoleLocalService userGroupRoleLocalService)
		throws PortalException {

		if (roleLocalService.hasUserRole(
				user.getUserId(), user.getCompanyId(),
				RoleConstants.ADMINISTRATOR, true)) {

			return true;
		}

		Role siteAdministratorRole = roleLocalService.fetchRole(
			user.getCompanyId(), RoleConstants.SITE_ADMINISTRATOR);

		if ((siteAdministratorRole != null) && (groupId > 0) &&
			userGroupRoleLocalService.hasUserGroupRole(
				user.getUserId(), groupId,
				siteAdministratorRole.getRoleId())) {

			return true;
		}

		return false;
	}

	public static Map<String, Object> b2bUserPermissionsResponse() {
		Map<String, Object> response = new LinkedHashMap<>();

		response.put("accounts", permissions(false, false, false, true));
		response.put("transactions", permissions(true, false, false, true));
		response.put(
			"creditApplications", permissions(true, false, false, true));

		return response;
	}

	public static Map<String, Object> emptyPermissionsResponse() {
		Map<String, Object> response = new LinkedHashMap<>();

		response.put("profile", "NONE");
		response.put("accounts", permissions(false, false, false, false));
		response.put("transactions", permissions(false, false, false, false));
		response.put(
			"creditApplications", permissions(false, false, false, false));

		return response;
	}

	public static Map<String, Object> fullPermissionsResponse() {
		Map<String, Object> response = new LinkedHashMap<>();

		response.put("accounts", permissions(true, true, true, true));
		response.put("transactions", permissions(true, true, true, true));
		response.put(
			"creditApplications", permissions(true, true, true, true));

		return response;
	}

	private static Map<String, Boolean> permissions(
		boolean add, boolean delete, boolean update, boolean view) {

		Map<String, Boolean> resourcePermissions = new LinkedHashMap<>();

		resourcePermissions.put("add", add);
		resourcePermissions.put("delete", delete);
		resourcePermissions.put("update", update);
		resourcePermissions.put("view", view);

		return resourcePermissions;
	}

	private FinB2BPermissionSupport() {
	}

}
