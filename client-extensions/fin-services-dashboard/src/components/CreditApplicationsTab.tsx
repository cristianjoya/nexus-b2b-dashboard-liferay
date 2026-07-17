import { useEffect, useMemo, useState } from 'react';

import {
	emptyCreditApplicationListResult,
	hasActiveCreditFilters,
	type CreditApplication,
	type CreditApplicationInput,
	DEFAULT_PAGE_SIZE,
} from '../api/creditApplications';
import { getUnauthenticatedError, isUserSignedIn } from '../api/apiError';
import type { ResourcePermissions } from '../api/permissions';
import {
	useCreateCreditApplication,
	useCreditApplications,
	useDeleteCreditApplication,
	useUpdateCreditApplication,
} from '../hooks/useCreditApplications';
import { formatAccountLabel, useAccountOptions } from '../hooks/useAccountOptions';
import { getErrorPresentation } from '../utils/presentation';

import { AccessMessage } from './AccessMessage';
import { ConfirmDeleteDialog } from './ConfirmDeleteDialog';
import { CreditApplicationFormModal } from './CreditApplicationFormModal';
import { CreditApplicationTable } from './CreditApplicationTable';
import { CreditListToolbar } from './CreditListToolbar';
import { SuccessToast } from './SuccessToast';

const SEARCH_DEBOUNCE_MS = 300;

interface CreditApplicationsTabProps {
	canFilterByOwner?: boolean;
	permissions: ResourcePermissions;
}

export function CreditApplicationsTab({
	canFilterByOwner = false,
	permissions,
}: CreditApplicationsTabProps) {
	const signedIn = isUserSignedIn();
	const { data: accountOptions } = useAccountOptions();
	const accountMap = useMemo(() => {
		const map = new Map<
			number,
			{ label: string; ownerUserName?: string }
		>();

		for (const account of accountOptions?.items ?? []) {
			if (account.accountId != null) {
				map.set(account.accountId, {
					label: formatAccountLabel(account),
					ownerUserName: account.ownerUserName,
				});
			}
		}

		return map;
	}, [accountOptions?.items]);

	const [searchInput, setSearchInput] = useState('');
	const [search, setSearch] = useState('');
	const [status, setStatus] = useState('');
	const [ownerUserId, setOwnerUserId] = useState<number | null>(null);
	const [ownerUserLabel, setOwnerUserLabel] = useState('');
	const [page, setPage] = useState(1);

	const listParams = useMemo(
		() => ({
			ownerUserId: ownerUserId ?? undefined,
			page,
			pageSize: DEFAULT_PAGE_SIZE,
			search: search || undefined,
			status: status === '' ? null : Number(status),
		}),
		[ownerUserId, page, search, status],
	);

	const {
		data: creditList = emptyCreditApplicationListResult(listParams),
		error,
		isFetching,
		isLoading,
	} = useCreditApplications(listParams);

	const createCreditApplication = useCreateCreditApplication();
	const updateCreditApplication = useUpdateCreditApplication();
	const deleteCreditApplication = useDeleteCreditApplication();

	const [formMode, setFormMode] = useState<'create' | 'edit' | 'review'>(
		'create',
	);
	const [formOpen, setFormOpen] = useState(false);
	const [editingCredit, setEditingCredit] = useState<CreditApplication | null>(
		null,
	);
	const [deletingCredit, setDeletingCredit] =
		useState<CreditApplication | null>(null);
	const [successMessage, setSuccessMessage] = useState<string | null>(null);

	useEffect(() => {
		const timer = window.setTimeout(() => {
			setSearch(searchInput);
			setPage(1);
		}, SEARCH_DEBOUNCE_MS);

		return () => window.clearTimeout(timer);
	}, [searchInput]);

	useEffect(() => {
		if (page > creditList.lastPage && creditList.lastPage > 0) {
			setPage(creditList.lastPage);
		}
	}, [creditList.lastPage, page]);

	const loadError = !signedIn
		? getErrorPresentation(getUnauthenticatedError())
		: error
			? getErrorPresentation(error)
			: null;
	const canView = signedIn && permissions.view && !loadError;
	const canAdd = permissions.add;
	const canUpdate = permissions.update;
	const canDelete = permissions.delete;
	const filtersActive = hasActiveCreditFilters(listParams);

	function clearFilters() {
		setSearchInput('');
		setSearch('');
		setStatus('');
		setOwnerUserId(null);
		setOwnerUserLabel('');
		setPage(1);
	}

	function resolveAccountLabel(accountId?: number) {
		if (accountId == null) {
			return '-';
		}

		return accountMap.get(accountId)?.label ?? `Cuenta #${accountId}`;
	}

	function resolveOwnerUserName(accountId?: number) {
		if (accountId == null) {
			return undefined;
		}

		return accountMap.get(accountId)?.ownerUserName;
	}

	async function handleFormSubmit(input: CreditApplicationInput) {
		try {
			if (formMode === 'create') {
				await createCreditApplication.mutateAsync(input);
				setSuccessMessage('Solicitud de crédito creada correctamente.');
				setPage(1);
			}
			else if (editingCredit?.creditAppId != null) {
				await updateCreditApplication.mutateAsync({
					creditApplication: input,
					creditAppId: editingCredit.creditAppId,
				});
				setSuccessMessage(
					input.status === 1
						? 'Solicitud aprobada. El monto se acreditó a la cuenta.'
						: 'Solicitud actualizada correctamente.',
				);
			}
			else {
				return;
			}

			setFormOpen(false);
			setEditingCredit(null);
		}
		catch {
			// Error en modal
		}
	}

	async function handleDeleteConfirm() {
		if (deletingCredit?.creditAppId == null) {
			return;
		}

		try {
			await deleteCreditApplication.mutateAsync(deletingCredit.creditAppId);
			setDeletingCredit(null);
			setSuccessMessage('Solicitud eliminada correctamente.');
		}
		catch {
			// Error en diálogo
		}
	}

	return (
		<>
			{successMessage && (
				<SuccessToast
					message={successMessage}
					onDismiss={() => setSuccessMessage(null)}
				/>
			)}

			{loadError && (
				<AccessMessage
					kind={loadError.kind}
					message={loadError.message}
					title={loadError.title}
				/>
			)}

			{canView && (
				<>
					{canAdd && (
					<div className="flex justify-end">
						<button
							className="nexus-btn-primary"
							onClick={() => {
								setFormMode('create');
								setEditingCredit(null);
								setFormOpen(true);
							}}
							type="button"
						>
							+ Nueva solicitud
						</button>
					</div>
					)}

					<CreditListToolbar
						canFilterByOwner={canFilterByOwner}
						isLoading={isLoading || isFetching}
						onClearFilters={clearFilters}
						onOwnerChange={(user) => {
							setOwnerUserId(user?.userId ?? null);
							setOwnerUserLabel(
								user
									? `${user.fullName?.trim() || user.emailAddress || ''}`.trim()
									: '',
							);
							setPage(1);
						}}
						onSearchChange={setSearchInput}
						onStatusChange={(value) => {
							setStatus(value);
							setPage(1);
						}}
						ownerUserId={ownerUserId}
						ownerUserLabel={ownerUserLabel}
						search={searchInput}
						status={status}
						totalCount={creditList.totalCount}
					/>

					{isLoading && signedIn ? (
						<div className="nexus-card nexus-subtitle p-6">
							Cargando solicitudes...
						</div>
					) : (
						<CreditApplicationTable
							canDelete={canDelete}
							canReview={canUpdate}
							creditApplications={creditList.items}
							hasFilters={filtersActive}
							isFetching={isFetching}
							isLoading={isLoading}
							lastPage={creditList.lastPage}
							onDelete={setDeletingCredit}
							onReview={(credit) => {
								setFormMode('review');
								setEditingCredit(credit);
								setFormOpen(true);
							}}
							onPageChange={setPage}
							page={creditList.page}
							pageSize={creditList.pageSize}
							resolveAccountLabel={resolveAccountLabel}
							resolveOwnerUserName={resolveOwnerUserName}
							totalCount={creditList.totalCount}
						/>
					)}
				</>
			)}

			<CreditApplicationFormModal
				creditApplication={editingCredit}
				isPending={
					createCreditApplication.isPending ||
					updateCreditApplication.isPending
				}
				mode={formMode}
				onClose={() => {
					if (
						createCreditApplication.isPending ||
						updateCreditApplication.isPending
					) {
						return;
					}

					setFormOpen(false);
					setEditingCredit(null);
				}}
				onSubmit={handleFormSubmit}
				open={
					formOpen &&
					((formMode === 'create' && canAdd) ||
						((formMode === 'edit' || formMode === 'review') && canUpdate))
				}
				serverError={
					createCreditApplication.error
						? getErrorPresentation(createCreditApplication.error).message
						: updateCreditApplication.error
							? getErrorPresentation(updateCreditApplication.error).message
							: null
				}
			/>

			<ConfirmDeleteDialog
				isPending={deleteCreditApplication.isPending}
				onClose={() => setDeletingCredit(null)}
				onConfirm={handleDeleteConfirm}
				open={Boolean(deletingCredit) && canDelete}
				title="Eliminar solicitud"
			>
				{deletingCredit && (
					<p>
						¿Confirmas eliminar la solicitud de {deletingCredit.applicantName}?
					</p>
				)}
			</ConfirmDeleteDialog>
		</>
	);
}
