package com.oreon.cerebrum.web.action.charts;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("chartItemList")
@Scope(ScopeType.CONVERSATION)
public class ChartItemListQuery extends ChartItemListQueryBase
		implements
			java.io.Serializable {

}
