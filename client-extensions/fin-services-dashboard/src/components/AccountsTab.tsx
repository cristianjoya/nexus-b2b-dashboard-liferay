import { useEffect, useMemo, useState } from 'react';

import {
	DEFAULT_PAGE_SIZE,
	emptyAccountListResult,
	hasActiveFilters,
	type Account,
	type AccountInput,
} from '../api/accounts';
import { getUnauthenticatedError, isUserSignedIn } from '../api/apiError';
import type { ResourcePermissions } from '../api/permissions';
import {
	useAccounts,
	useCreateAccount,
	useDeleteAccount,
	useUpdateAccount,
} from '../hooks/useAccounts';
import { getErrorPresentation } from '../utils/presentation';

import { AccessMessage } from './AccessMessage';
import { AccountFormModal } from './AccountFormModal';
import {
	AccountListToolbar,
	buildListParams,
} from './AccountListToolbar';
import { AccountTable, ConfirmDialog } from './AccountTable';
import { SuccessToast } from './SuccessToast';

type FormMode = 'create' | 'edit';

const SEARCH_DEBOUNCE_MS = 300;

interface AccountsTabProps {
	canAssignOwner?: boolean;
	permissions: ResourcePermissions;
}

export function AccountsTab({
	canAssignOwner = false,
	permissions,
}: AccountsTabProps) {
	const signedIn = isUserSignedIn();

	const [searchInput, setSearchInput] = useState('');
	const [search, setSearch] = useState('');
	const [accountType, setAccountType] = useState('');
	const [status, setStatus] = useState('');
	const [ownerUserId, setOwnerUserId] = useState<number | null>(null);
	const [ownerUserLabel, setOwnerUserLabel] = useState('');
	const [page, setPage] = useState(1);

	const listParams = useMemo(
		() =>
			buildListParams({
				accountType,
				ownerUserId,
				page,
				pageSize: DEFAULT_PAGE_SIZE,
				search,
				status,
			}),
		[accountType, ownerUserId, page, search, status],
	);

	const {
		data: accountList = emptyAccountListResult(listParams),
		error,
		isFetching,
		isLoading,
	} = useAccounts(listParams);

	const createAccount = useCreateAccount();
	const updateAccount = useUpdateAccount();
	const deleteAccount = useDeleteAccount();

	const [formMode, setFormMode] = useState<FormMode>('create');
	const [formOpen, setFormOpen] = useState(false);
	const [editingAccount, setEditingAccount] = useState<Account | null>(null);
	const [deletingAccount, setDeletingAccount] = useState<Account | null>(null);
	const [successMessage, setSuccessMessage] = useState<string | null>(null);

	useEffect(() => {
		const timer = window.setTimeout(() => {
			setSearch(searchInput);
			setPage(1);
		}, SEARCH_DEBOUNCE_MS);

		return () => window.clearTimeout(timer);
	}, [searchInput]);

	useEffect(() => {
		if (page > accountList.lastPage && accountList.lastPage > 0) {
			setPage(accountList.lastPage);
		}
	}, [accountList.lastPage, page]);

	const loadError = !signedIn
		? getErrorPresentation(getUnauthenticatedError())
		: error
			? getErrorPresentation(error)
			: null;
	const formMutationError =
		formMode === 'create' ? createAccount.error : updateAccount.error;
	const formServerError = formMutationError
		? getErrorPresentation(formMutationError).message
		: null;
	const deleteServerError = deleteAccount.error
		? getErrorPresentation(deleteAccount.error).message
		: null;
	const canView = signedIn && permissions.view && !loadError;
	const canAdd = permissions.add;
	const canUpdate = permissions.update;
	const canDelete = permissions.delete;
	const isFormPending = createAccount.isPending || updateAccount.isPending;
	const isDeletePending = deleteAccount.isPending;
	const filtersActive = hasActiveFilters(listParams);

	function clearMutationErrors() {
		createAccount.reset();
		updateAccount.reset();
		deleteAccount.reset();
	}

	function clearFilters() {
		setSearchInput('');
		setSearch('');
		setAccountType('');
		setStatus('');
		setOwnerUserId(null);
		setOwnerUserLabel('');
		setPage(1);
	}

	function openCreateForm() {
		clearMutationErrors();
		setFormMode('create');
		setEditingAccount(null);
		setFormOpen(true);
		setSuccessMessage(null);
	}

	function openEditForm(account: Account) {
		clearMutationErrors();
		setFormMode('edit');
		setEditingAccount(account);
		setFormOpen(true);
		setSuccessMessage(null);
	}

	function closeForm() {
		if (isFormPending) {
			return;
		}

		setFormOpen(false);
		setEditingAccount(null);
		createAccount.reset();
		updateAccount.reset();
	}

	function openDeleteDialog(account: Account) {
		clearMutationErrors();
		setDeletingAccount(account);
		setSuccessMessage(null);
	}

	function closeDeleteDialog() {
		if (isDeletePending) {
			return;
		}

		setDeletingAccount(null);
		deleteAccount.reset();
	}

	async function handleFormSubmit(input: AccountInput) {
		try {
			if (formMode === 'create') {
				await createAccount.mutateAsync(input);
				setSuccessMessage('Cuenta creada correctamente.');
				setPage(1);
			}
			else if (editingAccount?.accountId != null) {
				await updateAccount.mutateAsync({
					account: input,
					accountId: editingAccount.accountId,
				});
				setSuccessMessage('Cuenta actualizada correctamente.');
			}
			else {
				return;
			}

			setFormOpen(false);
			setEditingAccount(null);
			createAccount.reset();
			updateAccount.reset();
		}
		catch {
			// El error se muestra dentro del modal.
		}
	}

	async function handleDeleteConfirm() {
		if (deletingAccount?.accountId == null) {
			return;
		}

		try {
			await deleteAccount.mutateAsync(deletingAccount.accountId);
			setDeletingAccount(null);
			deleteAccount.reset();
			setSuccessMessage('Cuenta eliminada correctamente.');
		}
		catch {
			// El error se muestra dentro del diálogo.
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
							disabled={isLoading}
							onClick={openCreateForm}
							type="button"
						>
							+ Nueva cuenta
						</button>
					</div>
					)}

					<AccountListToolbar
						accountType={accountType}
						canFilterByOwner={canAssignOwner}
						isLoading={isLoading || isFetching}
						onAccountTypeChange={(value) => {
							setAccountType(value);
							setPage(1);
						}}
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
						totalCount={accountList.totalCount}
					/>

					{isLoading && signedIn ? (
						<div className="nexus-card nexus-subtitle p-6">
							Cargando cuentas...
						</div>
					) : (
						<AccountTable
							accounts={accountList.items}
							canDelete={canDelete}
							canEdit={canUpdate}
							hasFilters={filtersActive}
							isFetching={isFetching}
							isLoading={isLoading}
							lastPage={accountList.lastPage}
							onDelete={openDeleteDialog}
							onEdit={openEditForm}
							onPageChange={setPage}
							page={accountList.page}
							pageSize={accountList.pageSize}
							showAssignedOwner={canAssignOwner}
							totalCount={accountList.totalCount}
						/>
					)}
				</>
			)}

			<AccountFormModal
				account={editingAccount}
				canAssignOwner={canAssignOwner}
				isPending={isFormPending}
				mode={formMode}
				onClose={closeForm}
				onSubmit={handleFormSubmit}
				open={
					formOpen &&
					((formMode === 'create' && canAdd) ||
						(formMode === 'edit' && canUpdate))
				}
				serverError={formServerError}
			/>

			<ConfirmDialog
				account={deletingAccount}
				isPending={isDeletePending}
				onClose={closeDeleteDialog}
				onConfirm={handleDeleteConfirm}
				open={Boolean(deletingAccount) && canDelete}
				serverError={deleteServerError}
			/>
		</>
	);
}
