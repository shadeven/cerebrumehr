package com.oreon.cerebrum.web.action.patient;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.Allergy;

public class AllergyActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Allergy> {

	AllergyAction allergyAction = new AllergyAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Allergy> getAction() {
		return allergyAction;
	}

}
