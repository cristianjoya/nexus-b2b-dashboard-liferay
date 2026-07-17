package fin.services.rest.internal.resource.v1_0;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import fin.services.rest.internal.resource.support.FinB2BPermissionSupport;
import fin.services.rest.internal.resource.support.SiteScopeResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * Exposes current-user REST permissions for the active site scope.
 *
 * @author krism
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=FinServicesRest)",
		"osgi.jaxrs.resource=true"
	},
	scope = ServiceScope.PROTOTYPE, service = PermissionsResource.class
)
@Path("/v1.0")
public class PermissionsResource {

	@GET
	@Path("/permissions")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Object> getPermissions(
			@Context User contextUser,
			@Context HttpServletRequest httpServletRequest)
		throws Exception {

		User user = _resolveUser(contextUser);

		Company company = _companyLocalService.getCompany(user.getCompanyId());

		PermissionChecker currentPermissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		PermissionChecker permissionChecker =
			_permissionCheckerFactory.create(user);

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		try {
			long groupId = new SiteScopeResolver(
				company, _groupLocalService, httpServletRequest
			).getGroupId();

			if (groupId <= 0) {
				return FinB2BPermissionSupport.emptyPermissionsResponse();
			}

			if (!FinB2BPermissionSupport.canEvaluatePermissions(
					user, groupId, _roleLocalService,
					_userGroupRoleLocalService)) {

				return FinB2BPermissionSupport.emptyPermissionsResponse();
			}

			return FinB2BPermissionSupport.resolvePermissionsResponse(
				user, groupId, _roleLocalService,
				_userGroupRoleLocalService);
		}
		finally {
			PermissionThreadLocal.setPermissionChecker(
				currentPermissionChecker);
		}
	}

	private User _resolveUser(User contextUser) {
		User user = contextUser;

		if ((user != null) && !user.isGuestUser()) {
			return user;
		}

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker != null) {
			long userId = permissionChecker.getUserId();

			if (userId > 0) {
				try {
					user = _userLocalService.getUser(userId);

					if (!user.isGuestUser()) {
						return user;
					}
				}
				catch (PortalException portalException) {
					throw new WebApplicationException(
						portalException,
						Response.status(
							Response.Status.UNAUTHORIZED
						).build());
				}
			}
		}

		throw new WebApplicationException(
			Response.status(Response.Status.UNAUTHORIZED).build());
	}

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}
