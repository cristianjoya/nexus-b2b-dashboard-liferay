import { normalizeError, type ApiErrorKind } from '../api/apiError';

export interface ErrorPresentation {
	kind: ApiErrorKind;
	message: string;
	title: string;
}

export function getErrorPresentation(error: unknown): ErrorPresentation {
	const apiError = normalizeError(error);

	switch (apiError.kind) {
		case 'unauthenticated':
			return {
				kind: apiError.kind,
				message: apiError.message,
				title: 'Acceso restringido',
			};
		case 'forbidden':
			return {
				kind: apiError.kind,
				message: apiError.message,
				title: 'Sin permisos suficientes',
			};
		case 'server':
			return {
				kind: apiError.kind,
				message: apiError.message,
				title: 'Error del servidor',
			};
		default:
			return {
				kind: apiError.kind,
				message: apiError.message,
				title: 'No fue posible completar la operación',
			};
	}
}

export function formatCurrency(value?: number) {
	if (value == null) {
		return '-';
	}

	return new Intl.NumberFormat('es-CO', {
		currency: 'COP',
		style: 'currency',
	}).format(value);
}

export function formatRecordDate(value?: string) {
	if (!value) {
		return '-';
	}

	const date = new Date(value);

	if (Number.isNaN(date.getTime())) {
		return '-';
	}

	return new Intl.DateTimeFormat('es-CO', {
		day: '2-digit',
		month: 'short',
		year: 'numeric',
	}).format(date);
}
