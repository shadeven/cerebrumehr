package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("nurseList")
@Scope(ScopeType.CONVERSATION)
public class NurseListQuery extends NurseListQueryBase
		implements
			java.io.Serializable {

}
