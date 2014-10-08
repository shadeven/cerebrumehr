package com.oreon.cerebrum.web.action.ddx;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.LabFinding;

public class LabFindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<LabFinding> {

	LabFindingAction labFindingAction = new LabFindingAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<LabFinding> getAction() {
		return labFindingAction;
	}

}
