package com.oreon.cerebrum.web.action.drugs;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.drugs.DrugInteraction;

public class DrugInteractionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DrugInteraction> {

	DrugInteractionAction drugInteractionAction = new DrugInteractionAction();

	@Override
	public BaseAction<DrugInteraction> getAction() {
		return drugInteractionAction;
	}

}
