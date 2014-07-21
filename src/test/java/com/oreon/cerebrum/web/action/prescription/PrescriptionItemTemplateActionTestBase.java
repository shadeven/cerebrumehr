package com.oreon.cerebrum.web.action.prescription;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.prescription.PrescriptionItemTemplate;

public class PrescriptionItemTemplateActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PrescriptionItemTemplate> {

	PrescriptionItemTemplateAction prescriptionItemTemplateAction = new PrescriptionItemTemplateAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PrescriptionItemTemplate> getAction() {
		return prescriptionItemTemplateAction;
	}

}
