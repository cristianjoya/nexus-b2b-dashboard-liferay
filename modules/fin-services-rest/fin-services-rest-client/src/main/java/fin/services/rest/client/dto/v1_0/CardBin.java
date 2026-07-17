package fin.services.rest.client.dto.v1_0;

import fin.services.rest.client.function.UnsafeSupplier;
import fin.services.rest.client.serdes.v1_0.CardBinSerDes;

import jakarta.annotation.Generated;

import java.io.Serializable;

import java.util.Objects;

/**
 * @author krism
 * @generated
 */
@Generated("")
public class CardBin implements Cloneable, Serializable {

	public static CardBin toDTO(String json) {
		return CardBinSerDes.toDTO(json);
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setBankName(
		UnsafeSupplier<String, Exception> bankNameUnsafeSupplier) {

		try {
			bankName = bankNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String bankName;

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public void setBin(UnsafeSupplier<String, Exception> binUnsafeSupplier) {
		try {
			bin = binUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String bin;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setBrand(
		UnsafeSupplier<String, Exception> brandUnsafeSupplier) {

		try {
			brand = brandUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String brand;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setCountryCode(
		UnsafeSupplier<String, Exception> countryCodeUnsafeSupplier) {

		try {
			countryCode = countryCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String countryCode;

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public void setCountryName(
		UnsafeSupplier<String, Exception> countryNameUnsafeSupplier) {

		try {
			countryName = countryNameUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String countryName;

	public Boolean getLuhnValid() {
		return luhnValid;
	}

	public void setLuhnValid(Boolean luhnValid) {
		this.luhnValid = luhnValid;
	}

	public void setLuhnValid(
		UnsafeSupplier<Boolean, Exception> luhnValidUnsafeSupplier) {

		try {
			luhnValid = luhnValidUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Boolean luhnValid;

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public void setProvider(
		UnsafeSupplier<String, Exception> providerUnsafeSupplier) {

		try {
			provider = providerUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String provider;

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public void setScheme(
		UnsafeSupplier<String, Exception> schemeUnsafeSupplier) {

		try {
			scheme = schemeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String scheme;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setType(UnsafeSupplier<String, Exception> typeUnsafeSupplier) {
		try {
			type = typeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String type;

	@Override
	public CardBin clone() throws CloneNotSupportedException {
		return (CardBin)super.clone();
	}

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
		return CardBinSerDes.toJSON(this);
	}

}