package com.oreon.cerebrum.web.action.drugs;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.drugs.DrugCategory;

public class DrugCategoryActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DrugCategory> {

	DrugCategoryAction drugCategoryAction = new DrugCategoryAction();

	@Override
	public BaseAction<DrugCategory> getAction() {
		return drugCategoryAction;
	}

}
