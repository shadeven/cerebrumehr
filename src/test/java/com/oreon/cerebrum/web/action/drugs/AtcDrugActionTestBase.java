package com.oreon.cerebrum.web.action.drugs;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.drugs.AtcDrug;

public class AtcDrugActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AtcDrug> {

	AtcDrugAction atcDrugAction = new AtcDrugAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AtcDrug> getAction() {
		return atcDrugAction;
	}

}
