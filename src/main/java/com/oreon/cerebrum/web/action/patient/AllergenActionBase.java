package com.oreon.cerebrum.web.action.patient;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.Allergen;

//
public abstract class AllergenActionBase extends BaseAction<Allergen>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long allergenId;

	public void setAllergenId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAllergenIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getAllergenId() {
		return (Long) getId();
	}

	public Allergen getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Allergen t) {
		this.instance = t;
		loadAssociations();
	}

	public Allergen getAllergen() {
		return (Allergen) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('allergen', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('allergen', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Allergen createInstance() {
		Allergen instance = super.createInstance();

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

	public Allergen getDefinedInstance() {
		return (Allergen) (isIdDefined() ? getInstance() : null);
	}

	public void setAllergen(Allergen t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setAllergenId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Allergen> getEntityClass() {
		return Allergen.class;
	}

	public com.oreon.cerebrum.patient.Allergen findByUnqName(String name) {
		return executeSingleResultNamedQuery("allergen.findByUnqName", name);
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

	public String viewAllergen() {
		load(currentEntityId);
		return "viewAllergen";
	}

}
