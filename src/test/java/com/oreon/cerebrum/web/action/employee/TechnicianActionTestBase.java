package com.oreon.cerebrum.web.action.employee;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.employee.Technician;

public class TechnicianActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Technician> {

	TechnicianAction technicianAction = new TechnicianAction();

	@Override
	public BaseAction<Technician> getAction() {
		return technicianAction;
	}

}
