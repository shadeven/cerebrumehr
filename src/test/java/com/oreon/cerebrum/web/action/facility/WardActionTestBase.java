package com.oreon.cerebrum.web.action.facility;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.facility.Ward;

public class WardActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Ward> {

	WardAction wardAction = new WardAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Ward> getAction() {
		return wardAction;
	}

}
