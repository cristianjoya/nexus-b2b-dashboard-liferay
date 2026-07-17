import type { ApiErrorKind } from '../api/apiError';
import { getSignInUrl } from '../api/apiError';

interface AccessMessageProps {
	kind: ApiErrorKind;
	message: string;
	title: string;
}

export function AccessMessage({ kind, message, title }: AccessMessageProps) {
	const isAuthIssue = kind === 'unauthenticated' || kind === 'forbidden';
	const signInUrl = getSignInUrl();

	return (
		<div
			className={
				isAuthIssue
					? 'nexus-alert-warning rounded-xl p-5'
					: 'nexus-alert-error rounded-xl p-5'
			}
		>
			<div className="flex items-start gap-4">
				<div
					aria-hidden
					className="rounded-full bg-white/70 px-3 py-2 text-lg"
				>
					{isAuthIssue ? '🔒' : '⚠️'}
				</div>
				<div className="space-y-3">
					<div>
						<h2 className="nexus-title text-lg font-semibold">{title}</h2>
						<p className="nexus-subtitle mt-2 text-sm leading-6">{message}</p>
					</div>

					{kind === 'unauthenticated' && (
						<a
							className="nexus-btn-primary inline-flex !px-4 !py-2 text-sm"
							href={signInUrl}
						>
							Iniciar sesión
						</a>
					)}

					{kind === 'forbidden' && (
						<p className="nexus-subtitle text-sm">
							Si ya iniciaste sesión, solicita al administrador permisos sobre
							la API{' '}
							<code className="rounded bg-white/80 px-1.5 py-0.5 font-mono text-sm">
								FinServicesRest
							</code>
							.
						</p>
					)}
				</div>
			</div>
		</div>
	);
}
