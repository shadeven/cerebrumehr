package com.oreon.cerebrum.web.action.unusualoccurences;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.unusualoccurences.UnusualOccurence;

public class UnusualOccurenceActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<UnusualOccurence> {

	UnusualOccurenceAction unusualOccurenceAction = new UnusualOccurenceAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<UnusualOccurence> getAction() {
		return unusualOccurenceAction;
	}

}
