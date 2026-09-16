import type { Account } from '../api/accounts';
import type { CardBinInfo } from '../api/cardBin';
import { formatCardSchemeLabel, getCardSchemeStyles } from '../api/cardBin';

interface CardSchemeBadgeProps {
	brand?: string | null;
	scheme?: string | null;
	size?: 'md' | 'sm';
}

export function CardSchemeBadge({
	brand,
	scheme,
	size = 'sm',
}: CardSchemeBadgeProps) {
	if (!scheme) {
		return (
			<span className="nexus-badge-inactive inline-flex rounded-full px-2.5 py-1 text-xs font-semibold">
				Sin red
			</span>
		);
	}

	const label = brand ?? formatCardSchemeLabel(scheme) ?? scheme;
	const sizeClass = size === 'md' ? 'px-3 py-1.5 text-sm' : 'px-2.5 py-1 text-xs';

	return (
		<span
			className={`inline-flex rounded-full font-bold uppercase tracking-wide ${sizeClass} ${getCardSchemeStyles(scheme)}`}
		>
			{label}
		</span>
	);
}

export function accountToCardDisplay(account: Account): CardBinInfo {
	return {
		bankName: account.cardBankName,
		brand: account.cardBrand,
		countryName: account.cardCountryName,
		scheme: account.cardScheme,
	};
}

export function mergeCardDisplay(
	account: Account,
	lookup?: CardBinInfo | null,
): CardBinInfo {
	if (account.cardScheme) {
		return accountToCardDisplay(account);
	}

	return lookup ?? accountToCardDisplay(account);
}
