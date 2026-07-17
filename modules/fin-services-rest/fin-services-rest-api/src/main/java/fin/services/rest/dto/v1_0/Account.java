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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
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
@GraphQLName(description = "B2B financial account", value = "Account")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Account")
public class Account implements Serializable {

	public static Account toDTO(String json) {
		return ObjectMapperUtil.readValue(Account.class, json);
	}

	public static Account unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Account.class, json);
	}

	@io.swagger.v3.oas.annotations.media.Schema
	public Long getAccountId() {
		if (_accountIdSupplier != null) {
			accountId = _accountIdSupplier.get();

			_accountIdSupplier = null;
		}

		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;

		_accountIdSupplier = null;
	}

	@JsonIgnore
	public void setAccountId(
		UnsafeSupplier<Long, Exception> accountIdUnsafeSupplier) {

		_accountIdSupplier = () -> {
			try {
				return accountIdUnsafeSupplier.get();
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
	protected Long accountId;

	@JsonIgnore
	private Supplier<Long> _accountIdSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getAccountName() {
		if (_accountNameSupplier != null) {
			accountName = _accountNameSupplier.get();

			_accountNameSupplier = null;
		}

		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;

		_accountNameSupplier = null;
	}

	@JsonIgnore
	public void setAccountName(
		UnsafeSupplier<String, Exception> accountNameUnsafeSupplier) {

		_accountNameSupplier = () -> {
			try {
				return accountNameUnsafeSupplier.get();
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
	protected String accountName;

	@JsonIgnore
	private Supplier<String> _accountNameSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getAccountNumber() {
		if (_accountNumberSupplier != null) {
			accountNumber = _accountNumberSupplier.get();

			_accountNumberSupplier = null;
		}

		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;

		_accountNumberSupplier = null;
	}

	@JsonIgnore
	public void setAccountNumber(
		UnsafeSupplier<String, Exception> accountNumberUnsafeSupplier) {

		_accountNumberSupplier = () -> {
			try {
				return accountNumberUnsafeSupplier.get();
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
	protected String accountNumber;

	@JsonIgnore
	private Supplier<String> _accountNumberSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getAccountType() {
		if (_accountTypeSupplier != null) {
			accountType = _accountTypeSupplier.get();

			_accountTypeSupplier = null;
		}

		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;

		_accountTypeSupplier = null;
	}

	@JsonIgnore
	public void setAccountType(
		UnsafeSupplier<String, Exception> accountTypeUnsafeSupplier) {

		_accountTypeSupplier = () -> {
			try {
				return accountTypeUnsafeSupplier.get();
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
	protected String accountType;

	@JsonIgnore
	private Supplier<String> _accountTypeSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public Double getBalance() {
		if (_balanceSupplier != null) {
			balance = _balanceSupplier.get();

			_balanceSupplier = null;
		}

		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;

		_balanceSupplier = null;
	}

	@JsonIgnore
	public void setBalance(
		UnsafeSupplier<Double, Exception> balanceUnsafeSupplier) {

		_balanceSupplier = () -> {
			try {
				return balanceUnsafeSupplier.get();
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
	protected Double balance;

	@JsonIgnore
	private Supplier<Double> _balanceSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getCardBankName() {
		if (_cardBankNameSupplier != null) {
			cardBankName = _cardBankNameSupplier.get();

			_cardBankNameSupplier = null;
		}

		return cardBankName;
	}

	public void setCardBankName(String cardBankName) {
		this.cardBankName = cardBankName;

		_cardBankNameSupplier = null;
	}

	@JsonIgnore
	public void setCardBankName(
		UnsafeSupplier<String, Exception> cardBankNameUnsafeSupplier) {

		_cardBankNameSupplier = () -> {
			try {
				return cardBankNameUnsafeSupplier.get();
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
	protected String cardBankName;

	@JsonIgnore
	private Supplier<String> _cardBankNameSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getCardBrand() {
		if (_cardBrandSupplier != null) {
			cardBrand = _cardBrandSupplier.get();

			_cardBrandSupplier = null;
		}

		return cardBrand;
	}

	public void setCardBrand(String cardBrand) {
		this.cardBrand = cardBrand;

		_cardBrandSupplier = null;
	}

	@JsonIgnore
	public void setCardBrand(
		UnsafeSupplier<String, Exception> cardBrandUnsafeSupplier) {

		_cardBrandSupplier = () -> {
			try {
				return cardBrandUnsafeSupplier.get();
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
	protected String cardBrand;

	@JsonIgnore
	private Supplier<String> _cardBrandSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getCardCountryName() {
		if (_cardCountryNameSupplier != null) {
			cardCountryName = _cardCountryNameSupplier.get();

			_cardCountryNameSupplier = null;
		}

		return cardCountryName;
	}

	public void setCardCountryName(String cardCountryName) {
		this.cardCountryName = cardCountryName;

		_cardCountryNameSupplier = null;
	}

	@JsonIgnore
	public void setCardCountryName(
		UnsafeSupplier<String, Exception> cardCountryNameUnsafeSupplier) {

		_cardCountryNameSupplier = () -> {
			try {
				return cardCountryNameUnsafeSupplier.get();
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
	protected String cardCountryName;

	@JsonIgnore
	private Supplier<String> _cardCountryNameSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getCardScheme() {
		if (_cardSchemeSupplier != null) {
			cardScheme = _cardSchemeSupplier.get();

			_cardSchemeSupplier = null;
		}

		return cardScheme;
	}

	public void setCardScheme(String cardScheme) {
		this.cardScheme = cardScheme;

		_cardSchemeSupplier = null;
	}

	@JsonIgnore
	public void setCardScheme(
		UnsafeSupplier<String, Exception> cardSchemeUnsafeSupplier) {

		_cardSchemeSupplier = () -> {
			try {
				return cardSchemeUnsafeSupplier.get();
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
	protected String cardScheme;

	@JsonIgnore
	private Supplier<String> _cardSchemeSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public Date getCreateDate() {
		if (_createDateSupplier != null) {
			createDate = _createDateSupplier.get();

			_createDateSupplier = null;
		}

		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;

		_createDateSupplier = null;
	}

	@JsonIgnore
	public void setCreateDate(
		UnsafeSupplier<Date, Exception> createDateUnsafeSupplier) {

		_createDateSupplier = () -> {
			try {
				return createDateUnsafeSupplier.get();
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
	protected Date createDate;

	@JsonIgnore
	private Supplier<Date> _createDateSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public Date getModifiedDate() {
		if (_modifiedDateSupplier != null) {
			modifiedDate = _modifiedDateSupplier.get();

			_modifiedDateSupplier = null;
		}

		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;

		_modifiedDateSupplier = null;
	}

	@JsonIgnore
	public void setModifiedDate(
		UnsafeSupplier<Date, Exception> modifiedDateUnsafeSupplier) {

		_modifiedDateSupplier = () -> {
			try {
				return modifiedDateUnsafeSupplier.get();
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
	protected Date modifiedDate;

	@JsonIgnore
	private Supplier<Date> _modifiedDateSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public Long getOwnerUserId() {
		if (_ownerUserIdSupplier != null) {
			ownerUserId = _ownerUserIdSupplier.get();

			_ownerUserIdSupplier = null;
		}

		return ownerUserId;
	}

	public void setOwnerUserId(Long ownerUserId) {
		this.ownerUserId = ownerUserId;

		_ownerUserIdSupplier = null;
	}

	@JsonIgnore
	public void setOwnerUserId(
		UnsafeSupplier<Long, Exception> ownerUserIdUnsafeSupplier) {

		_ownerUserIdSupplier = () -> {
			try {
				return ownerUserIdUnsafeSupplier.get();
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
	protected Long ownerUserId;

	@JsonIgnore
	private Supplier<Long> _ownerUserIdSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getOwnerUserName() {
		if (_ownerUserNameSupplier != null) {
			ownerUserName = _ownerUserNameSupplier.get();

			_ownerUserNameSupplier = null;
		}

		return ownerUserName;
	}

	public void setOwnerUserName(String ownerUserName) {
		this.ownerUserName = ownerUserName;

		_ownerUserNameSupplier = null;
	}

	@JsonIgnore
	public void setOwnerUserName(
		UnsafeSupplier<String, Exception> ownerUserNameUnsafeSupplier) {

		_ownerUserNameSupplier = () -> {
			try {
				return ownerUserNameUnsafeSupplier.get();
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
	protected String ownerUserName;

	@JsonIgnore
	private Supplier<String> _ownerUserNameSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public Integer getStatus() {
		if (_statusSupplier != null) {
			status = _statusSupplier.get();

			_statusSupplier = null;
		}

		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;

		_statusSupplier = null;
	}

	@JsonIgnore
	public void setStatus(
		UnsafeSupplier<Integer, Exception> statusUnsafeSupplier) {

		_statusSupplier = () -> {
			try {
				return statusUnsafeSupplier.get();
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
	protected Integer status;

	@JsonIgnore
	private Supplier<Integer> _statusSupplier;

	@io.swagger.v3.oas.annotations.media.Schema
	public String getUuid() {
		if (_uuidSupplier != null) {
			uuid = _uuidSupplier.get();

			_uuidSupplier = null;
		}

		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;

		_uuidSupplier = null;
	}

	@JsonIgnore
	public void setUuid(UnsafeSupplier<String, Exception> uuidUnsafeSupplier) {
		_uuidSupplier = () -> {
			try {
				return uuidUnsafeSupplier.get();
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
	protected String uuid;

	@JsonIgnore
	private Supplier<String> _uuidSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Account)) {
			return false;
		}

		Account account = (Account)object;

		return Objects.equals(toString(), account.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		Long accountId = getAccountId();

		if (accountId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountId\": ");

			sb.append(accountId);
		}

		String accountName = getAccountName();

		if (accountName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountName\": ");

			sb.append("\"");

			sb.append(_escape(accountName));

			sb.append("\"");
		}

		String accountNumber = getAccountNumber();

		if (accountNumber != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountNumber\": ");

			sb.append("\"");

			sb.append(_escape(accountNumber));

			sb.append("\"");
		}

		String accountType = getAccountType();

		if (accountType != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"accountType\": ");

			sb.append("\"");

			sb.append(_escape(accountType));

			sb.append("\"");
		}

		Double balance = getBalance();

		if (balance != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"balance\": ");

			sb.append(balance);
		}

		String cardBankName = getCardBankName();

		if (cardBankName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"cardBankName\": ");

			sb.append("\"");

			sb.append(_escape(cardBankName));

			sb.append("\"");
		}

		String cardBrand = getCardBrand();

		if (cardBrand != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"cardBrand\": ");

			sb.append("\"");

			sb.append(_escape(cardBrand));

			sb.append("\"");
		}

		String cardCountryName = getCardCountryName();

		if (cardCountryName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"cardCountryName\": ");

			sb.append("\"");

			sb.append(_escape(cardCountryName));

			sb.append("\"");
		}

		String cardScheme = getCardScheme();

		if (cardScheme != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"cardScheme\": ");

			sb.append("\"");

			sb.append(_escape(cardScheme));

			sb.append("\"");
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(createDate));

			sb.append("\"");
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"modifiedDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(modifiedDate));

			sb.append("\"");
		}

		Long ownerUserId = getOwnerUserId();

		if (ownerUserId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ownerUserId\": ");

			sb.append(ownerUserId);
		}

		String ownerUserName = getOwnerUserName();

		if (ownerUserName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ownerUserName\": ");

			sb.append("\"");

			sb.append(_escape(ownerUserName));

			sb.append("\"");
		}

		Integer status = getStatus();

		if (status != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append(status);
		}

		String uuid = getUuid();

		if (uuid != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"uuid\": ");

			sb.append("\"");

			sb.append(_escape(uuid));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@io.swagger.v3.oas.annotations.media.Schema(
		accessMode = io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY,
		defaultValue = "fin.services.rest.dto.v1_0.Account",
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