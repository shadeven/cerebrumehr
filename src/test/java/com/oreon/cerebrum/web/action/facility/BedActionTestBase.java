package com.oreon.cerebrum.web.action.facility;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.facility.Bed;

public class BedActionTestBase extends org.witchcraft.action.test.BaseTest<Bed> {

	BedAction bedAction = new BedAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Bed> getAction() {
		return bedAction;
	}

	@Test
	public void testGetAvailableBeds() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				BedAction bedAction = (BedAction) org.jboss.seam.Component
						.getInstance("bedAction");

				// assert(bedAction.getAvailableBeds()).equals("");
			}

		}.run();
	}

}
