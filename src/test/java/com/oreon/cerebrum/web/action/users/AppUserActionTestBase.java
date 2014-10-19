package com.oreon.cerebrum.web.action.users;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.users.AppUser;

public class AppUserActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AppUser> {

	AppUserAction appUserAction = new AppUserAction();

	@Override
	public BaseAction<AppUser> getAction() {
		return appUserAction;
	}

}
