package com.oreon.cerebrum.web.action.facility;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("roomTypeList")
@Scope(ScopeType.CONVERSATION)
public class RoomTypeListQuery extends RoomTypeListQueryBase
		implements
			java.io.Serializable {

}
