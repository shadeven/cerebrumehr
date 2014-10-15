package com.oreon.cerebrum.web.action.drugs;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.drugs.DrugInteraction;

public class DrugInteractionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DrugInteraction> {

	DrugInteractionAction drugInteractionAction = new DrugInteractionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<DrugInteraction> getAction() {
		return drugInteractionAction;
	}

}
