import { useEffect } from 'react';

/** Prevents background page scroll while a modal is open. */
export function useLockBodyScroll(locked: boolean) {
	useEffect(() => {
		if (!locked) {
			return;
		}

		const previousOverflow = document.body.style.overflow;
		const previousPaddingRight = document.body.style.paddingRight;
		const scrollbarGap =
			window.innerWidth - document.documentElement.clientWidth;

		document.body.style.overflow = 'hidden';

		if (scrollbarGap > 0) {
			document.body.style.paddingRight = `${scrollbarGap}px`;
		}

		return () => {
			document.body.style.overflow = previousOverflow;
			document.body.style.paddingRight = previousPaddingRight;
		};
	}, [locked]);
}
