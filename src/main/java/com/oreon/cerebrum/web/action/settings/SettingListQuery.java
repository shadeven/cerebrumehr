package com.oreon.cerebrum.web.action.settings;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("settingList")
@Scope(ScopeType.CONVERSATION)
public class SettingListQuery extends SettingListQueryBase
		implements
			java.io.Serializable {

}
