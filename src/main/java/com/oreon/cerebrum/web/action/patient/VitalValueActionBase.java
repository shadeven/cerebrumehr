package com.oreon.cerebrum.web.action.patient;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.VitalValue;

//
public abstract class VitalValueActionBase extends BaseAction<VitalValue>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long vitalValueId;

	@In(create = true, value = "trackedVitalAction")
	com.oreon.cerebrum.web.action.patient.TrackedVitalAction trackedVitalAction;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	public void setVitalValueId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setVitalValueIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setTrackedVitalId(Long id) {

		if (id != null && id > 0)
			getInstance().setTrackedVital(trackedVitalAction.loadFromId(id));

	}

	public Long getTrackedVitalId() {
		if (getInstance().getTrackedVital() != null)
			return getInstance().getTrackedVital().getId();
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

	public Long getVitalValueId() {
		return (Long) getId();
	}

	public VitalValue getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(VitalValue t) {
		this.instance = t;
		loadAssociations();
	}

	public VitalValue getVitalValue() {
		return (VitalValue) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('vitalValue', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('vitalValue', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected VitalValue createInstance() {
		VitalValue instance = super.createInstance();

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

		com.oreon.cerebrum.patient.TrackedVital trackedVital = trackedVitalAction
				.getDefinedInstance();
		if (trackedVital != null && isNew()) {
			getInstance().setTrackedVital(trackedVital);
		}

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public VitalValue getDefinedInstance() {
		return (VitalValue) (isIdDefined() ? getInstance() : null);
	}

	public void setVitalValue(VitalValue t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setVitalValueId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<VitalValue> getEntityClass() {
		return VitalValue.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getTrackedVital() != null) {
			criteria = criteria.add(Restrictions.eq("trackedVital.id", instance
					.getTrackedVital().getId()));
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

		if (getInstance().getTrackedVital() != null) {
			trackedVitalAction.setInstance(getInstance().getTrackedVital());

			trackedVitalAction.loadAssociations();

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

	public String viewVitalValue() {
		load(currentEntityId);
		return "viewVitalValue";
	}

}
