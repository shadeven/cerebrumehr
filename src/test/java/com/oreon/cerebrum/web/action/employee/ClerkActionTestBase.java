package com.oreon.cerebrum.web.action.employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.employee.Clerk;

public class ClerkActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Clerk> {

	ClerkAction clerkAction = new ClerkAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Clerk> getAction() {
		return clerkAction;
	}

}
