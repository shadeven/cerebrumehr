package com.oreon.cerebrum.web.action.ddx;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.PhysicalFinding;

public class PhysicalFindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PhysicalFinding> {

	PhysicalFindingAction physicalFindingAction = new PhysicalFindingAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PhysicalFinding> getAction() {
		return physicalFindingAction;
	}

}
