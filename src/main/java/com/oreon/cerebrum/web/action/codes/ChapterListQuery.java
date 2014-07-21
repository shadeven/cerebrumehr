package com.oreon.cerebrum.web.action.codes;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("chapterList")
@Scope(ScopeType.CONVERSATION)
public class ChapterListQuery extends ChapterListQueryBase
		implements
			java.io.Serializable {

}
