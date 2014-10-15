package com.oreon.cerebrum.web.action.admission;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.admission.BedStay;

public class BedStayActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<BedStay> {

	BedStayAction bedStayAction = new BedStayAction();

	

	@Override
	public BaseAction<BedStay> getAction() {
		return bedStayAction;
	}

}
