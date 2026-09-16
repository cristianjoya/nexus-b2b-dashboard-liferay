import { resolveUserLabels } from './jsonws';
import {
	OBJECTS,
	objectsCollectAll,
	objectsCreate,
	objectsDelete,
	objectsGetPage,
	objectsUpdate,
	extractRelatedAccountId,
	paginateLocal,
	picklistKey,
	picklistToNumber,
} from './objectsClient';

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

type ObjectAccount = Record<string, unknown> & {
	accountName?: string;
	accountNumber?: string;
	accountType?: unknown;
	balance?: number;
	cardBankName?: string;
	cardBrand?: string;
	cardCountryName?: string;
	cardScheme?: string;
	dateCreated?: string;
	dateModified?: string;
	id?: number;
	nexusStatus?: unknown;
	ownerUserId?: number;
};

function normalizeOwnerUserId(value: unknown): number | undefined {
	if (value == null || value === '') {
		return undefined;
	}

	const parsed = Number(value);

	if (!Number.isFinite(parsed) || parsed <= 0) {
		return undefined;
	}

	return parsed;
}

function mapAccount(raw: ObjectAccount): Account {
	return {
		accountId: raw.id,
		accountName: raw.accountName,
		accountNumber: raw.accountNumber,
		accountType: picklistKey(raw.accountType) || undefined,
		balance: raw.balance != null ? Number(raw.balance) : 0,
		cardBankName: raw.cardBankName,
		cardBrand: raw.cardBrand,
		cardCountryName: raw.cardCountryName,
		cardScheme: raw.cardScheme,
		createDate: raw.dateCreated,
		modifiedDate: raw.dateModified,
		ownerUserId: normalizeOwnerUserId(raw.ownerUserId),
		status: picklistToNumber(raw.nexusStatus, 1),
	};
}

async function withOwnerNames(accounts: Account[]): Promise<Account[]> {
	const labels = await resolveUserLabels(
		accounts.map((account) => account.ownerUserId),
	);

	return accounts.map((account) => {
		if (account.ownerUserId == null) {
			return { ...account, ownerUserName: undefined };
		}

		return {
			...account,
			ownerUserName:
				labels.get(account.ownerUserId) ??
				`Usuario #${account.ownerUserId}`,
		};
	});
}

export function formatOwnerDisplay(account: {
	ownerUserId?: number;
	ownerUserName?: string;
}): string {
	if (account.ownerUserId == null || account.ownerUserId <= 0) {
		return 'Sin asignar';
	}

	return account.ownerUserName?.trim() || `Usuario #${account.ownerUserId}`;
}

function buildAccountFilter(params: AccountListParams): string | undefined {
	const parts: string[] = [];

	if (params.accountType) {
		parts.push(`accountType eq '${params.accountType}'`);
	}

	if (params.status != null) {
		parts.push(`nexusStatus eq '${params.status}'`);
	}

	if (params.ownerUserId != null && params.ownerUserId > 0) {
		parts.push(`ownerUserId eq ${params.ownerUserId}`);
	}

	return parts.length ? parts.join(' and ') : undefined;
}

function toObjectAccountBody(account: AccountInput): Record<string, unknown> {
	const body: Record<string, unknown> = {
		accountName: account.accountName,
		accountNumber: account.accountNumber,
		accountType: account.accountType,
		nexusStatus: String(account.status ?? 1),
		ownerUserId:
			account.ownerUserId != null && account.ownerUserId > 0
				? account.ownerUserId
				: 0,
	};

	if (account.cardBankName) {
		body.cardBankName = account.cardBankName;
	}

	if (account.cardBrand) {
		body.cardBrand = account.cardBrand;
	}

	if (account.cardCountryName) {
		body.cardCountryName = account.cardCountryName;
	}

	if (account.cardScheme) {
		body.cardScheme = account.cardScheme;
	}

	// Nunca enviar balance: lo calcula el Object Action. Un PATCH con 0 lo borra.

	return body;
}

export async function getAccountsPage(
	params: AccountListParams = {},
): Promise<AccountListResult> {
	const page = params.page ?? 1;
	const pageSize = params.pageSize ?? DEFAULT_PAGE_SIZE;
	const ownerUserId =
		params.ownerUserId != null && params.ownerUserId > 0
			? params.ownerUserId
			: null;

	// ownerUserId se filtra en cliente: el OData Integer de Objects es poco fiable.
	const serverFilter = buildAccountFilter({
		...params,
		ownerUserId: null,
	});

	if (ownerUserId == null) {
		const data = await objectsGetPage<ObjectAccount>(OBJECTS.accounts, {
			filter: serverFilter,
			page,
			pageSize,
			search: params.search,
			sort: 'dateCreated:desc',
		});

		const items = await withOwnerNames((data.items ?? []).map(mapAccount));
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

	const rawItems = await objectsCollectAll<ObjectAccount>(OBJECTS.accounts, {
		search: params.search,
		sort: 'dateCreated:desc',
	});

	let accounts = rawItems.map(mapAccount).filter((account) => {
		if (account.ownerUserId !== ownerUserId) {
			return false;
		}

		if (
			params.accountType &&
			account.accountType !== params.accountType
		) {
			return false;
		}

		if (params.status != null && account.status !== params.status) {
			return false;
		}

		return true;
	});

	accounts = await withOwnerNames(accounts);

	const paged = paginateLocal(accounts, page, pageSize);

	return {
		items: paged.items,
		lastPage: paged.lastPage,
		page: paged.page,
		pageSize: paged.pageSize,
		totalCount: paged.totalCount,
	};
}

export async function createAccount(account: AccountInput): Promise<Account> {
	const created = await objectsCreate<ObjectAccount>(
		OBJECTS.accounts,
		toObjectAccountBody(account),
	);

	const [enriched] = await withOwnerNames([mapAccount(created)]);

	return enriched;
}

export async function updateAccount(
	accountId: number,
	account: AccountInput,
): Promise<Account> {
	const updated = await objectsUpdate<ObjectAccount>(
		OBJECTS.accounts,
		accountId,
		toObjectAccountBody(account),
	);

	const [enriched] = await withOwnerNames([mapAccount(updated)]);

	return enriched;
}

export async function deleteAccount(accountId: number): Promise<void> {
	// Relación Objects = Impedir + OData de FK roto ("Incompatible types"):
	// listamos hijos sin filtro de relación y borramos en cliente.
	await deleteRelatedEntriesForAccount(
		OBJECTS.transactions,
		accountId,
		'r_finAccount_c_finAccountId',
		'finAccount',
	);
	await deleteRelatedEntriesForAccount(
		OBJECTS.creditApplications,
		accountId,
		'r_finAccounts_c_finAccountId',
		'finAccounts',
	);

	await objectsDelete(OBJECTS.accounts, accountId);
}

async function deleteRelatedEntriesForAccount(
	resourceBase: string,
	accountId: number,
	fkField: string,
	relationshipName: string,
): Promise<void> {
	const entries = await objectsCollectAll<Record<string, unknown>>(
		resourceBase,
		{ pageSize: 100, sort: 'dateCreated:desc' },
	);

	for (const entry of entries) {
		const relatedId = extractRelatedAccountId(entry, {
			fkField,
			relationshipName,
		});

		if (relatedId === accountId && entry.id != null) {
			const id = Number(entry.id);

			if (Number.isFinite(id) && id > 0) {
				await objectsDelete(resourceBase, id);
			}
		}
	}
}

export function sanitizeAccountNumber(raw: string): string {
	const digits = raw.replace(/\D/g, '').slice(0, 19);

	if (!digits) {
		return '';
	}

	return digits.replace(/^0+(?=\d)/, '');
}

export function sanitizeAccountName(raw: string): string {
	return raw
		.replace(/\d/g, '')
		.replace(/[<>"'`\\]/g, '')
		.replace(/[\u0000-\u001F\u007F]/g, '')
		.slice(0, 120);
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
		status: 1,
	};
}

export function accountToInput(account: Account): AccountInput {
	return {
		accountName: account.accountName ?? '',
		accountNumber: account.accountNumber ?? '',
		accountType: account.accountType ?? 'CHECKING',
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
