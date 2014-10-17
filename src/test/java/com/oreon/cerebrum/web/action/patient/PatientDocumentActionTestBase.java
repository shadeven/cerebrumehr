package com.oreon.cerebrum.web.action.patient;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.patient.PatientDocument;

public class PatientDocumentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<PatientDocument> {

	PatientDocumentAction patientDocumentAction = new PatientDocumentAction();

	@Override
	public BaseAction<PatientDocument> getAction() {
		return patientDocumentAction;
	}

}
