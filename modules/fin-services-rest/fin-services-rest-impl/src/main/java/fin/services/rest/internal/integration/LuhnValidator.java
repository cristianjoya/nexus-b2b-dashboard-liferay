package fin.services.rest.internal.integration;

import com.liferay.portal.kernel.util.Validator;

/**
 * @author krism
 */
public class LuhnValidator {

	public static boolean isValid(String number) {
		if (Validator.isNull(number)) {
			return false;
		}

		String digits = number.replaceAll("\\D", "");

		if ((digits.length() < 13) || (digits.length() > 19)) {
			return false;
		}

		int sum = 0;
		boolean alternate = false;

		for (int index = digits.length() - 1; index >= 0; index--) {
			int digit = digits.charAt(index) - '0';

			if (alternate) {
				digit *= 2;

				if (digit > 9) {
					digit -= 9;
				}
			}

			sum += digit;
			alternate = !alternate;
		}

		return (sum % 10) == 0;
	}

	public static boolean requiresLuhnCheck(String number) {
		if (Validator.isNull(number)) {
			return false;
		}

		String digits = number.replaceAll("\\D", "");

		return digits.length() >= 13;
	}

	private LuhnValidator() {
	}

}
