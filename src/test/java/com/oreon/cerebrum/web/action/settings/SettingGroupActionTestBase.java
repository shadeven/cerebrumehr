package com.oreon.cerebrum.web.action.settings;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.settings.SettingGroup;

public class SettingGroupActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<SettingGroup> {

	SettingGroupAction settingGroupAction = new SettingGroupAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<SettingGroup> getAction() {
		return settingGroupAction;
	}

}
