package fin.services.rest.internal.resource.v1_0;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.util.Validator;

import fin.services.rest.dto.v1_0.CardBin;
import fin.services.rest.internal.integration.BinlistClient;
import fin.services.rest.internal.resource.support.FinB2BPermissionEnforcer;
import fin.services.rest.internal.resource.support.SiteScopeResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * Authenticated BIN lookup (scheme/bank metadata). Luhn is validated
 * client-side; never accept full PANs as query parameters.
 *
 * @author krism
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=FinServicesRest)",
		"osgi.jaxrs.resource=true"
	},
	scope = ServiceScope.PROTOTYPE, service = CardBinResource.class
)
@Path("/v1.0")
public class CardBinResource {

	@GET
	@Path("/card-bin/{bin}")
	@Produces(MediaType.APPLICATION_JSON)
	public CardBin getCardBin(
			@PathParam("bin") String bin, @Context User contextUser,
			@Context HttpServletRequest httpServletRequest)
		throws PortalException {

		if ((contextUser == null) || contextUser.isGuestUser()) {
			throw new PrincipalException(
				"Authentication required for card-bin lookup");
		}

		Company company = _companyLocalService.getCompany(
			contextUser.getCompanyId());

		long groupId = new SiteScopeResolver(
			company, _groupLocalService, httpServletRequest
		).getGroupId();

		FinB2BPermissionEnforcer.require(
			contextUser, groupId, FinB2BPermissionEnforcer.RESOURCE_ACCOUNTS,
			FinB2BPermissionEnforcer.ACTION_VIEW, _roleLocalService,
			_userGroupRoleLocalService);

		String normalizedBin = _normalizeBin(bin);

		if (Validator.isNull(normalizedBin) || (normalizedBin.length() < 6)) {
			CardBin cardBin = new CardBin();

			cardBin.setBin(normalizedBin);

			return cardBin;
		}

		return _binlistClient.lookup(normalizedBin);
	}

	private String _normalizeBin(String bin) {
		String digits = _normalizeDigits(bin);

		if (Validator.isNull(digits)) {
			return null;
		}

		if (digits.length() > 8) {
			return digits.substring(0, 8);
		}

		return digits;
	}

	private String _normalizeDigits(String value) {
		if (Validator.isNull(value)) {
			return null;
		}

		String digits = value.replaceAll("\\D", "");

		if (digits.isEmpty()) {
			return null;
		}

		return digits;
	}

	@Reference
	private BinlistClient _binlistClient;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserGroupRoleLocalService _userGroupRoleLocalService;

}
