package com.oreon.cerebrum.web.action.employee;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.employee.Technician;

public class TechnicianActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Technician> {

	TechnicianAction technicianAction = new TechnicianAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Technician> getAction() {
		return technicianAction;
	}

}
