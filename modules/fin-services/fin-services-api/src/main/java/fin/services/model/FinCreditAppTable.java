/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;FinServices_FinCreditApp&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see FinCreditApp
 * @generated
 */
public class FinCreditAppTable extends BaseTable<FinCreditAppTable> {

	public static final FinCreditAppTable INSTANCE = new FinCreditAppTable();

	public final Column<FinCreditAppTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, Long> creditAppId = createColumn(
		"creditAppId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<FinCreditAppTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, Long> accountId = createColumn(
		"accountId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, String> applicantName = createColumn(
		"applicantName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, Double> requestedAmount =
		createColumn(
			"requestedAmount", Double.class, Types.DOUBLE, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, String> purpose = createColumn(
		"purpose", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<FinCreditAppTable, String> reviewNotes = createColumn(
		"reviewNotes", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private FinCreditAppTable() {
		super("FinServices_FinCreditApp", FinCreditAppTable::new);
	}

}