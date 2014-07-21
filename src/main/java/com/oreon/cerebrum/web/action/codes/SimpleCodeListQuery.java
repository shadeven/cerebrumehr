package com.oreon.cerebrum.web.action.codes;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("simpleCodeList")
@Scope(ScopeType.CONVERSATION)
public class SimpleCodeListQuery extends SimpleCodeListQueryBase
		implements
			java.io.Serializable {
	
	
	@Override
	protected void setupForAutoComplete(String input) {
		getInstance().setName(input);
	}

}
