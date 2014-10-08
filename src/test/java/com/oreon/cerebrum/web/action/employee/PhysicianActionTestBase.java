package com.oreon.cerebrum.web.action.employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.employee.Physician;

public class PhysicianActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Physician> {

	PhysicianAction physicianAction = new PhysicianAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Physician> getAction() {
		return physicianAction;
	}

}
