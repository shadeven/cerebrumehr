package com.oreon.cerebrum.web.action.prescription;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("prescriptionItemList")
@Scope(ScopeType.CONVERSATION)
public class PrescriptionItemListQuery extends PrescriptionItemListQueryBase
		implements
			java.io.Serializable {

}
