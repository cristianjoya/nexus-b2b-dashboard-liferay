package fin.services.upgrade.v1_0_3;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author krism
 */
@Component(
	immediate = true, service = UpgradeStepRegistrator.class
)
public class FinAccountOwnerUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.2", "1.0.3", new FinAccountOwnerUpgradeProcess());
	}

}
