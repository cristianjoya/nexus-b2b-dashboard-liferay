import { useQuery } from '@tanstack/react-query';

import {
	getAccountsPage,
	type Account,
	type AccountListResult,
} from '../api/accounts';
import { getLiferayUserId, isUserSignedIn } from '../api/apiError';
import { getSiteContext } from '../api/liferay';

const OPTIONS_PAGE_SIZE = 100;

function getAccountOptionsSessionKey(): string {
	const userId = getLiferayUserId();

	if (userId > 0) {
		return `user:${userId}`;
	}

	return `session:${window.Liferay?.authToken ?? 'guest'}`;
}

export function accountOptionsQueryKey(ownerUserId?: number | null) {
	const { scopeGroupId, siteFriendlyUrl } = getSiteContext();

	return [
		'accountOptions',
		getAccountOptionsSessionKey(),
		scopeGroupId,
		siteFriendlyUrl,
		ownerUserId ?? null,
	] as const;
}

async function fetchAccountsByStatus(
	status?: number,
	ownerUserId?: number | null,
): Promise<Account[]> {
	const items: Account[] = [];
	let page = 1;
	let lastPage = 1;

	do {
		const result = await getAccountsPage({
			ownerUserId:
				ownerUserId != null && ownerUserId > 0 ? ownerUserId : undefined,
			page,
			pageSize: OPTIONS_PAGE_SIZE,
			status,
		});

		items.push(
			...result.items.filter((account) => account.accountId != null),
		);
		lastPage = result.lastPage;
		page += 1;
	} while (page <= lastPage);

	return items;
}

export async function fetchAccountOptions(
	ownerUserId?: number | null,
): Promise<AccountListResult> {
	let items = await fetchAccountsByStatus(1, ownerUserId);

	if (items.length === 0) {
		items = await fetchAccountsByStatus(undefined, ownerUserId);
	}

	return {
		items,
		lastPage: 1,
		page: 1,
		pageSize: items.length || OPTIONS_PAGE_SIZE,
		totalCount: items.length,
	};
}

type UseAccountOptionsParams = {
	enabled?: boolean;
	ownerUserId?: number | null;
};

export function useAccountOptions(params: UseAccountOptionsParams = {}) {
	const { enabled = true, ownerUserId = null } = params;

	return useQuery({
		enabled: isUserSignedIn() && enabled,
		queryFn: () => fetchAccountOptions(ownerUserId),
		queryKey: accountOptionsQueryKey(ownerUserId),
		refetchOnMount: 'always',
		retry: 1,
		staleTime: 0,
	});
}

export function formatAccountLabel(account: {
	accountId?: number;
	accountName?: string;
	accountNumber?: string;
	status?: number;
}) {
	const name = account.accountName?.trim() || 'Sin nombre';
	const number = account.accountNumber ?? account.accountId ?? '';
	const inactiveSuffix = account.status === 0 ? ' · Inactiva' : '';

	return `${name} (${number})${inactiveSuffix}`;
}

export function getAccountSelectMessage(options: {
	accounts: Account[];
	errorMessage?: string;
	isError: boolean;
	isLoading: boolean;
}): string | null {
	if (options.isLoading) {
		return 'Cargando cuentas...';
	}

	if (options.isError) {
		return (
			options.errorMessage ??
			'No se pudieron cargar las cuentas. Recarga la página e intenta de nuevo.'
		);
	}

	if (options.accounts.length === 0) {
		return 'No hay cuentas disponibles en este sitio. Pide a un administrador que cree al menos una cuenta activa.';
	}

	return null;
}
