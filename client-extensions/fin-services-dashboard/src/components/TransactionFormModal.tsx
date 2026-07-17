import { useEffect, useState } from 'react';

import {
	formatBalanceText,
	parseBalanceText,
	sanitizeBalanceText,
} from '../api/accounts';
import {
	emptyTransactionInput,
	transactionToInput,
	validateTransactionInput,
	TRANSACTION_STATUS_OPTIONS,
	TRANSACTION_TYPES,
	type Transaction,
	type TransactionInput,
} from '../api/transactions';
import {
	formatAccountLabel,
	getAccountSelectMessage,
	useAccountOptions,
} from '../hooks/useAccountOptions';
import { useLockBodyScroll } from '../hooks/useLockBodyScroll';
import { getErrorPresentation, formatCurrency } from '../utils/presentation';

interface TransactionFormModalProps {
	isPending: boolean;
	mode: 'create' | 'edit';
	onClose: () => void;
	onSubmit: (input: TransactionInput) => void;
	open: boolean;
	serverError?: string | null;
	transaction?: Transaction | null;
}

export function TransactionFormModal({
	isPending,
	mode,
	onClose,
	onSubmit,
	open,
	serverError,
	transaction,
}: TransactionFormModalProps) {
	const {
		data: accountOptions,
		error: accountOptionsError,
		isError: accountOptionsFailed,
		isLoading: accountOptionsLoading,
	} = useAccountOptions({ enabled: open });
	const accounts = accountOptions?.items ?? [];
	const accountSelectMessage = getAccountSelectMessage({
		accounts,
		errorMessage: accountOptionsFailed
			? getErrorPresentation(accountOptionsError).message
			: undefined,
		isError: accountOptionsFailed,
		isLoading: accountOptionsLoading,
	});
	const accountSelectDisabled =
		accountOptionsLoading || accountOptionsFailed || accounts.length === 0;

	const [form, setForm] = useState<TransactionInput>(emptyTransactionInput());
	const [amountText, setAmountText] = useState('');
	const [validationError, setValidationError] = useState<string | null>(null);

	useLockBodyScroll(open);

	useEffect(() => {
		if (!open) {
			return;
		}

		if (mode === 'edit' && transaction) {
			const nextForm = transactionToInput(transaction);

			setForm(nextForm);
			setAmountText(formatBalanceText(nextForm.amount));
		}
		else {
			setForm(emptyTransactionInput());
			setAmountText('');
		}

		setValidationError(null);
	}, [mode, open, transaction]);

	if (!open) {
		return null;
	}

	const selectedAccount = accounts.find(
		(account) => account.accountId === form.accountId,
	);
	const debitsAccount =
		form.transactionType === 'WITHDRAWAL' || form.transactionType === 'PAYMENT';

	function handleSubmit(event: React.FormEvent) {
		event.preventDefault();

		const error = validateTransactionInput(form, {
			accountBalance: selectedAccount?.balance,
		});

		if (error) {
			setValidationError(error);

			return;
		}

		setValidationError(null);
		onSubmit(form);
	}

	return (
		<div
			aria-labelledby="transaction-form-title"
			aria-modal="true"
			className="nexus-modal-backdrop fixed inset-0 z-50 flex p-3 sm:p-6"
			onClick={() => {
				if (!isPending) {
					onClose();
				}
			}}
			onKeyDown={(event) => {
				if (event.key === 'Escape' && !isPending) {
					onClose();
				}
			}}
			role="dialog"
		>
			<form
				className="nexus-card nexus-modal-panel relative z-10 max-w-lg p-4 shadow-2xl sm:p-6"
				onClick={(event) => event.stopPropagation()}
				onSubmit={handleSubmit}
			>
				<div className="nexus-modal-header">
					<h2
						className="nexus-title text-lg font-bold sm:text-xl"
						id="transaction-form-title"
					>
						{mode === 'create' ? 'Nueva transacción' : 'Editar transacción'}
					</h2>
					<p className="nexus-subtitle mt-1 text-sm">
						{mode === 'create'
							? 'Registra un movimiento sobre una cuenta del sitio.'
							: 'Actualiza los datos del movimiento seleccionado.'}
					</p>
				</div>

				<div className="nexus-modal-body nexus-form-stack">
					<div className="nexus-form-field">
						<label className="block space-y-2">
							<span className="nexus-label text-sm font-medium">Cuenta</span>
							<select
								className="nexus-select"
								disabled={accountSelectDisabled}
								onChange={(event) =>
									setForm((previous) => ({
										...previous,
										accountId: Number(event.target.value),
									}))
								}
								required
								value={form.accountId ?? ''}
							>
								<option value="">
									{accountOptionsLoading
										? 'Cargando cuentas...'
										: 'Seleccionar cuenta...'}
								</option>
								{accounts.map((account) => (
									<option
										key={account.accountId}
										value={account.accountId}
									>
										{formatAccountLabel(account)}
									</option>
								))}
							</select>
						</label>
						{accountSelectMessage && (
							<p className="nexus-subtitle text-xs">{accountSelectMessage}</p>
						)}
						{selectedAccount?.balance != null && (
							<p className="nexus-form-callout">
								Saldo disponible:{' '}
								<strong>{formatCurrency(selectedAccount.balance)} COP</strong>
							</p>
						)}
					</div>

					<div className="nexus-form-field">
						<label className="block space-y-2">
							<span className="nexus-label text-sm font-medium">Tipo</span>
							<select
								className="nexus-select"
								onChange={(event) =>
									setForm((previous) => ({
										...previous,
										transactionType: event.target.value,
									}))
								}
								value={form.transactionType ?? 'DEPOSIT'}
							>
								{TRANSACTION_TYPES.map((type) => (
									<option key={type.value} value={type.value}>
										{type.label}
									</option>
								))}
							</select>
						</label>
					</div>

					<div className="nexus-form-field-grid nexus-form-field-grid-pair">
						<div className="nexus-form-pair-item">
							<label className="nexus-label text-sm font-medium" htmlFor="tx-amount">
								Monto (COP)
							</label>
							<input
								className="nexus-input"
								id="tx-amount"
								inputMode="decimal"
								onChange={(event) => {
									const sanitized = sanitizeBalanceText(event.target.value);

									setAmountText(sanitized);
									setForm((previous) => ({
										...previous,
										amount: parseBalanceText(sanitized),
									}));
								}}
								placeholder="0.00"
								required
								type="text"
								value={amountText}
							/>
							<span className="nexus-form-field-hint">
								{debitsAccount && selectedAccount?.balance != null
									? 'No puede superar el saldo disponible.'
									: '\u00a0'}
							</span>
						</div>

						<div className="nexus-form-pair-item">
							<label
								className="nexus-label text-sm font-medium"
								htmlFor="tx-motivo"
							>
								Motivo
							</label>
							<input
								className="nexus-input"
								id="tx-motivo"
								onChange={(event) =>
									setForm((previous) => ({
										...previous,
										description: event.target.value,
									}))
								}
								placeholder="Concepto del movimiento..."
								type="text"
								value={form.description ?? ''}
							/>
							<span className="nexus-form-field-hint" aria-hidden>
								{'\u00a0'}
							</span>
						</div>
					</div>

					{mode === 'edit' && (
						<div className="nexus-form-field">
							<label className="block space-y-2">
								<span className="nexus-label text-sm font-medium">Estado</span>
								<select
									className="nexus-select"
									onChange={(event) =>
										setForm((previous) => ({
											...previous,
											status: Number(event.target.value),
										}))
									}
									value={String(form.status ?? 1)}
								>
									{TRANSACTION_STATUS_OPTIONS.filter(
										(option) => option.value !== '',
									).map((option) => (
										<option key={option.value} value={option.value}>
											{option.label}
										</option>
									))}
								</select>
							</label>
						</div>
					)}

					{(validationError || serverError) && (
						<p className="nexus-alert-error rounded-lg px-3 py-2 text-sm">
							{validationError ?? serverError}
						</p>
					)}
				</div>

				<div className="nexus-modal-footer">
					<button
						className="nexus-btn-secondary"
						disabled={isPending}
						onClick={onClose}
						type="button"
					>
						Cancelar
					</button>
					<button
						className="nexus-btn-primary"
						disabled={isPending || accountSelectDisabled}
						type="submit"
					>
						{isPending
							? 'Guardando...'
							: mode === 'create'
								? 'Registrar'
								: 'Guardar cambios'}
					</button>
				</div>
			</form>
		</div>
	);
}
