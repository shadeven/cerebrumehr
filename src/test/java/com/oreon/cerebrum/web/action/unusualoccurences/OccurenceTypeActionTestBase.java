package com.oreon.cerebrum.web.action.unusualoccurences;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.unusualoccurences.OccurenceType;

public class OccurenceTypeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<OccurenceType> {

	OccurenceTypeAction occurenceTypeAction = new OccurenceTypeAction();

	@Override
	public BaseAction<OccurenceType> getAction() {
		return occurenceTypeAction;
	}

}
