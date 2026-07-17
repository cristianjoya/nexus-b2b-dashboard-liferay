package fin.services.upgrade.v1_0_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * Creates Phase 3 tables for transactions and credit applications.
 *
 * @author krism
 */
public class FinServicesUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasTable("FinServices_FinTransaction")) {
			runSQL(
				"create table FinServices_FinTransaction (" +
					"uuid_ VARCHAR(75) null, transactionId LONG not null " +
						"primary key, groupId LONG, companyId LONG, userId LONG, " +
						"userName VARCHAR(75) null, createDate DATE null, " +
						"modifiedDate DATE null, accountId LONG, " +
						"transactionType VARCHAR(75) null, amount DOUBLE, " +
						"description VARCHAR(75) null, transactionDate DATE null, " +
						"status INTEGER)");
		}

		if (!hasTable("FinServices_FinCreditApp")) {
			runSQL(
				"create table FinServices_FinCreditApp (" +
					"uuid_ VARCHAR(75) null, creditAppId LONG not null " +
						"primary key, groupId LONG, companyId LONG, userId LONG, " +
						"userName VARCHAR(75) null, createDate DATE null, " +
						"modifiedDate DATE null, accountId LONG, " +
						"applicantName VARCHAR(75) null, requestedAmount DOUBLE, " +
						"purpose VARCHAR(75) null, status INTEGER, " +
						"reviewNotes VARCHAR(75) null)");
		}

		if (!hasIndex("FinServices_FinTransaction", "IX_D12BB67D")) {
			runSQL(
				"create index IX_D12BB67D on FinServices_FinTransaction " +
					"(groupId, accountId)");
		}

		if (!hasIndex("FinServices_FinTransaction", "IX_45A8F485")) {
			runSQL(
				"create unique index IX_45A8F485 on FinServices_FinTransaction " +
					"(uuid_, groupId)");
		}

		if (!hasIndex("FinServices_FinCreditApp", "IX_1AF9A747")) {
			runSQL(
				"create index IX_1AF9A747 on FinServices_FinCreditApp " +
					"(groupId, accountId)");
		}

		if (!hasIndex("FinServices_FinCreditApp", "IX_25D2DA4F")) {
			runSQL(
				"create unique index IX_25D2DA4F on FinServices_FinCreditApp " +
					"(uuid_, groupId)");
		}
	}

}
