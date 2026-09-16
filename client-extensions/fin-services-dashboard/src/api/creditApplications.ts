import { getAccountsPage } from './accounts';
import {
	OBJECTS,
	extractRelatedAccountId,
	objectsCollectAll,
	objectsCreate,
	objectsDelete,
	objectsGetPage,
	objectsUpdate,
	paginateLocal,
	picklistToNumber,
} from './objectsClient';

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

type ObjectCredit = Record<string, unknown> & {
	applicantName?: string;
	dateCreated?: string;
	dateModified?: string;
	id?: number;
	nexusCreditStatus?: unknown;
	purpose?: string;
	requestedAmount?: number;
	reviewNotes?: string;
};

function mapCredit(raw: ObjectCredit): CreditApplication {
	return {
		accountId: extractRelatedAccountId(raw, {
			fkField: 'r_finAccounts_c_finAccountId',
			relationshipName: 'finAccounts',
		}),
		applicantName: raw.applicantName,
		createDate: raw.dateCreated,
		creditAppId: raw.id,
		modifiedDate: raw.dateModified,
		purpose: raw.purpose,
		requestedAmount:
			raw.requestedAmount != null ? Number(raw.requestedAmount) : 0,
		reviewNotes: raw.reviewNotes,
		status: picklistToNumber(raw.nexusCreditStatus, 0),
	};
}

async function resolveAccountIdsForOwner(
	ownerUserId: number,
): Promise<number[]> {
	const result = await getAccountsPage({
		ownerUserId,
		page: 1,
		pageSize: 200,
	});

	return result.items
		.map((account) => account.accountId)
		.filter((id): id is number => id != null && id > 0);
}

function buildScalarCreditFilter(
	params: CreditApplicationListParams,
): string | undefined {
	if (params.status == null) {
		return undefined;
	}

	return `nexusCreditStatus eq '${params.status}'`;
}

function toObjectCreditBody(
	creditApplication: CreditApplicationInput,
): Record<string, unknown> {
	return {
		applicantName: creditApplication.applicantName,
		nexusCreditStatus: String(creditApplication.status ?? 0),
		purpose: creditApplication.purpose ?? '',
		r_finAccounts_c_finAccountId: creditApplication.accountId,
		requestedAmount: creditApplication.requestedAmount,
		reviewNotes: creditApplication.reviewNotes ?? '',
	};
}

export async function getCreditApplicationsPage(
	params: CreditApplicationListParams = {},
): Promise<CreditApplicationListResult> {
	const page = params.page ?? 1;
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;
	const needsLocalAccountFilter =
		params.accountId != null ||
		(params.ownerUserId != null && params.ownerUserId > 0);

	let allowedAccountIds: number[] | null = null;

	if (params.accountId != null) {
		allowedAccountIds = [params.accountId];
	}
	else if (params.ownerUserId != null && params.ownerUserId > 0) {
		allowedAccountIds = await resolveAccountIdsForOwner(params.ownerUserId);

		if (allowedAccountIds.length === 0) {
			return emptyCreditApplicationListResult(params);
		}
	}

	const scalarFilter = buildScalarCreditFilter(params);

	if (!needsLocalAccountFilter) {
		const data = await objectsGetPage<ObjectCredit>(
			OBJECTS.creditApplications,
			{
				filter: scalarFilter,
				page,
				pageSize,
				search: params.search,
				sort: 'dateCreated:desc',
			},
		);

		const items = (data.items ?? []).map(mapCredit);
		const totalCount = data.totalCount ?? items.length;
		const lastPage =
			data.lastPage ?? Math.max(1, Math.ceil(totalCount / pageSize));

		return {
			items,
			lastPage,
			page: data.page ?? page,
			pageSize: data.pageSize ?? pageSize,
			totalCount,
		};
	}

	const rawItems = await objectsCollectAll<ObjectCredit>(
		OBJECTS.creditApplications,
		{
			search: params.search,
			sort: 'dateCreated:desc',
		},
	);

	let items = rawItems.map(mapCredit);

	if (allowedAccountIds) {
		const allowed = new Set(allowedAccountIds);
		items = items.filter(
			(item) => item.accountId != null && allowed.has(item.accountId),
		);
	}

	if (params.status != null) {
		items = items.filter((item) => item.status === params.status);
	}

	return paginateLocal(items, page, pageSize);
}

export async function createCreditApplication(
	creditApplication: CreditApplicationInput,
): Promise<CreditApplication> {
	const created = await objectsCreate<ObjectCredit>(
		OBJECTS.creditApplications,
		toObjectCreditBody(creditApplication),
	);

	return mapCredit(created);
}

export async function updateCreditApplication(
	creditAppId: number,
	creditApplication: CreditApplicationInput,
): Promise<CreditApplication> {
	const updated = await objectsUpdate<ObjectCredit>(
		OBJECTS.creditApplications,
		creditAppId,
		toObjectCreditBody(creditApplication),
	);

	return mapCredit(updated);
}

export async function deleteCreditApplication(
	creditAppId: number,
): Promise<void> {
	await objectsDelete(OBJECTS.creditApplications, creditAppId);
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
