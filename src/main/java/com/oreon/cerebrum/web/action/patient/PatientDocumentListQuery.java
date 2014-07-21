package com.oreon.cerebrum.web.action.patient;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("patientDocumentList")
@Scope(ScopeType.CONVERSATION)
public class PatientDocumentListQuery extends PatientDocumentListQueryBase
		implements
			java.io.Serializable {

}
