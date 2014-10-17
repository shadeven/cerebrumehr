package com.oreon.cerebrum.web.action.unusualoccurences;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.unusualoccurences.UnusualOccurence;

public class UnusualOccurenceActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<UnusualOccurence> {

	UnusualOccurenceAction unusualOccurenceAction = new UnusualOccurenceAction();

	@Override
	public BaseAction<UnusualOccurence> getAction() {
		return unusualOccurenceAction;
	}

}
