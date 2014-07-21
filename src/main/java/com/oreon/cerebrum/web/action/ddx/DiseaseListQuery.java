package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("diseaseList")
@Scope(ScopeType.CONVERSATION)
public class DiseaseListQuery extends DiseaseListQueryBase
		implements
			java.io.Serializable {

}
