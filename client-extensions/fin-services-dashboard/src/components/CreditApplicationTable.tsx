import type { CreditApplication } from '../api/creditApplications';
import { formatCurrency, formatRecordDate } from '../utils/presentation';

import { AccountPagination } from './AccountPagination';
import { getCreditStatusBadge, StatusBadge } from './StatusBadge';

interface CreditApplicationTableProps {
	canDelete?: boolean;
	canReview?: boolean;
	hasFilters: boolean;
	isFetching: boolean;
	isLoading: boolean;
	lastPage: number;
	onDelete: (credit: CreditApplication) => void;
	onReview: (credit: CreditApplication) => void;
	onPageChange: (page: number) => void;
	page: number;
	pageSize: number;
	resolveAccountLabel: (accountId?: number) => string;
	resolveOwnerUserName?: (accountId?: number) => string | undefined;
	totalCount: number;
	creditApplications: CreditApplication[];
}

function ApplicantAvatar({ name }: { name?: string }) {
	const initial = (name?.trim()?.charAt(0) ?? '?').toUpperCase();

	return <span className="nexus-avatar-chip">{initial}</span>;
}

export function CreditApplicationTable({
	canDelete = false,
	canReview = false,
	hasFilters,
	isFetching,
	isLoading,
	lastPage,
	onDelete,
	onReview,
	onPageChange,
	page,
	pageSize,
	resolveAccountLabel,
	resolveOwnerUserName,
	totalCount,
	creditApplications,
}: CreditApplicationTableProps) {
	const showActions = canReview || canDelete;
	const columnCount = 5 + (showActions ? 1 : 0);

	const emptyMessage = hasFilters
		? 'No se encontraron solicitudes con los filtros aplicados.'
		: 'Aún no hay solicitudes de crédito. Usa "Nueva solicitud" para crear la primera.';

	return (
		<section className="nexus-card overflow-hidden">
			<div className="overflow-x-auto">
				<table className="nexus-data-table min-w-full text-left text-sm">
					<thead className="nexus-table-head">
						<tr>
							<th className="nexus-label px-4 py-3 font-semibold">
								Solicitud
							</th>
							<th className="nexus-label px-4 py-3 font-semibold">Cuenta</th>
							<th className="nexus-label px-4 py-3 font-semibold">
								Monto solicitado
							</th>
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
						{creditApplications.length === 0 && !isLoading ? (
							<tr>
								<td
									className="nexus-empty-state px-4 py-12 text-center"
									colSpan={columnCount}
								>
									<p className="nexus-title text-base font-semibold">
										Sin solicitudes
									</p>
									<p className="nexus-subtitle mt-2 text-sm">{emptyMessage}</p>
								</td>
							</tr>
						) : (
							creditApplications.map((credit) => {
								const statusBadge = getCreditStatusBadge(credit.status);

								return (
									<tr
										className="nexus-table-row align-top"
										key={credit.creditAppId ?? credit.uuid}
									>
										<td className="px-4 py-4">
											<div className="nexus-record-cell flex gap-3">
												<ApplicantAvatar name={credit.applicantName} />
												<div className="min-w-0">
													<p className="nexus-title font-semibold">
														{credit.applicantName?.trim() || 'Sin nombre'}
													</p>
													<p className="nexus-subtitle mt-1 line-clamp-2 text-sm leading-5">
														{credit.purpose?.trim() ||
															'Sin propósito indicado'}
													</p>
													{credit.reviewNotes?.trim() && (
														<p className="nexus-text-muted mt-2 text-xs">
															Nota: {credit.reviewNotes}
														</p>
													)}
													<p className="nexus-text-muted mt-1 text-xs">
														ID #{credit.creditAppId ?? '—'}
													</p>
												</div>
											</div>
										</td>
										<td className="px-4 py-4">
											<div className="space-y-1">
												<span className="nexus-account-chip inline-flex max-w-[14rem] truncate">
													{resolveAccountLabel(credit.accountId)}
												</span>
												<p className="nexus-text-muted text-xs">
													Usuario:{' '}
													{resolveOwnerUserName?.(credit.accountId)?.trim() ||
														'Sin asignar'}
												</p>
											</div>
										</td>
										<td className="px-4 py-4">
											<p className="nexus-title text-lg font-bold">
												{formatCurrency(credit.requestedAmount)}
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
												{formatRecordDate(credit.createDate)}
											</p>
											{credit.modifiedDate &&
												credit.modifiedDate !== credit.createDate && (
													<p className="nexus-text-muted mt-1 text-xs">
														Actualizado{' '}
														{formatRecordDate(credit.modifiedDate)}
													</p>
												)}
										</td>
										{showActions && (
											<td className="px-4 py-4">
												<div className="flex justify-end gap-2">
													{credit.status === 0 && canReview && (
														<button
															className="nexus-btn-primary !px-3 !py-1.5 text-xs"
															onClick={() => onReview(credit)}
															type="button"
														>
															Revisar
														</button>
													)}
													{canDelete && (
														<button
															className="nexus-btn-ghost nexus-btn-ghost-danger"
															onClick={() => onDelete(credit)}
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
