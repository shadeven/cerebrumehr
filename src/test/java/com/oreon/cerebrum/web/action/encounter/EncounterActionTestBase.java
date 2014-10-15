package com.oreon.cerebrum.web.action.encounter;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.encounter.Encounter;

public class EncounterActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Encounter> {

	EncounterAction encounterAction = new EncounterAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Encounter> getAction() {
		return encounterAction;
	}

}
