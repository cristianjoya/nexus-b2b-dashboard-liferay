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
 * The table class for the &quot;FinServices_FinAccount&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see FinAccount
 * @generated
 */
public class FinAccountTable extends BaseTable<FinAccountTable> {

	public static final FinAccountTable INSTANCE = new FinAccountTable();

	public final Column<FinAccountTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, Long> accountId = createColumn(
		"accountId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<FinAccountTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, Long> ownerUserId = createColumn(
		"ownerUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, String> accountNumber = createColumn(
		"accountNumber", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, String> accountName = createColumn(
		"accountName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, String> accountType = createColumn(
		"accountType", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, Double> balance = createColumn(
		"balance", Double.class, Types.DOUBLE, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, String> cardScheme = createColumn(
		"cardScheme", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, String> cardBrand = createColumn(
		"cardBrand", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, String> cardBankName = createColumn(
		"cardBankName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FinAccountTable, String> cardCountryName = createColumn(
		"cardCountryName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private FinAccountTable() {
		super("FinServices_FinAccount", FinAccountTable::new);
	}

}