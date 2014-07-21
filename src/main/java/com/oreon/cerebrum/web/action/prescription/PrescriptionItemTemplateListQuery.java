package com.oreon.cerebrum.web.action.prescription;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("prescriptionItemTemplateList")
@Scope(ScopeType.CONVERSATION)
public class PrescriptionItemTemplateListQuery
		extends
			PrescriptionItemTemplateListQueryBase
		implements
			java.io.Serializable {

}
