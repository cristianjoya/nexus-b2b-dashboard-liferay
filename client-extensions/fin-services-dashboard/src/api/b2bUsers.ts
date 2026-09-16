import { liferayFetch } from './liferay';
import {
	formatPortalUserLabel,
	getCandidateGroupIds,
	getPortalUser,
	getSiteUsersWithRole,
	jsonwsInvoke,
	type PortalUser,
} from './jsonws';

export interface B2bAssignableUser {
	emailAddress?: string;
	fullName?: string;
	userId?: number;
}

export interface AssignableB2bUsersParams {
	page?: number;
	pageSize?: number;
	search?: string;
}

export interface AssignableB2bUsersResult {
	items: B2bAssignableUser[];
	lastPage: number;
	page: number;
	pageSize: number;
	totalCount: number;
}

const ROLE_B2B_USER = 'B2B User';
const DEFAULT_PAGE_SIZE = 20;

function matchesSearch(user: B2bAssignableUser, searchTerm: string): boolean {
	if (!searchTerm) {
		return true;
	}

	const haystack =
		`${user.fullName ?? ''} ${user.emailAddress ?? ''} ${user.userId ?? ''}`.toLowerCase();

	return haystack.includes(searchTerm);
}

function toAssignableUser(portalUser: PortalUser): B2bAssignableUser {
	return {
		emailAddress: portalUser.emailAddress,
		fullName: formatPortalUserLabel(portalUser),
		userId: portalUser.userId,
	};
}

function paginateUsers(
	users: B2bAssignableUser[],
	page: number,
	pageSize: number,
): AssignableB2bUsersResult {
	users.sort((a, b) =>
		(a.fullName || a.emailAddress || '').localeCompare(
			b.fullName || b.emailAddress || '',
			'es',
		),
	);

	const totalCount = users.length;
	const start = (page - 1) * pageSize;

	return {
		items: users.slice(start, start + pageSize),
		lastPage: Math.max(1, Math.ceil(totalCount / pageSize) || 1),
		page,
		pageSize,
		totalCount,
	};
}

function mergeByUserId(
	users: B2bAssignableUser[],
): Map<number, B2bAssignableUser> {
	const byId = new Map<number, B2bAssignableUser>();

	for (const user of users) {
		const userId = Number(user.userId);

		if (!Number.isFinite(userId) || userId <= 0) {
			continue;
		}

		const existing = byId.get(userId);

		byId.set(userId, {
			emailAddress: user.emailAddress || existing?.emailAddress,
			fullName: user.fullName || existing?.fullName,
			userId,
		});
	}

	return byId;
}

async function fetchSiteMembers(): Promise<PortalUser[]> {
	const groupIds = getCandidateGroupIds();
	const byId = new Map<number, PortalUser>();

	for (const groupId of groupIds) {
		let members: PortalUser[] = [];

		try {
			const raw = await jsonwsInvoke<PortalUser | PortalUser[]>(
				'user/get-group-users',
				{ end: -1, groupId, start: -1 },
			);
			members = Array.isArray(raw) ? raw : raw ? [raw] : [];
		}
		catch {
			try {
				const raw = await jsonwsInvoke<PortalUser | PortalUser[]>(
					'user/get-group-users',
					{ groupId },
				);
				members = Array.isArray(raw) ? raw : raw ? [raw] : [];
			}
			catch {
				members = [];
			}
		}

		for (const user of members) {
			const userId = Number(user.userId);

			if (!Number.isFinite(userId) || userId <= 0) {
				continue;
			}

			if (user.status != null && Number(user.status) !== 0) {
				continue;
			}

			const composed = [user.firstName, user.middleName, user.lastName]
				.filter(Boolean)
				.join(' ')
				.trim();

			byId.set(userId, {
				emailAddress: user.emailAddress,
				firstName: user.firstName,
				fullName: user.fullName?.trim() || composed || undefined,
				lastName: user.lastName,
				middleName: user.middleName,
				screenName: user.screenName,
				status: user.status,
				userId,
			});
		}
	}

	return [...byId.values()];
}

type HeadlessUserPage = {
	items?: Array<{
		emailAddress?: string;
		familyName?: string;
		givenName?: string;
		id?: number;
		name?: string;
		alternateName?: string;
	}>;
};

async function searchUsersHeadless(
	search: string,
): Promise<B2bAssignableUser[]> {
	if (!search.trim()) {
		return [];
	}

	try {
		const query = new URLSearchParams({
			page: '1',
			pageSize: '50',
			search: search.trim(),
		});
		const page = await liferayFetch<HeadlessUserPage>(
			`/o/headless-admin-user/v1.0/user-accounts?${query}`,
		);

		const users: B2bAssignableUser[] = [];

		for (const item of page.items ?? []) {
			const userId = Number(item.id);

			if (!Number.isFinite(userId) || userId <= 0) {
				continue;
			}

			const fullName =
				item.name?.trim() ||
				[item.givenName, item.familyName].filter(Boolean).join(' ').trim() ||
				item.alternateName?.trim() ||
				undefined;

			users.push({
				emailAddress: item.emailAddress,
				fullName:
					fullName || item.emailAddress || `Usuario #${userId}`,
				userId,
			});
		}

		return users;
	}
	catch {
		return [];
	}
}

async function enrichUsers(
	users: PortalUser[],
): Promise<B2bAssignableUser[]> {
	const enriched = await Promise.all(
		users.map(async (portalUser) => {
			const userId = Number(portalUser.userId);

			if (!Number.isFinite(userId) || userId <= 0) {
				return null;
			}

			const detailed = await getPortalUser(userId);
			const merged: PortalUser = {
				...portalUser,
				...(detailed ?? {}),
				emailAddress: detailed?.emailAddress ?? portalUser.emailAddress,
				firstName: detailed?.firstName ?? portalUser.firstName,
				fullName: detailed?.fullName ?? portalUser.fullName,
				lastName: detailed?.lastName ?? portalUser.lastName,
				userId,
			};

			return toAssignableUser(merged);
		}),
	);

	return enriched.filter((user): user is B2bAssignableUser => user != null);
}

function preferSiteAndRole(
	merged: B2bAssignableUser[],
	memberIds: Set<number>,
	roleIds: Set<number>,
): B2bAssignableUser[] {
	if (merged.length === 0) {
		return merged;
	}

	const withRoleOnSite = merged.filter(
		(user) =>
			user.userId != null &&
			roleIds.has(user.userId) &&
			(memberIds.size === 0 || memberIds.has(user.userId)),
	);

	if (withRoleOnSite.length > 0) {
		return withRoleOnSite;
	}

	const onSite = merged.filter(
		(user) => user.userId != null && memberIds.has(user.userId),
	);

	if (onSite.length > 0) {
		return onSite;
	}

	const withRole = merged.filter(
		(user) => user.userId != null && roleIds.has(user.userId),
	);

	if (withRole.length > 0) {
		return withRole;
	}

	return merged;
}

export async function getAssignableB2bUsers(
	params: AssignableB2bUsersParams = {},
): Promise<AssignableB2bUsersResult> {
	const page = params.page ?? 1;
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;
	const searchTerm = params.search?.trim().toLowerCase() ?? '';
	const rawSearch = params.search?.trim() ?? '';

	const [siteMembers, rolePortalUsers, headlessHits] = await Promise.all([
		fetchSiteMembers(),
		getSiteUsersWithRole(ROLE_B2B_USER).catch(() => [] as PortalUser[]),
		rawSearch ? searchUsersHeadless(rawSearch) : Promise.resolve([]),
	]);

	const memberIds = new Set(
		siteMembers
			.map((user) => Number(user.userId))
			.filter((id) => Number.isFinite(id) && id > 0),
	);
	const roleIds = new Set(
		rolePortalUsers
			.map((user) => Number(user.userId))
			.filter((id) => Number.isFinite(id) && id > 0),
	);

	if (searchTerm) {
		const localUsers = await enrichUsers([
			...rolePortalUsers,
			...siteMembers,
		]);
		const localMatches = localUsers.filter((user) =>
			matchesSearch(user, searchTerm),
		);
		const merged = [
			...mergeByUserId([...headlessHits, ...localMatches]).values(),
		];

		return paginateUsers(
			preferSiteAndRole(merged, memberIds, roleIds),
			page,
			pageSize,
		);
	}

	if (rolePortalUsers.length > 0) {
		return paginateUsers(await enrichUsers(rolePortalUsers), page, pageSize);
	}

	if (siteMembers.length > 0) {
		return paginateUsers(await enrichUsers(siteMembers), page, pageSize);
	}

	return paginateUsers([], page, pageSize);
}

export function formatAssignableUserLabel(user: B2bAssignableUser): string {
	const name = user.fullName?.trim() || user.emailAddress?.trim();

	if (name && user.emailAddress && name !== user.emailAddress) {
		return `${name} (${user.emailAddress})`;
	}

	return name || `Usuario #${user.userId ?? '—'}`;
}
