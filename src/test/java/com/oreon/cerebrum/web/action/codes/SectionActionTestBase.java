package com.oreon.cerebrum.web.action.codes;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.codes.Section;

public class SectionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Section> {

	SectionAction sectionAction = new SectionAction();

	@Override
	public BaseAction<Section> getAction() {
		return sectionAction;
	}

}
