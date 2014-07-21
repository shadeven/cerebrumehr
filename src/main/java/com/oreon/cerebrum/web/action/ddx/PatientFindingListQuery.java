package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("patientFindingList")
@Scope(ScopeType.CONVERSATION)
public class PatientFindingListQuery extends PatientFindingListQueryBase
		implements
			java.io.Serializable {

}
