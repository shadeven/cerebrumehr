package com.oreon.cerebrum.web.action.patient;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.Vaccine;

//
public abstract class VaccineActionBase extends BaseAction<Vaccine>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long vaccineId;

	public void setVaccineId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setVaccineIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getVaccineId() {
		return (Long) getId();
	}

	public Vaccine getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Vaccine t) {
		this.instance = t;
		loadAssociations();
	}

	public Vaccine getVaccine() {
		return (Vaccine) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('vaccine', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('vaccine', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Vaccine createInstance() {
		Vaccine instance = super.createInstance();

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

	public Vaccine getDefinedInstance() {
		return (Vaccine) (isIdDefined() ? getInstance() : null);
	}

	public void setVaccine(Vaccine t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setVaccineId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Vaccine> getEntityClass() {
		return Vaccine.class;
	}

	public com.oreon.cerebrum.patient.Vaccine findByUnqName(String name) {
		return executeSingleResultNamedQuery("vaccine.findByUnqName", name);
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

	public String viewVaccine() {
		load(currentEntityId);
		return "viewVaccine";
	}

}
