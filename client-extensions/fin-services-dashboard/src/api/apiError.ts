export type ApiErrorKind =
	| 'forbidden'
	| 'network'
	| 'server'
	| 'unauthenticated'
	| 'unknown';

export class LiferayApiError extends Error {
	readonly kind: ApiErrorKind;
	readonly status: number;

	constructor(message: string, status: number, kind: ApiErrorKind) {
		super(message);
		this.name = 'LiferayApiError';
		this.status = status;
		this.kind = kind;
	}
}

declare global {
	interface Window {
		Liferay?: {
			authToken?: string;
			ThemeDisplay?: {
				getPathMain?: () => string;
				getScopeGroupId?: () => number;
				getSiteGroupId?: () => number;
				getUserId?: () => number;
				isSignedIn?: () => boolean;
			};
		};
	}
}

export function isUserSignedIn(): boolean {
	const themeDisplay = window.Liferay?.ThemeDisplay;

	if (themeDisplay?.isSignedIn) {
		return themeDisplay.isSignedIn();
	}

	return false;
}

export function getLiferayUserId(): number {
	return window.Liferay?.ThemeDisplay?.getUserId?.() ?? 0;
}

export function getUnauthenticatedError(): LiferayApiError {
	return new LiferayApiError(
		'Debes iniciar sesión para consultar las cuentas de este portal.',
		401,
		'unauthenticated',
	);
}

export function getSignInUrl(): string {
	const pathMain = window.Liferay?.ThemeDisplay?.getPathMain?.();

	if (pathMain) {
		return `${pathMain}/portal/login`;
	}

	return '/c/portal/login';
}

export function toLiferayApiError(
	status: number,
	detail?: string,
): LiferayApiError {
	if (status === 401) {
		return new LiferayApiError(
			detail ??
				'Debes iniciar sesión para consultar las cuentas de este portal.',
			status,
			'unauthenticated',
		);
	}

	if (status === 403) {
		if (!isUserSignedIn()) {
			return new LiferayApiError(
				detail ??
					'No puedes ver esta información porque no has iniciado sesión.',
				status,
				'unauthenticated',
			);
		}

		return new LiferayApiError(
			detail ??
				'Tu usuario no tiene permisos para ver o gestionar cuentas en este sitio. Contacta al administrador si crees que deberías tener acceso.',
			status,
			'forbidden',
		);
	}

	if (status >= 400 && status < 500) {
		return new LiferayApiError(
			detail ?? 'No fue posible completar la operación.',
			status,
			status === 403 ? 'forbidden' : 'unknown',
		);
	}

	if (status >= 500) {
		return new LiferayApiError(
			detail ??
				'Ocurrió un error en el servidor al procesar la solicitud. Intenta de nuevo en unos momentos.',
			status,
			'server',
		);
	}

	return new LiferayApiError(
		detail ?? 'No fue posible completar la operación en este momento.',
		status,
		'unknown',
	);
}

export function normalizeError(error: unknown): LiferayApiError {
	if (error instanceof LiferayApiError) {
		return error;
	}

	if (error instanceof TypeError) {
		return new LiferayApiError(
			'No se pudo conectar con el servicio. Verifica tu conexión e intenta nuevamente.',
			0,
			'network',
		);
	}

	if (error instanceof SyntaxError) {
		return new LiferayApiError(
			'El servidor devolvió una respuesta inválida. Recarga la página; si persiste, reinicia Liferay.',
			0,
			'server',
		);
	}

	return new LiferayApiError(
		error instanceof Error
			? error.message
			: 'Ocurrió un error inesperado.',
		0,
		'unknown',
	);
}
