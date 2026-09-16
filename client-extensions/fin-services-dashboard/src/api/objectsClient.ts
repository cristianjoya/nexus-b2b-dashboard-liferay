import { getSiteContext, liferayFetch } from './liferay';
import { LiferayApiError } from './apiError';

export const OBJECTS = {
	accounts: '/o/c/finaccounts',
	creditApplications: '/o/c/fincreditapplications',
	transactions: '/o/c/fintransactions',
} as const;

export type ObjectPage<T> = {
	items?: T[];
	lastPage?: number;
	page?: number;
	pageSize?: number;
	totalCount?: number;
};

const MAX_PAGE_SIZE = 100;

export function picklistKey(value: unknown): string {
	if (value == null) {
		return '';
	}

	if (typeof value === 'string' || typeof value === 'number') {
		return String(value);
	}

	if (typeof value === 'object' && value !== null && 'key' in value) {
		const key = (value as { key?: unknown }).key;

		return key == null ? '' : String(key);
	}

	return '';
}

export function picklistToNumber(value: unknown, fallback = 0): number {
	const key = picklistKey(value);

	if (!key) {
		return fallback;
	}

	const parsed = Number(key);

	return Number.isNaN(parsed) ? fallback : parsed;
}

export function getObjectsScopeKey(): string {
	const { scopeGroupId } = getSiteContext();

	if (scopeGroupId <= 0) {
		throw new LiferayApiError(
			'No se pudo resolver el sitio. Abre el dashboard desde una página del sitio.',
			0,
			'unknown',
		);
	}

	return String(scopeGroupId);
}

export function objectsScopedCollectionUrl(resourceBase: string): string {
	return `${resourceBase}/scopes/${getObjectsScopeKey()}`;
}

export function objectsEntryUrl(resourceBase: string, id: number): string {
	if (!Number.isInteger(id) || id <= 0) {
		throw new LiferayApiError(
			'Identificador de recurso inválido.',
			0,
			'unknown',
		);
	}

	return `${resourceBase}/${id}`;
}

function clampPageSize(pageSize?: number): number {
	const raw = pageSize ?? 10;

	if (!Number.isFinite(raw)) {
		return 10;
	}

	return Math.min(Math.max(1, Math.floor(raw)), MAX_PAGE_SIZE);
}

export function buildObjectsListQuery(params: {
	filter?: string;
	page?: number;
	pageSize?: number;
	search?: string;
	sort?: string;
}): string {
	const query = new URLSearchParams();
	const page = Math.max(1, Math.floor(params.page ?? 1));

	query.set('page', String(page));
	query.set('pageSize', String(clampPageSize(params.pageSize)));

	if (params.search?.trim()) {
		query.set('search', params.search.trim().slice(0, 200));
	}

	if (params.filter?.trim()) {
		query.set('filter', params.filter.trim().slice(0, 500));
	}

	if (params.sort?.trim()) {
		query.set('sort', params.sort.trim().slice(0, 100));
	}

	return query.toString();
}

export async function objectsGetPage<T>(
	resourceBase: string,
	params: {
		filter?: string;
		page?: number;
		pageSize?: number;
		search?: string;
		sort?: string;
	},
): Promise<ObjectPage<T>> {
	const query = buildObjectsListQuery(params);
	const url = `${objectsScopedCollectionUrl(resourceBase)}?${query}`;

	return liferayFetch<ObjectPage<T>>(url);
}

export async function objectsCreate<T>(
	resourceBase: string,
	body: Record<string, unknown>,
): Promise<T> {
	return liferayFetch<T>(objectsScopedCollectionUrl(resourceBase), {
		body: JSON.stringify(body),
		method: 'POST',
	});
}

export async function objectsUpdate<T>(
	resourceBase: string,
	id: number,
	body: Record<string, unknown>,
): Promise<T> {
	return objectsPatch<T>(resourceBase, id, body);
}

export async function objectsPatch<T>(
	resourceBase: string,
	id: number,
	body: Record<string, unknown>,
): Promise<T> {
	return liferayFetch<T>(objectsEntryUrl(resourceBase, id), {
		body: JSON.stringify(body),
		method: 'PATCH',
	});
}

export async function objectsDelete(
	resourceBase: string,
	id: number,
): Promise<void> {
	await liferayFetch<void>(objectsEntryUrl(resourceBase, id), {
		method: 'DELETE',
	});
}

export async function objectsCollectAll<T>(
	resourceBase: string,
	params: {
		search?: string;
		sort?: string;
		pageSize?: number;
	} = {},
): Promise<T[]> {
	const pageSize = clampPageSize(params.pageSize ?? 100);
	const items: T[] = [];
	let page = 1;
	let lastPage = 1;

	do {
		const data = await objectsGetPage<T>(resourceBase, {
			page,
			pageSize,
			search: params.search,
			sort: params.sort,
		});

		items.push(...(data.items ?? []));
		lastPage = data.lastPage ?? 1;
		page += 1;
	} while (page <= lastPage);

	return items;
}

export function extractRelatedAccountId(
	raw: Record<string, unknown>,
	options: {
		fkField: string;
		relationshipName: string;
	},
): number | undefined {
	const fk = raw[options.fkField];

	if (fk != null && fk !== '') {
		const id = Number(fk);

		if (Number.isFinite(id) && id > 0) {
			return id;
		}
	}

	const related = raw[options.relationshipName];

	if (typeof related === 'number' && related > 0) {
		return related;
	}

	if (related && typeof related === 'object') {
		const id = Number((related as { id?: unknown }).id);

		if (Number.isFinite(id) && id > 0) {
			return id;
		}
	}

	return undefined;
}

function paginateLocal<T>(
	items: T[],
	page: number,
	pageSize: number,
): {
	items: T[];
	lastPage: number;
	page: number;
	pageSize: number;
	totalCount: number;
} {
	const size = clampPageSize(pageSize);
	const totalCount = items.length;
	const lastPage = Math.max(1, Math.ceil(totalCount / size) || 1);
	const safePage = Math.min(Math.max(1, page), lastPage);
	const start = (safePage - 1) * size;

	return {
		items: items.slice(start, start + size),
		lastPage,
		page: safePage,
		pageSize: size,
		totalCount,
	};
}

export { paginateLocal };
