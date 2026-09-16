import { type ReactNode, useEffect, useId } from 'react';

import { useLockBodyScroll } from '../hooks/useLockBodyScroll';

interface ConfirmDeleteDialogProps {
	children: ReactNode;
	confirmLabel?: string;
	isPending: boolean;
	onClose: () => void;
	onConfirm: () => void;
	open: boolean;
	serverError?: string | null;
	title: string;
}

export function ConfirmDeleteDialog({
	children,
	confirmLabel = 'Eliminar',
	isPending,
	onClose,
	onConfirm,
	open,
	serverError,
	title,
}: ConfirmDeleteDialogProps) {
	const titleId = useId();

	useLockBodyScroll(open);

	useEffect(() => {
		if (!open) {
			return;
		}

		function handleKeyDown(event: KeyboardEvent) {
			if (event.key === 'Escape' && !isPending) {
				onClose();
			}
		}

		document.addEventListener('keydown', handleKeyDown, true);

		return () => document.removeEventListener('keydown', handleKeyDown, true);
	}, [open, isPending, onClose]);

	if (!open) {
		return null;
	}

	return (
		<div
			aria-labelledby={titleId}
			aria-modal="true"
			className="nexus-modal-backdrop fixed inset-0 z-50 flex p-3 sm:p-6"
			onClick={() => {
				if (!isPending) {
					onClose();
				}
			}}
			role="alertdialog"
		>
			<div
				className="nexus-card nexus-modal-panel relative z-10 w-full max-w-md p-4 shadow-2xl sm:p-6"
				onClick={(event) => event.stopPropagation()}
			>
				<h2 className="nexus-text-danger text-xl font-bold" id={titleId}>
					{title}
				</h2>

				<div className="nexus-subtitle mt-3 text-sm leading-6">{children}</div>

				{serverError && (
					<p className="nexus-alert-error mt-3 rounded-lg px-3 py-2 text-sm">
						{serverError}
					</p>
				)}

				<div className="mt-6 flex justify-end gap-3">
					<button
						className="nexus-btn-secondary"
						disabled={isPending}
						onClick={onClose}
						type="button"
					>
						Cancelar
					</button>
					<button
						className="nexus-btn-danger"
						disabled={isPending}
						onClick={onConfirm}
						type="button"
					>
						{isPending ? 'Eliminando...' : confirmLabel}
					</button>
				</div>
			</div>
		</div>
	);
}
