package com.oreon.cerebrum.web.action.prescription;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.prescription.PrescriptionItem;

public class PrescriptionItemActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PrescriptionItem> {

	PrescriptionItemAction prescriptionItemAction = new PrescriptionItemAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PrescriptionItem> getAction() {
		return prescriptionItemAction;
	}

}
