package com.oreon.cerebrum.web.action.users;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.users.AppRole;

public class AppRoleActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AppRole> {

	AppRoleAction appRoleAction = new AppRoleAction();

	@Override
	public BaseAction<AppRole> getAction() {
		return appRoleAction;
	}

}
