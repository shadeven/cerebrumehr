package com.oreon.cerebrum.web.action.drugs;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.drugs.AtcDrug;

public class AtcDrugActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AtcDrug> {

	AtcDrugAction atcDrugAction = new AtcDrugAction();

	@Override
	public BaseAction<AtcDrug> getAction() {
		return atcDrugAction;
	}

}
