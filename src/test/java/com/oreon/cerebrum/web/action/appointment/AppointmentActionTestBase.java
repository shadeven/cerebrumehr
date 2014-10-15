package com.oreon.cerebrum.web.action.appointment;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.appointment.Appointment;

public class AppointmentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Appointment> {

	AppointmentAction appointmentAction = new AppointmentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Appointment> getAction() {
		return appointmentAction;
	}

}
