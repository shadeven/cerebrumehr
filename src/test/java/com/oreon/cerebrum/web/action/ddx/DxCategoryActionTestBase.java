package com.oreon.cerebrum.web.action.ddx;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.DxCategory;

public class DxCategoryActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DxCategory> {

	DxCategoryAction dxCategoryAction = new DxCategoryAction();

	@Override
	public BaseAction<DxCategory> getAction() {
		return dxCategoryAction;
	}

}
