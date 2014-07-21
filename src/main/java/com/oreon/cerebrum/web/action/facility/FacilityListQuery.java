package com.oreon.cerebrum.web.action.facility;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("facilityList")
@Scope(ScopeType.CONVERSATION)
public class FacilityListQuery extends FacilityListQueryBase
		implements
			java.io.Serializable {

}
