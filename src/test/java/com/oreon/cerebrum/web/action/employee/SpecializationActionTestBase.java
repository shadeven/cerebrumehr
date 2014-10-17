package com.oreon.cerebrum.web.action.employee;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.employee.Specialization;

public class SpecializationActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Specialization> {

	SpecializationAction specializationAction = new SpecializationAction();

	@Override
	public BaseAction<Specialization> getAction() {
		return specializationAction;
	}

}
