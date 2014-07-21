package com.oreon.cerebrum.web.action.codes;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.codes.SimpleCode;

//
public abstract class SimpleCodeActionBase extends BaseAction<SimpleCode>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long simpleCodeId;

	public void setSimpleCodeId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setSimpleCodeIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getSimpleCodeId() {
		return (Long) getId();
	}

	public SimpleCode getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(SimpleCode t) {
		this.instance = t;
		loadAssociations();
	}

	public SimpleCode getSimpleCode() {
		return (SimpleCode) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('simpleCode', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('simpleCode', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected SimpleCode createInstance() {
		SimpleCode instance = super.createInstance();

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

	public SimpleCode getDefinedInstance() {
		return (SimpleCode) (isIdDefined() ? getInstance() : null);
	}

	public void setSimpleCode(SimpleCode t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setSimpleCodeId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<SimpleCode> getEntityClass() {
		return SimpleCode.class;
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

	public String viewSimpleCode() {
		load(currentEntityId);
		return "viewSimpleCode";
	}

}
