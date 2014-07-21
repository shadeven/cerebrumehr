package com.oreon.cerebrum.web.action.settings;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.settings.SettingName;

public class SettingNameActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<SettingName> {

	SettingNameAction settingNameAction = new SettingNameAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<SettingName> getAction() {
		return settingNameAction;
	}

}
