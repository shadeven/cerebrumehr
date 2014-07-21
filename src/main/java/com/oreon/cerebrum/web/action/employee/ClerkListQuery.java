package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("clerkList")
@Scope(ScopeType.CONVERSATION)
public class ClerkListQuery extends ClerkListQueryBase
		implements
			java.io.Serializable {

}
