package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.web.action.users.AppRoleAction;

//@Scope(ScopeType.CONVERSATION)
@Name("physicianAction")
public class PhysicianAction extends PhysicianActionBase
		implements
			java.io.Serializable {
	
	@In(create=true)
	AppRoleAction appRoleAction;
	
	
	@Override
	public String save() {
		preSave();
		return super.save();
	}

	
	

}
