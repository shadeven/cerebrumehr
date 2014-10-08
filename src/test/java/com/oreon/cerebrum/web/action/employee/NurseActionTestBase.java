package com.oreon.cerebrum.web.action.employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.employee.Nurse;

public class NurseActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Nurse> {

	NurseAction nurseAction = new NurseAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Nurse> getAction() {
		return nurseAction;
	}

}
