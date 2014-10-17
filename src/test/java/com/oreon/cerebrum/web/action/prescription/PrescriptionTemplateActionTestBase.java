package com.oreon.cerebrum.web.action.prescription;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.prescription.PrescriptionTemplate;

public class PrescriptionTemplateActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PrescriptionTemplate> {

	PrescriptionTemplateAction prescriptionTemplateAction = new PrescriptionTemplateAction();

	@Override
	public BaseAction<PrescriptionTemplate> getAction() {
		return prescriptionTemplateAction;
	}

}
