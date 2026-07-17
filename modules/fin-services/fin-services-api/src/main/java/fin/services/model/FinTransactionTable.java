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
 * The table class for the &quot;FinServices_FinTransaction&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see FinTransaction
 * @generated
 */
public class FinTransactionTable extends BaseTable<FinTransactionTable> {

	public static final FinTransactionTable INSTANCE =
		new FinTransactionTable();

	public final Column<FinTransactionTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, Long> transactionId = createColumn(
		"transactionId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<FinTransactionTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, Long> accountId = createColumn(
		"accountId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, String> transactionType =
		createColumn(
			"transactionType", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, Double> amount = createColumn(
		"amount", Double.class, Types.DOUBLE, Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, Date> transactionDate =
		createColumn(
			"transactionDate", Date.class, Types.TIMESTAMP,
			Column.FLAG_DEFAULT);
	public final Column<FinTransactionTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);

	private FinTransactionTable() {
		super("FinServices_FinTransaction", FinTransactionTable::new);
	}

}