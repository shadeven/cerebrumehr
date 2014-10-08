package com.oreon.cerebrum.web.action.prescription;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.prescription.Prescription;

public class PrescriptionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Prescription> {

	PrescriptionAction prescriptionAction = new PrescriptionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Prescription> getAction() {
		return prescriptionAction;
	}

}
