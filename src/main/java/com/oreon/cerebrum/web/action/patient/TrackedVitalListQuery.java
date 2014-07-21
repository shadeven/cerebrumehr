package com.oreon.cerebrum.web.action.patient;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("trackedVitalList")
@Scope(ScopeType.CONVERSATION)
public class TrackedVitalListQuery extends TrackedVitalListQueryBase
		implements
			java.io.Serializable {

}
