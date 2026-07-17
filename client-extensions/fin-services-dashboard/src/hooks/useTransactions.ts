import {
	useMutation,
	useQuery,
	useQueryClient,
	type QueryClient,
} from '@tanstack/react-query';

import { isUserSignedIn } from '../api/apiError';
import { getSiteContext } from '../api/liferay';
import { accountOptionsQueryKey } from './useAccountOptions';
import {
	createTransaction,
	deleteTransaction,
	getTransactionsPage,
	updateTransaction,
	type TransactionInput,
	type TransactionListParams,
} from '../api/transactions';

function transactionsQueryKey(params: TransactionListParams) {
	const { scopeGroupId, siteFriendlyUrl } = getSiteContext();

	return [
		'transactions',
		scopeGroupId,
		siteFriendlyUrl,
		params.page ?? 1,
		params.pageSize ?? 10,
		params.search ?? '',
		params.accountId ?? null,
		params.ownerUserId ?? null,
		params.transactionType ?? '',
		params.status ?? null,
	] as const;
}

function transactionsRootKey() {
	const { scopeGroupId, siteFriendlyUrl } = getSiteContext();

	return ['transactions', scopeGroupId, siteFriendlyUrl] as const;
}

async function invalidateTransactions(queryClient: QueryClient) {
	await queryClient.invalidateQueries({ queryKey: transactionsRootKey() });
	await queryClient.invalidateQueries({ queryKey: ['accounts'] });
	await queryClient.invalidateQueries({ queryKey: accountOptionsQueryKey() });
}

export function useTransactions(params: TransactionListParams) {
	return useQuery({
		enabled: isUserSignedIn(),
		queryFn: () => getTransactionsPage(params),
		queryKey: transactionsQueryKey(params),
		placeholderData: (previousData) => previousData,
	});
}

export function useCreateTransaction() {
	const queryClient = useQueryClient();

	return useMutation({
		mutationFn: createTransaction,
		onSuccess: async () => invalidateTransactions(queryClient),
	});
}

export function useUpdateTransaction() {
	const queryClient = useQueryClient();

	return useMutation({
		mutationFn: ({
			transaction,
			transactionId,
		}: {
			transaction: TransactionInput;
			transactionId: number;
		}) => updateTransaction(transactionId, transaction),
		onSuccess: async () => invalidateTransactions(queryClient),
	});
}

export function useDeleteTransaction() {
	const queryClient = useQueryClient();

	return useMutation({
		mutationFn: deleteTransaction,
		onSuccess: async () => invalidateTransactions(queryClient),
	});
}
