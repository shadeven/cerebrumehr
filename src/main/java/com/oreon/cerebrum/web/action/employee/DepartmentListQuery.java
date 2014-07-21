package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("departmentList")
@Scope(ScopeType.CONVERSATION)
public class DepartmentListQuery extends DepartmentListQueryBase
		implements
			java.io.Serializable {

}
