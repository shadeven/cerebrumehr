package com.oreon.cerebrum.web.action.ddx;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.ddx.PatientFinding;

public class PatientFindingActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PatientFinding> {

	PatientFindingAction patientFindingAction = new PatientFindingAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PatientFinding> getAction() {
		return patientFindingAction;
	}

}
