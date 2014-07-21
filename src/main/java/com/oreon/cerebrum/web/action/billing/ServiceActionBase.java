package com.oreon.cerebrum.web.action.billing;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.billing.Service;

//
public abstract class ServiceActionBase extends BaseAction<Service>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long serviceId;

	public void setServiceId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setServiceIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getServiceId() {
		return (Long) getId();
	}

	public Service getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Service t) {
		this.instance = t;
		loadAssociations();
	}

	public Service getService() {
		return (Service) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('service', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('service', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Service createInstance() {
		Service instance = super.createInstance();

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

	public Service getDefinedInstance() {
		return (Service) (isIdDefined() ? getInstance() : null);
	}

	public void setService(Service t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setServiceId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Service> getEntityClass() {
		return Service.class;
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

	public String viewService() {
		load(currentEntityId);
		return "viewService";
	}

}
