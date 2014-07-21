package com.oreon.cerebrum.web.action.users;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("appUserList")
@Scope(ScopeType.CONVERSATION)
public class AppUserListQuery extends AppUserListQueryBase
		implements
			java.io.Serializable {

}
