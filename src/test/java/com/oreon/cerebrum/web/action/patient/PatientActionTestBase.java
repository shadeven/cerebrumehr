package com.oreon.cerebrum.web.action.patient;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.patient.Patient;

public class PatientActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Patient> {

	PatientAction patientAction = new PatientAction();

	@Override
	public BaseAction<Patient> getAction() {
		return patientAction;
	}

}
