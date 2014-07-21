package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("chronicConditionList")
@Scope(ScopeType.CONVERSATION)
public class ChronicConditionListQuery extends ChronicConditionListQueryBase
		implements
			java.io.Serializable {

	@Override
	protected void setupForAutoComplete(String input) {
		getInstance().setName(input);
	}
	
}
