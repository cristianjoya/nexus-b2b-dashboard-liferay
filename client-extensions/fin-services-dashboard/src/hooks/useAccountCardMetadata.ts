import { useMemo } from 'react';
import { useQueries } from '@tanstack/react-query';

import type { Account } from '../api/accounts';
import { sanitizeAccountNumber } from '../api/accounts';
import { lookupCardBin, type CardBinInfo } from '../api/cardBin';

export function useAccountCardMetadata(accounts: Account[]) {
	const lookupTargets = useMemo(
		() =>
			accounts.filter(
				(account) =>
					!account.cardScheme &&
					sanitizeAccountNumber(account.accountNumber ?? '').length >= 6,
			),
		[accounts],
	);

	const queries = useQueries({
		queries: lookupTargets.map((account) => {
			const digits = sanitizeAccountNumber(account.accountNumber ?? '');
			const bin = digits.slice(0, Math.min(8, digits.length));

			return {
				enabled: Boolean(account.accountId),
				queryFn: () => lookupCardBin(bin),
				queryKey: ['cardBin', 'table', account.accountId, bin] as const,
				staleTime: 10 * 60 * 1000,
			};
		}),
	});

	return useMemo(() => {
		const metadata = new Map<number, CardBinInfo>();

		for (const account of accounts) {
			if (!account.accountId) {
				continue;
			}

			if (account.cardScheme) {
				metadata.set(account.accountId, {
					bankName: account.cardBankName,
					brand: account.cardBrand,
					countryName: account.cardCountryName,
					scheme: account.cardScheme,
				});
			}
		}

		lookupTargets.forEach((account, index) => {
			const result = queries[index]?.data;

			if (account.accountId && result?.scheme) {
				metadata.set(account.accountId, result);
			}
		});

		return metadata;
	}, [accounts, lookupTargets, queries]);
}
