package com.oreon.cerebrum.web.action.facility;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.facility.Room;

public class RoomActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Room> {

	RoomAction roomAction = new RoomAction();

	@Override
	public BaseAction<Room> getAction() {
		return roomAction;
	}

}
