package com.oreon.cerebrum.web.action.codes;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("sectionList")
@Scope(ScopeType.CONVERSATION)
public class SectionListQuery extends SectionListQueryBase
		implements
			java.io.Serializable {

}
