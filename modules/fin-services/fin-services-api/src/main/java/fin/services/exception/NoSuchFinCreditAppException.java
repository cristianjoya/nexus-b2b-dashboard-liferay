/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package fin.services.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Brian Wing Shun Chan
 */
public class NoSuchFinCreditAppException extends NoSuchModelException {

	public NoSuchFinCreditAppException() {
	}

	public NoSuchFinCreditAppException(String msg) {
		super(msg);
	}

	public NoSuchFinCreditAppException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchFinCreditAppException(Throwable throwable) {
		super(throwable);
	}

}