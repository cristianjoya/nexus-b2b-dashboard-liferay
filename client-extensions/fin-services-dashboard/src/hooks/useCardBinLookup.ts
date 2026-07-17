import { useQuery } from '@tanstack/react-query';

import { sanitizeAccountNumber } from '../api/accounts';
import { isUserSignedIn } from '../api/apiError';
import { lookupCardBin, type CardBinInfo } from '../api/cardBin';

const MIN_BIN_LENGTH = 6;

export function useCardBinLookup(accountNumber: string, enabled = true) {
	const digits = sanitizeAccountNumber(accountNumber);
	const bin = digits.slice(0, Math.min(8, digits.length));
	const signedIn = isUserSignedIn();
	const canLookup = enabled && signedIn && digits.length >= MIN_BIN_LENGTH;

	return useQuery<CardBinInfo>({
		enabled: canLookup,
		queryFn: () => lookupCardBin(bin),
		queryKey: ['cardBin', bin],
		retry: (failureCount, error) => {
			if (
				error instanceof Error &&
				'status' in error &&
				typeof (error as { status: unknown }).status === 'number'
			) {
				const status = (error as { status: number }).status;

				if (status === 401 || status === 403) {
					return false;
				}
			}

			return failureCount < 2;
		},
		staleTime: 5 * 60 * 1000,
	});
}
