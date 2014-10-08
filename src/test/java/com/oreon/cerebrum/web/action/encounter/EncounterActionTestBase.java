package com.oreon.cerebrum.web.action.encounter;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
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
