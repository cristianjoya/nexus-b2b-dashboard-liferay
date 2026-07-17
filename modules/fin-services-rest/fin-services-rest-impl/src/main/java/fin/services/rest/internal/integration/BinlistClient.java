package fin.services.rest.internal.integration;

import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;

import fin.services.rest.dto.v1_0.CardBin;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Integrates with the public <a href="https://binlist.net/">binlist.net</a>
 * BIN database to resolve card networks (Visa, Mastercard, etc.).
 *
 * @author krism
 */
@Component(service = BinlistClient.class)
public class BinlistClient {

	/**
	 * Resolves scheme/bank metadata from a BIN (6–8 digits). Does not perform
	 * Luhn validation — that belongs on the client with the full number.
	 */
	public CardBin lookup(String bin) {
		CardBin cardBin = _buildLocalFallback(bin);

		if (Validator.isNull(bin) || (bin.length() < 6)) {
			return cardBin;
		}

		try {
			CardBin externalResult = _fetchFromBinlist(bin);

			if (externalResult != null) {
				externalResult.setProvider(PROVIDER_BINLIST);

				return externalResult;
			}
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"BIN lookup failed for " + bin + ": " +
						exception.getMessage());
			}
		}

		cardBin.setProvider(PROVIDER_LOCAL);

		return cardBin;
	}

	private CardBin _buildLocalFallback(String bin) {
		CardBin cardBin = new CardBin();

		cardBin.setBin(bin);

		if (Validator.isNull(bin)) {
			return cardBin;
		}

		String scheme = CardNetworkDetector.detectScheme(bin);

		if (Validator.isNotNull(scheme)) {
			cardBin.setScheme(scheme);
			cardBin.setBrand(formatSchemeLabel(scheme));
		}

		return cardBin;
	}

	private CardBin _fetchFromBinlist(String bin) throws Exception {
		Http.Options options = new Http.Options();

		options.addHeader("Accept", "application/json");
		options.addHeader("Accept-Version", "3");
		options.setLocation(BINLIST_URL + bin);
		options.setMethod(Http.Method.GET);
		options.setTimeout(BINLIST_TIMEOUT_MS);

		String response = HttpUtil.URLtoString(options);

		Http.Response httpResponse = options.getResponse();

		if ((httpResponse == null) || (httpResponse.getResponseCode() != 200)) {
			return null;
		}

		JSONObject jsonObject = _jsonFactory.createJSONObject(response);

		CardBin cardBin = new CardBin();

		cardBin.setBin(bin);
		cardBin.setScheme(jsonObject.getString("scheme"));
		cardBin.setBrand(
			Validator.isNotNull(jsonObject.getString("brand")) ?
				jsonObject.getString("brand") :
				formatSchemeLabel(jsonObject.getString("scheme")));
		cardBin.setType(jsonObject.getString("type"));

		JSONObject bankObject = jsonObject.getJSONObject("bank");

		if (bankObject != null) {
			cardBin.setBankName(bankObject.getString("name"));
		}

		JSONObject countryObject = jsonObject.getJSONObject("country");

		if (countryObject != null) {
			cardBin.setCountryName(countryObject.getString("name"));
			cardBin.setCountryCode(countryObject.getString("alpha2"));
		}

		return cardBin;
	}

	public static String formatSchemeLabel(String scheme) {
		if (Validator.isNull(scheme)) {
			return null;
		}

		return scheme.substring(0, 1).toUpperCase(Locale.ROOT) +
			scheme.substring(1);
	}

	private static final String BINLIST_URL = "https://lookup.binlist.net/";

	private static final int BINLIST_TIMEOUT_MS = 5000;

	private static final String PROVIDER_BINLIST = "binlist.net";

	private static final String PROVIDER_LOCAL = "local-heuristics";

	private static final Log _log = LogFactoryUtil.getLog(BinlistClient.class);

	@Reference
	private JSONFactory _jsonFactory;

}
