package com.oreon.cerebrum.web.action.patient;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.patient.Vaccine;

public class VaccineActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Vaccine> {

	VaccineAction vaccineAction = new VaccineAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Vaccine> getAction() {
		return vaccineAction;
	}

}
