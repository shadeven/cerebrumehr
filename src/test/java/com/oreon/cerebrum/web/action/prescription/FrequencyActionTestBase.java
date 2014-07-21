package com.oreon.cerebrum.web.action.prescription;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.prescription.Frequency;

public class FrequencyActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Frequency> {

	FrequencyAction frequencyAction = new FrequencyAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Frequency> getAction() {
		return frequencyAction;
	}

}
