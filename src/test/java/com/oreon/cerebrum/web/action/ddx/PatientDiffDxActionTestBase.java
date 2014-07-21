package com.oreon.cerebrum.web.action.ddx;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.PatientDiffDx;

public class PatientDiffDxActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PatientDiffDx> {

	PatientDiffDxAction patientDiffDxAction = new PatientDiffDxAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PatientDiffDx> getAction() {
		return patientDiffDxAction;
	}

}
