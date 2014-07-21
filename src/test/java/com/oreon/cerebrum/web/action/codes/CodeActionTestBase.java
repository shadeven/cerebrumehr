package com.oreon.cerebrum.web.action.codes;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.codes.Code;

public class CodeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Code> {

	CodeAction codeAction = new CodeAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Code> getAction() {
		return codeAction;
	}

}
