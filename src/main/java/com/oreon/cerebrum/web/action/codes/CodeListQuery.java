package com.oreon.cerebrum.web.action.codes;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("codeList")
@Scope(ScopeType.CONVERSATION)
public class CodeListQuery extends CodeListQueryBase
		implements
			java.io.Serializable {

}
