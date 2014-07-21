package com.oreon.cerebrum.web.action.admission;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("admissionList")
@Scope(ScopeType.CONVERSATION)
public class AdmissionListQuery extends AdmissionListQueryBase
		implements
			java.io.Serializable {

}
