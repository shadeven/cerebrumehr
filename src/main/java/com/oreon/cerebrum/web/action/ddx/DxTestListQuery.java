package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("dxTestList")
@Scope(ScopeType.CONVERSATION)
public class DxTestListQuery extends DxTestListQueryBase
		implements
			java.io.Serializable {

}
