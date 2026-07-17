package fin.services.rest.client.serdes.v1_0;

import fin.services.rest.client.dto.v1_0.CardBin;
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
public class CardBinSerDes {

	public static CardBin toDTO(String json) {
		CardBinJSONParser cardBinJSONParser = new CardBinJSONParser();

		return cardBinJSONParser.parseToDTO(json);
	}

	public static CardBin[] toDTOs(String json) {
		CardBinJSONParser cardBinJSONParser = new CardBinJSONParser();

		return cardBinJSONParser.parseToDTOs(json);
	}

	public static String toJSON(CardBin cardBin) {
		if (cardBin == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (cardBin.getBankName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"bankName\": ");

			sb.append("\"");

			sb.append(_escape(cardBin.getBankName()));

			sb.append("\"");
		}

		if (cardBin.getBin() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"bin\": ");

			sb.append("\"");

			sb.append(_escape(cardBin.getBin()));

			sb.append("\"");
		}

		if (cardBin.getBrand() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"brand\": ");

			sb.append("\"");

			sb.append(_escape(cardBin.getBrand()));

			sb.append("\"");
		}

		if (cardBin.getCountryCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"countryCode\": ");

			sb.append("\"");

			sb.append(_escape(cardBin.getCountryCode()));

			sb.append("\"");
		}

		if (cardBin.getCountryName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"countryName\": ");

			sb.append("\"");

			sb.append(_escape(cardBin.getCountryName()));

			sb.append("\"");
		}

		if (cardBin.getLuhnValid() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"luhnValid\": ");

			sb.append(cardBin.getLuhnValid());
		}

		if (cardBin.getProvider() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"provider\": ");

			sb.append("\"");

			sb.append(_escape(cardBin.getProvider()));

			sb.append("\"");
		}

		if (cardBin.getScheme() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"scheme\": ");

			sb.append("\"");

			sb.append(_escape(cardBin.getScheme()));

			sb.append("\"");
		}

		if (cardBin.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(cardBin.getType()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		CardBinJSONParser cardBinJSONParser = new CardBinJSONParser();

		return cardBinJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(CardBin cardBin) {
		if (cardBin == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (cardBin.getBankName() == null) {
			map.put("bankName", null);
		}
		else {
			map.put("bankName", String.valueOf(cardBin.getBankName()));
		}

		if (cardBin.getBin() == null) {
			map.put("bin", null);
		}
		else {
			map.put("bin", String.valueOf(cardBin.getBin()));
		}

		if (cardBin.getBrand() == null) {
			map.put("brand", null);
		}
		else {
			map.put("brand", String.valueOf(cardBin.getBrand()));
		}

		if (cardBin.getCountryCode() == null) {
			map.put("countryCode", null);
		}
		else {
			map.put("countryCode", String.valueOf(cardBin.getCountryCode()));
		}

		if (cardBin.getCountryName() == null) {
			map.put("countryName", null);
		}
		else {
			map.put("countryName", String.valueOf(cardBin.getCountryName()));
		}

		if (cardBin.getLuhnValid() == null) {
			map.put("luhnValid", null);
		}
		else {
			map.put("luhnValid", String.valueOf(cardBin.getLuhnValid()));
		}

		if (cardBin.getProvider() == null) {
			map.put("provider", null);
		}
		else {
			map.put("provider", String.valueOf(cardBin.getProvider()));
		}

		if (cardBin.getScheme() == null) {
			map.put("scheme", null);
		}
		else {
			map.put("scheme", String.valueOf(cardBin.getScheme()));
		}

		if (cardBin.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(cardBin.getType()));
		}

		return map;
	}

	public static class CardBinJSONParser extends BaseJSONParser<CardBin> {

		@Override
		protected CardBin createDTO() {
			return new CardBin();
		}

		@Override
		protected CardBin[] createDTOArray(int size) {
			return new CardBin[size];
		}

		@Override
		protected boolean parseMaps(String jsonParserFieldName) {
			if (Objects.equals(jsonParserFieldName, "bankName")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "bin")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "brand")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "countryCode")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "countryName")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "luhnValid")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "provider")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "scheme")) {
				return false;
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				return false;
			}

			return false;
		}

		@Override
		protected void setField(
			CardBin cardBin, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "bankName")) {
				if (jsonParserFieldValue != null) {
					cardBin.setBankName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "bin")) {
				if (jsonParserFieldValue != null) {
					cardBin.setBin((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "brand")) {
				if (jsonParserFieldValue != null) {
					cardBin.setBrand((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "countryCode")) {
				if (jsonParserFieldValue != null) {
					cardBin.setCountryCode((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "countryName")) {
				if (jsonParserFieldValue != null) {
					cardBin.setCountryName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "luhnValid")) {
				if (jsonParserFieldValue != null) {
					cardBin.setLuhnValid((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "provider")) {
				if (jsonParserFieldValue != null) {
					cardBin.setProvider((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "scheme")) {
				if (jsonParserFieldValue != null) {
					cardBin.setScheme((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					cardBin.setType((String)jsonParserFieldValue);
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