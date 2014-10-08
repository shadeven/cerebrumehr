package com.oreon.cerebrum.web.action.admission;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.admission.Admission;

public class AdmissionActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Admission> {

	AdmissionAction admissionAction = new AdmissionAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Admission> getAction() {
		return admissionAction;
	}

	@Test
	public void testTransfer() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				AdmissionAction admissionAction = (AdmissionAction) org.jboss.seam.Component
						.getInstance("admissionAction");

				// assert(admissionAction.transfer()).equals("");
			}

		}.run();
	}

	@Test
	public void testDischarge() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				AdmissionAction admissionAction = (AdmissionAction) org.jboss.seam.Component
						.getInstance("admissionAction");

				// assert(admissionAction.discharge()).equals("");
			}

		}.run();
	}

}
