/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package fin.services.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the FinCreditApp service. Represents a row in the &quot;FinServices_FinCreditApp&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see FinCreditAppModel
 * @generated
 */
@ImplementationClassName("fin.services.model.impl.FinCreditAppImpl")
@ProviderType
public interface FinCreditApp extends FinCreditAppModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>fin.services.model.impl.FinCreditAppImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<FinCreditApp, Long> CREDIT_APP_ID_ACCESSOR =
		new Accessor<FinCreditApp, Long>() {

			@Override
			public Long get(FinCreditApp finCreditApp) {
				return finCreditApp.getCreditAppId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<FinCreditApp> getTypeClass() {
				return FinCreditApp.class;
			}

		};

}