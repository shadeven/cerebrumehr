package com.oreon.cerebrum.web.action.ddx;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.Disease;

public class DiseaseActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Disease> {

	DiseaseAction diseaseAction = new DiseaseAction();

	@Override
	public BaseAction<Disease> getAction() {
		return diseaseAction;
	}

}
