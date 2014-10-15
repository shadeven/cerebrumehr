package com.oreon.cerebrum.web.action.patient;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.Vaccine;

public class VaccineActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Vaccine> {

	VaccineAction vaccineAction = new VaccineAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Vaccine> getAction() {
		return vaccineAction;
	}

}
