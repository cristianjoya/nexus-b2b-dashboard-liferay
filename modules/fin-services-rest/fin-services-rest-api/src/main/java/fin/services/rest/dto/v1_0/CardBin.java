package fin.services.rest.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import jakarta.annotation.Generated;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author krism
 * @generated
 */
@Generated("")
@GraphQLName(
	description = "BIN lookup enriched with external provider data",
	value = "CardBin"
)
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "CardBin")
public class CardBin implements Serializable {

	public static CardBin toDTO(String json) {
		return ObjectMapperUtil.readValue(CardBin.class, json);
	}

	public static CardBin unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(CardBin.class, json);
	}

	@io.swagger.v3.oas.annotations.media.Schema
	public String getBankName() {
		if (_bankNameSupplier != null) {
			bankName = _bankNameSupplier.get();

			_bankNameSupplier = null;
		}

		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;

		_bankNameSupplier = null;
	}

	@JsonIgnore
	public void setBankName(
		UnsafeSupplier<String, Exception> bankNameUnsafeSupplier) {

		_bankNameSupplier = () -> {
			try {
				return bankNameUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String bankName;

	@JsonIgnore
	private Supplier<String> _bankNameSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getBin() {
		if (_binSupplier != null) {
			bin = _binSupplier.get();

			_binSupplier = null;
		}

		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;

		_binSupplier = null;
	}

	@JsonIgnore
	public void setBin(UnsafeSupplier<String, Exception> binUnsafeSupplier) {
		_binSupplier = () -> {
			try {
				return binUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String bin;

	@JsonIgnore
	private Supplier<String> _binSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getBrand() {
		if (_brandSupplier != null) {
			brand = _brandSupplier.get();

			_brandSupplier = null;
		}

		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;

		_brandSupplier = null;
	}

	@JsonIgnore
	public void setBrand(
		UnsafeSupplier<String, Exception> brandUnsafeSupplier) {

		_brandSupplier = () -> {
			try {
				return brandUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String brand;

	@JsonIgnore
	private Supplier<String> _brandSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getCountryCode() {
		if (_countryCodeSupplier != null) {
			countryCode = _countryCodeSupplier.get();

			_countryCodeSupplier = null;
		}

		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;

		_countryCodeSupplier = null;
	}

	@JsonIgnore
	public void setCountryCode(
		UnsafeSupplier<String, Exception> countryCodeUnsafeSupplier) {

		_countryCodeSupplier = () -> {
			try {
				return countryCodeUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String countryCode;

	@JsonIgnore
	private Supplier<String> _countryCodeSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getCountryName() {
		if (_countryNameSupplier != null) {
			countryName = _countryNameSupplier.get();

			_countryNameSupplier = null;
		}

		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;

		_countryNameSupplier = null;
	}

	@JsonIgnore
	public void setCountryName(
		UnsafeSupplier<String, Exception> countryNameUnsafeSupplier) {

		_countryNameSupplier = () -> {
			try {
				return countryNameUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String countryName;

	@JsonIgnore
	private Supplier<String> _countryNameSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public Boolean getLuhnValid() {
		if (_luhnValidSupplier != null) {
			luhnValid = _luhnValidSupplier.get();

			_luhnValidSupplier = null;
		}

		return luhnValid;
	}

	public void setLuhnValid(Boolean luhnValid) {
		this.luhnValid = luhnValid;

		_luhnValidSupplier = null;
	}

	@JsonIgnore
	public void setLuhnValid(
		UnsafeSupplier<Boolean, Exception> luhnValidUnsafeSupplier) {

		_luhnValidSupplier = () -> {
			try {
				return luhnValidUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean luhnValid;

	@JsonIgnore
	private Supplier<Boolean> _luhnValidSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getProvider() {
		if (_providerSupplier != null) {
			provider = _providerSupplier.get();

			_providerSupplier = null;
		}

		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;

		_providerSupplier = null;
	}

	@JsonIgnore
	public void setProvider(
		UnsafeSupplier<String, Exception> providerUnsafeSupplier) {

		_providerSupplier = () -> {
			try {
				return providerUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String provider;

	@JsonIgnore
	private Supplier<String> _providerSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getScheme() {
		if (_schemeSupplier != null) {
			scheme = _schemeSupplier.get();

			_schemeSupplier = null;
		}

		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;

		_schemeSupplier = null;
	}

	@JsonIgnore
	public void setScheme(
		UnsafeSupplier<String, Exception> schemeUnsafeSupplier) {

		_schemeSupplier = () -> {
			try {
				return schemeUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String scheme;

	@JsonIgnore
	private Supplier<String> _schemeSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getType() {
		if (_typeSupplier != null) {
			type = _typeSupplier.get();

			_typeSupplier = null;
		}

		return type;
	}

	public void setType(String type) {
		this.type = type;

		_typeSupplier = null;
	}

	@JsonIgnore
	public void setType(UnsafeSupplier<String, Exception> typeUnsafeSupplier) {
		_typeSupplier = () -> {
			try {
				return typeUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String type;

	@JsonIgnore
	private Supplier<String> _typeSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CardBin)) {
			return false;
		}

		CardBin cardBin = (CardBin)object;

		return Objects.equals(toString(), cardBin.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		String bankName = getBankName();

		if (bankName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"bankName\": ");

			sb.append("\"");

			sb.append(_escape(bankName));

			sb.append("\"");
		}

		String bin = getBin();

		if (bin != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"bin\": ");

			sb.append("\"");

			sb.append(_escape(bin));

			sb.append("\"");
		}

		String brand = getBrand();

		if (brand != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"brand\": ");

			sb.append("\"");

			sb.append(_escape(brand));

			sb.append("\"");
		}

		String countryCode = getCountryCode();

		if (countryCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"countryCode\": ");

			sb.append("\"");

			sb.append(_escape(countryCode));

			sb.append("\"");
		}

		String countryName = getCountryName();

		if (countryName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"countryName\": ");

			sb.append("\"");

			sb.append(_escape(countryName));

			sb.append("\"");
		}

		Boolean luhnValid = getLuhnValid();

		if (luhnValid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"luhnValid\": ");

			sb.append(luhnValid);
		}

		String provider = getProvider();

		if (provider != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"provider\": ");

			sb.append("\"");

			sb.append(_escape(provider));

			sb.append("\"");
		}

		String scheme = getScheme();

		if (scheme != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"scheme\": ");

			sb.append("\"");

			sb.append(_escape(scheme));

			sb.append("\"");
		}

		String type = getType();

		if (type != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(type));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@io.swagger.v3.oas.annotations.media.Schema(
		accessMode = io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY,
		defaultValue = "fin.services.rest.dto.v1_0.CardBin",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
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
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof Map) {
						sb.append(_toJSON((Map<String, ?>)valueArray[i]));
					}
					else if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

	private Map<String, Serializable> _extendedProperties;

}