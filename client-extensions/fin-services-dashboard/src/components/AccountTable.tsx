import { formatAccountType, type Account } from '../api/accounts';
import { useAccountCardMetadata } from '../hooks/useAccountCardMetadata';
import { formatCardMetaLine, maskCardNumber } from '../utils/cardDisplay';
import { formatCurrency } from '../utils/presentation';

import { AccountPagination } from './AccountPagination';
import { CardSchemeBadge, mergeCardDisplay } from './CardSchemeBadge';
import { ConfirmDeleteDialog } from './ConfirmDeleteDialog';

interface ConfirmDialogProps {
	account: Account | null;
	isPending: boolean;
	onClose: () => void;
	onConfirm: () => void;
	open: boolean;
	serverError?: string | null;
}

export function ConfirmDialog({
	account,
	isPending,
	onClose,
	onConfirm,
	open,
	serverError,
}: ConfirmDialogProps) {
	return (
		<ConfirmDeleteDialog
			isPending={isPending}
			onClose={onClose}
			onConfirm={onConfirm}
			open={open && Boolean(account)}
			serverError={serverError}
			title="Eliminar cuenta"
		>
			{account && (
				<p>
					¿Confirmas que deseas eliminar la cuenta{' '}
					<span className="nexus-text-accent font-mono font-semibold">
						{maskCardNumber(account.accountNumber)}
					</span>{' '}
					({account.accountName})? Esta acción no se puede deshacer.
				</p>
			)}
		</ConfirmDeleteDialog>
	);
}

interface AccountTableProps {
	accounts: Account[];
	canDelete?: boolean;
	canEdit?: boolean;
	hasFilters: boolean;
	isFetching: boolean;
	isLoading: boolean;
	lastPage: number;
	onDelete: (account: Account) => void;
	onEdit: (account: Account) => void;
	onPageChange: (page: number) => void;
	page: number;
	pageSize: number;
	showAssignedOwner?: boolean;
	totalCount: number;
}

function AccountCardCell({
	account,
	cardMeta,
}: {
	account: Account;
	cardMeta: ReturnType<typeof mergeCardDisplay>;
}) {
	const metaLine = formatCardMetaLine({
		bankName: cardMeta.bankName,
		countryName: cardMeta.countryName,
	});

	return (
		<div className="nexus-account-card-cell">
			<div className="flex flex-wrap items-center gap-2">
				<CardSchemeBadge
					brand={cardMeta.brand}
					scheme={cardMeta.scheme}
				/>
				<span className="nexus-badge-inactive inline-flex rounded-full px-2.5 py-1 text-xs font-semibold">
					{formatAccountType(account.accountType)}
				</span>
			</div>
			<p className="nexus-account-card-number mt-2 font-mono text-base font-semibold tracking-wide">
				{maskCardNumber(account.accountNumber)}
			</p>
			{metaLine && (
				<p className="nexus-text-muted mt-1 text-xs">{metaLine}</p>
			)}
		</div>
	);
}

export function AccountTable({
	accounts,
	canDelete = false,
	canEdit = false,
	hasFilters,
	isFetching,
	isLoading,
	lastPage,
	onDelete,
	onEdit,
	onPageChange,
	page,
	pageSize,
	showAssignedOwner = false,
	totalCount,
}: AccountTableProps) {
	const cardMetadata = useAccountCardMetadata(accounts);

	const emptyMessage = hasFilters
		? 'No se encontraron cuentas con los filtros aplicados.'
		: canEdit
			? 'No hay cuentas registradas en este sitio. Usa "Nueva cuenta" para crear la primera.'
			: 'No hay cuentas registradas en este sitio.';

	const showActions = canEdit || canDelete;
	const columnCount =
		4 + (showAssignedOwner ? 1 : 0) + (showActions ? 1 : 0);

	return (
		<section className="nexus-card overflow-hidden">
			<div className="overflow-x-auto">
				<table className="nexus-account-table min-w-full text-left text-sm">
					<thead className="nexus-table-head">
						<tr>
							<th className="nexus-label px-4 py-3 font-semibold">
								Tarjeta / Red
							</th>
							<th className="nexus-label px-4 py-3 font-semibold">
								Titular
							</th>
							{showAssignedOwner && (
								<th className="nexus-label px-4 py-3 font-semibold">
									Usuario B2B
								</th>
							)}
							<th className="nexus-label px-4 py-3 font-semibold">Saldo</th>
							<th className="nexus-label px-4 py-3 font-semibold">Estado</th>
							{showActions && (
							<th className="nexus-label px-4 py-3 text-right font-semibold">
								Acciones
							</th>
							)}
						</tr>
					</thead>
					<tbody className="nexus-table-body divide-y divide-slate-200">
						{accounts.length === 0 && !isLoading ? (
							<tr>
								<td
									className="nexus-text-muted px-4 py-10 text-center"
									colSpan={columnCount}
								>
									{emptyMessage}
								</td>
							</tr>
						) : (
							accounts.map((account) => {
								const cardMeta = mergeCardDisplay(
									account,
									account.accountId
										? cardMetadata.get(account.accountId)
										: undefined,
								);

								return (
									<tr
										className="nexus-table-row align-top"
										key={account.accountId ?? account.uuid}
									>
										<td className="px-4 py-4 sm:px-5">
											<AccountCardCell
												account={account}
												cardMeta={cardMeta}
											/>
										</td>
										<td className="px-4 py-4 sm:px-5">
											<p className="nexus-title font-semibold leading-snug">
												{account.accountName}
											</p>
											<p className="nexus-text-muted mt-1.5 text-xs">
												ID #{account.accountId ?? '—'}
											</p>
										</td>
										{showAssignedOwner && (
											<td className="px-4 py-4">
												<p className="nexus-title font-medium">
													{account.ownerUserName?.trim() || 'Sin asignar'}
												</p>
												{account.ownerUserId != null && (
													<p className="nexus-text-muted mt-1 text-xs">
														User ID #{account.ownerUserId}
													</p>
												)}
											</td>
										)}
										<td className="px-4 py-4">
											<p className="nexus-title text-lg font-bold">
												{formatCurrency(account.balance)}
											</p>
											<p className="nexus-text-muted mt-1 text-xs">COP</p>
										</td>
										<td className="px-4 py-4">
											<span
												className={
													account.status === 1
														? 'nexus-badge-active inline-flex rounded-full px-2.5 py-1 text-xs font-semibold'
														: 'nexus-badge-inactive inline-flex rounded-full px-2.5 py-1 text-xs font-semibold'
												}
											>
												{account.status === 1 ? 'Activa' : 'Inactiva'}
											</span>
										</td>
										{showActions && (
										<td className="px-4 py-4">
											<div className="flex justify-end gap-2">
												{canEdit && (
												<button
													className="nexus-btn-ghost"
													onClick={() => onEdit(account)}
													type="button"
												>
													Editar
												</button>
												)}
												{canDelete && (
												<button
													className="nexus-btn-ghost nexus-btn-ghost-danger"
													onClick={() => onDelete(account)}
													type="button"
												>
													Eliminar
												</button>
												)}
											</div>
										</td>
										)}
									</tr>
								);
							})
						)}
					</tbody>
				</table>
			</div>

			{isFetching && !isLoading && (
				<p className="nexus-subtitle border-t border-slate-200 px-4 py-2 text-sm">
					Actualizando resultados...
				</p>
			)}

			<AccountPagination
				isLoading={isLoading || isFetching}
				lastPage={lastPage}
				onPageChange={onPageChange}
				page={page}
				pageSize={pageSize}
				totalCount={totalCount}
			/>
		</section>
	);
}
