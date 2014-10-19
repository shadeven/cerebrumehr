package com.oreon.cerebrum.web.action.ddx;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.PatientFinding;

public class PatientFindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PatientFinding> {

	PatientFindingAction patientFindingAction = new PatientFindingAction();

	@Override
	public BaseAction<PatientFinding> getAction() {
		return patientFindingAction;
	}

}
