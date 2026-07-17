package fin.services.rest.client.serdes.v1_0;

import fin.services.rest.client.dto.v1_0.Account;
import fin.services.rest.client.json.BaseJSONParser;

import jakarta.annotation.Generated;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author krism
 * @generated
 */
@Generated("")
public class AccountSerDes {

	public static Account toDTO(String json) {
		AccountJSONParser accountJSONParser = new AccountJSONParser();

		return accountJSONParser.parseToDTO(json);
	}

	public static Account[] toDTOs(String json) {
		AccountJSONParser accountJSONParser = new AccountJSONParser();

		return accountJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Account account) {
		if (account == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (account.getAccountId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountId\": ");

			sb.append(account.getAccountId());
		}

		if (account.getAccountName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountName\": ");

			sb.append("\"");

			sb.append(_escape(account.getAccountName()));

			sb.append("\"");
		}

		if (account.getAccountNumber() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountNumber\": ");

			sb.append("\"");

			sb.append(_escape(account.getAccountNumber()));

			sb.append("\"");
		}

		if (account.getAccountType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountType\": ");

			sb.append("\"");

			sb.append(_escape(account.getAccountType()));

			sb.append("\"");
		}

		if (account.getBalance() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"balance\": ");

			sb.append(account.getBalance());
		}

		if (account.getCardBankName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"cardBankName\": ");

			sb.append("\"");

			sb.append(_escape(account.getCardBankName()));

			sb.append("\"");
		}

		if (account.getCardBrand() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"cardBrand\": ");

			sb.append("\"");

			sb.append(_escape(account.getCardBrand()));

			sb.append("\"");
		}

		if (account.getCardCountryName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"cardCountryName\": ");

			sb.append("\"");

			sb.append(_escape(account.getCardCountryName()));

			sb.append("\"");
		}

		if (account.getCardScheme() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"cardScheme\": ");

			sb.append("\"");

			sb.append(_escape(account.getCardScheme()));

			sb.append("\"");
		}

		if (account.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(account.getCreateDate()));

			sb.append("\"");
		}

		if (account.getModifiedDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(account.getModifiedDate()));

			sb.append("\"");
		}

		if (account.getOwnerUserId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ownerUserId\": ");

			sb.append(account.getOwnerUserId());
		}

		if (account.getOwnerUserName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ownerUserName\": ");

			sb.append("\"");

			sb.append(_escape(account.getOwnerUserName()));

			sb.append("\"");
		}

		if (account.getStatus() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append(account.getStatus());
		}

		if (account.getUuid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"uuid\": ");

			sb.append("\"");

			sb.append(_escape(account.getUuid()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		AccountJSONParser accountJSONParser = new AccountJSONParser();

		return accountJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Account account) {
		if (account == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (account.getAccountId() == null) {
			map.put("accountId", null);
		}
		else {
			map.put("accountId", String.valueOf(account.getAccountId()));
		}

		if (account.getAccountName() == null) {
			map.put("accountName", null);
		}
		else {
			map.put("accountName", String.valueOf(account.getAccountName()));
		}

		if (account.getAccountNumber() == null) {
			map.put("accountNumber", null);
		}
		else {
			map.put(
				"accountNumber", String.valueOf(account.getAccountNumber()));
		}

		if (account.getAccountType() == null) {
			map.put("accountType", null);
		}
		else {
			map.put("accountType", String.valueOf(account.getAccountType()));
		}

		if (account.getBalance() == null) {
			map.put("balance", null);
		}
		else {
			map.put("balance", String.valueOf(account.getBalance()));
		}

		if (account.getCardBankName() == null) {
			map.put("cardBankName", null);
		}
		else {
			map.put("cardBankName", String.valueOf(account.getCardBankName()));
		}

		if (account.getCardBrand() == null) {
			map.put("cardBrand", null);
		}
		else {
			map.put("cardBrand", String.valueOf(account.getCardBrand()));
		}

		if (account.getCardCountryName() == null) {
			map.put("cardCountryName", null);
		}
		else {
			map.put(
				"cardCountryName",
				String.valueOf(account.getCardCountryName()));
		}

		if (account.getCardScheme() == null) {
			map.put("cardScheme", null);
		}
		else {
			map.put("cardScheme", String.valueOf(account.getCardScheme()));
		}

		if (account.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put(
				"createDate",
				liferayToJSONDateFormat.format(account.getCreateDate()));
		}

		if (account.getModifiedDate() == null) {
			map.put("modifiedDate", null);
		}
		else {
			map.put(
				"modifiedDate",
				liferayToJSONDateFormat.format(account.getModifiedDate()));
		}

		if (account.getOwnerUserId() == null) {
			map.put("ownerUserId", null);
		}
		else {
			map.put("ownerUserId", String.valueOf(account.getOwnerUserId()));
		}

		if (account.getOwnerUserName() == null) {
			map.put("ownerUserName", null);
		}
		else {
			map.put(
				"ownerUserName", String.valueOf(account.getOwnerUserName()));
		}

		if (account.getStatus() == null) {
			map.put("status", null);
		}
		else {
			map.put("status", String.valueOf(account.getStatus()));
		}

		if (account.getUuid() == null) {
			map.put("uuid", null);
		}
		else {
			map.put("uuid", String.valueOf(account.getUuid()));
		}

		return map;
	}

	public static class AccountJSONParser extends BaseJSONParser<Account> {

		@Override
		protected Account createDTO() {
			return new Account();
		}

		@Override
		protected Account[] createDTOArray(int size) {
			return new Account[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "accountId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "accountName")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "accountNumber")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "accountType")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "balance")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "cardBankName")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "cardBrand")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "cardCountryName")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "cardScheme")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedDate")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "ownerUserId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "ownerUserName")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "status")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "uuid")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			Account account, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "accountId")) {
				if (jsonParserFieldValue != null) {
					account.setAccountId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "accountName")) {
				if (jsonParserFieldValue != null) {
					account.setAccountName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "accountNumber")) {
				if (jsonParserFieldValue != null) {
					account.setAccountNumber((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "accountType")) {
				if (jsonParserFieldValue != null) {
					account.setAccountType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "balance")) {
				if (jsonParserFieldValue != null) {
					account.setBalance(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "cardBankName")) {
				if (jsonParserFieldValue != null) {
					account.setCardBankName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "cardBrand")) {
				if (jsonParserFieldValue != null) {
					account.setCardBrand((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "cardCountryName")) {
				if (jsonParserFieldValue != null) {
					account.setCardCountryName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "cardScheme")) {
				if (jsonParserFieldValue != null) {
					account.setCardScheme((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					account.setCreateDate(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedDate")) {
				if (jsonParserFieldValue != null) {
					account.setModifiedDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "ownerUserId")) {
				if (jsonParserFieldValue != null) {
					account.setOwnerUserId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "ownerUserName")) {
				if (jsonParserFieldValue != null) {
					account.setOwnerUserName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "status")) {
				if (jsonParserFieldValue != null) {
					account.setStatus(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "uuid")) {
				if (jsonParserFieldValue != null) {
					account.setUuid((String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			sb.append(_toJSON(value));

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static String _toJSON(Object value) {
		if (value == null) {
			return "null";
		}

		if (value instanceof Map) {
			return _toJSON((Map)value);
		}

		Class<?> clazz = value.getClass();

		if (clazz.isArray()) {
			StringBuilder sb = new StringBuilder("[");

			Object[] values = (Object[])value;

			for (int i = 0; i < values.length; i++) {
				sb.append(_toJSON(values[i]));

				if ((i + 1) < values.length) {
					sb.append(", ");
				}
			}

			sb.append("]");

			return sb.toString();
		}

		if (value instanceof String) {
			return "\"" + _escape(value) + "\"";
		}

		return String.valueOf(value);
	}

}