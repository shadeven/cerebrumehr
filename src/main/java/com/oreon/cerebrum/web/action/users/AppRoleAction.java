
	
package com.oreon.cerebrum.web.action.users;
	

import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.users.AppRole;

	
//@Scope(ScopeType.CONVERSATION)
@Name("appRoleAction")
public class AppRoleAction extends AppRoleActionBase implements java.io.Serializable{
	
	
	public AppRole getOrCreateRoleByName(String name){
		AppRole role = findByUnqName(name);
		if(role == null){
			 role = new AppRole();
			 role.setName(name);
			 persist(role);
		}
		
		return role;
	}
	
	public String getRolePhysician(){
		return null;
	}
}
	