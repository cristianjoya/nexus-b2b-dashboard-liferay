import { useEffect, useState } from 'react';

import {
	formatBalanceText,
	parseBalanceText,
	sanitizeBalanceText,
} from '../api/accounts';
import {
	creditApplicationToInput,
	CREDIT_STATUS_OPTIONS,
	emptyCreditApplicationInput,
	validateCreditApplicationInput,
	type CreditApplication,
	type CreditApplicationInput,
} from '../api/creditApplications';
import {
	formatAccountLabel,
	getAccountSelectMessage,
	useAccountOptions,
} from '../hooks/useAccountOptions';
import { useLockBodyScroll } from '../hooks/useLockBodyScroll';
import { getErrorPresentation } from '../utils/presentation';

interface CreditApplicationFormModalProps {
	creditApplication?: CreditApplication | null;
	isPending: boolean;
	mode: 'create' | 'edit' | 'review';
	onClose: () => void;
	onSubmit: (input: CreditApplicationInput) => void;
	open: boolean;
	serverError?: string | null;
}

export function CreditApplicationFormModal({
	creditApplication,
	isPending,
	mode,
	onClose,
	onSubmit,
	open,
	serverError,
}: CreditApplicationFormModalProps) {
	const isReview = mode === 'review';
	const readOnlyFields = isReview;

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
		readOnlyFields ||
		accountOptionsLoading ||
		accountOptionsFailed ||
		accounts.length === 0;

	const [form, setForm] = useState<CreditApplicationInput>(
		emptyCreditApplicationInput(),
	);
	const [amountText, setAmountText] = useState('');
	const [validationError, setValidationError] = useState<string | null>(null);

	useLockBodyScroll(open);

	useEffect(() => {
		if (!open) {
			return;
		}

		if ((mode === 'edit' || mode === 'review') && creditApplication) {
			const nextForm = creditApplicationToInput(creditApplication);

			setForm(nextForm);
			setAmountText(formatBalanceText(nextForm.requestedAmount));
		}
		else {
			setForm(emptyCreditApplicationInput());
			setAmountText('');
		}

		setValidationError(null);
	}, [creditApplication, mode, open]);

	if (!open) {
		return null;
	}

	function handleSubmit(event: React.FormEvent) {
		event.preventDefault();

		if (!isReview) {
			const error = validateCreditApplicationInput(form);

			if (error) {
				setValidationError(error);

				return;
			}
		}

		setValidationError(null);
		onSubmit(form);
	}

	const title =
		mode === 'create'
			? 'Nueva solicitud de crédito'
			: mode === 'review'
				? 'Revisar solicitud'
				: 'Editar solicitud';
	const subtitle =
		mode === 'create'
			? 'Completa los datos para solicitar crédito sobre una cuenta.'
			: mode === 'review'
				? 'Aprueba o rechaza la solicitud y deja notas internas.'
				: 'Actualiza los datos de la solicitud seleccionada.';

	return (
		<div
			aria-labelledby="credit-form-title"
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
						id="credit-form-title"
					>
						{title}
					</h2>
					<p className="nexus-subtitle mt-1 text-sm">{subtitle}</p>
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
					</div>

					<div className="nexus-form-field">
						<label className="block space-y-2">
							<span className="nexus-label text-sm font-medium">
								Solicitante
							</span>
							<input
								className="nexus-input"
								disabled={readOnlyFields}
								onChange={(event) =>
									setForm((previous) => ({
										...previous,
										applicantName: event.target.value,
									}))
								}
								placeholder="Nombre de quien solicita"
								required
								type="text"
								value={form.applicantName ?? ''}
							/>
						</label>
					</div>

					<div className="nexus-form-field">
						<label className="block space-y-2">
							<span className="nexus-label text-sm font-medium">
								Monto solicitado (COP)
							</span>
							<input
								className="nexus-input"
								disabled={readOnlyFields}
								inputMode="decimal"
								onChange={(event) => {
									const sanitized = sanitizeBalanceText(event.target.value);

									setAmountText(sanitized);
									setForm((previous) => ({
										...previous,
										requestedAmount: parseBalanceText(sanitized),
									}));
								}}
								placeholder="0.00"
								required
								type="text"
								value={amountText}
							/>
						</label>
					</div>

					<div className="nexus-form-field">
						<label className="block space-y-2">
							<span className="nexus-label text-sm font-medium">Propósito</span>
							<textarea
								className="nexus-input nexus-form-textarea"
								disabled={readOnlyFields}
								onChange={(event) =>
									setForm((previous) => ({
										...previous,
										purpose: event.target.value,
									}))
								}
								placeholder="Describe para qué se solicita el crédito..."
								required
								rows={3}
								value={form.purpose ?? ''}
							/>
						</label>
					</div>

					{(mode === 'edit' || mode === 'review') && (
						<>
							<div className="nexus-form-field">
								<label className="block space-y-2">
									<span className="nexus-label text-sm font-medium">
										Estado
									</span>
									<select
										className="nexus-select"
										disabled={mode === 'edit' && form.status !== 0}
										onChange={(event) =>
											setForm((previous) => ({
												...previous,
												status: Number(event.target.value),
											}))
										}
										value={String(form.status ?? 0)}
									>
										{CREDIT_STATUS_OPTIONS.filter(
											(option) => option.value !== '',
										).map((option) => (
											<option key={option.value} value={option.value}>
												{option.label}
											</option>
										))}
									</select>
								</label>
							</div>

							<div className="nexus-form-field">
								<label className="block space-y-2">
									<span className="nexus-label text-sm font-medium">
										Notas de revisión
									</span>
									<textarea
										className="nexus-input nexus-form-textarea nexus-form-textarea-sm"
										onChange={(event) =>
											setForm((previous) => ({
												...previous,
												reviewNotes: event.target.value,
											}))
										}
										placeholder="Comentarios internos..."
										rows={2}
										value={form.reviewNotes ?? ''}
									/>
								</label>
							</div>
						</>
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
						disabled={
							isPending || (!readOnlyFields && accountSelectDisabled)
						}
						type="submit"
					>
						{isPending
							? 'Guardando...'
							: isReview
								? 'Guardar revisión'
								: mode === 'create'
									? 'Crear solicitud'
									: 'Guardar cambios'}
					</button>
				</div>
			</form>
		</div>
	);
}
