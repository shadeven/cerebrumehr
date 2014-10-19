package com.oreon.cerebrum.web.action.codes;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.codes.Code;

public class CodeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Code> {

	CodeAction codeAction = new CodeAction();

	@Override
	public BaseAction<Code> getAction() {
		return codeAction;
	}

}
