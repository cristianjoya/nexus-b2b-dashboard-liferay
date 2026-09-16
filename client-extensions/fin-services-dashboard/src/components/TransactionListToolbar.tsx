import type { B2bAssignableUser } from '../api/b2bUsers';
import { TRANSACTION_STATUS_OPTIONS, TRANSACTION_TYPES } from '../api/transactions';

import { UserTypeahead } from './UserTypeahead';

interface TransactionListToolbarProps {
	canFilterByOwner?: boolean;
	isLoading: boolean;
	onClearFilters: () => void;
	onOwnerChange: (user: B2bAssignableUser | null) => void;
	onSearchChange: (value: string) => void;
	onStatusChange: (value: string) => void;
	onTransactionTypeChange: (value: string) => void;
	ownerUserId?: number | null;
	ownerUserLabel?: string;
	search: string;
	status: string;
	totalCount: number;
	transactionType: string;
}

export function TransactionListToolbar({
	canFilterByOwner = false,
	isLoading,
	onClearFilters,
	onOwnerChange,
	onSearchChange,
	onStatusChange,
	onTransactionTypeChange,
	ownerUserId,
	ownerUserLabel,
	search,
	status,
	totalCount,
	transactionType,
}: TransactionListToolbarProps) {
	const hasFilters = Boolean(
		search.trim() ||
			transactionType ||
			status ||
			(ownerUserId != null && ownerUserId > 0),
	);

	return (
		<section className="nexus-card p-4 md:p-5">
			<div className="flex flex-col gap-4 lg:flex-row lg:items-end lg:justify-between">
				<div
					className={
						canFilterByOwner
							? 'grid flex-1 gap-3 md:grid-cols-2 xl:grid-cols-5'
							: 'grid flex-1 gap-3 md:grid-cols-2 xl:grid-cols-4'
					}
				>
					<label className="block space-y-1.5 md:col-span-2">
						<span className="nexus-label text-sm font-medium">Buscar</span>
						<input
							className="nexus-input"
							onChange={(event) => onSearchChange(event.target.value)}
							placeholder="Descripción o tipo de movimiento..."
							type="search"
							value={search}
						/>
					</label>

					{canFilterByOwner && (
						<div className="md:col-span-2 xl:col-span-1">
							<UserTypeahead
								disabled={isLoading}
								label="Usuario"
								onChange={onOwnerChange}
								placeholder="Buscar usuario..."
								selectedUserId={ownerUserId}
								selectedUserLabel={ownerUserLabel}
							/>
						</div>
					)}

					<label className="block space-y-1.5">
						<span className="nexus-label text-sm font-medium">Tipo</span>
						<select
							className="nexus-select"
							onChange={(event) =>
								onTransactionTypeChange(event.target.value)
							}
							value={transactionType}
						>
							<option value="">Todos los tipos</option>
							{TRANSACTION_TYPES.map((type) => (
								<option key={type.value} value={type.value}>
									{type.label}
								</option>
							))}
						</select>
					</label>

					<label className="block space-y-1.5">
						<span className="nexus-label text-sm font-medium">Estado</span>
						<select
							className="nexus-select"
							onChange={(event) => onStatusChange(event.target.value)}
							value={status}
						>
							{TRANSACTION_STATUS_OPTIONS.map((option) => (
								<option key={option.label} value={option.value}>
									{option.label}
								</option>
							))}
						</select>
					</label>
				</div>

				{hasFilters && (
					<button
						className="nexus-btn-secondary shrink-0"
						disabled={isLoading}
						onClick={onClearFilters}
						type="button"
					>
						Limpiar filtros
					</button>
				)}
			</div>

			<p className="nexus-subtitle mt-4 text-sm">
				{isLoading
					? 'Buscando transacciones...'
					: totalCount === 0
						? 'Sin resultados'
						: totalCount === 1
							? '1 transacción encontrada'
							: `${totalCount} transacciones encontradas`}
			</p>
		</section>
	);
}
