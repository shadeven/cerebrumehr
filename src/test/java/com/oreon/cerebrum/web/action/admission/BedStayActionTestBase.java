package com.oreon.cerebrum.web.action.admission;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.admission.BedStay;

public class BedStayActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<BedStay> {

	BedStayAction bedStayAction = new BedStayAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<BedStay> getAction() {
		return bedStayAction;
	}

}
