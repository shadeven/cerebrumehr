package com.oreon.cerebrum.web.action.drugs;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("drugCategoryList")
@Scope(ScopeType.CONVERSATION)
public class DrugCategoryListQuery extends DrugCategoryListQueryBase
		implements
			java.io.Serializable {

}
