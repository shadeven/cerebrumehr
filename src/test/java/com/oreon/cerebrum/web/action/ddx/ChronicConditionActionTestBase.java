package com.oreon.cerebrum.web.action.ddx;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.ChronicCondition;

public class ChronicConditionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ChronicCondition> {

	ChronicConditionAction chronicConditionAction = new ChronicConditionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ChronicCondition> getAction() {
		return chronicConditionAction;
	}

}
