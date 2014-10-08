package com.oreon.cerebrum.web.action.settings;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
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
