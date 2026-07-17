import {
	formatCardSchemeLabel,
	isLuhnValid,
	requiresLuhnCheck,
	type CardBinInfo,
} from '../api/cardBin';

interface CardBinPreviewProps {
	cardBin?: CardBinInfo | null;
	digits: string;
	error?: unknown;
	isLoading: boolean;
}

function formatMaskedNumber(digits: string): string {
	if (digits.length >= 13) {
		return `${digits.slice(0, 4)}  ••••  ••••  ${digits.slice(-4)}`;
	}

	if (digits.length >= 8) {
		return `${digits.slice(0, 4)}  ${digits.slice(4, 8)}  ••••`;
	}

	return digits.replace(/(\d{4})(?=\d)/g, '$1 ').trim();
}

function schemeToneClass(scheme?: string): string {
	switch (scheme?.toLowerCase()) {
		case 'visa':
			return 'nexus-bin-card-visa';
		case 'mastercard':
			return 'nexus-bin-card-mastercard';
		case 'amex':
			return 'nexus-bin-card-amex';
		case 'discover':
			return 'nexus-bin-card-discover';
		default:
			return 'nexus-bin-card-default';
	}
}

function SchemeMark({ scheme }: { scheme?: string }) {
	const normalized = scheme?.toLowerCase();

	if (normalized === 'mastercard') {
		return (
			<span aria-hidden className="nexus-bin-mc-mark">
				<span className="nexus-bin-mc-circle nexus-bin-mc-circle-red" />
				<span className="nexus-bin-mc-circle nexus-bin-mc-circle-orange" />
			</span>
		);
	}

	return (
		<span className="nexus-bin-scheme-label">
			{formatCardSchemeLabel(scheme) ?? scheme}
		</span>
	);
}

export function CardBinPreview({
	cardBin,
	digits,
	error,
	isLoading,
}: CardBinPreviewProps) {
	if (digits.length < 6) {
		return (
			<p className="nexus-bin-hint">
				Escribe al menos 6 dígitos para detectar la red de pago.
			</p>
		);
	}

	if (isLoading) {
		return (
			<div className="nexus-bin-card nexus-bin-card-loading" aria-busy="true">
				<div className="nexus-bin-card-shimmer" />
				<p className="nexus-bin-hint nexus-bin-hint-on-card">
					Detectando red de pago…
				</p>
			</div>
		);
	}

	if (error) {
		return (
			<p className="nexus-alert-warning rounded-lg px-3 py-2 text-xs">
				No fue posible consultar la red. Se usarán reglas locales.
			</p>
		);
	}

	if (!cardBin?.scheme) {
		return (
			<p className="nexus-alert-warning rounded-lg px-3 py-2 text-xs">
				No se detectó una red de pago conocida para este número.
			</p>
		);
	}

	const schemeLabel =
		cardBin.brand?.trim() ||
		formatCardSchemeLabel(cardBin.scheme) ||
		cardBin.scheme;
	const luhnValid = requiresLuhnCheck(digits) ? isLuhnValid(digits) : null;
	const showLuhn = luhnValid != null;
	const typeLabel = cardBin.type?.trim();

	return (
		<article
			aria-label={`Tarjeta ${schemeLabel} detectada`}
			className={`nexus-bin-card ${schemeToneClass(cardBin.scheme)}`}
		>
			<div className="nexus-bin-card-top">
				<div className="nexus-bin-card-brand">
					<SchemeMark scheme={cardBin.scheme} />
					<span className="nexus-bin-card-brand-name">{schemeLabel}</span>
				</div>
				<span className="nexus-bin-chip" aria-hidden />
			</div>

			<p className="nexus-bin-card-number">{formatMaskedNumber(digits)}</p>

			<div className="nexus-bin-card-meta">
				<div className="nexus-bin-card-meta-block">
					<span className="nexus-bin-card-meta-label">Emisor</span>
					<span className="nexus-bin-card-meta-value">
						{cardBin.bankName?.trim() || 'No identificado'}
					</span>
				</div>
				<div className="nexus-bin-card-meta-block nexus-bin-card-meta-end">
					<span className="nexus-bin-card-meta-label">País</span>
					<span className="nexus-bin-card-meta-value">
						{cardBin.countryName?.trim() ||
							cardBin.countryCode?.trim() ||
							'—'}
					</span>
				</div>
			</div>

			{(typeLabel || showLuhn) && (
				<div className="nexus-bin-card-footer">
					{typeLabel && (
						<span className="nexus-bin-pill capitalize">{typeLabel}</span>
					)}
					{showLuhn && luhnValid && (
						<span className="nexus-bin-pill nexus-bin-pill-ok">
							Número válido
						</span>
					)}
					{showLuhn && !luhnValid && (
						<span className="nexus-bin-pill nexus-bin-pill-bad">
							Número inválido
						</span>
					)}
				</div>
			)}
		</article>
	);
}
