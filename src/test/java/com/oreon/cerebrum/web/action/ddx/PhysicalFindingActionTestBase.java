package com.oreon.cerebrum.web.action.ddx;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.PhysicalFinding;

public class PhysicalFindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PhysicalFinding> {

	PhysicalFindingAction physicalFindingAction = new PhysicalFindingAction();

	@Override
	public BaseAction<PhysicalFinding> getAction() {
		return physicalFindingAction;
	}

}
