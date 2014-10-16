package com.oreon.cerebrum.web.action.users;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.users.AppUser;

public class AppUserActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AppUser> {

	AppUserAction appUserAction = new AppUserAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AppUser> getAction() {
		return appUserAction;
	}

}
