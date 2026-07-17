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
	createCreditApplication,
	deleteCreditApplication,
	getCreditApplicationsPage,
	updateCreditApplication,
	type CreditApplicationInput,
	type CreditApplicationListParams,
} from '../api/creditApplications';

function creditApplicationsQueryKey(params: CreditApplicationListParams) {
	const { scopeGroupId, siteFriendlyUrl } = getSiteContext();

	return [
		'creditApplications',
		scopeGroupId,
		siteFriendlyUrl,
		params.page ?? 1,
		params.pageSize ?? 10,
		params.search ?? '',
		params.accountId ?? null,
		params.ownerUserId ?? null,
		params.status ?? null,
	] as const;
}

function creditApplicationsRootKey() {
	const { scopeGroupId, siteFriendlyUrl } = getSiteContext();

	return ['creditApplications', scopeGroupId, siteFriendlyUrl] as const;
}

async function invalidateCreditApplications(queryClient: QueryClient) {
	await queryClient.invalidateQueries({
		queryKey: creditApplicationsRootKey(),
	});
	await queryClient.invalidateQueries({ queryKey: ['accounts'] });
	await queryClient.invalidateQueries({ queryKey: accountOptionsQueryKey() });
}

export function useCreditApplications(params: CreditApplicationListParams) {
	return useQuery({
		enabled: isUserSignedIn(),
		queryFn: () => getCreditApplicationsPage(params),
		queryKey: creditApplicationsQueryKey(params),
		placeholderData: (previousData) => previousData,
	});
}

export function useCreateCreditApplication() {
	const queryClient = useQueryClient();

	return useMutation({
		mutationFn: createCreditApplication,
		onSuccess: async () => invalidateCreditApplications(queryClient),
	});
}

export function useUpdateCreditApplication() {
	const queryClient = useQueryClient();

	return useMutation({
		mutationFn: ({
			creditAppId,
			creditApplication,
		}: {
			creditApplication: CreditApplicationInput;
			creditAppId: number;
		}) => updateCreditApplication(creditAppId, creditApplication),
		onSuccess: async () => invalidateCreditApplications(queryClient),
	});
}

export function useDeleteCreditApplication() {
	const queryClient = useQueryClient();

	return useMutation({
		mutationFn: deleteCreditApplication,
		onSuccess: async () => invalidateCreditApplications(queryClient),
	});
}
