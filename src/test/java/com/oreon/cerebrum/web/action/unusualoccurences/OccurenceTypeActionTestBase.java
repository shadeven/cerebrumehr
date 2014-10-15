package com.oreon.cerebrum.web.action.unusualoccurences;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.unusualoccurences.OccurenceType;

public class OccurenceTypeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<OccurenceType> {

	OccurenceTypeAction occurenceTypeAction = new OccurenceTypeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<OccurenceType> getAction() {
		return occurenceTypeAction;
	}

}
