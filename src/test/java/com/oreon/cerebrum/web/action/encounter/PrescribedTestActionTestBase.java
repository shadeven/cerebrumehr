package com.oreon.cerebrum.web.action.encounter;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.encounter.PrescribedTest;

public class PrescribedTestActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PrescribedTest> {

	PrescribedTestAction prescribedTestAction = new PrescribedTestAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PrescribedTest> getAction() {
		return prescribedTestAction;
	}

}
