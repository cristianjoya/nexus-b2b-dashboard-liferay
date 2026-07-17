import { appendSiteScopeParams, liferayFetch } from './liferay';

export interface CreditApplication {
	accountId?: number;
	applicantName?: string;
	createDate?: string;
	creditAppId?: number;
	modifiedDate?: string;
	purpose?: string;
	requestedAmount?: number;
	reviewNotes?: string;
	status?: number;
	uuid?: string;
}

export type CreditApplicationInput = Pick<
	CreditApplication,
	| 'accountId'
	| 'applicantName'
	| 'purpose'
	| 'requestedAmount'
	| 'reviewNotes'
	| 'status'
>;

export const CREDIT_STATUS_OPTIONS = [
	{ label: 'Todos los estados', value: '' },
	{ label: 'Pendiente', value: '0' },
	{ label: 'Aprobada', value: '1' },
	{ label: 'Rechazada', value: '2' },
] as const;

export const DEFAULT_PAGE_SIZE = 10;

export interface CreditApplicationListParams {
	accountId?: number | null;
	ownerUserId?: number | null;
	page?: number;
	pageSize?: number;
	search?: string;
	status?: number | null;
}

export interface CreditApplicationListResult {
	items: CreditApplication[];
	lastPage: number;
	page: number;
	pageSize: number;
	totalCount: number;
}

const API_BASE = '/o/fin-services-rest/v1.0';

function buildCreditApplicationsQuery(
	params: CreditApplicationListParams,
): string {
	const page = params.page ?? 1;
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;
	const query = new URLSearchParams();

	query.set('page', String(page));
	query.set('pageSize', String(pageSize));

	if (params.search?.trim()) {
		query.set('search', params.search.trim());
	}

	if (params.accountId != null) {
		query.set('accountId', String(params.accountId));
	}

	if (params.ownerUserId != null && params.ownerUserId > 0) {
		query.set('ownerUserId', String(params.ownerUserId));
	}

	if (params.status != null) {
		query.set('status', String(params.status));
	}

	return query.toString();
}

function normalizeListResult(
	data: {
		items?: CreditApplication[];
		lastPage?: number;
		page?: number;
		pageSize?: number;
		totalCount?: number;
	} | CreditApplication[],
	params: CreditApplicationListParams,
): CreditApplicationListResult {
	const page = params.page ?? 1;
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;

	if (Array.isArray(data)) {
		const totalCount = data.length;
		const lastPage = Math.max(1, Math.ceil(totalCount / pageSize));

		return { items: data, lastPage, page, pageSize, totalCount };
	}

	const totalCount = data.totalCount ?? data.items?.length ?? 0;
	const lastPage =
		data.lastPage ?? Math.max(1, Math.ceil(totalCount / pageSize));

	return {
		items: data.items ?? [],
		lastPage,
		page: data.page ?? page,
		pageSize: data.pageSize ?? pageSize,
		totalCount,
	};
}

export async function getCreditApplicationsPage(
	params: CreditApplicationListParams = {},
): Promise<CreditApplicationListResult> {
	const query = buildCreditApplicationsQuery(params);
	const data = await liferayFetch<
		| {
				items?: CreditApplication[];
				lastPage?: number;
				page?: number;
				pageSize?: number;
				totalCount?: number;
		  }
		| CreditApplication[]
	>(
		appendSiteScopeParams(`${API_BASE}/credit-applications?${query}`),
	);

	return normalizeListResult(data, params);
}

export async function createCreditApplication(
	creditApplication: CreditApplicationInput,
): Promise<CreditApplication> {
	return liferayFetch<CreditApplication>(
		appendSiteScopeParams(`${API_BASE}/credit-applications`),
		{
			body: JSON.stringify(creditApplication),
			method: 'POST',
		},
	);
}

export async function updateCreditApplication(
	creditAppId: number,
	creditApplication: CreditApplicationInput,
): Promise<CreditApplication> {
	return liferayFetch<CreditApplication>(
		appendSiteScopeParams(`${API_BASE}/credit-applications/${creditAppId}`),
		{
			body: JSON.stringify(creditApplication),
			method: 'PUT',
		},
	);
}

export async function deleteCreditApplication(
	creditAppId: number,
): Promise<void> {
	await liferayFetch<void>(
		appendSiteScopeParams(`${API_BASE}/credit-applications/${creditAppId}`),
		{ method: 'DELETE' },
	);
}

export function formatCreditStatus(value?: number) {
	switch (value) {
		case 0:
			return 'Pendiente';
		case 1:
			return 'Aprobada';
		case 2:
			return 'Rechazada';
		default:
			return '-';
	}
}

export function validateCreditApplicationInput(
	creditApplication: CreditApplicationInput,
): string | null {
	if (!creditApplication.accountId) {
		return 'Selecciona una cuenta.';
	}

	if (!creditApplication.applicantName?.trim()) {
		return 'El nombre del solicitante es obligatorio.';
	}

	if (
		creditApplication.requestedAmount == null ||
		creditApplication.requestedAmount <= 0
	) {
		return 'El monto solicitado debe ser mayor a cero.';
	}

	if (!creditApplication.purpose?.trim()) {
		return 'Describe el propósito del crédito.';
	}

	return null;
}

export function emptyCreditApplicationInput(): CreditApplicationInput {
	return {
		accountId: undefined,
		applicantName: '',
		purpose: '',
		requestedAmount: 0,
		reviewNotes: '',
		status: 0,
	};
}

export function creditApplicationToInput(
	creditApplication: CreditApplication,
): CreditApplicationInput {
	return {
		accountId: creditApplication.accountId,
		applicantName: creditApplication.applicantName ?? '',
		purpose: creditApplication.purpose ?? '',
		requestedAmount: creditApplication.requestedAmount ?? 0,
		reviewNotes: creditApplication.reviewNotes ?? '',
		status: creditApplication.status ?? 0,
	};
}

export function hasActiveCreditFilters(
	params: CreditApplicationListParams,
): boolean {
	return Boolean(
		params.search?.trim() ||
			params.accountId != null ||
			(params.ownerUserId != null && params.ownerUserId > 0) ||
			params.status != null,
	);
}

export function emptyCreditApplicationListResult(
	params: CreditApplicationListParams = {},
): CreditApplicationListResult {
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;

	return {
		items: [],
		lastPage: 1,
		page: params.page ?? 1,
		pageSize,
		totalCount: 0,
	};
}
