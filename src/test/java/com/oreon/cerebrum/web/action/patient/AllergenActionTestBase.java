package com.oreon.cerebrum.web.action.patient;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.Allergen;

public class AllergenActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Allergen> {

	AllergenAction allergenAction = new AllergenAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Allergen> getAction() {
		return allergenAction;
	}

}
