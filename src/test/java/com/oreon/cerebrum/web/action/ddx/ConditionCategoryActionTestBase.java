package com.oreon.cerebrum.web.action.ddx;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.ConditionCategory;

public class ConditionCategoryActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ConditionCategory> {

	ConditionCategoryAction conditionCategoryAction = new ConditionCategoryAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ConditionCategory> getAction() {
		return conditionCategoryAction;
	}

}
