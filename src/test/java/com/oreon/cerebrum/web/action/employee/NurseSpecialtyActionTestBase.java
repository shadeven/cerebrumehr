package com.oreon.cerebrum.web.action.employee;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.employee.NurseSpecialty;

public class NurseSpecialtyActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<NurseSpecialty> {

	NurseSpecialtyAction nurseSpecialtyAction = new NurseSpecialtyAction();

	@Override
	public BaseAction<NurseSpecialty> getAction() {
		return nurseSpecialtyAction;
	}

}
