export type BadgeTone = 'danger' | 'info' | 'neutral' | 'success' | 'warning';

const TONE_CLASS: Record<BadgeTone, string> = {
	danger: 'nexus-badge-danger',
	info: 'nexus-badge-info',
	neutral: 'nexus-badge-inactive',
	success: 'nexus-badge-active',
	warning: 'nexus-badge-warning',
};

interface StatusBadgeProps {
	label: string;
	tone?: BadgeTone;
}

export function StatusBadge({ label, tone = 'neutral' }: StatusBadgeProps) {
	return (
		<span
			className={`inline-flex rounded-full px-2.5 py-1 text-xs font-semibold ${TONE_CLASS[tone]}`}
		>
			{label}
		</span>
	);
}

export function getTransactionTypeBadge(
	transactionType?: string,
): { label: string; tone: BadgeTone } {
	switch (transactionType) {
		case 'DEPOSIT':
			return { label: 'Depósito', tone: 'success' };
		case 'WITHDRAWAL':
			return { label: 'Retiro', tone: 'warning' };
		case 'PAYMENT':
			return { label: 'Pago', tone: 'info' };
		default:
			return { label: transactionType ?? 'Movimiento', tone: 'neutral' };
	}
}

export function getTransactionStatusBadge(
	status?: number,
): { label: string; tone: BadgeTone } {
	switch (status) {
		case 0:
			return { label: 'Pendiente', tone: 'warning' };
		case 1:
			return { label: 'Completada', tone: 'success' };
		case 2:
			return { label: 'Fallida', tone: 'danger' };
		default:
			return { label: '-', tone: 'neutral' };
	}
}

export function getCreditStatusBadge(
	status?: number,
): { label: string; tone: BadgeTone } {
	switch (status) {
		case 0:
			return { label: 'Pendiente', tone: 'warning' };
		case 1:
			return { label: 'Aprobada', tone: 'success' };
		case 2:
			return { label: 'Rechazada', tone: 'danger' };
		default:
			return { label: '-', tone: 'neutral' };
	}
}

export function isDebitTransactionType(transactionType?: string) {
	return transactionType === 'WITHDRAWAL' || transactionType === 'PAYMENT';
}
