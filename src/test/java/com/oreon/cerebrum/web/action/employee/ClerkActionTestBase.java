package com.oreon.cerebrum.web.action.employee;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.employee.Clerk;

public class ClerkActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Clerk> {

	ClerkAction clerkAction = new ClerkAction();

	@Override
	public BaseAction<Clerk> getAction() {
		return clerkAction;
	}

}
