package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.ConditionCategory;

//
public abstract class ConditionCategoryActionBase
		extends
			BaseAction<ConditionCategory> implements java.io.Serializable {

	@RequestParameter
	protected Long conditionCategoryId;

	public void setConditionCategoryId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setConditionCategoryIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getConditionCategoryId() {
		return (Long) getId();
	}

	public ConditionCategory getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(ConditionCategory t) {
		this.instance = t;
		loadAssociations();
	}

	public ConditionCategory getConditionCategory() {
		return (ConditionCategory) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('conditionCategory', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('conditionCategory', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected ConditionCategory createInstance() {
		ConditionCategory instance = super.createInstance();

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

	public ConditionCategory getDefinedInstance() {
		return (ConditionCategory) (isIdDefined() ? getInstance() : null);
	}

	public void setConditionCategory(ConditionCategory t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setConditionCategoryId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<ConditionCategory> getEntityClass() {
		return ConditionCategory.class;
	}

	public com.oreon.cerebrum.ddx.ConditionCategory findByUnqName(String name) {
		return executeSingleResultNamedQuery("conditionCategory.findByUnqName",
				name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewConditionCategory() {
		load(currentEntityId);
		return "viewConditionCategory";
	}

}
