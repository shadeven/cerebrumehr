package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.employee.Specialization;

//
public abstract class SpecializationActionBase
		extends
			BaseAction<Specialization> implements java.io.Serializable {

	@RequestParameter
	protected Long specializationId;

	public void setSpecializationId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setSpecializationIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getSpecializationId() {
		return (Long) getId();
	}

	public Specialization getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Specialization t) {
		this.instance = t;
		loadAssociations();
	}

	public Specialization getSpecialization() {
		return (Specialization) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('specialization', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('specialization', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Specialization createInstance() {
		Specialization instance = super.createInstance();

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

	public Specialization getDefinedInstance() {
		return (Specialization) (isIdDefined() ? getInstance() : null);
	}

	public void setSpecialization(Specialization t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setSpecializationId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Specialization> getEntityClass() {
		return Specialization.class;
	}

	public com.oreon.cerebrum.employee.Specialization findByUnqName(String name) {
		return executeSingleResultNamedQuery("specialization.findByUnqName",
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

	public String viewSpecialization() {
		load(currentEntityId);
		return "viewSpecialization";
	}

}
