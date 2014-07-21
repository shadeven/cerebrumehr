package com.oreon.cerebrum.web.action.settings;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.settings.Settings;

public class SettingsActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Settings> {

	SettingsAction settingsAction = new SettingsAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Settings> getAction() {
		return settingsAction;
	}

}
