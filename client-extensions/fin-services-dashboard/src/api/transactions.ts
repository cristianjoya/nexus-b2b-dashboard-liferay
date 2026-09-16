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
	picklistKey,
	picklistToNumber,
} from './objectsClient';

export interface Transaction {
	accountId?: number;
	amount?: number;
	createDate?: string;
	description?: string;
	modifiedDate?: string;
	status?: number;
	transactionDate?: string;
	transactionId?: number;
	transactionType?: string;
	uuid?: string;
}

export type TransactionInput = Pick<
	Transaction,
	'accountId' | 'amount' | 'description' | 'status' | 'transactionType'
>;

export const TRANSACTION_TYPES = [
	{ label: 'Depósito', value: 'DEPOSIT' },
	{ label: 'Retiro', value: 'WITHDRAWAL' },
	{ label: 'Pago', value: 'PAYMENT' },
] as const;

export const TRANSACTION_STATUS_OPTIONS = [
	{ label: 'Todos los estados', value: '' },
	{ label: 'Pendiente', value: '0' },
	{ label: 'Completada', value: '1' },
	{ label: 'Fallida', value: '2' },
] as const;

export const DEFAULT_PAGE_SIZE = 10;

export interface TransactionListParams {
	accountId?: number | null;
	ownerUserId?: number | null;
	page?: number;
	pageSize?: number;
	search?: string;
	status?: number | null;
	transactionType?: string;
}

export interface TransactionListResult {
	items: Transaction[];
	lastPage: number;
	page: number;
	pageSize: number;
	totalCount: number;
}

type ObjectTransaction = Record<string, unknown> & {
	amount?: number;
	dateCreated?: string;
	dateModified?: string;
	description?: string;
	id?: number;
	nexusTransactionStatus?: unknown;
	transactionDate?: string;
	transactionType?: unknown;
};

function mapTransaction(raw: ObjectTransaction): Transaction {
	return {
		accountId: extractRelatedAccountId(raw, {
			fkField: 'r_finAccount_c_finAccountId',
			relationshipName: 'finAccount',
		}),
		amount: raw.amount != null ? Number(raw.amount) : 0,
		createDate: raw.dateCreated,
		description: raw.description,
		modifiedDate: raw.dateModified,
		status: picklistToNumber(raw.nexusTransactionStatus, 1),
		transactionDate: raw.transactionDate,
		transactionId: raw.id,
		transactionType: picklistKey(raw.transactionType) || undefined,
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

/** Filtro OData solo con campos escalares (el FK de relación rompe el filtro). */
function buildScalarTransactionFilter(
	params: TransactionListParams,
): string | undefined {
	const parts: string[] = [];

	if (params.transactionType) {
		parts.push(`transactionType eq '${params.transactionType}'`);
	}

	if (params.status != null) {
		parts.push(`nexusTransactionStatus eq '${params.status}'`);
	}

	return parts.length ? parts.join(' and ') : undefined;
}

function toObjectTransactionBody(
	transaction: TransactionInput,
): Record<string, unknown> {
	return {
		amount: transaction.amount,
		description: transaction.description ?? '',
		nexusTransactionStatus: String(transaction.status ?? 1),
		r_finAccount_c_finAccountId: transaction.accountId,
		transactionType: transaction.transactionType,
	};
}

export async function getTransactionsPage(
	params: TransactionListParams = {},
): Promise<TransactionListResult> {
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
			return emptyTransactionListResult(params);
		}
	}

	const scalarFilter = buildScalarTransactionFilter(params);

	if (!needsLocalAccountFilter) {
		const data = await objectsGetPage<ObjectTransaction>(
			OBJECTS.transactions,
			{
				filter: scalarFilter,
				page,
				pageSize,
				search: params.search,
				sort: 'dateCreated:desc',
			},
		);

		const items = (data.items ?? []).map(mapTransaction);
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

	const rawItems = await objectsCollectAll<ObjectTransaction>(
		OBJECTS.transactions,
		{
			search: params.search,
			sort: 'dateCreated:desc',
		},
	);

	let items = rawItems.map(mapTransaction);

	if (allowedAccountIds) {
		const allowed = new Set(allowedAccountIds);
		items = items.filter(
			(item) => item.accountId != null && allowed.has(item.accountId),
		);
	}

	if (params.transactionType) {
		items = items.filter(
			(item) => item.transactionType === params.transactionType,
		);
	}

	if (params.status != null) {
		items = items.filter((item) => item.status === params.status);
	}

	return paginateLocal(items, page, pageSize);
}

export async function createTransaction(
	transaction: TransactionInput,
): Promise<Transaction> {
	const created = await objectsCreate<ObjectTransaction>(
		OBJECTS.transactions,
		toObjectTransactionBody(transaction),
	);

	return mapTransaction(created);
}

export async function updateTransaction(
	transactionId: number,
	transaction: TransactionInput,
): Promise<Transaction> {
	const updated = await objectsUpdate<ObjectTransaction>(
		OBJECTS.transactions,
		transactionId,
		toObjectTransactionBody(transaction),
	);

	return mapTransaction(updated);
}

export async function deleteTransaction(transactionId: number): Promise<void> {
	await objectsDelete(OBJECTS.transactions, transactionId);
}

export function formatTransactionType(value?: string) {
	const match = TRANSACTION_TYPES.find((type) => type.value === value);

	return match?.label ?? value ?? '-';
}

export function formatTransactionStatus(value?: number) {
	switch (value) {
		case 0:
			return 'Pendiente';
		case 1:
			return 'Completada';
		case 2:
			return 'Fallida';
		default:
			return '-';
	}
}

export function validateTransactionInput(
	transaction: TransactionInput,
	options?: { accountBalance?: number },
): string | null {
	if (!transaction.accountId) {
		return 'Selecciona una cuenta.';
	}

	if (!transaction.transactionType?.trim()) {
		return 'Selecciona un tipo de movimiento.';
	}

	if (transaction.amount == null || transaction.amount <= 0) {
		return 'El monto debe ser mayor a cero.';
	}

	const debitsAccount =
		transaction.transactionType === 'WITHDRAWAL' ||
		transaction.transactionType === 'PAYMENT';

	if (
		debitsAccount &&
		options?.accountBalance != null &&
		transaction.amount > options.accountBalance
	) {
		return 'El monto supera el saldo disponible en la cuenta seleccionada.';
	}

	return null;
}

export function emptyTransactionInput(): TransactionInput {
	return {
		accountId: undefined,
		amount: 0,
		description: '',
		status: 1,
		transactionType: 'DEPOSIT',
	};
}

export function transactionToInput(transaction: Transaction): TransactionInput {
	return {
		accountId: transaction.accountId,
		amount: transaction.amount ?? 0,
		description: transaction.description ?? '',
		status: transaction.status ?? 1,
		transactionType: transaction.transactionType ?? 'DEPOSIT',
	};
}

export function hasActiveTransactionFilters(
	params: TransactionListParams,
): boolean {
	return Boolean(
		params.search?.trim() ||
			params.transactionType ||
			params.accountId != null ||
			(params.ownerUserId != null && params.ownerUserId > 0) ||
			params.status != null,
	);
}

export function emptyTransactionListResult(
	params: TransactionListParams = {},
): TransactionListResult {
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;

	return {
		items: [],
		lastPage: 1,
		page: params.page ?? 1,
		pageSize,
		totalCount: 0,
	};
}
