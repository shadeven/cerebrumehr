package com.oreon.cerebrum.web.action.encounter;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.encounter.Differential;

public class DifferentialActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Differential> {

	DifferentialAction differentialAction = new DifferentialAction();

	@Override
	public BaseAction<Differential> getAction() {
		return differentialAction;
	}

}
