package com.oreon.cerebrum.web.action.employee;

import org.junit.BeforeClass;
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
