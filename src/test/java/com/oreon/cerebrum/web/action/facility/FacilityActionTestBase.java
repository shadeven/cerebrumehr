package com.oreon.cerebrum.web.action.facility;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.facility.Facility;

public class FacilityActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Facility> {

	FacilityAction facilityAction = new FacilityAction();

	@Override
	public BaseAction<Facility> getAction() {
		return facilityAction;
	}

}
