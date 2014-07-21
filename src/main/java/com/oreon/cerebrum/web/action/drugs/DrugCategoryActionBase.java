package com.oreon.cerebrum.web.action.drugs;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.primefaces.model.DualListModel;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.drugs.DrugCategory;

//
public abstract class DrugCategoryActionBase extends BaseAction<DrugCategory>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long drugCategoryId;

	public void setDrugCategoryId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDrugCategoryIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getDrugCategoryId() {
		return (Long) getId();
	}

	public DrugCategory getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(DrugCategory t) {
		this.instance = t;
		loadAssociations();
	}

	public DrugCategory getDrugCategory() {
		return (DrugCategory) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('drugCategory', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('drugCategory', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected DrugCategory createInstance() {
		DrugCategory instance = super.createInstance();

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

	public DrugCategory getDefinedInstance() {
		return (DrugCategory) (isIdDefined() ? getInstance() : null);
	}

	public void setDrugCategory(DrugCategory t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setDrugCategoryId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<DrugCategory> getEntityClass() {
		return DrugCategory.class;
	}

	public com.oreon.cerebrum.drugs.DrugCategory findByUnqName(String name) {
		return executeSingleResultNamedQuery("drugCategory.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListDrugs();
		initListAvailableDrugs();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.drugs.Drug> listDrugs = new ArrayList<com.oreon.cerebrum.drugs.Drug>();

	void initListDrugs() {

		if (listDrugs.isEmpty())
			listDrugs.addAll(getInstance().getDrugs());

	}

	public List<com.oreon.cerebrum.drugs.Drug> getListDrugs() {

		prePopulateListDrugs();
		return listDrugs;
	}

	public void prePopulateListDrugs() {
	}

	public void setListDrugs(List<com.oreon.cerebrum.drugs.Drug> listDrugs) {
		this.listDrugs = listDrugs;
	}

	protected DualListModel<com.oreon.cerebrum.drugs.Drug> listAvailableDrugs;

	void initListAvailableDrugs() {

		List<com.oreon.cerebrum.drugs.Drug> availabledrugs = ((com.oreon.cerebrum.web.action.drugs.DrugListQuery) Component
				.getInstance("drugList")).getAll();

		List<com.oreon.cerebrum.drugs.Drug> currentDrugs = getInstance()
				.getListDrugs();

		if (availabledrugs == null)
			availabledrugs = new ArrayList<com.oreon.cerebrum.drugs.Drug>();

		if (getEntity() != null)
			availabledrugs.removeAll(getEntity().getDrugs());

		listAvailableDrugs = new DualListModel<com.oreon.cerebrum.drugs.Drug>(
				availabledrugs, currentDrugs);
	}

	public DualListModel<com.oreon.cerebrum.drugs.Drug> getListAvailableDrugs() {
		if (listAvailableDrugs == null)
			initListAvailableDrugs();

		return listAvailableDrugs;
	}

	public void setListAvailableDrugs(
			DualListModel<com.oreon.cerebrum.drugs.Drug> listAvailableDrugs) {
		this.listAvailableDrugs = listAvailableDrugs;
	}

	public void updateComposedAssociations() {

		if (listAvailableDrugs != null) {
			getInstance().getDrugs().clear();
			getInstance().getDrugs().addAll(listAvailableDrugs.getTarget());
		}

	}

	public void clearLists() {

		listDrugs.clear();

	}

	public String viewDrugCategory() {
		load(currentEntityId);
		return "viewDrugCategory";
	}

}
