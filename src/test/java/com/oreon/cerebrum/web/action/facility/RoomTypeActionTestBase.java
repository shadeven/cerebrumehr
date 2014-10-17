package com.oreon.cerebrum.web.action.facility;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.facility.RoomType;

public class RoomTypeActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<RoomType> {

	RoomTypeAction roomTypeAction = new RoomTypeAction();

	@Override
	public BaseAction<RoomType> getAction() {
		return roomTypeAction;
	}

}
