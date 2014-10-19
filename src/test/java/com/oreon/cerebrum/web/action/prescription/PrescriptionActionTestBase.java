package com.oreon.cerebrum.web.action.prescription;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.prescription.Prescription;

public class PrescriptionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Prescription> {

	PrescriptionAction prescriptionAction = new PrescriptionAction();

	@Override
	public BaseAction<Prescription> getAction() {
		return prescriptionAction;
	}

}
