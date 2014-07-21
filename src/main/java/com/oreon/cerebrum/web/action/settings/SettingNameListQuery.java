package com.oreon.cerebrum.web.action.settings;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("settingNameList")
@Scope(ScopeType.CONVERSATION)
public class SettingNameListQuery extends SettingNameListQueryBase
		implements
			java.io.Serializable {

}
