import type { Transaction } from '../api/transactions';
import { formatCurrency, formatRecordDate } from '../utils/presentation';

import { AccountPagination } from './AccountPagination';
import {
	getTransactionStatusBadge,
	getTransactionTypeBadge,
	isDebitTransactionType,
	StatusBadge,
} from './StatusBadge';

interface TransactionTableProps {
	canDelete?: boolean;
	canEdit?: boolean;
	hasFilters: boolean;
	isFetching: boolean;
	isLoading: boolean;
	lastPage: number;
	onDelete: (transaction: Transaction) => void;
	onEdit: (transaction: Transaction) => void;
	onPageChange: (page: number) => void;
	page: number;
	pageSize: number;
	resolveAccountLabel: (accountId?: number) => string;
	resolveOwnerUserName?: (accountId?: number) => string | undefined;
	totalCount: number;
	transactions: Transaction[];
}

function TransactionIcon({ transactionType }: { transactionType?: string }) {
	const isDebit = isDebitTransactionType(transactionType);
	const symbol = transactionType === 'DEPOSIT' ? '↓' : '↑';

	return (
		<span
			aria-hidden
			className={
				isDebit
					? 'nexus-record-icon nexus-record-icon-debit'
					: 'nexus-record-icon nexus-record-icon-credit'
			}
		>
			{symbol}
		</span>
	);
}

export function TransactionTable({
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
	resolveAccountLabel,
	resolveOwnerUserName,
	totalCount,
	transactions,
}: TransactionTableProps) {
	const showActions = canEdit || canDelete;
	const columnCount = 5 + (showActions ? 1 : 0);

	const emptyMessage = hasFilters
		? 'No se encontraron transacciones con los filtros aplicados.'
		: 'Aún no hay movimientos registrados. Usa "Nueva transacción" para registrar el primero.';

	return (
		<section className="nexus-card overflow-hidden">
			<div className="overflow-x-auto">
				<table className="nexus-data-table min-w-full text-left text-sm">
					<thead className="nexus-table-head">
						<tr>
							<th className="nexus-label px-4 py-3 font-semibold">
								Movimiento
							</th>
							<th className="nexus-label px-4 py-3 font-semibold">Cuenta</th>
							<th className="nexus-label px-4 py-3 font-semibold">Monto</th>
							<th className="nexus-label px-4 py-3 font-semibold">Estado</th>
							<th className="nexus-label px-4 py-3 font-semibold">Fecha</th>
							{showActions && (
								<th className="nexus-label px-4 py-3 text-right font-semibold">
									Acciones
								</th>
							)}
						</tr>
					</thead>
					<tbody className="nexus-table-body divide-y divide-slate-200">
						{transactions.length === 0 && !isLoading ? (
							<tr>
								<td
									className="nexus-empty-state px-4 py-12 text-center"
									colSpan={columnCount}
								>
									<p className="nexus-title text-base font-semibold">
										Sin transacciones
									</p>
									<p className="nexus-subtitle mt-2 text-sm">{emptyMessage}</p>
								</td>
							</tr>
						) : (
							transactions.map((transaction) => {
								const typeBadge = getTransactionTypeBadge(
									transaction.transactionType,
								);
								const statusBadge = getTransactionStatusBadge(
									transaction.status,
								);
								const isDebit = isDebitTransactionType(
									transaction.transactionType,
								);

								return (
									<tr
										className="nexus-table-row align-top"
										key={
											transaction.transactionId ?? transaction.uuid
										}
									>
										<td className="px-4 py-4">
											<div className="nexus-record-cell flex gap-3">
												<TransactionIcon
													transactionType={transaction.transactionType}
												/>
												<div className="min-w-0">
													<div className="flex flex-wrap items-center gap-2">
														<StatusBadge
															label={typeBadge.label}
															tone={typeBadge.tone}
														/>
													</div>
													<p className="nexus-title mt-2 font-semibold">
														{transaction.description?.trim() ||
															'Sin descripción'}
													</p>
													<p className="nexus-text-muted mt-1 text-xs">
														ID #{transaction.transactionId ?? '—'}
													</p>
												</div>
											</div>
										</td>
						<td className="px-4 py-4">
							<div className="space-y-1">
								<span className="nexus-account-chip inline-flex max-w-[14rem] truncate">
									{resolveAccountLabel(transaction.accountId)}
								</span>
								<p className="nexus-text-muted text-xs">
									Usuario:{' '}
									{resolveOwnerUserName?.(transaction.accountId)?.trim() ||
										'Sin asignar'}
								</p>
							</div>
						</td>
										<td className="px-4 py-4">
											<p
												className={
													isDebit
														? 'nexus-amount-negative text-lg font-bold'
														: 'nexus-amount-positive text-lg font-bold'
												}
											>
												{isDebit ? '−' : '+'}
												{formatCurrency(transaction.amount)}
											</p>
											<p className="nexus-text-muted mt-1 text-xs">COP</p>
										</td>
										<td className="px-4 py-4">
											<StatusBadge
												label={statusBadge.label}
												tone={statusBadge.tone}
											/>
										</td>
										<td className="px-4 py-4">
											<p className="nexus-title font-medium">
												{formatRecordDate(
													transaction.transactionDate ??
														transaction.createDate,
												)}
											</p>
											{transaction.modifiedDate &&
												transaction.modifiedDate !==
													transaction.createDate && (
													<p className="nexus-text-muted mt-1 text-xs">
														Actualizado{' '}
														{formatRecordDate(transaction.modifiedDate)}
													</p>
												)}
										</td>
										{showActions && (
											<td className="px-4 py-4">
												<div className="flex justify-end gap-2">
													{canEdit && (
														<button
															className="nexus-btn-ghost"
															onClick={() => onEdit(transaction)}
															type="button"
														>
															Editar
														</button>
													)}
													{canDelete && (
														<button
															className="nexus-btn-ghost nexus-btn-ghost-danger"
															onClick={() => onDelete(transaction)}
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
