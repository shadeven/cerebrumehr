package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("nurseSpecialtyList")
@Scope(ScopeType.CONVERSATION)
public class NurseSpecialtyListQuery extends NurseSpecialtyListQueryBase
		implements
			java.io.Serializable {

}
