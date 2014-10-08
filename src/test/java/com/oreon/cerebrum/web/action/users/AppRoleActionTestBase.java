package com.oreon.cerebrum.web.action.users;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.users.AppRole;

public class AppRoleActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AppRole> {

	AppRoleAction appRoleAction = new AppRoleAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AppRole> getAction() {
		return appRoleAction;
	}

}
