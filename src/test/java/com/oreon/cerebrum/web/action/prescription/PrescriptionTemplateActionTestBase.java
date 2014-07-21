package com.oreon.cerebrum.web.action.prescription;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.prescription.PrescriptionTemplate;

public class PrescriptionTemplateActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PrescriptionTemplate> {

	PrescriptionTemplateAction prescriptionTemplateAction = new PrescriptionTemplateAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PrescriptionTemplate> getAction() {
		return prescriptionTemplateAction;
	}

}
