import { getLiferayUserId } from './apiError';
import { appendSiteScopeParams, getSiteContext, liferayFetch } from './liferay';

export type ResourcePermissions = {
	add: boolean;
	delete: boolean;
	update: boolean;
	view: boolean;
};

export type B2BProfile = 'ADMIN' | 'B2B_MANAGER' | 'B2B_USER' | 'NONE';

export type FinResourceKey =
	| 'accounts'
	| 'creditApplications'
	| 'transactions';

export type FinPermissions = {
	accounts: ResourcePermissions;
	creditApplications: ResourcePermissions;
	profile?: B2BProfile;
	transactions: ResourcePermissions;
};

const API_BASE = '/o/fin-services-rest/v1.0';

const B2B_USER_PERMISSIONS: FinPermissions = {
	profile: 'B2B_USER',
	accounts: { add: false, delete: false, update: false, view: true },
	transactions: { add: true, delete: false, update: false, view: true },
	creditApplications: { add: true, delete: false, update: false, view: true },
};

export const emptyResourcePermissions = (): ResourcePermissions => ({
	add: false,
	delete: false,
	update: false,
	view: false,
});

export const emptyFinPermissions = (): FinPermissions => ({
	profile: 'NONE',
	accounts: emptyResourcePermissions(),
	creditApplications: emptyResourcePermissions(),
	transactions: emptyResourcePermissions(),
});

function toBoolean(value: unknown): boolean {
	return value === true || value === 'true';
}

function parseResourcePermissions(raw: unknown): ResourcePermissions {
	if (!raw || typeof raw !== 'object') {
		return emptyResourcePermissions();
	}

	const permissions = raw as Record<string, unknown>;

	return {
		add: toBoolean(permissions.add),
		delete: toBoolean(permissions.delete),
		update: toBoolean(permissions.update),
		view: toBoolean(permissions.view),
	};
}

export function parseFinPermissions(raw: Partial<FinPermissions>): FinPermissions {
	const profile = raw.profile ?? 'NONE';

	const parsed: FinPermissions = {
		profile,
		accounts: parseResourcePermissions(raw.accounts),
		creditApplications: parseResourcePermissions(raw.creditApplications),
		transactions: parseResourcePermissions(raw.transactions),
	};

	return normalizeFinPermissions(parsed);
}

export function normalizeFinPermissions(
	permissions: FinPermissions,
): FinPermissions {
	if (permissions.profile === 'B2B_USER') {
		return B2B_USER_PERMISSIONS;
	}

	return permissions;
}

export function resolveResourcePermissions(
	permissions: FinPermissions | undefined,
	resource: FinResourceKey,
): ResourcePermissions {
	if (!permissions) {
		return emptyResourcePermissions();
	}

	const normalized = normalizeFinPermissions(permissions);

	return normalized[resource];
}

function getPermissionsSessionKey(): string {
	const userId = getLiferayUserId();

	if (userId > 0) {
		return `user:${userId}`;
	}

	const authToken = window.Liferay?.authToken;

	if (authToken) {
		return `session:${authToken}`;
	}

	return 'anonymous';
}

export async function getPermissions(): Promise<FinPermissions> {
	const permissions = await liferayFetch<Partial<FinPermissions>>(
		appendSiteScopeParams(`${API_BASE}/permissions`),
	);

	return parseFinPermissions(permissions);
}

export function permissionsQueryKey() {
	const { scopeGroupId, siteFriendlyUrl } = getSiteContext();

	return [
		'permissions',
		getPermissionsSessionKey(),
		scopeGroupId,
		siteFriendlyUrl,
	] as const;
}
