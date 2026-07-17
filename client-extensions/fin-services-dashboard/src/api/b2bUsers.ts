import { appendSiteScopeParams, liferayFetch } from './liferay';

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

const API_BASE = '/o/fin-services-rest/v1.0';
const DEFAULT_PAGE_SIZE = 20;

export async function getAssignableB2bUsers(
	params: AssignableB2bUsersParams = {},
): Promise<AssignableB2bUsersResult> {
	const page = params.page ?? 1;
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;
	const query = new URLSearchParams();

	query.set('page', String(page));
	query.set('pageSize', String(pageSize));

	if (params.search?.trim()) {
		query.set('search', params.search.trim());
	}

	const data = await liferayFetch<
		| {
				items?: B2bAssignableUser[];
				lastPage?: number;
				page?: number;
				pageSize?: number;
				totalCount?: number;
		  }
		| B2bAssignableUser[]
	>(
		appendSiteScopeParams(
			`${API_BASE}/assignable-b2b-users?${query.toString()}`,
		),
	);

	if (Array.isArray(data)) {
		return {
			items: data,
			lastPage: 1,
			page,
			pageSize: data.length || pageSize,
			totalCount: data.length,
		};
	}

	const items = data.items ?? [];
	const totalCount = data.totalCount ?? items.length;

	return {
		items,
		lastPage:
			data.lastPage ?? Math.max(1, Math.ceil(totalCount / pageSize)),
		page: data.page ?? page,
		pageSize: data.pageSize ?? pageSize,
		totalCount,
	};
}

export function formatAssignableUserLabel(user: B2bAssignableUser): string {
	const name = user.fullName?.trim() || user.emailAddress?.trim();

	if (name && user.emailAddress && name !== user.emailAddress) {
		return `${name} (${user.emailAddress})`;
	}

	return name || `Usuario #${user.userId ?? '—'}`;
}
