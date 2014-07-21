package com.oreon.cerebrum.web.action.appointment;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.oreon.cerebrum.appointment.Appointment;
import com.oreon.cerebrum.employee.Physician;

@Name("appointmentList")
@Scope(ScopeType.CONVERSATION)
public class AppointmentListQuery extends AppointmentListQueryBase
		implements
			java.io.Serializable {

	
	//@Override
	public  List<Appointment> getAppointmentsByPhysician (Physician physician) {
		
		// TODO Auto-generated method stub
		appointment.setPhysician(physician);
		return getResultList();
	}
}
