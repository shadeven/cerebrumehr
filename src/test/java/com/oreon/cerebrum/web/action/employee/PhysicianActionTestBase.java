package com.oreon.cerebrum.web.action.employee;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.employee.Physician;

public class PhysicianActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Physician> {

	PhysicianAction physicianAction = new PhysicianAction();

	@Override
	public BaseAction<Physician> getAction() {
		return physicianAction;
	}

}
