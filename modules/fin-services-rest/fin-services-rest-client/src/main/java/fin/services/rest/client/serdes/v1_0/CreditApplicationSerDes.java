package fin.services.rest.client.serdes.v1_0;

import fin.services.rest.client.dto.v1_0.CreditApplication;
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
public class CreditApplicationSerDes {

	public static CreditApplication toDTO(String json) {
		CreditApplicationJSONParser creditApplicationJSONParser =
			new CreditApplicationJSONParser();

		return creditApplicationJSONParser.parseToDTO(json);
	}

	public static CreditApplication[] toDTOs(String json) {
		CreditApplicationJSONParser creditApplicationJSONParser =
			new CreditApplicationJSONParser();

		return creditApplicationJSONParser.parseToDTOs(json);
	}

	public static String toJSON(CreditApplication creditApplication) {
		if (creditApplication == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (creditApplication.getAccountId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountId\": ");

			sb.append(creditApplication.getAccountId());
		}

		if (creditApplication.getApplicantName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"applicantName\": ");

			sb.append("\"");

			sb.append(_escape(creditApplication.getApplicantName()));

			sb.append("\"");
		}

		if (creditApplication.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					creditApplication.getCreateDate()));

			sb.append("\"");
		}

		if (creditApplication.getCreditAppId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"creditAppId\": ");

			sb.append(creditApplication.getCreditAppId());
		}

		if (creditApplication.getModifiedDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedDate\": ");

			sb.append("\"");

			sb.append(
				liferayToJSONDateFormat.format(
					creditApplication.getModifiedDate()));

			sb.append("\"");
		}

		if (creditApplication.getPurpose() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"purpose\": ");

			sb.append("\"");

			sb.append(_escape(creditApplication.getPurpose()));

			sb.append("\"");
		}

		if (creditApplication.getRequestedAmount() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"requestedAmount\": ");

			sb.append(creditApplication.getRequestedAmount());
		}

		if (creditApplication.getReviewNotes() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"reviewNotes\": ");

			sb.append("\"");

			sb.append(_escape(creditApplication.getReviewNotes()));

			sb.append("\"");
		}

		if (creditApplication.getStatus() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append(creditApplication.getStatus());
		}

		if (creditApplication.getUuid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"uuid\": ");

			sb.append("\"");

			sb.append(_escape(creditApplication.getUuid()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		CreditApplicationJSONParser creditApplicationJSONParser =
			new CreditApplicationJSONParser();

		return creditApplicationJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		CreditApplication creditApplication) {

		if (creditApplication == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ssXX");

		if (creditApplication.getAccountId() == null) {
			map.put("accountId", null);
		}
		else {
			map.put(
				"accountId", String.valueOf(creditApplication.getAccountId()));
		}

		if (creditApplication.getApplicantName() == null) {
			map.put("applicantName", null);
		}
		else {
			map.put(
				"applicantName",
				String.valueOf(creditApplication.getApplicantName()));
		}

		if (creditApplication.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put(
				"createDate",
				liferayToJSONDateFormat.format(
					creditApplication.getCreateDate()));
		}

		if (creditApplication.getCreditAppId() == null) {
			map.put("creditAppId", null);
		}
		else {
			map.put(
				"creditAppId",
				String.valueOf(creditApplication.getCreditAppId()));
		}

		if (creditApplication.getModifiedDate() == null) {
			map.put("modifiedDate", null);
		}
		else {
			map.put(
				"modifiedDate",
				liferayToJSONDateFormat.format(
					creditApplication.getModifiedDate()));
		}

		if (creditApplication.getPurpose() == null) {
			map.put("purpose", null);
		}
		else {
			map.put("purpose", String.valueOf(creditApplication.getPurpose()));
		}

		if (creditApplication.getRequestedAmount() == null) {
			map.put("requestedAmount", null);
		}
		else {
			map.put(
				"requestedAmount",
				String.valueOf(creditApplication.getRequestedAmount()));
		}

		if (creditApplication.getReviewNotes() == null) {
			map.put("reviewNotes", null);
		}
		else {
			map.put(
				"reviewNotes",
				String.valueOf(creditApplication.getReviewNotes()));
		}

		if (creditApplication.getStatus() == null) {
			map.put("status", null);
		}
		else {
			map.put("status", String.valueOf(creditApplication.getStatus()));
		}

		if (creditApplication.getUuid() == null) {
			map.put("uuid", null);
		}
		else {
			map.put("uuid", String.valueOf(creditApplication.getUuid()));
		}

		return map;
	}

	public static class CreditApplicationJSONParser
		extends BaseJSONParser<CreditApplication> {

		@Override
		protected CreditApplication createDTO() {
			return new CreditApplication();
		}

		@Override
		protected CreditApplication[] createDTOArray(int size) {
			return new CreditApplication[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "accountId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "applicantName")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "creditAppId")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedDate")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "purpose")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "requestedAmount")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "reviewNotes")) {
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
			CreditApplication creditApplication, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "accountId")) {
				if (jsonParserFieldValue != null) {
					creditApplication.setAccountId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "applicantName")) {
				if (jsonParserFieldValue != null) {
					creditApplication.setApplicantName(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					creditApplication.setCreateDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "creditAppId")) {
				if (jsonParserFieldValue != null) {
					creditApplication.setCreditAppId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "modifiedDate")) {
				if (jsonParserFieldValue != null) {
					creditApplication.setModifiedDate(
						toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "purpose")) {
				if (jsonParserFieldValue != null) {
					creditApplication.setPurpose((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "requestedAmount")) {
				if (jsonParserFieldValue != null) {
					creditApplication.setRequestedAmount(
						Double.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "reviewNotes")) {
				if (jsonParserFieldValue != null) {
					creditApplication.setReviewNotes(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "status")) {
				if (jsonParserFieldValue != null) {
					creditApplication.setStatus(
						Integer.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "uuid")) {
				if (jsonParserFieldValue != null) {
					creditApplication.setUuid((String)jsonParserFieldValue);
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