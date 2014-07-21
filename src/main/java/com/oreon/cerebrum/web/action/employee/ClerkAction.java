package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.annotations.Name;

//@Scope(ScopeType.CONVERSATION)
@Name("clerkAction")
public class ClerkAction extends ClerkActionBase
		implements
			java.io.Serializable {

	@Override
	String getDefaultRoleName() {
		// TODO Auto-generated method stub
		return "clerk";
	}

}
