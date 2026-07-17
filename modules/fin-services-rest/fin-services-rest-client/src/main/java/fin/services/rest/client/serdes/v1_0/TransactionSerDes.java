package fin.services.rest.client.serdes.v1_0;

import fin.services.rest.client.dto.v1_0.Transaction;
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
public class TransactionSerDes {

	public static Transaction toDTO(String json) {
		TransactionJSONParser transactionJSONParser =
			new TransactionJSONParser();

		return transactionJSONParser.parseToDTO(json);
	}

	public static Transaction[] toDTOs(String json) {
		TransactionJSONParser transactionJSONParser =
			new TransactionJSONParser();

		return transactionJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Transaction transaction) {
		if (transaction == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (transaction.getAccountId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountId\": ");

			sb.append(transaction.getAccountId());
		}

		if (transaction.getAmount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"amount\": ");

			sb.append(transaction.getAmount());
		}

		if (transaction.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(transaction.getCreateDate()));

			sb.append("\"");
		}

		if (transaction.getDescription() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"description\": ");

			sb.append("\"");

			sb.append(_escape(transaction.getDescription()));

			sb.append("\"");
		}

		if (transaction.getModifiedDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(transaction.getModifiedDate()));

			sb.append("\"");
		}

		if (transaction.getStatus() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append(transaction.getStatus());
		}

		if (transaction.getTransactionDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"transactionDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					transaction.getTransactionDate()));

			sb.append("\"");
		}

		if (transaction.getTransactionId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"transactionId\": ");

			sb.append(transaction.getTransactionId());
		}

		if (transaction.getTransactionType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"transactionType\": ");

			sb.append("\"");

			sb.append(_escape(transaction.getTransactionType()));

			sb.append("\"");
		}

		if (transaction.getUuid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"uuid\": ");

			sb.append("\"");

			sb.append(_escape(transaction.getUuid()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TransactionJSONParser transactionJSONParser =
			new TransactionJSONParser();

		return transactionJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Transaction transaction) {
		if (transaction == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (transaction.getAccountId() == null) {
			map.put("accountId", null);
		}
		else {
			map.put("accountId", String.valueOf(transaction.getAccountId()));
		}

		if (transaction.getAmount() == null) {
			map.put("amount", null);
		}
		else {
			map.put("amount", String.valueOf(transaction.getAmount()));
		}

		if (transaction.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put(
				"createDate",
				liferayToJSONDateFormat.format(transaction.getCreateDate()));
		}

		if (transaction.getDescription() == null) {
			map.put("description", null);
		}
		else {
			map.put(
				"description", String.valueOf(transaction.getDescription()));
		}

		if (transaction.getModifiedDate() == null) {
			map.put("modifiedDate", null);
		}
		else {
			map.put(
				"modifiedDate",
				liferayToJSONDateFormat.format(transaction.getModifiedDate()));
		}

		if (transaction.getStatus() == null) {
			map.put("status", null);
		}
		else {
			map.put("status", String.valueOf(transaction.getStatus()));
		}

		if (transaction.getTransactionDate() == null) {
			map.put("transactionDate", null);
		}
		else {
			map.put(
				"transactionDate",
				liferayToJSONDateFormat.format(
					transaction.getTransactionDate()));
		}

		if (transaction.getTransactionId() == null) {
			map.put("transactionId", null);
		}
		else {
			map.put(
				"transactionId",
				String.valueOf(transaction.getTransactionId()));
		}

		if (transaction.getTransactionType() == null) {
			map.put("transactionType", null);
		}
		else {
			map.put(
				"transactionType",
				String.valueOf(transaction.getTransactionType()));
		}

		if (transaction.getUuid() == null) {
			map.put("uuid", null);
		}
		else {
			map.put("uuid", String.valueOf(transaction.getUuid()));
		}

		return map;
	}

	public static class TransactionJSONParser
		extends BaseJSONParser<Transaction> {

		@Override
		protected Transaction createDTO() {
			return new Transaction();
		}

		@Override
		protected Transaction[] createDTOArray(int size) {
			return new Transaction[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "accountId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "amount")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedDate")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "status")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "transactionDate")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "transactionId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "transactionType")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "uuid")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			Transaction transaction, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "accountId")) {
				if (jsonParserFieldValue != null) {
					transaction.setAccountId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "amount")) {
				if (jsonParserFieldValue != null) {
					transaction.setAmount(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					transaction.setCreateDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "description")) {
				if (jsonParserFieldValue != null) {
					transaction.setDescription((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedDate")) {
				if (jsonParserFieldValue != null) {
					transaction.setModifiedDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "status")) {
				if (jsonParserFieldValue != null) {
					transaction.setStatus(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "transactionDate")) {
				if (jsonParserFieldValue != null) {
					transaction.setTransactionDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "transactionId")) {
				if (jsonParserFieldValue != null) {
					transaction.setTransactionId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "transactionType")) {
				if (jsonParserFieldValue != null) {
					transaction.setTransactionType(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "uuid")) {
				if (jsonParserFieldValue != null) {
					transaction.setUuid((String)jsonParserFieldValue);
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