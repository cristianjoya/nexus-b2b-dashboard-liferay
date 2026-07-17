package fin.services.rest.internal.resource.permission;

import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.service.ResourceActionLocalService;

import fin.services.rest.internal.constants.FinB2BConstants;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Ensures REST model resource actions exist in Liferay (required before
 * assigning role permissions via ResourcePermissionLocalService).
 *
 * @author krism
 */
@Component(immediate = true, service = FinServicesResourceActionsActivator.class)
public class FinServicesResourceActionsActivator {

	@Activate
	protected void activate() throws Exception {
		for (String modelName : _MODEL_NAMES) {
			long bitwiseValue = 1;

			for (String actionId : _ACTION_IDS) {
				if (_resourceActionLocalService.fetchResourceAction(
						modelName, actionId) != null) {

					continue;
				}

				ResourceAction resourceAction =
					_resourceActionLocalService.createResourceAction(
						_counterLocalService.increment(
							ResourceAction.class.getName()));

				resourceAction.setName(modelName);
				resourceAction.setActionId(actionId);
				resourceAction.setBitwiseValue(bitwiseValue);

				_resourceActionLocalService.addResourceAction(resourceAction);

				bitwiseValue *= 2;
			}
		}
	}

	private static final String[] _ACTION_IDS = {
		"ADD", "DELETE", "PERMISSIONS", "UPDATE", "VIEW"
	};

	private static final String[] _MODEL_NAMES = {
		FinB2BConstants.REST_MODEL_ACCOUNT,
		FinB2BConstants.REST_MODEL_TRANSACTION,
		FinB2BConstants.REST_MODEL_CREDIT_APPLICATION
	};

	@Reference
	private CounterLocalService _counterLocalService;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

}
