package com.oreon.cerebrum.web.action.facility;

import org.junit.BeforeClass;
import org.junit.Test;
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
