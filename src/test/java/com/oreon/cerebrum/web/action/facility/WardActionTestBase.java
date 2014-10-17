package com.oreon.cerebrum.web.action.facility;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.facility.Ward;

public class WardActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Ward> {

	WardAction wardAction = new WardAction();

	@Override
	public BaseAction<Ward> getAction() {
		return wardAction;
	}

}
