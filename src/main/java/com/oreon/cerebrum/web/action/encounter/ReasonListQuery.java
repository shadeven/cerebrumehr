package com.oreon.cerebrum.web.action.encounter;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("reasonList")
@Scope(ScopeType.CONVERSATION)
public class ReasonListQuery extends ReasonListQueryBase
		implements
			java.io.Serializable {

}
