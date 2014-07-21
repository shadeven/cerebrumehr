package com.oreon.cerebrum.web.action.billing;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("serviceList")
@Scope(ScopeType.CONVERSATION)
public class ServiceListQuery extends ServiceListQueryBase
		implements
			java.io.Serializable {

}
