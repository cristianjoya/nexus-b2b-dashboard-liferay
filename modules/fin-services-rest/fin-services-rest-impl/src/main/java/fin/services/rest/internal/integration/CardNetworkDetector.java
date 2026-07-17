package fin.services.rest.internal.integration;

import com.liferay.portal.kernel.util.Validator;

/**
 * @author krism
 */
public class CardNetworkDetector {

	public static String detectScheme(String digits) {
		if (Validator.isNull(digits)) {
			return null;
		}

		if (digits.startsWith("4")) {
			return "visa";
		}

		if (_matchesMastercard(digits)) {
			return "mastercard";
		}

		if (digits.startsWith("34") || digits.startsWith("37")) {
			return "amex";
		}

		if (digits.startsWith("6011") || digits.startsWith("65")) {
			return "discover";
		}

		if (digits.startsWith("36") || digits.startsWith("38")) {
			return "diners";
		}

		if (digits.startsWith("35")) {
			return "jcb";
		}

		if (digits.startsWith("62")) {
			return "unionpay";
		}

		return null;
	}

	private static boolean _matchesMastercard(String digits) {
		if (digits.length() < 2) {
			return false;
		}

		int prefix2 = Integer.parseInt(digits.substring(0, 2));

		if ((prefix2 >= 51) && (prefix2 <= 55)) {
			return true;
		}

		if (digits.length() < 4) {
			return false;
		}

		int prefix4 = Integer.parseInt(digits.substring(0, 4));

		return (prefix4 >= 2221) && (prefix4 <= 2720);
	}

	private CardNetworkDetector() {
	}

}
