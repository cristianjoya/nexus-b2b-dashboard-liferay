interface AccountPaginationProps {
	isLoading: boolean;
	lastPage: number;
	onPageChange: (page: number) => void;
	page: number;
	pageSize: number;
	totalCount: number;
}

function getVisiblePages(page: number, lastPage: number): number[] {
	if (lastPage <= 5) {
		return Array.from({ length: lastPage }, (_, index) => index + 1);
	}

	const pages = new Set<number>([1, lastPage, page]);

	if (page > 1) {
		pages.add(page - 1);
	}

	if (page < lastPage) {
		pages.add(page + 1);
	}

	return [...pages].sort((left, right) => left - right);
}

export function AccountPagination({
	isLoading,
	lastPage,
	onPageChange,
	page,
	pageSize,
	totalCount,
}: AccountPaginationProps) {
	if (totalCount === 0) {
		return null;
	}

	const start = (page - 1) * pageSize + 1;
	const end = Math.min(page * pageSize, totalCount);
	const visiblePages = getVisiblePages(page, lastPage);

	return (
		<div className="flex flex-col gap-3 border-t border-slate-200 px-4 py-4 md:flex-row md:items-center md:justify-between">
			<p className="nexus-subtitle text-sm">
				Mostrando {start}–{end} de {totalCount}
			</p>

			<div className="flex flex-wrap items-center gap-2">
				<button
					className="nexus-btn-secondary !px-3 !py-1.5 text-sm"
					disabled={isLoading || page <= 1}
					onClick={() => onPageChange(page - 1)}
					type="button"
				>
					Anterior
				</button>

				{visiblePages.map((pageNumber, index) => {
					const previousPage = visiblePages[index - 1];
					const showEllipsis =
						index > 0 && previousPage != null && pageNumber - previousPage > 1;

					return (
						<span className="flex items-center gap-2" key={pageNumber}>
							{showEllipsis && (
								<span className="nexus-text-muted px-1 text-sm">…</span>
							)}
							<button
								className={
									pageNumber === page
										? 'nexus-btn-primary !min-w-9 !px-3 !py-1.5 text-sm'
										: 'nexus-btn-secondary !min-w-9 !px-3 !py-1.5 text-sm'
								}
								disabled={isLoading}
								onClick={() => onPageChange(pageNumber)}
								type="button"
							>
								{pageNumber}
							</button>
						</span>
					);
				})}

				<button
					className="nexus-btn-secondary !px-3 !py-1.5 text-sm"
					disabled={isLoading || page >= lastPage}
					onClick={() => onPageChange(page + 1)}
					type="button"
				>
					Siguiente
				</button>
			</div>
		</div>
	);
}
