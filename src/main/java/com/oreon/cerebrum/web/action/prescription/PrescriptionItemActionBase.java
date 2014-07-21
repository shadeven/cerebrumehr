package com.oreon.cerebrum.web.action.prescription;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.prescription.PrescriptionItem;

//
public abstract class PrescriptionItemActionBase
		extends
			BaseAction<PrescriptionItem> implements java.io.Serializable {

	@RequestParameter
	protected Long prescriptionItemId;

	@In(create = true, value = "drugAction")
	com.oreon.cerebrum.web.action.drugs.DrugAction drugAction;

	@In(create = true, value = "prescriptionAction")
	com.oreon.cerebrum.web.action.prescription.PrescriptionAction prescriptionAction;

	@In(create = true, value = "frequencyAction")
	com.oreon.cerebrum.web.action.prescription.FrequencyAction frequencyAction;

	public void setPrescriptionItemId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPrescriptionItemIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
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

	public void setPrescriptionId(Long id) {

		if (id != null && id > 0)
			getInstance().setPrescription(prescriptionAction.loadFromId(id));

	}

	public Long getPrescriptionId() {
		if (getInstance().getPrescription() != null)
			return getInstance().getPrescription().getId();
		return 0L;
	}

	public void setFrequencyId(Long id) {

		if (id != null && id > 0)
			getInstance().setFrequency(frequencyAction.loadFromId(id));

	}

	public Long getFrequencyId() {
		if (getInstance().getFrequency() != null)
			return getInstance().getFrequency().getId();
		return 0L;
	}

	public Long getPrescriptionItemId() {
		return (Long) getId();
	}

	public PrescriptionItem getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(PrescriptionItem t) {
		this.instance = t;
		loadAssociations();
	}

	public PrescriptionItem getPrescriptionItem() {
		return (PrescriptionItem) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('prescriptionItem', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('prescriptionItem', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected PrescriptionItem createInstance() {
		PrescriptionItem instance = super.createInstance();

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

		com.oreon.cerebrum.drugs.Drug drug = drugAction.getDefinedInstance();
		if (drug != null && isNew()) {
			getInstance().setDrug(drug);
		}

		com.oreon.cerebrum.prescription.Prescription prescription = prescriptionAction
				.getDefinedInstance();
		if (prescription != null && isNew()) {
			getInstance().setPrescription(prescription);
		}

		com.oreon.cerebrum.prescription.Frequency frequency = frequencyAction
				.getDefinedInstance();
		if (frequency != null && isNew()) {
			getInstance().setFrequency(frequency);
		}

	}

	public PrescriptionItem getDefinedInstance() {
		return (PrescriptionItem) (isIdDefined() ? getInstance() : null);
	}

	public void setPrescriptionItem(PrescriptionItem t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setPrescriptionItemId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<PrescriptionItem> getEntityClass() {
		return PrescriptionItem.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getDrug() != null) {
			criteria = criteria.add(Restrictions.eq("drug.id", instance
					.getDrug().getId()));
		}

		if (instance.getPrescription() != null) {
			criteria = criteria.add(Restrictions.eq("prescription.id", instance
					.getPrescription().getId()));
		}

		if (instance.getFrequency() != null) {
			criteria = criteria.add(Restrictions.eq("frequency.id", instance
					.getFrequency().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getDrug() != null) {
			drugAction.setInstance(getInstance().getDrug());

			drugAction.loadAssociations();

		}

		if (getInstance().getPrescription() != null) {
			prescriptionAction.setInstance(getInstance().getPrescription());

			prescriptionAction.loadAssociations();

		}

		if (getInstance().getFrequency() != null) {
			frequencyAction.setInstance(getInstance().getFrequency());

			frequencyAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewPrescriptionItem() {
		load(currentEntityId);
		return "viewPrescriptionItem";
	}

}
