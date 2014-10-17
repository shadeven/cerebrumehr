package com.oreon.cerebrum.web.action.ddx;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.ConditionFinding;

public class ConditionFindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ConditionFinding> {

	ConditionFindingAction conditionFindingAction = new ConditionFindingAction();

	@Override
	public BaseAction<ConditionFinding> getAction() {
		return conditionFindingAction;
	}

}
