package com.oreon.cerebrum.web.action.appointment;

import com.oreon.cerebrum.appointment.Appointment;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

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

			"appointment.start = #{appointmentList.appointment.start}",

			"appointment.end = #{appointmentList.appointment.end}",

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
