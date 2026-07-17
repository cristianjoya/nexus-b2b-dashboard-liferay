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
 * The extended model interface for the FinAccount service. Represents a row in the &quot;FinServices_FinAccount&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see FinAccountModel
 * @generated
 */
@ImplementationClassName("fin.services.model.impl.FinAccountImpl")
@ProviderType
public interface FinAccount extends FinAccountModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>fin.services.model.impl.FinAccountImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<FinAccount, Long> ACCOUNT_ID_ACCESSOR =
		new Accessor<FinAccount, Long>() {

			@Override
			public Long get(FinAccount finAccount) {
				return finAccount.getAccountId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<FinAccount> getTypeClass() {
				return FinAccount.class;
			}

		};

}