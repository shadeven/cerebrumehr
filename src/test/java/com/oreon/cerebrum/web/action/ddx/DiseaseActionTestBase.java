package com.oreon.cerebrum.web.action.ddx;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.Disease;

public class DiseaseActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Disease> {

	DiseaseAction diseaseAction = new DiseaseAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Disease> getAction() {
		return diseaseAction;
	}

}
