package com.oreon.cerebrum.web.action.ddx;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.DifferentialDx;

public class DifferentialDxActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DifferentialDx> {

	DifferentialDxAction differentialDxAction = new DifferentialDxAction();

	@Override
	public BaseAction<DifferentialDx> getAction() {
		return differentialDxAction;
	}

}
