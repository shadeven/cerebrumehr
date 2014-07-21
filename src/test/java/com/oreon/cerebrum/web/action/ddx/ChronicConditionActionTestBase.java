package com.oreon.cerebrum.web.action.ddx;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.ChronicCondition;

public class ChronicConditionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ChronicCondition> {

	ChronicConditionAction chronicConditionAction = new ChronicConditionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ChronicCondition> getAction() {
		return chronicConditionAction;
	}

}
