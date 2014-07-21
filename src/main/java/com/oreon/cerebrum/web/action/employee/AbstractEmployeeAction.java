package com.oreon.cerebrum.web.action.employee;

import java.util.Date;

import org.jboss.seam.annotations.In;
import org.witchcraft.base.entity.UserUtilAction;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.web.action.users.AppRoleAction;

public abstract class AbstractEmployeeAction<T extends com.oreon.cerebrum.employee.Employee>
		extends
			BaseAction<T> implements java.io.Serializable {
	
	
	@In(create = true)
	AppRoleAction appRoleAction;
	
	@In(create = true)
	UserUtilAction userUtilAction;
	
	abstract String getDefaultRoleName();

	
	//add role and employee number 
	@Override
	public void preSave() {
		if(!isNew())
			return;
		addRole();
		//getInstance().setEmployeeNumber(createEmployeeNumber(getInstance()));
	}
	
	protected void addRole() {
		getInstance().getAppUser().addAppRole(appRoleAction.getOrCreateRoleByName(getClassName().toLowerCase()));
	}
	

	@Override
	//add current user's facility to the newly created employee
	protected T createInstance() {
		T result = super.createInstance();
		result.setFacility(userUtilAction.getCurrentFacility());
		
		return result;
	}

	private String createEmployeeNumber(T result) {
		// TODO Auto-generated method stub
		return getDefaultRoleName().substring(0,1) +  result.getFacility().getId() + "-" + new Date().getTime();
	}
	
	

}
