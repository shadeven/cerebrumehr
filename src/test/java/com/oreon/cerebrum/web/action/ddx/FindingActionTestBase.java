package com.oreon.cerebrum.web.action.ddx;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.Finding;

public class FindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Finding> {

	FindingAction findingAction = new FindingAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Finding> getAction() {
		return findingAction;
	}

}
