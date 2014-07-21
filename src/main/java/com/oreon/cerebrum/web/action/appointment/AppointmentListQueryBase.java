package com.oreon.cerebrum.web.action.appointment;

import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.appointment.Appointment;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AppointmentListQueryBase
		extends
			BaseQuery<Appointment, Long> {

	private static final String EJBQL = "select appointment from Appointment appointment";

	protected Appointment appointment = new Appointment();

	public AppointmentListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Appointment getAppointment() {
		return appointment;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Appointment getInstance() {
		return getAppointment();
	}

	@Override
	//@Restrict("#{s:hasPermission('appointment', 'view')}")
	public List<Appointment> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Appointment> getEntityClass() {
		return Appointment.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> startRange = new Range<Date>();

	public Range<Date> getStartRange() {
		return startRange;
	}
	public void setStart(Range<Date> startRange) {
		this.startRange = startRange;
	}

	private Range<Date> endRange = new Range<Date>();

	public Range<Date> getEndRange() {
		return endRange;
	}
	public void setEnd(Range<Date> endRange) {
		this.endRange = endRange;
	}

	private Range<Integer> unitsRange = new Range<Integer>();

	public Range<Integer> getUnitsRange() {
		return unitsRange;
	}
	public void setUnits(Range<Integer> unitsRange) {
		this.unitsRange = unitsRange;
	}

	private static final String[] RESTRICTIONS = {
			"appointment.id = #{appointmentList.appointment.id}",

			"appointment.archived = #{appointmentList.appointment.archived}",

			"appointment.start >= #{appointmentList.startRange.begin}",
			"appointment.start <= #{appointmentList.startRange.end}",

			"appointment.end >= #{appointmentList.endRange.begin}",
			"appointment.end <= #{appointmentList.endRange.end}",

			"appointment.physician.id = #{appointmentList.appointment.physician.id}",

			"appointment.patient.id = #{appointmentList.appointment.patient.id}",

			"lower(appointment.remarks) like concat(lower(#{appointmentList.appointment.remarks}),'%')",

			"appointment.units >= #{appointmentList.unitsRange.begin}",
			"appointment.units <= #{appointmentList.unitsRange.end}",

			"appointment.dateCreated <= #{appointmentList.dateCreatedRange.end}",
			"appointment.dateCreated >= #{appointmentList.dateCreatedRange.begin}",};

	@Observer("archivedAppointment")
	public void onArchive() {
		refresh();
	}

	public void setPhysicianId(Long id) {
		if (appointment.getPhysician() == null) {
			appointment
					.setPhysician(new com.oreon.cerebrum.employee.Physician());
		}
		appointment.getPhysician().setId(id);
	}

	public Long getPhysicianId() {
		return appointment.getPhysician() == null ? null : appointment
				.getPhysician().getId();
	}

	public void setPatientId(Long id) {
		if (appointment.getPatient() == null) {
			appointment.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		appointment.getPatient().setId(id);
	}

	public Long getPatientId() {
		return appointment.getPatient() == null ? null : appointment
				.getPatient().getId();
	}

}
