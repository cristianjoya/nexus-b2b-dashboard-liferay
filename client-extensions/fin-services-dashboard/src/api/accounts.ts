import { appendSiteScopeParams, liferayFetch } from './liferay';

export interface Account {
	accountId?: number;
	accountName?: string;
	accountNumber?: string;
	accountType?: string;
	balance?: number;
	cardBankName?: string;
	cardBrand?: string;
	cardCountryName?: string;
	cardScheme?: string;
	createDate?: string;
	modifiedDate?: string;
	ownerUserId?: number;
	ownerUserName?: string;
	status?: number;
	uuid?: string;
}

export type AccountInput = Pick<
	Account,
	| 'accountName'
	| 'accountNumber'
	| 'accountType'
	| 'balance'
	| 'ownerUserId'
	| 'status'
	| 'cardBankName'
	| 'cardBrand'
	| 'cardCountryName'
	| 'cardScheme'
>;

export const ACCOUNT_TYPES = [
	{ label: 'Corriente', value: 'CHECKING' },
	{ label: 'Ahorros', value: 'SAVINGS' },
] as const;

export const ACCOUNT_STATUS_OPTIONS = [
	{ label: 'Todos los estados', value: '' },
	{ label: 'Activa', value: '1' },
	{ label: 'Inactiva', value: '0' },
] as const;

export const DEFAULT_PAGE_SIZE = 10;

export interface AccountListParams {
	accountType?: string;
	ownerUserId?: number | null;
	page?: number;
	pageSize?: number;
	search?: string;
	status?: number | null;
}

export interface AccountListResult {
	items: Account[];
	lastPage: number;
	page: number;
	pageSize: number;
	totalCount: number;
}

const API_BASE = '/o/fin-services-rest/v1.0';

function buildAccountsQuery(params: AccountListParams): string {
	const page = params.page ?? 1;
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;
	const query = new URLSearchParams();

	query.set('page', String(page));
	query.set('pageSize', String(pageSize));

	if (params.search?.trim()) {
		query.set('search', params.search.trim());
	}

	if (params.accountType) {
		query.set('accountType', params.accountType);
	}

	if (params.status != null) {
		query.set('status', String(params.status));
	}

	if (params.ownerUserId != null && params.ownerUserId > 0) {
		query.set('ownerUserId', String(params.ownerUserId));
	}

	return query.toString();
}

function normalizeAccountListResult(
	data: {
		items?: Account[];
		lastPage?: number;
		page?: number;
		pageSize?: number;
		totalCount?: number;
	} | Account[],
	params: AccountListParams,
): AccountListResult {
	const page = params.page ?? 1;
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;

	if (Array.isArray(data)) {
		const totalCount = data.length;
		const lastPage = Math.max(1, Math.ceil(totalCount / pageSize));

		return {
			items: data,
			lastPage,
			page,
			pageSize,
			totalCount,
		};
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

export async function getAccountsPage(
	params: AccountListParams = {},
): Promise<AccountListResult> {
	const query = buildAccountsQuery(params);
	const data = await liferayFetch<
		| {
				items?: Account[];
				lastPage?: number;
				page?: number;
				pageSize?: number;
				totalCount?: number;
		  }
		| Account[]
	>(appendSiteScopeParams(`${API_BASE}/accounts?${query}`));

	return normalizeAccountListResult(data, params);
}

export async function createAccount(account: AccountInput): Promise<Account> {
	return liferayFetch<Account>(appendSiteScopeParams(`${API_BASE}/accounts`), {
		body: JSON.stringify(account),
		method: 'POST',
	});
}

export async function updateAccount(
	accountId: number,
	account: AccountInput,
): Promise<Account> {
	return liferayFetch<Account>(
		appendSiteScopeParams(`${API_BASE}/accounts/${accountId}`),
		{
			body: JSON.stringify(account),
			method: 'PUT',
		},
	);
}

export async function deleteAccount(accountId: number): Promise<void> {
	await liferayFetch<void>(
		appendSiteScopeParams(`${API_BASE}/accounts/${accountId}`),
		{
			method: 'DELETE',
		},
	);
}

export function sanitizeAccountNumber(raw: string): string {
	const digits = raw.replace(/\D/g, '');

	if (!digits) {
		return '';
	}

	return digits.replace(/^0+(?=\d)/, '');
}

export function sanitizeAccountName(raw: string): string {
	return raw.replace(/\d/g, '');
}

export function sanitizeBalanceText(raw: string): string {
	if (!raw) {
		return '';
	}

	let value = raw.replace(/[^\d.]/g, '');
	const dotIndex = value.indexOf('.');

	if (dotIndex >= 0) {
		value =
			value.slice(0, dotIndex + 1) +
			value.slice(dotIndex + 1).replace(/\./g, '');
	}

	const [intRaw = '', decRaw] = value.split('.');
	let intPart = intRaw.replace(/^0+(?=\d)/, '');

	if (!intPart && decRaw !== undefined) {
		intPart = '0';
	}

	if (decRaw === undefined) {
		return intPart;
	}

	return `${intPart}.${decRaw.slice(0, 2)}`;
}

export function parseBalanceText(value: string): number {
	if (!value || value === '.') {
		return 0;
	}

	const parsed = Number(value);

	return Number.isNaN(parsed) ? 0 : parsed;
}

export function formatBalanceText(balance?: number): string {
	if (balance == null || balance === 0) {
		return '';
	}

	return String(balance);
}

export function validateAccountInput(
	account: AccountInput,
	options?: { requireOwner?: boolean },
): string | null {
	const accountNumber = sanitizeAccountNumber(account.accountNumber ?? '');
	const accountName = sanitizeAccountName(account.accountName ?? '').trim();

	if (!accountNumber) {
		return 'El número de cuenta es obligatorio.';
	}

	if (!/^\d+$/.test(accountNumber)) {
		return 'El número de cuenta solo puede contener dígitos.';
	}

	if (accountNumber.length < 6) {
		return 'Ingresa al menos 6 dígitos para identificar la red de pago.';
	}

	if (!accountName) {
		return 'El nombre de la cuenta es obligatorio.';
	}

	if (/\d/.test(account.accountName ?? '')) {
		return 'El nombre no puede contener números.';
	}

	if (!account.accountType?.trim()) {
		return 'Selecciona un tipo de cuenta.';
	}

	if (account.balance != null && Number.isNaN(Number(account.balance))) {
		return 'El saldo debe ser un número válido.';
	}

	if (account.balance != null && account.balance < 0) {
		return 'El saldo no puede ser negativo.';
	}

	if (
		options?.requireOwner &&
		(account.ownerUserId == null || account.ownerUserId <= 0)
	) {
		return 'Selecciona el usuario B2B asignado a esta cuenta.';
	}

	return null;
}

export function emptyAccountInput(): AccountInput {
	return {
		accountName: '',
		accountNumber: '',
		accountType: 'CHECKING',
		balance: 0,
		status: 1,
	};
}

export function accountToInput(account: Account): AccountInput {
	return {
		accountName: account.accountName ?? '',
		accountNumber: account.accountNumber ?? '',
		accountType: account.accountType ?? 'CHECKING',
		balance: account.balance ?? 0,
		ownerUserId: account.ownerUserId,
		status: account.status ?? 1,
	};
}

export function formatAccountType(value?: string) {
	const match = ACCOUNT_TYPES.find((type) => type.value === value);

	return match?.label ?? value ?? '-';
}

export function hasActiveFilters(params: AccountListParams): boolean {
	return Boolean(
		params.search?.trim() ||
			params.accountType ||
			(params.ownerUserId != null && params.ownerUserId > 0) ||
			params.status != null,
	);
}

export function emptyAccountListResult(
	params: AccountListParams = {},
): AccountListResult {
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;

	return {
		items: [],
		lastPage: 1,
		page: params.page ?? 1,
		pageSize,
		totalCount: 0,
	};
}
