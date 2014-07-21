package com.oreon.cerebrum.web.action.drugs;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.drugs.Drug;
import com.oreon.cerebrum.drugs.DrugInteraction;

//
public abstract class DrugActionBase extends BaseAction<Drug>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long drugId;

	public void setDrugId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDrugIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getDrugId() {
		return (Long) getId();
	}

	public Drug getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Drug t) {
		this.instance = t;
		loadAssociations();
	}

	public Drug getDrug() {
		return (Drug) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('drug', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('drug', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Drug createInstance() {
		Drug instance = super.createInstance();

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

	}

	public Drug getDefinedInstance() {
		return (Drug) (isIdDefined() ? getInstance() : null);
	}

	public void setDrug(Drug t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setDrugId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Drug> getEntityClass() {
		return Drug.class;
	}

	public com.oreon.cerebrum.drugs.Drug findByUnqName(String name) {
		return executeSingleResultNamedQuery("drug.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListDrugInteractions();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.drugs.DrugInteraction> listDrugInteractions = new ArrayList<com.oreon.cerebrum.drugs.DrugInteraction>();

	void initListDrugInteractions() {

		if (listDrugInteractions.isEmpty())
			listDrugInteractions.addAll(getInstance().getDrugInteractions());

	}

	public List<com.oreon.cerebrum.drugs.DrugInteraction> getListDrugInteractions() {

		prePopulateListDrugInteractions();
		return listDrugInteractions;
	}

	public void prePopulateListDrugInteractions() {
	}

	public void setListDrugInteractions(
			List<com.oreon.cerebrum.drugs.DrugInteraction> listDrugInteractions) {
		this.listDrugInteractions = listDrugInteractions;
	}

	public void deleteDrugInteractions(int index) {
		listDrugInteractions.remove(index);
	}

	@Begin(join = true)
	public void addDrugInteractions() {

		initListDrugInteractions();
		DrugInteraction drugInteractions = new DrugInteraction();

		drugInteractions.setDrug(getInstance());

		getListDrugInteractions().add(drugInteractions);

	}

	public void updateComposedAssociations() {

		if (listDrugInteractions != null) {

			java.util.Set<DrugInteraction> items = getInstance()
					.getDrugInteractions();
			for (DrugInteraction item : items) {
				if (!listDrugInteractions.contains(item))
					getEntityManager().remove(item);
			}

			for (DrugInteraction item : listDrugInteractions) {
				item.setDrug(getInstance());
			}

			getInstance().getDrugInteractions().clear();
			getInstance().getDrugInteractions().addAll(listDrugInteractions);
		}
	}

	public void clearLists() {
		listDrugInteractions.clear();

	}

	public String viewDrug() {
		load(currentEntityId);
		return "viewDrug";
	}

}
