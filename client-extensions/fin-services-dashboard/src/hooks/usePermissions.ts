import { useQuery } from '@tanstack/react-query';

import { isUserSignedIn } from '../api/apiError';
import { getPermissions, permissionsQueryKey } from '../api/permissions';

export function usePermissions() {
	const signedIn = isUserSignedIn();

	return useQuery({
		enabled: signedIn,
		gcTime: 0,
		queryFn: getPermissions,
		queryKey: permissionsQueryKey(),
		refetchOnMount: 'always',
		refetchOnReconnect: true,
		refetchOnWindowFocus: true,
		retry: 1,
		staleTime: 0,
	});
}
