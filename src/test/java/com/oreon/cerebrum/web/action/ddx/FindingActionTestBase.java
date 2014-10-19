package com.oreon.cerebrum.web.action.ddx;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.Finding;

public class FindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Finding> {

	FindingAction findingAction = new FindingAction();

	@Override
	public BaseAction<Finding> getAction() {
		return findingAction;
	}

}
