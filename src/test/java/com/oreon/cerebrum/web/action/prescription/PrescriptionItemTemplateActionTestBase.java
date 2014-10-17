package com.oreon.cerebrum.web.action.prescription;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.prescription.PrescriptionItemTemplate;

public class PrescriptionItemTemplateActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PrescriptionItemTemplate> {

	PrescriptionItemTemplateAction prescriptionItemTemplateAction = new PrescriptionItemTemplateAction();

	@Override
	public BaseAction<PrescriptionItemTemplate> getAction() {
		return prescriptionItemTemplateAction;
	}

}
