package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.DxCategory;

//
public abstract class DxCategoryActionBase extends BaseAction<DxCategory>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long dxCategoryId;

	public void setDxCategoryId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDxCategoryIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getDxCategoryId() {
		return (Long) getId();
	}

	public DxCategory getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(DxCategory t) {
		this.instance = t;
		loadAssociations();
	}

	public DxCategory getDxCategory() {
		return (DxCategory) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('dxCategory', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('dxCategory', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected DxCategory createInstance() {
		DxCategory instance = super.createInstance();

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

	public DxCategory getDefinedInstance() {
		return (DxCategory) (isIdDefined() ? getInstance() : null);
	}

	public void setDxCategory(DxCategory t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setDxCategoryId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<DxCategory> getEntityClass() {
		return DxCategory.class;
	}

	public com.oreon.cerebrum.ddx.DxCategory findByUnqName(String name) {
		return executeSingleResultNamedQuery("dxCategory.findByUnqName", name);
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

	public String viewDxCategory() {
		load(currentEntityId);
		return "viewDxCategory";
	}

}
