package com.oreon.cerebrum.web.action.patient;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("immunizationList")
@Scope(ScopeType.CONVERSATION)
public class ImmunizationListQuery extends ImmunizationListQueryBase
		implements
			java.io.Serializable {

}
