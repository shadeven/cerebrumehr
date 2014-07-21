package com.oreon.cerebrum.web.action.unusualoccurences;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("unusualOccurenceList")
@Scope(ScopeType.CONVERSATION)
public class UnusualOccurenceListQuery extends UnusualOccurenceListQueryBase
		implements
			java.io.Serializable {

}
