package com.oreon.cerebrum.web.action.users;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("appRoleList")
@Scope(ScopeType.CONVERSATION)
public class AppRoleListQuery extends AppRoleListQueryBase
		implements
			java.io.Serializable {

}
