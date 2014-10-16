package com.oreon.cerebrum.web.action.prescription;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.prescription.Prescription;

public class PrescriptionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Prescription> {

	PrescriptionAction prescriptionAction = new PrescriptionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Prescription> getAction() {
		return prescriptionAction;
	}

}
