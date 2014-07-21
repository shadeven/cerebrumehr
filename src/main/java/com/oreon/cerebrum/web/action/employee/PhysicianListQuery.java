package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("physicianList")
@Scope(ScopeType.CONVERSATION)
public class PhysicianListQuery extends PhysicianListQueryBase
		implements
			java.io.Serializable {

}
