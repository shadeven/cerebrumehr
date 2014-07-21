package com.oreon.cerebrum.web.action.facility;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("wardList")
@Scope(ScopeType.CONVERSATION)
public class WardListQuery extends WardListQueryBase
		implements
			java.io.Serializable {

}
