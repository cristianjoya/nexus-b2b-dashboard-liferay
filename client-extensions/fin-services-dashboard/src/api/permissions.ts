import { getLiferayUserId } from './apiError';
import { getSiteContext, liferayFetch } from './liferay';
import {
	getCandidateGroupIds,
	myAccountHasRole,
} from './jsonws';
import { OBJECTS } from './objectsClient';

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

const ROLE_ADMINISTRATOR = 'Administrator';

const FULL: ResourcePermissions = {
	add: true,
	delete: true,
	update: true,
	view: true,
};

const NONE: ResourcePermissions = {
	add: false,
	delete: false,
	update: false,
	view: false,
};

const B2B_USER_PERMISSIONS: FinPermissions = {
	profile: 'B2B_USER',
	accounts: { add: false, delete: false, update: false, view: true },
	transactions: { add: true, delete: false, update: false, view: true },
	creditApplications: { add: true, delete: false, update: false, view: true },
};

export const emptyResourcePermissions = (): ResourcePermissions => ({
	...NONE,
});

export const emptyFinPermissions = (): FinPermissions => ({
	profile: 'NONE',
	accounts: emptyResourcePermissions(),
	creditApplications: emptyResourcePermissions(),
	transactions: emptyResourcePermissions(),
});

function permissionsForProfile(profile: B2BProfile): FinPermissions {
	if (profile === 'ADMIN' || profile === 'B2B_MANAGER') {
		return {
			profile,
			accounts: { ...FULL },
			creditApplications: { ...FULL },
			transactions: { ...FULL },
		};
	}

	if (profile === 'B2B_USER') {
		return { ...B2B_USER_PERMISSIONS };
	}

	return emptyFinPermissions();
}

export function parseFinPermissions(
	raw: Partial<FinPermissions>,
): FinPermissions {
	const profile = (raw.profile ?? 'NONE') as B2BProfile;

	return normalizeFinPermissions({
		profile,
		accounts: raw.accounts ?? emptyResourcePermissions(),
		creditApplications:
			raw.creditApplications ?? emptyResourcePermissions(),
		transactions: raw.transactions ?? emptyResourcePermissions(),
	});
}

export function normalizeFinPermissions(
	permissions: FinPermissions,
): FinPermissions {
	if (permissions.profile === 'B2B_USER') {
		return B2B_USER_PERMISSIONS;
	}

	if (
		permissions.profile === 'ADMIN' ||
		permissions.profile === 'B2B_MANAGER'
	) {
		return permissionsForProfile(permissions.profile);
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

	return normalizeFinPermissions(permissions)[resource];
}

type CollectionProbe = {
	actions?: Record<string, unknown>;
};

function collectionHasCreate(actions: Record<string, unknown>): boolean {
	return (
		'create' in actions ||
		'create-batch' in actions ||
		'createBatch' in actions
	);
}

type ProbeResult = {
	canCreate: boolean;
	canView: boolean;
};

async function probeCollection(
	resourceBase: string,
	groupId: number,
): Promise<ProbeResult | null> {
	try {
		const page = await liferayFetch<CollectionProbe>(
			`${resourceBase}/scopes/${groupId}?page=1&pageSize=1`,
		);

		return {
			canCreate: collectionHasCreate(page.actions ?? {}),
			canView: true,
		};
	}
	catch {
		return null;
	}
}

async function resolveProfileFromObjects(): Promise<B2BProfile | null> {
	for (const groupId of getCandidateGroupIds()) {
		const accounts = await probeCollection(OBJECTS.accounts, groupId);

		if (accounts?.canView) {
			return accounts.canCreate ? 'B2B_MANAGER' : 'B2B_USER';
		}

		const transactions = await probeCollection(
			OBJECTS.transactions,
			groupId,
		);
		const credits = await probeCollection(
			OBJECTS.creditApplications,
			groupId,
		);

		if (transactions?.canView || credits?.canView) {
			return 'B2B_USER';
		}
	}

	return null;
}

async function resolveB2BProfile(): Promise<B2BProfile> {
	if (await myAccountHasRole(ROLE_ADMINISTRATOR)) {
		return 'ADMIN';
	}

	return (await resolveProfileFromObjects()) ?? 'NONE';
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
	return permissionsForProfile(await resolveB2BProfile());
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
