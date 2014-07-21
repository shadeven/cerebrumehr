package com.oreon.cerebrum.web.action.patient;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.TrackedVital;

public class TrackedVitalActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<TrackedVital> {

	TrackedVitalAction trackedVitalAction = new TrackedVitalAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<TrackedVital> getAction() {
		return trackedVitalAction;
	}

}
