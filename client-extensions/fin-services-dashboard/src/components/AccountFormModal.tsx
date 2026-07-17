import { useEffect, useState } from 'react';

import {
	ACCOUNT_TYPES,
	emptyAccountInput,
	formatBalanceText,
	parseBalanceText,
	sanitizeAccountName,
	sanitizeAccountNumber,
	sanitizeBalanceText,
	validateAccountInput,
	type Account,
	type AccountInput,
} from '../api/accounts';

import { validateCardNumber } from '../api/cardBin';
import { useCardBinLookup } from '../hooks/useCardBinLookup';
import { useLockBodyScroll } from '../hooks/useLockBodyScroll';

import { CardBinPreview } from './CardBinPreview';
import { UserTypeahead } from './UserTypeahead';

interface AccountFormModalProps {
	account?: Account | null;
	canAssignOwner?: boolean;
	isPending: boolean;
	mode: 'create' | 'edit';
	onClose: () => void;
	onSubmit: (input: AccountInput) => void;
	open: boolean;
	serverError?: string | null;
}

export function AccountFormModal({
	account,
	canAssignOwner = false,
	isPending,
	mode,
	onClose,
	onSubmit,
	open,
	serverError,
}: AccountFormModalProps) {
	const [form, setForm] = useState<AccountInput>(emptyAccountInput());
	const [balanceText, setBalanceText] = useState('');
	const [validationError, setValidationError] = useState<string | null>(null);
	const [ownerUserLabel, setOwnerUserLabel] = useState('');
	const accountDigits = sanitizeAccountNumber(form.accountNumber ?? '');
	const {
		data: cardBin,
		error: cardBinError,
		isFetching: isCardBinLoading,
	} = useCardBinLookup(form.accountNumber ?? '', open);

	useLockBodyScroll(open);

	useEffect(() => {
		if (!open) {
			return;
		}

		if (mode === 'edit' && account) {
			const nextForm = {
				accountName: sanitizeAccountName(account.accountName ?? ''),
				accountNumber: sanitizeAccountNumber(account.accountNumber ?? ''),
				accountType: account.accountType ?? 'CHECKING',
				balance: account.balance ?? 0,
				ownerUserId: account.ownerUserId,
				status: account.status ?? 1,
			};

			setForm(nextForm);
			setBalanceText(formatBalanceText(nextForm.balance));
			setOwnerUserLabel(account.ownerUserName?.trim() || '');
		}
		else {
			setForm(emptyAccountInput());
			setBalanceText('');
			setOwnerUserLabel('');
		}

		setValidationError(null);
	}, [account, mode, open]);

	if (!open) {
		return null;
	}

	function handleSubmit(event: React.FormEvent) {
		event.preventDefault();

		const normalizedInput: AccountInput = {
			...form,
			accountName: sanitizeAccountName(form.accountName ?? '').trim(),
			accountNumber: sanitizeAccountNumber(form.accountNumber ?? ''),
			balance: parseBalanceText(balanceText),
		};

		const error = validateAccountInput(normalizedInput, {
			requireOwner: canAssignOwner,
		});

		if (error) {
			setValidationError(error);

			return;
		}

		const cardError = validateCardNumber(
			normalizedInput.accountNumber ?? '',
			cardBin,
		);

		if (cardError) {
			setValidationError(cardError);

			return;
		}

		onSubmit({
			...normalizedInput,
			cardBankName: cardBin?.bankName,
			cardBrand: cardBin?.brand,
			cardCountryName: cardBin?.countryName,
			cardScheme: cardBin?.scheme,
		});
	}

	function updateField<K extends keyof AccountInput>(
		field: K,
		value: AccountInput[K],
	) {
		setForm((current) => ({ ...current, [field]: value }));
		setValidationError(null);
	}

	function handleAccountNumberChange(raw: string) {
		updateField('accountNumber', sanitizeAccountNumber(raw));
	}

	function handleAccountNameChange(raw: string) {
		updateField('accountName', sanitizeAccountName(raw));
	}

	function handleBalanceChange(raw: string) {
		const sanitized = sanitizeBalanceText(raw);

		setBalanceText(sanitized);
		updateField('balance', parseBalanceText(sanitized));
	}

	return (
		<div
			aria-labelledby="account-form-title"
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
						id="account-form-title"
					>
						{mode === 'create' ? 'Nueva cuenta' : 'Editar cuenta'}
					</h2>
					<p className="nexus-subtitle mt-1 text-sm">
						{mode === 'create'
							? 'Registra una cuenta financiera en este sitio.'
							: 'Actualiza los datos de la cuenta seleccionada.'}
					</p>
				</div>

				<div className="nexus-modal-body nexus-form-stack">
					<div className="nexus-form-field">
						<label className="block space-y-2">
							<span className="nexus-label text-sm font-medium">
								Número de cuenta
							</span>
							<input
								autoComplete="off"
								className="nexus-input font-mono"
								inputMode="numeric"
								onChange={(event) =>
									handleAccountNumberChange(event.target.value)
								}
								pattern="[0-9]*"
								placeholder="4532015112830366"
								required
								type="text"
								value={form.accountNumber}
							/>
						</label>
						<div className="nexus-form-field-preview">
							<CardBinPreview
								cardBin={cardBin}
								digits={accountDigits}
								error={cardBinError}
								isLoading={isCardBinLoading}
							/>
						</div>
					</div>

					<div className="nexus-form-field">
						<label className="block space-y-2">
							<span className="nexus-label text-sm font-medium">Nombre</span>
							<input
								autoComplete="off"
								className="nexus-input"
								onChange={(event) =>
									handleAccountNameChange(event.target.value)
								}
								placeholder="Cuenta principal"
								required
								type="text"
								value={form.accountName}
							/>
						</label>
						<span className="nexus-text-muted text-xs">
							Solo letras y espacios, sin números.
						</span>
					</div>

					{canAssignOwner && (
						<div className="nexus-form-field">
							<UserTypeahead
								disabled={isPending}
								label="Usuario B2B asignado"
								onChange={(user) => {
									updateField(
										'ownerUserId',
										user?.userId ?? undefined,
									);
									setOwnerUserLabel(
										user
											? `${user.fullName?.trim() || user.emailAddress || ''}`.trim()
											: '',
									);
									setValidationError(null);
								}}
								placeholder="Buscar por nombre o correo..."
								required={canAssignOwner}
								selectedUserId={form.ownerUserId}
								selectedUserLabel={ownerUserLabel}
							/>
							<span className="nexus-text-muted text-xs">
								Escribe al menos 2 caracteres. El usuario seleccionado será el
								único que podrá operar esta cuenta.
							</span>
						</div>
					)}

					<div className="nexus-form-field-grid">
						<label className="nexus-form-field block space-y-2">
							<span className="nexus-label text-sm font-medium">Tipo</span>
							<select
								className="nexus-select"
								onChange={(event) =>
									updateField('accountType', event.target.value)
								}
								value={form.accountType}
							>
								{ACCOUNT_TYPES.map((type) => (
									<option key={type.value} value={type.value}>
										{type.label}
									</option>
								))}
							</select>
						</label>

						<label className="nexus-form-field block space-y-2">
							<span className="nexus-label text-sm font-medium">Estado</span>
							<select
								className="nexus-select"
								onChange={(event) =>
									updateField('status', Number(event.target.value))
								}
								value={form.status ?? 1}
							>
								<option value={1}>Activa</option>
								<option value={0}>Inactiva</option>
							</select>
						</label>
					</div>

					<div className="nexus-form-field">
						<label className="block space-y-2">
							<span className="nexus-label text-sm font-medium">
								Saldo (COP)
							</span>
							<input
								autoComplete="off"
								className="nexus-input"
								inputMode="decimal"
								onChange={(event) => handleBalanceChange(event.target.value)}
								placeholder="0"
								type="text"
								value={balanceText}
							/>
						</label>
						<span className="nexus-text-muted text-xs">
							Valor numérico sin ceros a la izquierda.
						</span>
					</div>

					{validationError && (
						<p className="nexus-alert-error rounded-lg px-3 py-2 text-sm">
							{validationError}
						</p>
					)}

					{serverError && (
						<p className="nexus-alert-error rounded-lg px-3 py-2 text-sm">
							{serverError}
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
							isPending ||
							(accountDigits.length >= 6 &&
								(isCardBinLoading || !cardBin?.scheme))
						}
						type="submit"
					>
						{isPending
							? 'Guardando...'
							: mode === 'create'
								? 'Crear cuenta'
								: 'Guardar cambios'}
					</button>
				</div>
			</form>
		</div>
	);
}
