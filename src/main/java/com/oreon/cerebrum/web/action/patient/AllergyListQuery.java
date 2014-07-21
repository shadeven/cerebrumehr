package com.oreon.cerebrum.web.action.patient;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("allergyList")
@Scope(ScopeType.CONVERSATION)
public class AllergyListQuery extends AllergyListQueryBase
		implements
			java.io.Serializable {

}
