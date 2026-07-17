package fin.services.upgrade.v1_0_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * Adds owner assignment for B2B account scoping.
 *
 * @author krism
 */
public class FinAccountOwnerUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("FinServices_FinAccount", "ownerUserId")) {
			runSQL(
				"alter table FinServices_FinAccount add column ownerUserId " +
					"LONG null");
		}

		if (!hasIndex("FinServices_FinAccount", "IX_3623D7FD")) {
			runSQL(
				"create index IX_3623D7FD on FinServices_FinAccount " +
					"(groupId, ownerUserId)");
		}
	}

}
