package com.oreon.cerebrum.web.action.drugs;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.drugs.Drug;

public class DrugActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Drug> {

	DrugAction drugAction = new DrugAction();

	@Override
	public BaseAction<Drug> getAction() {
		return drugAction;
	}

}
