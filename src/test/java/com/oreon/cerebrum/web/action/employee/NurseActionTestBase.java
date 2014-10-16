package com.oreon.cerebrum.web.action.employee;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.employee.Nurse;

public class NurseActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Nurse> {

	NurseAction nurseAction = new NurseAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Nurse> getAction() {
		return nurseAction;
	}

}
