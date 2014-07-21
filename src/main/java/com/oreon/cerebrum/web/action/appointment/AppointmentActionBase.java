package com.oreon.cerebrum.web.action.appointment;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.appointment.Appointment;

//
public abstract class AppointmentActionBase extends BaseAction<Appointment>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long appointmentId;

	@In(create = true, value = "physicianAction")
	com.oreon.cerebrum.web.action.employee.PhysicianAction physicianAction;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	public void setAppointmentId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAppointmentIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setPhysicianId(Long id) {

		if (id != null && id > 0)
			getInstance().setPhysician(physicianAction.loadFromId(id));

	}

	public Long getPhysicianId() {
		if (getInstance().getPhysician() != null)
			return getInstance().getPhysician().getId();
		return 0L;
	}

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
		return 0L;
	}

	public Long getAppointmentId() {
		return (Long) getId();
	}

	public Appointment getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Appointment t) {
		this.instance = t;
		loadAssociations();
	}

	public Appointment getAppointment() {
		return (Appointment) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('appointment', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('appointment', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Appointment createInstance() {
		Appointment instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.employee.Physician physician = physicianAction
				.getDefinedInstance();
		if (physician != null && isNew()) {
			getInstance().setPhysician(physician);
		}

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public Appointment getDefinedInstance() {
		return (Appointment) (isIdDefined() ? getInstance() : null);
	}

	public void setAppointment(Appointment t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setAppointmentId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Appointment> getEntityClass() {
		return Appointment.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getPhysician() != null) {
			criteria = criteria.add(Restrictions.eq("physician.id", instance
					.getPhysician().getId()));
		}

		if (instance.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", instance
					.getPatient().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getPhysician() != null) {
			physicianAction.setInstance(getInstance().getPhysician());

			physicianAction.loadAssociations();

		}

		if (getInstance().getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());

			patientAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewAppointment() {
		load(currentEntityId);
		return "viewAppointment";
	}

}
