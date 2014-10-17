package com.oreon.cerebrum.web.action.patient;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.patient.Immunization;

public class ImmunizationActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Immunization> {

	ImmunizationAction immunizationAction = new ImmunizationAction();

	@Override
	public BaseAction<Immunization> getAction() {
		return immunizationAction;
	}

}
