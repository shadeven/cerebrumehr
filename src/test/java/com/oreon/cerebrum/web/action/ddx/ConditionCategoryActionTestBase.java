package com.oreon.cerebrum.web.action.ddx;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.ConditionCategory;

public class ConditionCategoryActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ConditionCategory> {

	ConditionCategoryAction conditionCategoryAction = new ConditionCategoryAction();

	@Override
	public BaseAction<ConditionCategory> getAction() {
		return conditionCategoryAction;
	}

}
