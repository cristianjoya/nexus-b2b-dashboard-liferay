package fin.services.upgrade.v1_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * Adds card metadata columns required for BIN / card network persistence.
 *
 * @author krism
 */
public class FinAccountUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("FinServices_FinAccount", "cardScheme")) {
			runSQL(
				"alter table FinServices_FinAccount add column cardScheme " +
					"VARCHAR(75) null");
		}

		if (!hasColumn("FinServices_FinAccount", "cardBrand")) {
			runSQL(
				"alter table FinServices_FinAccount add column cardBrand " +
					"VARCHAR(75) null");
		}

		if (!hasColumn("FinServices_FinAccount", "cardBankName")) {
			runSQL(
				"alter table FinServices_FinAccount add column cardBankName " +
					"VARCHAR(75) null");
		}

		if (!hasColumn("FinServices_FinAccount", "cardCountryName")) {
			runSQL(
				"alter table FinServices_FinAccount add column cardCountryName " +
					"VARCHAR(75) null");
		}
	}

}
