package com.oreon.cerebrum.web.action.charts;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("chartList")
@Scope(ScopeType.CONVERSATION)
public class ChartListQuery extends ChartListQueryBase
		implements
			java.io.Serializable {

}
