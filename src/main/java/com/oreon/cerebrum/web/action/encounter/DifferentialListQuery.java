package com.oreon.cerebrum.web.action.encounter;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("differentialList")
@Scope(ScopeType.CONVERSATION)
public class DifferentialListQuery extends DifferentialListQueryBase
		implements
			java.io.Serializable {

}
