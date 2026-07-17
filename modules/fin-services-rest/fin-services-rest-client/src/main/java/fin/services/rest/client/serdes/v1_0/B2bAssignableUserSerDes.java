package fin.services.rest.client.serdes.v1_0;

import fin.services.rest.client.dto.v1_0.B2bAssignableUser;
import fin.services.rest.client.json.BaseJSONParser;

import jakarta.annotation.Generated;

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
public class B2bAssignableUserSerDes {

	public static B2bAssignableUser toDTO(String json) {
		B2bAssignableUserJSONParser b2bAssignableUserJSONParser =
			new B2bAssignableUserJSONParser();

		return b2bAssignableUserJSONParser.parseToDTO(json);
	}

	public static B2bAssignableUser[] toDTOs(String json) {
		B2bAssignableUserJSONParser b2bAssignableUserJSONParser =
			new B2bAssignableUserJSONParser();

		return b2bAssignableUserJSONParser.parseToDTOs(json);
	}

	public static String toJSON(B2bAssignableUser b2bAssignableUser) {
		if (b2bAssignableUser == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (b2bAssignableUser.getEmailAddress() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"emailAddress\": ");

			sb.append("\"");

			sb.append(_escape(b2bAssignableUser.getEmailAddress()));

			sb.append("\"");
		}

		if (b2bAssignableUser.getFullName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"fullName\": ");

			sb.append("\"");

			sb.append(_escape(b2bAssignableUser.getFullName()));

			sb.append("\"");
		}

		if (b2bAssignableUser.getUserId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userId\": ");

			sb.append(b2bAssignableUser.getUserId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		B2bAssignableUserJSONParser b2bAssignableUserJSONParser =
			new B2bAssignableUserJSONParser();

		return b2bAssignableUserJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(
		B2bAssignableUser b2bAssignableUser) {

		if (b2bAssignableUser == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (b2bAssignableUser.getEmailAddress() == null) {
			map.put("emailAddress", null);
		}
		else {
			map.put(
				"emailAddress",
				String.valueOf(b2bAssignableUser.getEmailAddress()));
		}

		if (b2bAssignableUser.getFullName() == null) {
			map.put("fullName", null);
		}
		else {
			map.put(
				"fullName", String.valueOf(b2bAssignableUser.getFullName()));
		}

		if (b2bAssignableUser.getUserId() == null) {
			map.put("userId", null);
		}
		else {
			map.put("userId", String.valueOf(b2bAssignableUser.getUserId()));
		}

		return map;
	}

	public static class B2bAssignableUserJSONParser
		extends BaseJSONParser<B2bAssignableUser> {

		@Override
		protected B2bAssignableUser createDTO() {
			return new B2bAssignableUser();
		}

		@Override
		protected B2bAssignableUser[] createDTOArray(int size) {
			return new B2bAssignableUser[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "emailAddress")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "fullName")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "userId")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			B2bAssignableUser b2bAssignableUser, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "emailAddress")) {
				if (jsonParserFieldValue != null) {
					b2bAssignableUser.setEmailAddress(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "fullName")) {
				if (jsonParserFieldValue != null) {
					b2bAssignableUser.setFullName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userId")) {
				if (jsonParserFieldValue != null) {
					b2bAssignableUser.setUserId(
						Long.valueOf((String)jsonParserFieldValue));
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