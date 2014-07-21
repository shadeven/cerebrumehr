package com.oreon.cerebrum.web.action.unusualoccurences;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.unusualoccurences.UnusualOccurence;

//
public abstract class UnusualOccurenceActionBase
		extends
			BaseAction<UnusualOccurence> implements java.io.Serializable {

	@RequestParameter
	protected Long unusualOccurenceId;

	@In(create = true, value = "occurenceTypeAction")
	com.oreon.cerebrum.web.action.unusualoccurences.OccurenceTypeAction occurenceTypeAction;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	public void setUnusualOccurenceId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setUnusualOccurenceIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setOccurenceTypeId(Long id) {

		if (id != null && id > 0)
			getInstance().setOccurenceType(occurenceTypeAction.loadFromId(id));

	}

	public Long getOccurenceTypeId() {
		if (getInstance().getOccurenceType() != null)
			return getInstance().getOccurenceType().getId();
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

	public Long getUnusualOccurenceId() {
		return (Long) getId();
	}

	public UnusualOccurence getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(UnusualOccurence t) {
		this.instance = t;
		loadAssociations();
	}

	public UnusualOccurence getUnusualOccurence() {
		return (UnusualOccurence) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('unusualOccurence', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('unusualOccurence', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected UnusualOccurence createInstance() {
		UnusualOccurence instance = super.createInstance();

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

		com.oreon.cerebrum.unusualoccurences.OccurenceType occurenceType = occurenceTypeAction
				.getDefinedInstance();
		if (occurenceType != null && isNew()) {
			getInstance().setOccurenceType(occurenceType);
		}

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public UnusualOccurence getDefinedInstance() {
		return (UnusualOccurence) (isIdDefined() ? getInstance() : null);
	}

	public void setUnusualOccurence(UnusualOccurence t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setUnusualOccurenceId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<UnusualOccurence> getEntityClass() {
		return UnusualOccurence.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getOccurenceType() != null) {
			criteria = criteria.add(Restrictions.eq("occurenceType.id",
					instance.getOccurenceType().getId()));
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

		if (getInstance().getOccurenceType() != null) {
			occurenceTypeAction.setInstance(getInstance().getOccurenceType());

			occurenceTypeAction.loadAssociations();

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

	public String viewUnusualOccurence() {
		load(currentEntityId);
		return "viewUnusualOccurence";
	}

}
