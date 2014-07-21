package com.oreon.cerebrum.web.action.charts;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("appliedChartList")
@Scope(ScopeType.CONVERSATION)
public class AppliedChartListQuery extends AppliedChartListQueryBase
		implements
			java.io.Serializable {

}
