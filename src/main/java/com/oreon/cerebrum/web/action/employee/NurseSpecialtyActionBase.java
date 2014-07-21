package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.employee.NurseSpecialty;

//
public abstract class NurseSpecialtyActionBase
		extends
			BaseAction<NurseSpecialty> implements java.io.Serializable {

	@RequestParameter
	protected Long nurseSpecialtyId;

	public void setNurseSpecialtyId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setNurseSpecialtyIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getNurseSpecialtyId() {
		return (Long) getId();
	}

	public NurseSpecialty getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(NurseSpecialty t) {
		this.instance = t;
		loadAssociations();
	}

	public NurseSpecialty getNurseSpecialty() {
		return (NurseSpecialty) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('nurseSpecialty', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('nurseSpecialty', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected NurseSpecialty createInstance() {
		NurseSpecialty instance = super.createInstance();

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

	public NurseSpecialty getDefinedInstance() {
		return (NurseSpecialty) (isIdDefined() ? getInstance() : null);
	}

	public void setNurseSpecialty(NurseSpecialty t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setNurseSpecialtyId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<NurseSpecialty> getEntityClass() {
		return NurseSpecialty.class;
	}

	public com.oreon.cerebrum.employee.NurseSpecialty findByUnqName(String name) {
		return executeSingleResultNamedQuery("nurseSpecialty.findByUnqName",
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

	public String viewNurseSpecialty() {
		load(currentEntityId);
		return "viewNurseSpecialty";
	}

}
