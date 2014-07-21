package com.oreon.cerebrum.web.action.ddx;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.DxTest;

public class DxTestActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DxTest> {

	DxTestAction dxTestAction = new DxTestAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<DxTest> getAction() {
		return dxTestAction;
	}

}
