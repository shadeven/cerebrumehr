package com.oreon.cerebrum.web.action.prescription;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("frequencyList")
@Scope(ScopeType.CONVERSATION)
public class FrequencyListQuery extends FrequencyListQueryBase
		implements
			java.io.Serializable {

}
