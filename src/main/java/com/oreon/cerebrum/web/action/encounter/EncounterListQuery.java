package com.oreon.cerebrum.web.action.encounter;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("encounterList")
@Scope(ScopeType.CONVERSATION)
public class EncounterListQuery extends EncounterListQueryBase
		implements
			java.io.Serializable {

}
