package com.oreon.cerebrum.web.action.facility;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("bedList")
@Scope(ScopeType.CONVERSATION)
public class BedListQuery extends BedListQueryBase
		implements
			java.io.Serializable {

}
