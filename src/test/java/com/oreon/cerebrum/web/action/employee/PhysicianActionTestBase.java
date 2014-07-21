package com.oreon.cerebrum.web.action.employee;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.employee.Physician;

public class PhysicianActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Physician> {

	PhysicianAction physicianAction = new PhysicianAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Physician> getAction() {
		return physicianAction;
	}

}
