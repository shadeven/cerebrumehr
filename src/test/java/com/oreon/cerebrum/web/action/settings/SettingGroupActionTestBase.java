package com.oreon.cerebrum.web.action.settings;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
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
