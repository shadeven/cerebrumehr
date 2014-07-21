package com.oreon.cerebrum.web.action.patient;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("vitalValueList")
@Scope(ScopeType.CONVERSATION)
public class VitalValueListQuery extends VitalValueListQueryBase
		implements
			java.io.Serializable {

}
