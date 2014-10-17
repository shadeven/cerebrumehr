package com.oreon.cerebrum.web.action.ddx;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.LabFinding;

public class LabFindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<LabFinding> {

	LabFindingAction labFindingAction = new LabFindingAction();

	@Override
	public BaseAction<LabFinding> getAction() {
		return labFindingAction;
	}

}
