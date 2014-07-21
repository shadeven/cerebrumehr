package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.DxTest;

//
public abstract class DxTestActionBase extends BaseAction<DxTest>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long dxTestId;

	public void setDxTestId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDxTestIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getDxTestId() {
		return (Long) getId();
	}

	public DxTest getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(DxTest t) {
		this.instance = t;
		loadAssociations();
	}

	public DxTest getDxTest() {
		return (DxTest) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('dxTest', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('dxTest', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected DxTest createInstance() {
		DxTest instance = super.createInstance();

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

	public DxTest getDefinedInstance() {
		return (DxTest) (isIdDefined() ? getInstance() : null);
	}

	public void setDxTest(DxTest t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setDxTestId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<DxTest> getEntityClass() {
		return DxTest.class;
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

	public String viewDxTest() {
		load(currentEntityId);
		return "viewDxTest";
	}

}
