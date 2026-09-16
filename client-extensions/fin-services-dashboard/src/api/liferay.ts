import { normalizeError, LiferayApiError, toLiferayApiError } from './apiError';
import { toSafeUserMessage } from '../utils/safeMessage';

export type SiteContext = {
	pageUrl: string;
	scopeGroupId: number;
	siteFriendlyUrl: string | null;
};

const RESERVED_PATH_SEGMENTS = new Set([
	'api',
	'c',
	'documents',
	'group',
	'image',
	'o',
	'web',
]);

function getPathSegments(pathname: string): string[] {
	return pathname.split('/').filter(Boolean);
}

function isLocaleSegment(segment: string): boolean {
	return /^[a-z]{2}(_[A-Z]{2})?$/.test(segment);
}

function getSegmentAfterLocale(segments: string[]): string | null {
	let index = 0;

	if (segments.length > index && isLocaleSegment(segments[index])) {
		index++;
	}

	if (segments.length <= index) {
		return null;
	}

	return segments[index];
}

export function getSiteFriendlyUrl(
	pathname = window.location.pathname,
): string | null {
	const segments = getPathSegments(pathname);
	const webIndex = segments.indexOf('web');

	if (webIndex >= 0 && webIndex + 1 < segments.length) {
		return `/${segments[webIndex + 1]}`;
	}

	const groupIndex = segments.indexOf('group');

	if (groupIndex >= 0 && groupIndex + 1 < segments.length) {
		return `/${segments[groupIndex + 1]}`;
	}

	const siteSegment = getSegmentAfterLocale(segments);

	if (siteSegment && !RESERVED_PATH_SEGMENTS.has(siteSegment)) {
		return `/${siteSegment}`;
	}

	return null;
}

export function getSiteContext(): SiteContext {
	const themeDisplay = window.Liferay?.ThemeDisplay;
	const siteFriendlyUrl = getSiteFriendlyUrl();
	const siteGroupId = Number(themeDisplay?.getSiteGroupId?.() ?? 0);
	const scopeGroupId = Number(themeDisplay?.getScopeGroupId?.() ?? 0);
	const resolvedGroupId =
		Number.isFinite(siteGroupId) && siteGroupId > 0
			? siteGroupId
			: Number.isFinite(scopeGroupId) && scopeGroupId > 0
				? scopeGroupId
				: 0;

	return {
		pageUrl: window.location.href,
		scopeGroupId: resolvedGroupId,
		siteFriendlyUrl,
	};
}

export function getScopeGroupId(): number {
	return getSiteContext().scopeGroupId;
}

function getCsrfToken(): string {
	return window.Liferay?.authToken ?? '';
}

/** Solo rutas relativas del portal (mitiga open-redirect / SSRF desde el CE). */
function assertSafePortalUrl(url: string): void {
	if (!url.startsWith('/') || url.startsWith('//')) {
		throw new LiferayApiError(
			'URL de API no permitida.',
			0,
			'unknown',
		);
	}
}

function mergeHeaders(
	defaults: Record<string, string>,
	init?: HeadersInit,
): Headers {
	const headers = new Headers(defaults);

	if (!init) {
		return headers;
	}

	const extra = new Headers(init);

	extra.forEach((value, key) => {
		headers.set(key, value);
	});

	return headers;
}

export async function liferayFetch<T>(
	url: string,
	init?: RequestInit,
): Promise<T> {
	assertSafePortalUrl(url);

	const { pageUrl, scopeGroupId, siteFriendlyUrl } = getSiteContext();
	const method = (init?.method ?? 'GET').toUpperCase();
	const defaultHeaders: Record<string, string> = {
		Accept: 'application/json',
		'X-Liferay-Current-Url': pageUrl,
		'x-csrf-token': getCsrfToken(),
	};

	if (siteFriendlyUrl) {
		defaultHeaders['X-Liferay-Site-Friendly-Url'] = siteFriendlyUrl;
	}

	if (scopeGroupId > 0) {
		defaultHeaders['X-Liferay-Scope-Group-Id'] = String(scopeGroupId);
	}

	if (method !== 'GET' && method !== 'HEAD') {
		defaultHeaders['Content-Type'] = 'application/json';
	}

	const { headers: initHeaders, ...restInit } = init ?? {};
	let response: Response;

	try {
		response = await fetch(url, {
			credentials: 'include',
			referrerPolicy: 'strict-origin-when-cross-origin',
			...restInit,
			headers: mergeHeaders(defaultHeaders, initHeaders),
		});
	}
	catch (error) {
		throw normalizeError(error);
	}

	if (!response.ok) {
		let detail: string | undefined;

		try {
			const body = (await response.json()) as {
				detail?: string;
				message?: string;
				title?: string;
			};

			detail = toSafeUserMessage(
				body.title ?? body.message ?? body.detail,
				'',
			) || undefined;
		}
		catch {
			// cuerpo no JSON
		}

		throw toLiferayApiError(response.status, detail);
	}

	if (response.status === 204) {
		return undefined as T;
	}

	const text = await response.text();

	if (!text.trim()) {
		return undefined as T;
	}

	try {
		return JSON.parse(text) as T;
	}
	catch {
		throw new LiferayApiError(
			'El servidor devolvió una respuesta inválida. Recarga la página; si persiste, reinicia Liferay.',
			response.status,
			'server',
		);
	}
}
