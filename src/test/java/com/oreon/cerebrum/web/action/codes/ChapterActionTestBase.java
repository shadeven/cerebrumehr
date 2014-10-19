package com.oreon.cerebrum.web.action.codes;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.codes.Chapter;

public class ChapterActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Chapter> {

	ChapterAction chapterAction = new ChapterAction();

	@Override
	public BaseAction<Chapter> getAction() {
		return chapterAction;
	}

}
