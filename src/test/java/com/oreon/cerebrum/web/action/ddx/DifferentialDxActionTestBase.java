package com.oreon.cerebrum.web.action.ddx;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.DifferentialDx;

public class DifferentialDxActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<DifferentialDx> {

	DifferentialDxAction differentialDxAction = new DifferentialDxAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<DifferentialDx> getAction() {
		return differentialDxAction;
	}

}
