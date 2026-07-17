package fin.services.upgrade.v1_0_1;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author krism
 */
@Component(
	immediate = true, service = UpgradeStepRegistrator.class
)
public class FinAccountUpgradeStepRegistrator implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"1.0.0", "1.0.1", new FinAccountUpgradeProcess());
	}

}
