package com.oreon.cerebrum.web.action.prescription;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("prescriptionList")
@Scope(ScopeType.CONVERSATION)
public class PrescriptionListQuery extends PrescriptionListQueryBase
		implements
			java.io.Serializable {

}
