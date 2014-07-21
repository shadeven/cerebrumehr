package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("findingList")
@Scope(ScopeType.CONVERSATION)
public class FindingListQuery extends FindingListQueryBase
		implements
			java.io.Serializable {

}
