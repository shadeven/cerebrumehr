package com.oreon.cerebrum.web.action.patient;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.Immunization;

public class ImmunizationActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Immunization> {

	ImmunizationAction immunizationAction = new ImmunizationAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Immunization> getAction() {
		return immunizationAction;
	}

}
