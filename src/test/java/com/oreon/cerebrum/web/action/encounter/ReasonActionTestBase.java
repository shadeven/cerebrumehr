package com.oreon.cerebrum.web.action.encounter;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.encounter.Reason;

public class ReasonActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Reason> {

	ReasonAction reasonAction = new ReasonAction();

	@Override
	public BaseAction<Reason> getAction() {
		return reasonAction;
	}

}
