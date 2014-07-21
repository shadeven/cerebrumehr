package com.oreon.cerebrum.web.action.patient;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("allergenList")
@Scope(ScopeType.CONVERSATION)
public class AllergenListQuery extends AllergenListQueryBase
		implements
			java.io.Serializable {

}
