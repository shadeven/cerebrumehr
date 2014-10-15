package com.oreon.cerebrum.web.action.drugs;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.drugs.AtcDrug;

public class AtcDrugActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AtcDrug> {

	AtcDrugAction atcDrugAction = new AtcDrugAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AtcDrug> getAction() {
		return atcDrugAction;
	}

}
