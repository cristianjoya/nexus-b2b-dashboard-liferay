/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package fin.services.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchFinTransactionException extends NoSuchModelException {

	public NoSuchFinTransactionException() {
	}

	public NoSuchFinTransactionException(String msg) {
		super(msg);
	}

	public NoSuchFinTransactionException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchFinTransactionException(Throwable throwable) {
		super(throwable);
	}

}