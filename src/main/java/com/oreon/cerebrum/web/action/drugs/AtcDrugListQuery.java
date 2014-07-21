package com.oreon.cerebrum.web.action.drugs;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("atcDrugList")
@Scope(ScopeType.CONVERSATION)
public class AtcDrugListQuery extends AtcDrugListQueryBase
		implements
			java.io.Serializable {

}
