import { CREDIT_STATUS_OPTIONS } from '../api/creditApplications';
import type { B2bAssignableUser } from '../api/b2bUsers';

import { UserTypeahead } from './UserTypeahead';

interface CreditListToolbarProps {
	canFilterByOwner?: boolean;
	isLoading: boolean;
	onClearFilters: () => void;
	onOwnerChange: (user: B2bAssignableUser | null) => void;
	onSearchChange: (value: string) => void;
	onStatusChange: (value: string) => void;
	ownerUserId?: number | null;
	ownerUserLabel?: string;
	search: string;
	status: string;
	totalCount: number;
}

export function CreditListToolbar({
	canFilterByOwner = false,
	isLoading,
	onClearFilters,
	onOwnerChange,
	onSearchChange,
	onStatusChange,
	ownerUserId,
	ownerUserLabel,
	search,
	status,
	totalCount,
}: CreditListToolbarProps) {
	const hasFilters = Boolean(
		search.trim() || status || (ownerUserId != null && ownerUserId > 0),
	);

	return (
		<section className="nexus-card p-4 md:p-5">
			<div className="flex flex-col gap-4 lg:flex-row lg:items-end lg:justify-between">
				<div
					className={
						canFilterByOwner
							? 'grid flex-1 gap-3 md:grid-cols-3'
							: 'grid flex-1 gap-3 md:grid-cols-2'
					}
				>
					<label className="block space-y-1.5">
						<span className="nexus-label text-sm font-medium">Buscar</span>
						<input
							className="nexus-input"
							onChange={(event) => onSearchChange(event.target.value)}
							placeholder="Solicitante, propósito o notas..."
							type="search"
							value={search}
						/>
					</label>

					{canFilterByOwner && (
						<UserTypeahead
							disabled={isLoading}
							label="Usuario"
							onChange={onOwnerChange}
							placeholder="Buscar usuario..."
							selectedUserId={ownerUserId}
							selectedUserLabel={ownerUserLabel}
						/>
					)}

					<label className="block space-y-1.5">
						<span className="nexus-label text-sm font-medium">Estado</span>
						<select
							className="nexus-select"
							onChange={(event) => onStatusChange(event.target.value)}
							value={status}
						>
							{CREDIT_STATUS_OPTIONS.map((option) => (
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
					? 'Buscando solicitudes...'
					: totalCount === 0
						? 'Sin resultados'
						: totalCount === 1
							? '1 solicitud encontrada'
							: `${totalCount} solicitudes encontradas`}
			</p>
		</section>
	);
}
