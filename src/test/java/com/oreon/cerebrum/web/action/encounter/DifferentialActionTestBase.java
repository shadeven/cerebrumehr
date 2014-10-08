package com.oreon.cerebrum.web.action.encounter;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.encounter.Differential;

public class DifferentialActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Differential> {

	DifferentialAction differentialAction = new DifferentialAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Differential> getAction() {
		return differentialAction;
	}

}
