package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("technicianList")
@Scope(ScopeType.CONVERSATION)
public class TechnicianListQuery extends TechnicianListQueryBase
		implements
			java.io.Serializable {

}
