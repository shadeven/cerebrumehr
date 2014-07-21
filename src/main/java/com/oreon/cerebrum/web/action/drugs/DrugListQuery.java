package com.oreon.cerebrum.web.action.drugs;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.oreon.cerebrum.drugs.Drug;

@Name("drugList")
@Scope(ScopeType.CONVERSATION)
public class DrugListQuery extends DrugListQueryBase
		implements
			java.io.Serializable {
	
	@Override
	public List<Drug> getAll() {
		setOrderColumn("name");
		return super.getAll();
	}

}
