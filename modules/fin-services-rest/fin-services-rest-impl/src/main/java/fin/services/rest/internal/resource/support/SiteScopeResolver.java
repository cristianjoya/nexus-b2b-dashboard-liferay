package fin.services.rest.internal.resource.support;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Resolves the Liferay site (group) scope from HTTP context.
 *
 * @author krism
 */
public class SiteScopeResolver {

	public SiteScopeResolver(
		Company company, GroupLocalService groupLocalService,
		HttpServletRequest httpServletRequest) {

		_company = company;
		_groupLocalService = groupLocalService;
		_httpServletRequest = httpServletRequest;
	}

	public long getGroupId() throws PortalException {
		long scopeGroupId = 0;

		String friendlyUrl = _httpServletRequest.getHeader(
			"X-Liferay-Site-Friendly-Url");

		if (Validator.isNull(friendlyUrl)) {
			friendlyUrl = _httpServletRequest.getParameter("siteFriendlyUrl");
		}

		scopeGroupId = _resolveGroupIdFromFriendlyUrl(friendlyUrl);

		if (scopeGroupId > 0) {
			return scopeGroupId;
		}

		String currentUrl = _httpServletRequest.getHeader(
			"X-Liferay-Current-Url");

		scopeGroupId = _resolveGroupIdFromPageUrl(currentUrl);

		if (scopeGroupId > 0) {
			return scopeGroupId;
		}

		scopeGroupId = _resolveGroupIdFromPageUrl(
			_httpServletRequest.getHeader("Referer"));

		if (scopeGroupId > 0) {
			return scopeGroupId;
		}

		scopeGroupId = GetterUtil.getLong(
			_httpServletRequest.getHeader("X-Liferay-Scope-Group-Id"), 0);

		if (scopeGroupId == 0) {
			scopeGroupId = GetterUtil.getLong(
				_httpServletRequest.getParameter("scopeGroupId"), 0);
		}

		if (scopeGroupId > 0) {
			return _validateGroupId(scopeGroupId);
		}

		return PortalUtil.getScopeGroupId(_httpServletRequest);
	}

	private long _resolveGroupIdFromFriendlyUrl(String friendlyUrl) {
		if (Validator.isNull(friendlyUrl)) {
			return 0;
		}

		if (!friendlyUrl.startsWith("/")) {
			friendlyUrl = "/" + friendlyUrl;
		}

		Group group = _groupLocalService.fetchFriendlyURLGroup(
			_company.getCompanyId(), friendlyUrl);

		if ((group == null) ||
			(group.getCompanyId() != _company.getCompanyId())) {

			return 0;
		}

		return group.getGroupId();
	}

	private long _resolveGroupIdFromPageUrl(String url) {
		if (Validator.isNull(url)) {
			return 0;
		}

		int pathStart = url.indexOf("://");

		if (pathStart > 0) {
			pathStart = url.indexOf('/', pathStart + 3);

			if (pathStart > 0) {
				url = url.substring(pathStart);
			}
		}

		int queryIndex = url.indexOf('?');

		if (queryIndex > 0) {
			url = url.substring(0, queryIndex);
		}

		return _resolveGroupIdFromPath(url);
	}

	private long _resolveGroupIdFromPath(String path) {
		String[] segments = path.split("/");
		int index = 0;

		if ((segments.length > index + 1) && _isLocaleSegment(segments[index])) {
			index++;
		}

		if ((segments.length > index) && segments[index].equals("web") &&
			(segments.length > index + 1)) {

			return _resolveGroupIdFromFriendlyUrl("/" + segments[index + 1]);
		}

		if ((segments.length > index) && segments[index].equals("group") &&
			(segments.length > index + 1)) {

			return _resolveGroupIdFromFriendlyUrl("/" + segments[index + 1]);
		}

		if ((segments.length > index) &&
			!_isReservedPathSegment(segments[index])) {

			return _resolveGroupIdFromFriendlyUrl("/" + segments[index]);
		}

		return 0;
	}

	private boolean _isLocaleSegment(String segment) {
		if (Validator.isNull(segment)) {
			return false;
		}

		return segment.matches("[a-z]{2}") ||
			segment.matches("[a-z]{2}_[A-Z]{2}");
	}

	private boolean _isReservedPathSegment(String segment) {
		return segment.equals("api") || segment.equals("c") ||
			segment.equals("documents") || segment.equals("group") ||
			segment.equals("image") || segment.equals("o") ||
			segment.equals("web");
	}

	private long _validateGroupId(long scopeGroupId) throws PortalException {
		Group group = _groupLocalService.getGroup(scopeGroupId);

		if (group.getCompanyId() != _company.getCompanyId()) {
			throw new PrincipalException();
		}

		return scopeGroupId;
	}

	private final Company _company;
	private final GroupLocalService _groupLocalService;
	private final HttpServletRequest _httpServletRequest;

}
