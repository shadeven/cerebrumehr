package com.oreon.cerebrum.web.action.unusualoccurences;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("occurenceTypeList")
@Scope(ScopeType.CONVERSATION)
public class OccurenceTypeListQuery extends OccurenceTypeListQueryBase
		implements
			java.io.Serializable {

}
