package com.oreon.cerebrum.web.action.patient;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.Patient;

public class PatientActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Patient> {

	PatientAction patientAction = new PatientAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Patient> getAction() {
		return patientAction;
	}

}
