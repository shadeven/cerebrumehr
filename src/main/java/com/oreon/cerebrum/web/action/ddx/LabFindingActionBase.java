package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import com.oreon.cerebrum.ddx.LabFinding;

//
public abstract class LabFindingActionBase
		extends
			com.oreon.cerebrum.web.action.ddx.AbstractFindingAction<LabFinding>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long labFindingId;

	public void setLabFindingId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setLabFindingIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getLabFindingId() {
		return (Long) getId();
	}

	public LabFinding getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(LabFinding t) {
		this.instance = t;
		loadAssociations();
	}

	public LabFinding getLabFinding() {
		return (LabFinding) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('labFinding', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('labFinding', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected LabFinding createInstance() {
		LabFinding instance = super.createInstance();

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

	public LabFinding getDefinedInstance() {
		return (LabFinding) (isIdDefined() ? getInstance() : null);
	}

	public void setLabFinding(LabFinding t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setLabFindingId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<LabFinding> getEntityClass() {
		return LabFinding.class;
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

	public String viewLabFinding() {
		load(currentEntityId);
		return "viewLabFinding";
	}

}
