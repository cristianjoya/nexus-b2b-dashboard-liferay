import { useEffect, useMemo, useState } from 'react';

import {
	getUnauthenticatedError,
	isUserSignedIn,
	LiferayApiError,
	normalizeError,
} from '../api/apiError';
import { resolveResourcePermissions, normalizeFinPermissions } from '../api/permissions';
import { usePermissions } from '../hooks/usePermissions';
import { getErrorPresentation } from '../utils/presentation';

import { AccessMessage } from './AccessMessage';
import { AccountsTab } from './AccountsTab';
import { CreditApplicationsTab } from './CreditApplicationsTab';
import { TransactionsTab } from './TransactionsTab';

type DashboardTab = 'accounts' | 'transactions' | 'credit';

const ALL_TABS: {
	id: DashboardTab;
	label: string;
	viewKey: 'accounts' | 'transactions' | 'creditApplications';
}[] = [
	{ id: 'accounts', label: 'Cuentas', viewKey: 'accounts' },
	{ id: 'transactions', label: 'Transacciones', viewKey: 'transactions' },
	{ id: 'credit', label: 'Crédito', viewKey: 'creditApplications' },
];

function getPermissionsErrorMessage(error: unknown): string {
	const apiError =
		error instanceof LiferayApiError ? error : normalizeError(error);

	return apiError.message;
}

const unauthenticatedPresentation = getErrorPresentation(
	getUnauthenticatedError(),
);

export function Dashboard() {
	const signedIn = isUserSignedIn();
	const {
		data: permissions,
		error: permissionsError,
		isError: permissionsFailed,
		isFetching: permissionsFetching,
		isSuccess: permissionsLoaded,
	} = usePermissions();

	const permissionsReady =
		permissionsLoaded && !permissionsFetching && Boolean(permissions);

	const visibleTabs = useMemo(() => {
		if (!permissions) {
			return [];
		}

		const normalized = normalizeFinPermissions(permissions);

		return ALL_TABS.filter((tab) => normalized[tab.viewKey]?.view ?? false);
	}, [permissions]);

	const [activeTab, setActiveTab] = useState<DashboardTab>('accounts');

	useEffect(() => {
		if (!permissionsReady || visibleTabs.length === 0) {
			return;
		}

		if (!visibleTabs.some((tab) => tab.id === activeTab)) {
			setActiveTab(visibleTabs[0].id);
		}
	}, [activeTab, permissionsReady, visibleTabs]);

	return (
		<div className="nexus-dashboard p-4 md:p-6">
			<div className="mx-auto max-w-6xl space-y-5">
				<div className="nexus-card p-6">
					<header>
						<p className="nexus-text-accent text-sm font-semibold uppercase tracking-wide">
							Nexus B2B
						</p>
						<h1 className="nexus-title mt-1 text-3xl font-bold">
							Dashboard Financiero
						</h1>
						<p className="nexus-subtitle mt-2 text-base">
							Cuentas, movimientos y solicitudes de crédito por sitio Liferay
						</p>
					</header>

					{!signedIn ? (
						<div className="mt-6">
							<AccessMessage
								kind={unauthenticatedPresentation.kind}
								message={unauthenticatedPresentation.message}
								title={unauthenticatedPresentation.title}
							/>
						</div>
					) : !permissionsReady ? (
						<p className="nexus-subtitle mt-6 text-sm">
							Verificando permisos...
						</p>
					) : permissionsFailed ? (
						<p className="nexus-subtitle mt-6 text-sm">
							No se pudieron verificar tus permisos:{' '}
							{getPermissionsErrorMessage(permissionsError)}. Recarga la
							página o contacta al administrador del sitio.
						</p>
					) : visibleTabs.length === 0 ? (
						<p className="nexus-subtitle mt-6 text-sm">
							No tienes permisos para ver las secciones de este dashboard.
							Solicita el rol B2B Manager o B2B User en este sitio.
						</p>
					) : (
					<nav
						aria-label="Secciones del dashboard"
						className="mt-6 flex flex-wrap gap-2 border-b border-slate-200 pb-0"
						role="tablist"
					>
						{visibleTabs.map((tab) => (
							<button
								aria-controls={`nexus-panel-${tab.id}`}
								aria-selected={activeTab === tab.id}
								className={
									activeTab === tab.id
										? 'nexus-tab-active -mb-px border-b-2 border-blue-600 px-4 py-2 text-sm font-semibold text-blue-700'
										: 'nexus-tab px-4 py-2 text-sm font-medium text-slate-600 hover:text-slate-900'
								}
								id={`nexus-tab-${tab.id}`}
								key={tab.id}
								onClick={() => setActiveTab(tab.id)}
								role="tab"
								tabIndex={activeTab === tab.id ? 0 : -1}
								type="button"
							>
								{tab.label}
							</button>
						))}
					</nav>
					)}
				</div>

				{permissionsReady &&
					activeTab === 'accounts' &&
					resolveResourcePermissions(permissions, 'accounts').view && (
					<div
						aria-labelledby="nexus-tab-accounts"
						id="nexus-panel-accounts"
						role="tabpanel"
					>
						<AccountsTab
							canAssignOwner={
								permissions.profile === 'ADMIN' ||
								permissions.profile === 'B2B_MANAGER'
							}
							permissions={resolveResourcePermissions(
								permissions,
								'accounts',
							)}
						/>
					</div>
				)}
				{permissionsReady &&
					activeTab === 'transactions' &&
					resolveResourcePermissions(permissions, 'transactions')
						.view && (
					<div
						aria-labelledby="nexus-tab-transactions"
						id="nexus-panel-transactions"
						role="tabpanel"
					>
						<TransactionsTab
							canFilterByOwner={
								permissions.profile === 'ADMIN' ||
								permissions.profile === 'B2B_MANAGER'
							}
							permissions={resolveResourcePermissions(
								permissions,
								'transactions',
							)}
						/>
					</div>
				)}
				{permissionsReady &&
					activeTab === 'credit' &&
					resolveResourcePermissions(permissions, 'creditApplications')
						.view && (
					<div
						aria-labelledby="nexus-tab-credit"
						id="nexus-panel-credit"
						role="tabpanel"
					>
						<CreditApplicationsTab
							canFilterByOwner={
								permissions.profile === 'ADMIN' ||
								permissions.profile === 'B2B_MANAGER'
							}
							permissions={resolveResourcePermissions(
								permissions,
								'creditApplications',
							)}
						/>
					</div>
				)}
			</div>
		</div>
	);
}
