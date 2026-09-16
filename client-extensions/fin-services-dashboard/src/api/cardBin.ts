export interface CardBinInfo {
	bankName?: string;
	bin?: string;
	brand?: string;
	countryCode?: string;
	countryName?: string;
	luhnValid?: boolean | null;
	provider?: string;
	scheme?: string;
	type?: string;
}

function detectSchemeLocal(digits: string): string | null {
	if (!digits) {
		return null;
	}

	if (digits.startsWith('4')) {
		return 'visa';
	}

	if (matchesMastercard(digits)) {
		return 'mastercard';
	}

	if (digits.startsWith('34') || digits.startsWith('37')) {
		return 'amex';
	}

	if (digits.startsWith('6011') || digits.startsWith('65')) {
		return 'discover';
	}

	if (digits.startsWith('36') || digits.startsWith('38')) {
		return 'diners';
	}

	if (digits.startsWith('35')) {
		return 'jcb';
	}

	if (digits.startsWith('62')) {
		return 'unionpay';
	}

	return null;
}

function matchesMastercard(digits: string): boolean {
	if (digits.length < 2) {
		return false;
	}

	const prefix2 = Number(digits.slice(0, 2));

	if (prefix2 >= 51 && prefix2 <= 55) {
		return true;
	}

	if (digits.length < 4) {
		return false;
	}

	const prefix4 = Number(digits.slice(0, 4));

	return prefix4 >= 2221 && prefix4 <= 2720;
}

/**
 * Detección local de red (BIN). Sin llamadas externas (evita CORS y filtrado de PAN).
 */
export async function lookupCardBin(bin: string): Promise<CardBinInfo> {
	const digits = bin.replace(/\D/g, '').slice(0, 8);
	const scheme = detectSchemeLocal(digits) ?? undefined;

	return {
		bin: digits,
		brand: scheme ? formatCardSchemeLabel(scheme) ?? undefined : undefined,
		provider: 'local-heuristics',
		scheme,
	};
}

export function formatCardSchemeLabel(scheme?: string) {
	if (!scheme) {
		return null;
	}

	return scheme.charAt(0).toUpperCase() + scheme.slice(1);
}

export function getCardSchemeStyles(scheme?: string) {
	switch (scheme?.toLowerCase()) {
		case 'visa':
			return 'nexus-card-scheme-visa';
		case 'mastercard':
			return 'nexus-card-scheme-mastercard';
		case 'amex':
			return 'nexus-card-scheme-amex';
		case 'discover':
			return 'nexus-card-scheme-discover';
		default:
			return 'nexus-card-scheme-default';
	}
}

export function requiresLuhnCheck(digits: string) {
	return digits.length >= 13;
}

export function isLuhnValid(number: string): boolean {
	const digits = number.replace(/\D/g, '');

	if (digits.length < 13 || digits.length > 19) {
		return false;
	}

	let sum = 0;
	let alternate = false;

	for (let index = digits.length - 1; index >= 0; index--) {
		let digit = Number(digits[index]);

		if (alternate) {
			digit *= 2;

			if (digit > 9) {
				digit -= 9;
			}
		}

		sum += digit;
		alternate = !alternate;
	}

	return sum % 10 === 0;
}

export function validateCardNumber(
	accountNumber: string,
	cardBin: CardBinInfo | null | undefined,
): string | null {
	const digits = accountNumber.replace(/\D/g, '');

	if (digits.length < 6) {
		return 'Ingresa al menos 6 dígitos para identificar la red de pago.';
	}

	if (!cardBin?.scheme) {
		return 'No se reconoce una red de pago válida (Visa, Mastercard, Amex, etc.).';
	}

	if (requiresLuhnCheck(digits) && !isLuhnValid(digits)) {
		return 'El número no supera la validación Luhn de tarjetas de pago.';
	}

	return null;
}
