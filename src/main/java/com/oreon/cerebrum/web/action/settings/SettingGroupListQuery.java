package com.oreon.cerebrum.web.action.settings;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("settingGroupList")
@Scope(ScopeType.CONVERSATION)
public class SettingGroupListQuery extends SettingGroupListQueryBase
		implements
			java.io.Serializable {

}
