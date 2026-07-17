export function maskCardNumber(value?: string | null): string {
	const digits = (value ?? '').replace(/\D/g, '');

	if (!digits) {
		return '—';
	}

	if (digits.length <= 4) {
		return digits;
	}

	if (digits.length <= 8) {
		return `${digits.slice(0, 4)} •••• ${digits.slice(-4)}`;
	}

	return `${digits.slice(0, 4)} •••• •••• ${digits.slice(-4)}`;
}

export function formatCardMetaLine(options: {
	bankName?: string | null;
	countryName?: string | null;
}) {
	const parts = [options.bankName, options.countryName].filter(Boolean);

	if (parts.length === 0) {
		return null;
	}

	return parts.join(' · ');
}
