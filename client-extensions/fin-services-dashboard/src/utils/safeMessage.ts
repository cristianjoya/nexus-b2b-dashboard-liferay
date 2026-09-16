/** Mensajes seguros para UI (OWASP: evitar XSS e info leakage). */

const CONTROL_CHARS = /[\u0000-\u0008\u000B\u000C\u000E-\u001F\u007F]/g;
const HTML_TAGS = /<[^>]*>/g;
const MAX_SAFE_MESSAGE = 280;

export function toSafeUserMessage(
	value: unknown,
	fallback = 'No fue posible completar la operación.',
): string {
	if (value == null) {
		return fallback;
	}

	const raw = String(value)
		.replace(HTML_TAGS, '')
		.replace(CONTROL_CHARS, '')
		.replace(/\s+/g, ' ')
		.trim();

	if (!raw) {
		return fallback;
	}

	return raw.length > MAX_SAFE_MESSAGE
		? `${raw.slice(0, MAX_SAFE_MESSAGE - 1)}…`
		: raw;
}
