package com.oreon.cerebrum.web.action.patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
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
