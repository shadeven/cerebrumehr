package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("specializationList")
@Scope(ScopeType.CONVERSATION)
public class SpecializationListQuery extends SpecializationListQueryBase
		implements
			java.io.Serializable {

}
