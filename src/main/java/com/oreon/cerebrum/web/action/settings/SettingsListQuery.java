package com.oreon.cerebrum.web.action.settings;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("settingsList")
@Scope(ScopeType.CONVERSATION)
public class SettingsListQuery extends SettingsListQueryBase
		implements
			java.io.Serializable {

}
