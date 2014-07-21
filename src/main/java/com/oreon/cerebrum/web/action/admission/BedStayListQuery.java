package com.oreon.cerebrum.web.action.admission;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("bedStayList")
@Scope(ScopeType.CONVERSATION)
public class BedStayListQuery extends BedStayListQueryBase
		implements
			java.io.Serializable {

}
