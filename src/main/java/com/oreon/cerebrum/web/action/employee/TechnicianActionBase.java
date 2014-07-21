package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.security.Identity;

import com.oreon.cerebrum.employee.Technician;

//
public abstract class TechnicianActionBase
		extends
			com.oreon.cerebrum.web.action.employee.AbstractEmployeeAction<Technician>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long technicianId;

	public static final String DEFAULT_ROLE_NAME = "technician";

	public void setTechnicianId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setTechnicianIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getTechnicianId() {
		return (Long) getId();
	}

	public Technician getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Technician t) {
		this.instance = t;
		loadAssociations();
	}

	public Technician getTechnician() {
		return (Technician) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('technician', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('technician', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Technician createInstance() {
		Technician instance = super.createInstance();

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

	public Technician getDefinedInstance() {
		return (Technician) (isIdDefined() ? getInstance() : null);
	}

	public void setTechnician(Technician t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setTechnicianId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Technician> getEntityClass() {
		return Technician.class;
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

	public Technician getCurrentLoggedInTechnician() {
		String query = "Select e from Technician e where e.appUser.userName = ?1";
		return (Technician) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

	public String getDefaultRoleName() {
		return DEFAULT_ROLE_NAME;
	}

	public String viewTechnician() {
		load(currentEntityId);
		return "viewTechnician";
	}

}
