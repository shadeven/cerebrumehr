package com.oreon.cerebrum.web.action.ddx;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.Finding;

public class FindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Finding> {

	FindingAction findingAction = new FindingAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Finding> getAction() {
		return findingAction;
	}

}
