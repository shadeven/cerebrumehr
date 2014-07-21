package com.oreon.cerebrum.web.action.patient;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.Allergy;

//
public abstract class AllergyActionBase extends BaseAction<Allergy>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long allergyId;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	@In(create = true, value = "drugAction")
	com.oreon.cerebrum.web.action.drugs.DrugAction drugAction;

	public void setAllergyId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAllergyIdForModalDlg(Long id) {
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

	public void setDrugId(Long id) {

		if (id != null && id > 0)
			getInstance().setDrug(drugAction.loadFromId(id));

	}

	public Long getDrugId() {
		if (getInstance().getDrug() != null)
			return getInstance().getDrug().getId();
		return 0L;
	}

	public Long getAllergyId() {
		return (Long) getId();
	}

	public Allergy getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Allergy t) {
		this.instance = t;
		loadAssociations();
	}

	public Allergy getAllergy() {
		return (Allergy) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('allergy', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('allergy', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Allergy createInstance() {
		Allergy instance = super.createInstance();

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

		com.oreon.cerebrum.drugs.Drug drug = drugAction.getDefinedInstance();
		if (drug != null && isNew()) {
			getInstance().setDrug(drug);
		}

	}

	public Allergy getDefinedInstance() {
		return (Allergy) (isIdDefined() ? getInstance() : null);
	}

	public void setAllergy(Allergy t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setAllergyId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Allergy> getEntityClass() {
		return Allergy.class;
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

		if (instance.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", instance
					.getDrug().getId()));
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

		if (getInstance().getDrug() != null) {
			drugAction.setInstance(getInstance().getDrug());

			drugAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewAllergy() {
		load(currentEntityId);
		return "viewAllergy";
	}

}
