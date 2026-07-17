import { appendSiteScopeParams, liferayFetch } from './liferay';

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

const API_BASE = '/o/fin-services-rest/v1.0';

function buildTransactionsQuery(params: TransactionListParams): string {
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

	if (params.transactionType) {
		query.set('transactionType', params.transactionType);
	}

	if (params.status != null) {
		query.set('status', String(params.status));
	}

	return query.toString();
}

function normalizeListResult(
	data: {
		items?: Transaction[];
		lastPage?: number;
		page?: number;
		pageSize?: number;
		totalCount?: number;
	} | Transaction[],
	params: TransactionListParams,
): TransactionListResult {
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

export async function getTransactionsPage(
	params: TransactionListParams = {},
): Promise<TransactionListResult> {
	const query = buildTransactionsQuery(params);
	const data = await liferayFetch<
		| {
				items?: Transaction[];
				lastPage?: number;
				page?: number;
				pageSize?: number;
				totalCount?: number;
		  }
		| Transaction[]
	>(appendSiteScopeParams(`${API_BASE}/transactions?${query}`));

	return normalizeListResult(data, params);
}

export async function createTransaction(
	transaction: TransactionInput,
): Promise<Transaction> {
	return liferayFetch<Transaction>(
		appendSiteScopeParams(`${API_BASE}/transactions`),
		{
			body: JSON.stringify(transaction),
			method: 'POST',
		},
	);
}

export async function updateTransaction(
	transactionId: number,
	transaction: TransactionInput,
): Promise<Transaction> {
	return liferayFetch<Transaction>(
		appendSiteScopeParams(`${API_BASE}/transactions/${transactionId}`),
		{
			body: JSON.stringify(transaction),
			method: 'PUT',
		},
	);
}

export async function deleteTransaction(transactionId: number): Promise<void> {
	await liferayFetch<void>(
		appendSiteScopeParams(`${API_BASE}/transactions/${transactionId}`),
		{ method: 'DELETE' },
	);
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
