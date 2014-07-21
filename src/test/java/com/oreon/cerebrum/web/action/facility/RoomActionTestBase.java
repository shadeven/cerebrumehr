package com.oreon.cerebrum.web.action.facility;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.facility.Room;

public class RoomActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Room> {

	RoomAction roomAction = new RoomAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Room> getAction() {
		return roomAction;
	}

}
