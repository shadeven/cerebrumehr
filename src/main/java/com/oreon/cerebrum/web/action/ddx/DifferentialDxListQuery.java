package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("differentialDxList")
@Scope(ScopeType.CONVERSATION)
public class DifferentialDxListQuery extends DifferentialDxListQueryBase
		implements
			java.io.Serializable {

}
