package com.oreon.cerebrum.web.action.ddx;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.LabFinding;

public class LabFindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<LabFinding> {

	LabFindingAction labFindingAction = new LabFindingAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<LabFinding> getAction() {
		return labFindingAction;
	}

}
