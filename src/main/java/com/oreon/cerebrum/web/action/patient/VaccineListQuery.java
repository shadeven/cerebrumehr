package com.oreon.cerebrum.web.action.patient;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("vaccineList")
@Scope(ScopeType.CONVERSATION)
public class VaccineListQuery extends VaccineListQueryBase
		implements
			java.io.Serializable {

}
