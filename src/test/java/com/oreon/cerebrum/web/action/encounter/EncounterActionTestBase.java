package com.oreon.cerebrum.web.action.encounter;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.encounter.Encounter;

public class EncounterActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Encounter> {

	EncounterAction encounterAction = new EncounterAction();

	@Override
	public BaseAction<Encounter> getAction() {
		return encounterAction;
	}

}
