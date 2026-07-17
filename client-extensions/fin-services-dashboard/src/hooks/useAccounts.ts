import {
	useMutation,
	useQuery,
	useQueryClient,
	type QueryClient,
} from '@tanstack/react-query';

import {
	createAccount,
	deleteAccount,
	getAccountsPage,
	updateAccount,
	type AccountInput,
	type AccountListParams,
} from '../api/accounts';
import { isUserSignedIn } from '../api/apiError';
import { getSiteContext } from '../api/liferay';
import { accountOptionsQueryKey } from './useAccountOptions';

function accountsQueryKey(params: AccountListParams) {
	const { scopeGroupId, siteFriendlyUrl } = getSiteContext();

	return [
		'accounts',
		scopeGroupId,
		siteFriendlyUrl,
		params.page ?? 1,
		params.pageSize ?? 10,
		params.search ?? '',
		params.accountType ?? '',
		params.ownerUserId ?? null,
		params.status ?? null,
	] as const;
}

function accountsRootKey() {
	const { scopeGroupId, siteFriendlyUrl } = getSiteContext();

	return ['accounts', scopeGroupId, siteFriendlyUrl] as const;
}

async function invalidateAccounts(queryClient: QueryClient) {
	await queryClient.invalidateQueries({ queryKey: accountsRootKey() });
	await queryClient.invalidateQueries({ queryKey: accountOptionsQueryKey() });
}

export function useAccounts(params: AccountListParams) {
	const signedIn = isUserSignedIn();

	return useQuery({
		enabled: signedIn,
		queryFn: () => getAccountsPage(params),
		queryKey: accountsQueryKey(params),
		placeholderData: (previousData) => previousData,
	});
}

export function useCreateAccount() {
	const queryClient = useQueryClient();

	return useMutation({
		mutationFn: createAccount,
		onSuccess: async () => invalidateAccounts(queryClient),
	});
}

export function useUpdateAccount() {
	const queryClient = useQueryClient();

	return useMutation({
		mutationFn: ({
			accountId,
			account,
		}: {
			account: AccountInput;
			accountId: number;
		}) => updateAccount(accountId, account),
		onSuccess: async () => invalidateAccounts(queryClient),
	});
}

export function useDeleteAccount() {
	const queryClient = useQueryClient();

	return useMutation({
		mutationFn: deleteAccount,
		onSuccess: async () => invalidateAccounts(queryClient),
	});
}
