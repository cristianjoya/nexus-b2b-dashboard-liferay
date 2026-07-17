import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { createRoot, type Root } from 'react-dom/client';

import { LiferayApiError } from './api/apiError';
import { Dashboard } from './components/Dashboard';
import './index.css';

const ELEMENT_NAME = 'fin-services-dashboard';

/** Stop bubble to body: Liferay delegates call target.closest without Text-node guards. */
const PORTAL_ISOLATION_EVENTS = [
	'click',
	'mousedown',
	'mouseup',
	'pointerdown',
	'pointerup',
] as const;

function createQueryClient() {
	return new QueryClient({
		defaultOptions: {
			queries: {
				refetchOnWindowFocus: false,
				retry: (failureCount, error) => {
					if (error instanceof LiferayApiError) {
						if (
							error.status === 401 ||
							error.status === 403 ||
							error.kind === 'unauthenticated' ||
							error.kind === 'forbidden'
						) {
							return false;
						}
					}

					return failureCount < 2;
				},
			},
		},
	});
}

class FinServicesDashboardElement extends HTMLElement {
	private _queryClient: QueryClient | null = null;
	private _reactRoot: Root | null = null;
	private _stopPortalPropagation = (event: Event) => {
		event.stopPropagation();
	};

	connectedCallback() {
		if (this._reactRoot) {
			return;
		}

		this.classList.add('nexus-app');

		for (const type of PORTAL_ISOLATION_EVENTS) {
			this.addEventListener(type, this._stopPortalPropagation);
		}

		this._queryClient = createQueryClient();
		this._reactRoot = createRoot(this);
		this._reactRoot.render(
			<QueryClientProvider client={this._queryClient}>
				<Dashboard />
			</QueryClientProvider>,
		);
	}

	disconnectedCallback() {
		for (const type of PORTAL_ISOLATION_EVENTS) {
			this.removeEventListener(type, this._stopPortalPropagation);
		}

		const reactRoot = this._reactRoot;
		const queryClient = this._queryClient;

		this._reactRoot = null;
		this._queryClient = null;

		if (queryClient) {
			queryClient.clear();
			queryClient.cancelQueries();
		}

		if (reactRoot) {
			queueMicrotask(() => {
				reactRoot.unmount();
			});
		}
	}
}

if (!customElements.get(ELEMENT_NAME)) {
	customElements.define(ELEMENT_NAME, FinServicesDashboardElement);
}
