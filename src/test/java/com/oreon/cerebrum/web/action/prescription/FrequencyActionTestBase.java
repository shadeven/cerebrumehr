package com.oreon.cerebrum.web.action.prescription;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.prescription.Frequency;

public class FrequencyActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Frequency> {

	FrequencyAction frequencyAction = new FrequencyAction();

	@Override
	public BaseAction<Frequency> getAction() {
		return frequencyAction;
	}

}
