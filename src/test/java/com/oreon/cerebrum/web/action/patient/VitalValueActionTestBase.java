package com.oreon.cerebrum.web.action.patient;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.VitalValue;

public class VitalValueActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<VitalValue> {

	VitalValueAction vitalValueAction = new VitalValueAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<VitalValue> getAction() {
		return vitalValueAction;
	}

}
