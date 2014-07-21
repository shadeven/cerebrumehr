package com.oreon.cerebrum.web.action.patient;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.Immunization;

//
public abstract class ImmunizationActionBase extends BaseAction<Immunization>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long immunizationId;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	@In(create = true, value = "vaccineAction")
	com.oreon.cerebrum.web.action.patient.VaccineAction vaccineAction;

	public void setImmunizationId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setImmunizationIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
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

	public void setVaccineId(Long id) {

		if (id != null && id > 0)
			getInstance().setVaccine(vaccineAction.loadFromId(id));

	}

	public Long getVaccineId() {
		if (getInstance().getVaccine() != null)
			return getInstance().getVaccine().getId();
		return 0L;
	}

	public Long getImmunizationId() {
		return (Long) getId();
	}

	public Immunization getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Immunization t) {
		this.instance = t;
		loadAssociations();
	}

	public Immunization getImmunization() {
		return (Immunization) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('immunization', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('immunization', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Immunization createInstance() {
		Immunization instance = super.createInstance();

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

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

		com.oreon.cerebrum.patient.Vaccine vaccine = vaccineAction
				.getDefinedInstance();
		if (vaccine != null && isNew()) {
			getInstance().setVaccine(vaccine);
		}

	}

	public Immunization getDefinedInstance() {
		return (Immunization) (isIdDefined() ? getInstance() : null);
	}

	public void setImmunization(Immunization t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setImmunizationId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Immunization> getEntityClass() {
		return Immunization.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", instance
					.getPatient().getId()));
		}

		if (instance.getVaccine() != null) {
			criteria = criteria.add(Restrictions.eq("vaccine.id", instance
					.getVaccine().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());

			patientAction.loadAssociations();

		}

		if (getInstance().getVaccine() != null) {
			vaccineAction.setInstance(getInstance().getVaccine());

			vaccineAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewImmunization() {
		load(currentEntityId);
		return "viewImmunization";
	}

}
