package com.oreon.cerebrum.web.action.codes;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.codes.SimpleCode;

public class SimpleCodeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<SimpleCode> {

	SimpleCodeAction simpleCodeAction = new SimpleCodeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<SimpleCode> getAction() {
		return simpleCodeAction;
	}

}
