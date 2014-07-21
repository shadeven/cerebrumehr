package com.oreon.cerebrum.web.action.ddx;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import com.oreon.cerebrum.ddx.PhysicalFinding;

//
public abstract class PhysicalFindingActionBase
		extends
			com.oreon.cerebrum.web.action.ddx.AbstractFindingAction<PhysicalFinding>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long physicalFindingId;

	public void setPhysicalFindingId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPhysicalFindingIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getPhysicalFindingId() {
		return (Long) getId();
	}

	public PhysicalFinding getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(PhysicalFinding t) {
		this.instance = t;
		loadAssociations();
	}

	public PhysicalFinding getPhysicalFinding() {
		return (PhysicalFinding) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('physicalFinding', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('physicalFinding', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected PhysicalFinding createInstance() {
		PhysicalFinding instance = super.createInstance();

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

	public PhysicalFinding getDefinedInstance() {
		return (PhysicalFinding) (isIdDefined() ? getInstance() : null);
	}

	public void setPhysicalFinding(PhysicalFinding t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setPhysicalFindingId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<PhysicalFinding> getEntityClass() {
		return PhysicalFinding.class;
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

	public String viewPhysicalFinding() {
		load(currentEntityId);
		return "viewPhysicalFinding";
	}

}
