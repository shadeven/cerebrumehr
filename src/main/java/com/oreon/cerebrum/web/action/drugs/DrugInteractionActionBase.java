package com.oreon.cerebrum.web.action.drugs;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.drugs.DrugInteraction;

//
public abstract class DrugInteractionActionBase
		extends
			BaseAction<DrugInteraction> implements java.io.Serializable {

	@RequestParameter
	protected Long drugInteractionId;

	@In(create = true, value = "drugAction")
	com.oreon.cerebrum.web.action.drugs.DrugAction drugAction;

	@In(create = true, value = "drugAction")
	com.oreon.cerebrum.web.action.drugs.DrugAction interactingDrugAction;

	public void setDrugInteractionId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDrugInteractionIdForModalDlg(Long id) {
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

	public void setInteractingDrugId(Long id) {

		if (id != null && id > 0)
			getInstance().setInteractingDrug(
					interactingDrugAction.loadFromId(id));

	}

	public Long getInteractingDrugId() {
		if (getInstance().getInteractingDrug() != null)
			return getInstance().getInteractingDrug().getId();
		return 0L;
	}

	public Long getDrugInteractionId() {
		return (Long) getId();
	}

	public DrugInteraction getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(DrugInteraction t) {
		this.instance = t;
		loadAssociations();
	}

	public DrugInteraction getDrugInteraction() {
		return (DrugInteraction) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('drugInteraction', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('drugInteraction', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected DrugInteraction createInstance() {
		DrugInteraction instance = super.createInstance();

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

		com.oreon.cerebrum.drugs.Drug interactingDrug = interactingDrugAction
				.getDefinedInstance();
		if (interactingDrug != null && isNew()) {
			getInstance().setInteractingDrug(interactingDrug);
		}

	}

	public DrugInteraction getDefinedInstance() {
		return (DrugInteraction) (isIdDefined() ? getInstance() : null);
	}

	public void setDrugInteraction(DrugInteraction t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setDrugInteractionId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<DrugInteraction> getEntityClass() {
		return DrugInteraction.class;
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

		if (instance.getInteractingDrug() != null) {
			criteria = criteria.add(Restrictions.eq("interactingDrug.id",
					instance.getInteractingDrug().getId()));
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

		if (getInstance().getInteractingDrug() != null) {
			interactingDrugAction.setInstance(getInstance()
					.getInteractingDrug());

			interactingDrugAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewDrugInteraction() {
		load(currentEntityId);
		return "viewDrugInteraction";
	}

}
