package com.oreon.cerebrum.web.action.encounter;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("prescribedTestList")
@Scope(ScopeType.CONVERSATION)
public class PrescribedTestListQuery extends PrescribedTestListQueryBase
		implements
			java.io.Serializable {

}
