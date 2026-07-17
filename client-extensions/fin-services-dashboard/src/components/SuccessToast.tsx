import { useEffect, useState } from 'react';

const VISIBLE_MS = 3000;
const FADE_MS = 300;

interface SuccessToastProps {
	message: string;
	onDismiss: () => void;
}

export function SuccessToast({ message, onDismiss }: SuccessToastProps) {
	const [isVisible, setIsVisible] = useState(true);

	useEffect(() => {
		setIsVisible(true);

		const fadeTimer = window.setTimeout(() => {
			setIsVisible(false);
		}, VISIBLE_MS - FADE_MS);

		const dismissTimer = window.setTimeout(() => {
			onDismiss();
		}, VISIBLE_MS);

		return () => {
			window.clearTimeout(fadeTimer);
			window.clearTimeout(dismissTimer);
		};
	}, [message, onDismiss]);

	return (
		<div
			aria-live="polite"
			className={`nexus-alert-success nexus-toast rounded-xl px-4 py-3 text-sm font-medium ${
				isVisible ? 'nexus-toast-visible' : 'nexus-toast-hidden'
			}`}
			role="status"
		>
			{message}
		</div>
	);
}
