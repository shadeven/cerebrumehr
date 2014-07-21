package com.oreon.cerebrum.web.action.drugs;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("drugInteractionList")
@Scope(ScopeType.CONVERSATION)
public class DrugInteractionListQuery extends DrugInteractionListQueryBase
		implements
			java.io.Serializable {

}
