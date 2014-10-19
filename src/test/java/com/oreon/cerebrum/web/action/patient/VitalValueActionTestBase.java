package com.oreon.cerebrum.web.action.patient;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.patient.VitalValue;

public class VitalValueActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<VitalValue> {

	VitalValueAction vitalValueAction = new VitalValueAction();

	@Override
	public BaseAction<VitalValue> getAction() {
		return vitalValueAction;
	}

}
