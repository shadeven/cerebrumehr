package com.oreon.cerebrum.web.action.settings;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.settings.Setting;

public class SettingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Setting> {

	SettingAction settingAction = new SettingAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Setting> getAction() {
		return settingAction;
	}

}
