import { useEffect, useId, useRef, useState } from 'react';

import {
	formatAssignableUserLabel,
	type B2bAssignableUser,
} from '../api/b2bUsers';
import { useAssignableB2bUsers } from '../hooks/useAssignableB2bUsers';

const SEARCH_DEBOUNCE_MS = 300;
const MIN_SEARCH_CHARS = 2;
const SUGGESTION_PAGE_SIZE = 20;

interface UserTypeaheadProps {
	disabled?: boolean;
	label?: string;
	onChange: (user: B2bAssignableUser | null) => void;
	placeholder?: string;
	required?: boolean;
	selectedUserId?: number | null;
	selectedUserLabel?: string;
}

export function UserTypeahead({
	disabled = false,
	label = 'Usuario B2B',
	onChange,
	placeholder = 'Buscar por nombre o correo...',
	required = false,
	selectedUserId,
	selectedUserLabel,
}: UserTypeaheadProps) {
	const listId = useId();
	const rootRef = useRef<HTMLDivElement>(null);
	const [inputValue, setInputValue] = useState(selectedUserLabel ?? '');
	const [debouncedSearch, setDebouncedSearch] = useState('');
	const [open, setOpen] = useState(false);
	const [highlightIndex, setHighlightIndex] = useState(0);

	useEffect(() => {
		setInputValue(selectedUserLabel ?? '');
	}, [selectedUserId, selectedUserLabel]);

	useEffect(() => {
		const timer = window.setTimeout(() => {
			setDebouncedSearch(inputValue.trim());
		}, SEARCH_DEBOUNCE_MS);

		return () => window.clearTimeout(timer);
	}, [inputValue]);

	const canSearch =
		!selectedUserId && debouncedSearch.length >= MIN_SEARCH_CHARS;

	const { data, isError, isFetching, isLoading } = useAssignableB2bUsers({
		enabled: open && canSearch,
		page: 1,
		pageSize: SUGGESTION_PAGE_SIZE,
		search: debouncedSearch,
	});

	const suggestions = data?.items ?? [];

	useEffect(() => {
		setHighlightIndex(0);
	}, [debouncedSearch, suggestions.length]);

	useEffect(() => {
		function handlePointerDown(event: MouseEvent) {
			const target = event.target;

			if (!(target instanceof Node) || !rootRef.current) {
				return;
			}

			if (!rootRef.current.contains(target)) {
				setOpen(false);
			}
		}

		// Capture: runs before the CE host stops bubbling to isolate from Liferay.
		document.addEventListener('mousedown', handlePointerDown, true);

		return () =>
			document.removeEventListener('mousedown', handlePointerDown, true);
	}, []);

	function selectUser(user: B2bAssignableUser) {
		onChange(user);
		setInputValue(formatAssignableUserLabel(user));
		setOpen(false);
	}

	function clearSelection() {
		onChange(null);
		setInputValue('');
		setDebouncedSearch('');
		setOpen(false);
	}

	function handleKeyDown(event: React.KeyboardEvent<HTMLInputElement>) {
		if (event.key === 'Escape') {
			setOpen(false);

			return;
		}

		if (!open || suggestions.length === 0) {
			return;
		}

		if (event.key === 'ArrowDown') {
			event.preventDefault();
			setHighlightIndex((index) =>
				Math.min(index + 1, suggestions.length - 1),
			);
		}
		else if (event.key === 'ArrowUp') {
			event.preventDefault();
			setHighlightIndex((index) => Math.max(index - 1, 0));
		}
		else if (event.key === 'Enter') {
			event.preventDefault();
			const user = suggestions[highlightIndex];

			if (user) {
				selectUser(user);
			}
		}
	}

	const hint =
		selectedUserId != null
			? null
			: inputValue.trim().length > 0 &&
				  inputValue.trim().length < MIN_SEARCH_CHARS
				? `Escribe al menos ${MIN_SEARCH_CHARS} caracteres`
				: null;

	return (
		<div className="block space-y-1.5" ref={rootRef}>
			<span className="nexus-label text-sm font-medium">{label}</span>
			<div className="relative">
				<input
					aria-autocomplete="list"
					aria-controls={listId}
					aria-expanded={open}
					autoComplete="off"
					className="nexus-input pr-20"
					disabled={disabled}
					onChange={(event) => {
						const value = event.target.value;

						setInputValue(value);

						if (selectedUserId != null) {
							onChange(null);
						}
						else if (!value.trim()) {
							onChange(null);
						}

						setOpen(Boolean(value.trim()));
					}}
					onFocus={() => setOpen(true)}
					onKeyDown={handleKeyDown}
					placeholder={placeholder}
					required={required && selectedUserId == null}
					role="combobox"
					type="search"
					value={inputValue}
				/>
				{selectedUserId != null && (
					<button
						className="nexus-btn-ghost absolute right-1 top-1/2 -translate-y-1/2 !px-2 !py-1 text-xs"
						disabled={disabled}
						onClick={clearSelection}
						type="button"
					>
						Limpiar
					</button>
				)}

				{open && !selectedUserId && (
					<ul
						className="nexus-typeahead-menu absolute z-20 mt-1 max-h-60 w-full overflow-auto"
						id={listId}
						role="listbox"
					>
						{hint && (
							<li className="nexus-typeahead-hint px-3 py-2 text-sm">
								{hint}
							</li>
						)}
						{canSearch && (isLoading || isFetching) && (
							<li className="nexus-typeahead-hint px-3 py-2 text-sm">
								Buscando usuarios...
							</li>
						)}
						{canSearch && isError && (
							<li className="nexus-typeahead-hint px-3 py-2 text-sm">
								No se pudieron cargar usuarios.
							</li>
						)}
						{canSearch &&
							!isLoading &&
							!isFetching &&
							!isError &&
							suggestions.length === 0 && (
								<li className="nexus-typeahead-hint px-3 py-2 text-sm">
									Sin coincidencias
								</li>
							)}
						{suggestions.map((user, index) => (
							<li key={user.userId ?? user.emailAddress}>
								<button
									aria-selected={index === highlightIndex}
									className={
										index === highlightIndex
											? 'nexus-typeahead-option nexus-typeahead-option-active'
											: 'nexus-typeahead-option'
									}
									onMouseDown={(event) => {
										event.preventDefault();
										selectUser(user);
									}}
									onMouseEnter={() => setHighlightIndex(index)}
									role="option"
									type="button"
								>
									<span className="block font-medium">
										{user.fullName?.trim() ||
											user.emailAddress ||
											`Usuario #${user.userId}`}
									</span>
									{user.emailAddress && (
										<span className="nexus-text-muted mt-0.5 block text-xs">
											{user.emailAddress}
										</span>
									)}
								</button>
							</li>
						))}
					</ul>
				)}
			</div>
		</div>
	);
}
