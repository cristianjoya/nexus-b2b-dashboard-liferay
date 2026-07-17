package fin.services.rest.internal.resource.support;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import fin.services.model.FinAccount;
import fin.services.rest.internal.constants.FinB2BConstants;
import fin.services.service.FinAccountLocalService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Resolves B2B account ownership and scoped access.
 *
 * @author krism
 */
public class FinB2BAccountAccess {

	public static List<Long> getAccessibleAccountIds(
			User user, long groupId, FinAccountLocalService finAccountLocalService,
			RoleLocalService roleLocalService,
			UserGroupRoleLocalService userGroupRoleLocalService)
		throws PortalException {

		Long ownerUserId = resolveListOwnerUserIdFilter(
			user, groupId, roleLocalService, userGroupRoleLocalService);

		if (ownerUserId == null) {
			return null;
		}

		List<FinAccount> finAccounts =
			finAccountLocalService.searchFinAccountsByGroupId(
				groupId, null, null, null, ownerUserId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		if (finAccounts.isEmpty()) {
			return Collections.emptyList();
		}

		List<Long> accountIds = new ArrayList<>(finAccounts.size());

		for (FinAccount finAccount : finAccounts) {
			accountIds.add(finAccount.getAccountId());
		}

		return accountIds;
	}

	public static Long resolveListOwnerUserIdFilter(
			User user, long groupId, RoleLocalService roleLocalService,
			UserGroupRoleLocalService userGroupRoleLocalService)
		throws PortalException {

		String profile = FinB2BPermissionSupport.resolveProfile(
			user, groupId, roleLocalService, userGroupRoleLocalService);

		if ("B2B_USER".equals(profile)) {
			return user.getUserId();
		}

		return null;
	}

	public static void requireAccountAccess(
			User user, long groupId, FinAccount finAccount,
			RoleLocalService roleLocalService,
			UserGroupRoleLocalService userGroupRoleLocalService)
		throws PortalException {

		if ((finAccount == null) || (finAccount.getGroupId() != groupId)) {
			throw new PrincipalException("Account is not in the active site scope");
		}

		Long ownerUserId = resolveListOwnerUserIdFilter(
			user, groupId, roleLocalService, userGroupRoleLocalService);

		if ((ownerUserId != null) &&
			(finAccount.getOwnerUserId() != ownerUserId.longValue())) {

			throw new PrincipalException(
				"Account is not assigned to the current B2B user");
		}
	}

	public static void requireAssignableOwner(
			long companyId, long groupId, long ownerUserId,
			RoleLocalService roleLocalService,
			UserGroupRoleLocalService userGroupRoleLocalService,
			UserLocalService userLocalService)
		throws PortalException {

		if (ownerUserId <= 0) {
			throw new PortalException("Assigned B2B user is required");
		}

		User ownerUser = userLocalService.fetchUser(ownerUserId);

		if ((ownerUser == null) || ownerUser.isGuestUser()) {
			throw new PortalException("Assigned B2B user was not found");
		}

		if (!FinB2BPermissionSupport.hasGroupRole(
				companyId, ownerUserId, groupId,
				FinB2BConstants.ROLE_B2B_USER, roleLocalService,
				userGroupRoleLocalService)) {

			throw new PortalException(
				"Assigned user must have the B2B User role on this site");
		}
	}

	public static boolean canAssignOwners(
			User user, long groupId, RoleLocalService roleLocalService,
			UserGroupRoleLocalService userGroupRoleLocalService)
		throws PortalException {

		String profile = FinB2BPermissionSupport.resolveProfile(
			user, groupId, roleLocalService, userGroupRoleLocalService);

		return "ADMIN".equals(profile) || "B2B_MANAGER".equals(profile);
	}

	/**
	 * Intersects accessible accounts with accounts owned by {@code ownerUserId}.
	 * Returns an empty list when the owner has no matching accounts.
	 */
	public static List<Long> applyOwnerUserIdFilter(
			long groupId, Long ownerUserId, List<Long> accessibleAccountIds,
			FinAccountLocalService finAccountLocalService) {

		if ((ownerUserId == null) || (ownerUserId <= 0)) {
			return accessibleAccountIds;
		}

		List<FinAccount> ownedAccounts =
			finAccountLocalService.searchFinAccountsByGroupId(
				groupId, null, null, null, ownerUserId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		if (ownedAccounts.isEmpty()) {
			return Collections.emptyList();
		}

		List<Long> ownedAccountIds = new ArrayList<>(ownedAccounts.size());

		for (FinAccount finAccount : ownedAccounts) {
			ownedAccountIds.add(finAccount.getAccountId());
		}

		if (accessibleAccountIds == null) {
			return ownedAccountIds;
		}

		List<Long> intersection = new ArrayList<>();

		for (Long accountId : ownedAccountIds) {
			if (accessibleAccountIds.contains(accountId)) {
				intersection.add(accountId);
			}
		}

		return intersection;
	}

	private FinB2BAccountAccess() {
	}

}
