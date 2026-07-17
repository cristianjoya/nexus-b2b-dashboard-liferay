import { useEffect, useMemo, useState } from 'react';

import {
	DEFAULT_PAGE_SIZE,
	emptyTransactionListResult,
	hasActiveTransactionFilters,
	type Transaction,
	type TransactionInput,
} from '../api/transactions';
import { getUnauthenticatedError, isUserSignedIn } from '../api/apiError';
import type { ResourcePermissions } from '../api/permissions';
import {
	useCreateTransaction,
	useDeleteTransaction,
	useTransactions,
	useUpdateTransaction,
} from '../hooks/useTransactions';
import { formatAccountLabel, useAccountOptions } from '../hooks/useAccountOptions';
import { getErrorPresentation } from '../utils/presentation';

import { AccessMessage } from './AccessMessage';
import { ConfirmDeleteDialog } from './ConfirmDeleteDialog';
import { SuccessToast } from './SuccessToast';
import { TransactionFormModal } from './TransactionFormModal';
import { TransactionListToolbar } from './TransactionListToolbar';
import { TransactionTable } from './TransactionTable';

const SEARCH_DEBOUNCE_MS = 300;

interface TransactionsTabProps {
	canFilterByOwner?: boolean;
	permissions: ResourcePermissions;
}

export function TransactionsTab({
	canFilterByOwner = false,
	permissions,
}: TransactionsTabProps) {
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
	const [transactionType, setTransactionType] = useState('');
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
			transactionType: transactionType || undefined,
		}),
		[ownerUserId, page, search, status, transactionType],
	);

	const {
		data: transactionList = emptyTransactionListResult(listParams),
		error,
		isFetching,
		isLoading,
	} = useTransactions(listParams);

	const createTransaction = useCreateTransaction();
	const updateTransaction = useUpdateTransaction();
	const deleteTransaction = useDeleteTransaction();

	const [formMode, setFormMode] = useState<'create' | 'edit'>('create');
	const [formOpen, setFormOpen] = useState(false);
	const [editingTransaction, setEditingTransaction] =
		useState<Transaction | null>(null);
	const [deletingTransaction, setDeletingTransaction] =
		useState<Transaction | null>(null);
	const [successMessage, setSuccessMessage] = useState<string | null>(null);

	useEffect(() => {
		const timer = window.setTimeout(() => {
			setSearch(searchInput);
			setPage(1);
		}, SEARCH_DEBOUNCE_MS);

		return () => window.clearTimeout(timer);
	}, [searchInput]);

	useEffect(() => {
		if (page > transactionList.lastPage && transactionList.lastPage > 0) {
			setPage(transactionList.lastPage);
		}
	}, [page, transactionList.lastPage]);

	const loadError = !signedIn
		? getErrorPresentation(getUnauthenticatedError())
		: error
			? getErrorPresentation(error)
			: null;
	const canView = signedIn && permissions.view && !loadError;
	const canAdd = permissions.add;
	const canUpdate = permissions.update;
	const canDelete = permissions.delete;
	const filtersActive = hasActiveTransactionFilters(listParams);

	function clearFilters() {
		setSearchInput('');
		setSearch('');
		setTransactionType('');
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

	async function handleFormSubmit(input: TransactionInput) {
		try {
			if (formMode === 'create') {
				await createTransaction.mutateAsync(input);
				setSuccessMessage('Transacción registrada correctamente.');
				setPage(1);
			}
			else if (editingTransaction?.transactionId != null) {
				await updateTransaction.mutateAsync({
					transaction: input,
					transactionId: editingTransaction.transactionId,
				});
				setSuccessMessage('Transacción actualizada correctamente.');
			}
			else {
				return;
			}

			setFormOpen(false);
			setEditingTransaction(null);
		}
		catch {
			// Error en modal
		}
	}

	async function handleDeleteConfirm() {
		if (deletingTransaction?.transactionId == null) {
			return;
		}

		try {
			await deleteTransaction.mutateAsync(deletingTransaction.transactionId);
			setDeletingTransaction(null);
			setSuccessMessage('Transacción eliminada correctamente.');
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
								setEditingTransaction(null);
								setFormOpen(true);
							}}
							type="button"
						>
							+ Nueva transacción
						</button>
					</div>
					)}

					<TransactionListToolbar
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
						onTransactionTypeChange={(value) => {
							setTransactionType(value);
							setPage(1);
						}}
						ownerUserId={ownerUserId}
						ownerUserLabel={ownerUserLabel}
						search={searchInput}
						status={status}
						totalCount={transactionList.totalCount}
						transactionType={transactionType}
					/>

					{isLoading && signedIn ? (
						<div className="nexus-card nexus-subtitle p-6">
							Cargando transacciones...
						</div>
					) : (
						<TransactionTable
							canDelete={canDelete}
							canEdit={canUpdate}
							hasFilters={filtersActive}
							isFetching={isFetching}
							isLoading={isLoading}
							lastPage={transactionList.lastPage}
							onDelete={setDeletingTransaction}
							onEdit={(transaction) => {
								setFormMode('edit');
								setEditingTransaction(transaction);
								setFormOpen(true);
							}}
							onPageChange={setPage}
							page={transactionList.page}
							pageSize={transactionList.pageSize}
							resolveAccountLabel={resolveAccountLabel}
							resolveOwnerUserName={resolveOwnerUserName}
							totalCount={transactionList.totalCount}
							transactions={transactionList.items}
						/>
					)}
				</>
			)}

			<TransactionFormModal
				isPending={createTransaction.isPending || updateTransaction.isPending}
				mode={formMode}
				onClose={() => {
					if (createTransaction.isPending || updateTransaction.isPending) {
						return;
					}

					setFormOpen(false);
					setEditingTransaction(null);
				}}
				onSubmit={handleFormSubmit}
				open={
					formOpen &&
					((formMode === 'create' && canAdd) ||
						(formMode === 'edit' && canUpdate))
				}
				serverError={
					formMode === 'create' && createTransaction.error
						? getErrorPresentation(createTransaction.error).message
						: updateTransaction.error
							? getErrorPresentation(updateTransaction.error).message
							: null
				}
				transaction={editingTransaction}
			/>

			<ConfirmDeleteDialog
				isPending={deleteTransaction.isPending}
				onClose={() => setDeletingTransaction(null)}
				onConfirm={handleDeleteConfirm}
				open={Boolean(deletingTransaction) && canDelete}
				title="Eliminar transacción"
			>
				{deletingTransaction && (
					<p>
						¿Confirmas eliminar este movimiento de{' '}
						<span className="nexus-title font-semibold">
							{deletingTransaction.description?.trim() || 'sin descripción'}
						</span>
						? El saldo de la cuenta se revertirá.
					</p>
				)}
			</ConfirmDeleteDialog>
		</>
	);
}
