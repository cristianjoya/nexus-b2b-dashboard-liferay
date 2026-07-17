import { appendSiteScopeParams, liferayFetch } from './liferay';

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

const API_BASE = '/o/fin-services-rest/v1.0';

/** BIN-only lookup. Full account numbers must never appear in the URL. */
export async function lookupCardBin(bin: string): Promise<CardBinInfo> {
	return liferayFetch<CardBinInfo>(
		appendSiteScopeParams(`${API_BASE}/card-bin/${encodeURIComponent(bin)}`),
	);
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
