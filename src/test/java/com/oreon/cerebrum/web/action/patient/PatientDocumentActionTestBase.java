package com.oreon.cerebrum.web.action.patient;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.PatientDocument;

public class PatientDocumentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PatientDocument> {

	PatientDocumentAction patientDocumentAction = new PatientDocumentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<PatientDocument> getAction() {
		return patientDocumentAction;
	}

}
