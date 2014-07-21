package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import com.oreon.cerebrum.employee.Clerk;

//
public abstract class ClerkActionBase
		extends
			com.oreon.cerebrum.web.action.employee.AbstractEmployeeAction<Clerk>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long clerkId;

	public void setClerkId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setClerkIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getClerkId() {
		return (Long) getId();
	}

	public Clerk getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Clerk t) {
		this.instance = t;
		loadAssociations();
	}

	public Clerk getClerk() {
		return (Clerk) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('clerk', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('clerk', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Clerk createInstance() {
		Clerk instance = super.createInstance();

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

	public Clerk getDefinedInstance() {
		return (Clerk) (isIdDefined() ? getInstance() : null);
	}

	public void setClerk(Clerk t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setClerkId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Clerk> getEntityClass() {
		return Clerk.class;
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

	public String viewClerk() {
		load(currentEntityId);
		return "viewClerk";
	}

}
