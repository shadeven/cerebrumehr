package com.oreon.cerebrum.web.action.prescription;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.prescription.Frequency;

//
public abstract class FrequencyActionBase extends BaseAction<Frequency>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long frequencyId;

	public void setFrequencyId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setFrequencyIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getFrequencyId() {
		return (Long) getId();
	}

	public Frequency getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Frequency t) {
		this.instance = t;
		loadAssociations();
	}

	public Frequency getFrequency() {
		return (Frequency) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('frequency', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('frequency', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Frequency createInstance() {
		Frequency instance = super.createInstance();

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

	public Frequency getDefinedInstance() {
		return (Frequency) (isIdDefined() ? getInstance() : null);
	}

	public void setFrequency(Frequency t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setFrequencyId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Frequency> getEntityClass() {
		return Frequency.class;
	}

	public com.oreon.cerebrum.prescription.Frequency findByUnqName(String name) {
		return executeSingleResultNamedQuery("frequency.findByUnqName", name);
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

	public String viewFrequency() {
		load(currentEntityId);
		return "viewFrequency";
	}

}
