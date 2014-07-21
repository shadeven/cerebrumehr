package com.oreon.cerebrum.web.action.prescription;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("prescriptionTemplateList")
@Scope(ScopeType.CONVERSATION)
public class PrescriptionTemplateListQuery
		extends
			PrescriptionTemplateListQueryBase implements java.io.Serializable {

}
