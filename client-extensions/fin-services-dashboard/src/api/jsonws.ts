import { getSiteContext, liferayFetch } from './liferay';

function getCsrfToken(): string {
	return window.Liferay?.authToken ?? '';
}

function isJsonwsException(data: unknown): boolean {
	return Boolean(
		data &&
			typeof data === 'object' &&
			!Array.isArray(data) &&
			'exception' in data &&
			(data as { exception?: string }).exception,
	);
}

type ThemeDisplayLike = {
	getLayoutGroupId?: () => number | string;
	getScopeGroupId?: () => number | string;
	getSiteGroupId?: () => number | string;
};

function getThemeDisplay(): ThemeDisplayLike | undefined {
	return window.Liferay?.ThemeDisplay as ThemeDisplayLike | undefined;
}

export function getCandidateGroupIds(): number[] {
	const themeDisplay = getThemeDisplay();
	const ids = new Set<number>();

	for (const value of [
		themeDisplay?.getSiteGroupId?.(),
		themeDisplay?.getScopeGroupId?.(),
		themeDisplay?.getLayoutGroupId?.(),
		getSiteContext().scopeGroupId,
	]) {
		const parsed = Number(value);

		if (Number.isFinite(parsed) && parsed > 0) {
			ids.add(parsed);
		}
	}

	return [...ids];
}

type JsonwsErrorBody = {
	exception?: string;
};

type LiferayService = (
	servicePath: string,
	params: Record<string, string | number | boolean>,
	callback?: (result: unknown) => void,
) => Promise<unknown> | void;

function getLiferayService(): LiferayService | undefined {
	const liferay = window.Liferay as { Service?: LiferayService } | undefined;

	return liferay?.Service;
}

async function invokeViaLiferayService<T>(
	servicePath: string,
	params: Record<string, string | number | boolean>,
): Promise<T | null> {
	const service = getLiferayService();

	if (!service) {
		return null;
	}

	const path = servicePath.startsWith('/') ? servicePath : `/${servicePath}`;

	try {
		const maybePromise = service(path, params);

		if (
			maybePromise &&
			typeof (maybePromise as Promise<unknown>).then === 'function'
		) {
			const result = (await maybePromise) as T;

			if (isJsonwsException(result)) {
				return null;
			}

			return result;
		}

		return await new Promise<T>((resolve, reject) => {
			try {
				service(path, params, (result) => {
					if (isJsonwsException(result)) {
						reject(
							new Error(
								String((result as JsonwsErrorBody).exception),
							),
						);

						return;
					}

					resolve(result as T);
				});
			}
			catch (error) {
				reject(error);
			}
		});
	}
	catch {
		return null;
	}
}

/** Fetch primero; Liferay.Service solo como fallback (evita [] prematuro). */
export async function jsonwsInvoke<T>(
	servicePath: string,
	params: Record<string, string | number | boolean> = {},
): Promise<T> {
	if (
		!servicePath ||
		servicePath.includes('..') ||
		servicePath.includes('://')
	) {
		throw new Error('JSONWS path inválido');
	}

	const query = new URLSearchParams();

	for (const [key, value] of Object.entries(params)) {
		query.set(key, String(value));
	}

	query.set('p_auth', getCsrfToken());

	const bases = ['/api/jsonws', '/o/portal/api/jsonws'];
	let lastError: Error | null = null;
	let emptyArrayResult: T | null = null;

	for (const base of bases) {
		try {
			const response = await fetch(`${base}/${servicePath}?${query}`, {
				credentials: 'include',
				referrerPolicy: 'strict-origin-when-cross-origin',
				headers: {
					Accept: 'application/json',
					'x-csrf-token': getCsrfToken(),
				},
			});

			if (!response.ok) {
				lastError = new Error(
					`JSONWS ${servicePath} falló (${response.status})`,
				);
				continue;
			}

			const data = (await response.json()) as T | JsonwsErrorBody;

			if (isJsonwsException(data)) {
				lastError = new Error(
					(data as JsonwsErrorBody).exception ?? 'JSONWS error',
				);
				continue;
			}

			if (Array.isArray(data) && data.length === 0) {
				emptyArrayResult = data as T;
				continue;
			}

			return data as T;
		}
		catch (error) {
			lastError =
				error instanceof Error ? error : new Error(String(error));
		}
	}

	const viaService = await invokeViaLiferayService<T>(servicePath, params);

	if (viaService !== null) {
		if (
			Array.isArray(viaService) &&
			viaService.length === 0 &&
			emptyArrayResult !== null
		) {
			return emptyArrayResult;
		}

		return viaService;
	}

	if (emptyArrayResult !== null) {
		return emptyArrayResult;
	}

	throw lastError ?? new Error(`JSONWS ${servicePath} falló`);
}

function normalizeRoleKey(value: string): string {
	return value.trim().toLowerCase().replace(/\s+/g, ' ');
}

type MyUserAccount = {
	id?: number;
	roleBriefs?: Array<{ name?: string; roleId?: number }>;
};

let myAccountCache: MyUserAccount | null | undefined;

export async function fetchMyUserAccount(): Promise<MyUserAccount | null> {
	if (myAccountCache !== undefined) {
		return myAccountCache;
	}

	try {
		myAccountCache = await liferayFetch<MyUserAccount>(
			'/o/headless-admin-user/v1.0/my-user-account',
		);

		return myAccountCache;
	}
	catch {
		myAccountCache = null;

		return null;
	}
}

export async function myAccountHasRole(roleName: string): Promise<boolean> {
	const me = await fetchMyUserAccount();
	const target = normalizeRoleKey(roleName);
	const compactTarget = target.replace(/\s+/g, '');

	return (me?.roleBriefs ?? []).some((role) => {
		const normalized = normalizeRoleKey(role.name ?? '');

		return (
			normalized === target ||
			normalized.replace(/\s+/g, '') === compactTarget
		);
	});
}

export type PortalUser = {
	emailAddress?: string;
	firstName?: string;
	fullName?: string;
	lastName?: string;
	middleName?: string;
	screenName?: string;
	status?: number;
	userId?: number;
};

const userCache = new Map<number, PortalUser | null>();

export function formatPortalUserLabel(user: PortalUser): string {
	const fullName = user.fullName?.trim();

	if (fullName) {
		return fullName;
	}

	const composed = [user.firstName, user.middleName, user.lastName]
		.filter(Boolean)
		.join(' ')
		.trim();

	if (composed) {
		return composed;
	}

	if (user.emailAddress?.trim()) {
		return user.emailAddress.trim();
	}

	if (user.screenName?.trim()) {
		return user.screenName.trim();
	}

	return `Usuario #${user.userId ?? '—'}`;
}

type HeadlessUserAccount = {
	additionalName?: string;
	emailAddress?: string;
	familyName?: string;
	givenName?: string;
	id?: number;
	name?: string;
};

async function fetchPortalUserHeadless(
	userId: number,
): Promise<PortalUser | null> {
	try {
		const account = await liferayFetch<HeadlessUserAccount>(
			`/o/headless-admin-user/v1.0/user-accounts/${userId}`,
		);

		return {
			emailAddress: account.emailAddress,
			firstName: account.givenName,
			fullName: account.name?.trim() || undefined,
			lastName: account.familyName,
			middleName: account.additionalName,
			userId: account.id ?? userId,
		};
	}
	catch {
		return null;
	}
}

function isUsablePortalUser(user: PortalUser | null | undefined): boolean {
	if (!user) {
		return false;
	}

	return Boolean(
		user.fullName?.trim() ||
			user.firstName?.trim() ||
			user.lastName?.trim() ||
			user.emailAddress?.trim() ||
			user.screenName?.trim(),
	);
}

export async function getPortalUser(
	userId: number,
): Promise<PortalUser | null> {
	if (userId <= 0) {
		return null;
	}

	if (userCache.has(userId)) {
		return userCache.get(userId) ?? null;
	}

	const fromHeadless = await fetchPortalUserHeadless(userId);

	if (isUsablePortalUser(fromHeadless)) {
		userCache.set(userId, fromHeadless);

		return fromHeadless;
	}

	try {
		const user = await jsonwsInvoke<PortalUser>('user/get-user-by-id', {
			userId,
		});

		if (isUsablePortalUser(user)) {
			userCache.set(userId, {
				...user,
				userId: user.userId ?? userId,
			});

			return userCache.get(userId) ?? null;
		}
	}
	catch {
		// ignore
	}

	if (fromHeadless) {
		userCache.set(userId, fromHeadless);

		return fromHeadless;
	}

	userCache.set(userId, null);

	return null;
}

export async function resolveUserLabels(
	userIds: Array<number | null | undefined>,
): Promise<Map<number, string>> {
	const uniqueIds = [
		...new Set(
			userIds
				.map((id) => Number(id))
				.filter((id) => Number.isFinite(id) && id > 0),
		),
	];

	await Promise.all(uniqueIds.map((id) => getPortalUser(id)));

	const labels = new Map<number, string>();

	for (const id of uniqueIds) {
		const user = userCache.get(id);

		if (isUsablePortalUser(user) && user) {
			labels.set(id, formatPortalUserLabel(user));
		}
	}

	return labels;
}

async function fetchSiteUsersWithRoleHeadless(
	siteId: number,
	roleName: string,
): Promise<PortalUser[]> {
	const escaped = roleName.replace(/'/g, "''");
	const filters = [
		`userGroupRoleNames/any(r:r eq '${escaped}')`,
		`roleNames/any(r:r eq '${escaped}')`,
	];

	for (const filter of filters) {
		try {
			const query = new URLSearchParams({
				filter,
				page: '1',
				pageSize: '100',
			});
			const page = await liferayFetch<{
				items?: Array<{
					emailAddress?: string;
					familyName?: string;
					givenName?: string;
					id?: number;
					name?: string;
				}>;
			}>(
				`/o/headless-admin-user/v1.0/sites/${siteId}/user-accounts?${query}`,
			);

			const users: PortalUser[] = [];

			for (const item of page.items ?? []) {
				const userId = Number(item.id);

				if (!Number.isFinite(userId) || userId <= 0) {
					continue;
				}

				users.push({
					emailAddress: item.emailAddress,
					firstName: item.givenName,
					fullName:
						item.name?.trim() ||
						[item.givenName, item.familyName]
							.filter(Boolean)
							.join(' ')
							.trim() ||
						undefined,
					lastName: item.familyName,
					userId,
				});
			}

			if (users.length > 0) {
				return users;
			}
		}
		catch {
			// next filter
		}
	}

	return [];
}

export async function getSiteUsersWithRole(
	roleName: string,
): Promise<PortalUser[]> {
	const groupIds = getCandidateGroupIds();

	if (groupIds.length === 0) {
		return [];
	}

	const byUserId = new Map<number, PortalUser>();

	for (const groupId of groupIds) {
		for (const user of await fetchSiteUsersWithRoleHeadless(
			groupId,
			roleName,
		)) {
			const userId = Number(user.userId);

			if (Number.isFinite(userId) && userId > 0) {
				byUserId.set(userId, user);
			}
		}
	}

	return [...byUserId.values()];
}
