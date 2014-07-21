package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("patientDiffDxList")
@Scope(ScopeType.CONVERSATION)
public class PatientDiffDxListQuery extends PatientDiffDxListQueryBase
		implements
			java.io.Serializable {

}
