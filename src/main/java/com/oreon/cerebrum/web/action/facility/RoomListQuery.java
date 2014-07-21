package com.oreon.cerebrum.web.action.facility;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("roomList")
@Scope(ScopeType.CONVERSATION)
public class RoomListQuery extends RoomListQueryBase
		implements
			java.io.Serializable {

}
