import { useQuery } from '@tanstack/react-query';

import {
	getAssignableB2bUsers,
	type AssignableB2bUsersParams,
} from '../api/b2bUsers';
import { getLiferayUserId, isUserSignedIn } from '../api/apiError';
import { getSiteContext } from '../api/liferay';

function getAssignableUsersSessionKey(): string {
	const userId = getLiferayUserId();

	if (userId > 0) {
		return `user:${userId}`;
	}

	return `session:${window.Liferay?.authToken ?? 'guest'}`;
}

export function assignableB2bUsersQueryKey(
	params: AssignableB2bUsersParams = {},
) {
	const { scopeGroupId, siteFriendlyUrl } = getSiteContext();

	return [
		'assignableB2bUsers',
		getAssignableUsersSessionKey(),
		scopeGroupId,
		siteFriendlyUrl,
		params.search?.trim() ?? '',
		params.page ?? 1,
		params.pageSize ?? 20,
	] as const;
}

type UseAssignableB2bUsersParams = AssignableB2bUsersParams & {
	enabled?: boolean;
};

export function useAssignableB2bUsers(
	enabledOrParams: boolean | UseAssignableB2bUsersParams = true,
) {
	const params: UseAssignableB2bUsersParams =
		typeof enabledOrParams === 'boolean'
			? { enabled: enabledOrParams }
			: enabledOrParams;

	const {
		enabled = true,
		page = 1,
		pageSize = 20,
		search = '',
	} = params;

	const queryParams = { page, pageSize, search };

	return useQuery({
		enabled: enabled && isUserSignedIn(),
		queryFn: () => getAssignableB2bUsers(queryParams),
		queryKey: assignableB2bUsersQueryKey(queryParams),
		staleTime: 30_000,
	});
}
