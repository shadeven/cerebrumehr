package com.oreon.cerebrum.web.action.prescription;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.prescription.PrescriptionItem;

public class PrescriptionItemActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PrescriptionItem> {

	PrescriptionItemAction prescriptionItemAction = new PrescriptionItemAction();

	@Override
	public BaseAction<PrescriptionItem> getAction() {
		return prescriptionItemAction;
	}

}
