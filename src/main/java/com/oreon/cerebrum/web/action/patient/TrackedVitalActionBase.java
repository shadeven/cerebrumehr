package com.oreon.cerebrum.web.action.patient;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.patient.TrackedVital;

//
public abstract class TrackedVitalActionBase extends BaseAction<TrackedVital>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long trackedVitalId;

	public void setTrackedVitalId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setTrackedVitalIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getTrackedVitalId() {
		return (Long) getId();
	}

	public TrackedVital getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(TrackedVital t) {
		this.instance = t;
		loadAssociations();
	}

	public TrackedVital getTrackedVital() {
		return (TrackedVital) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('trackedVital', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('trackedVital', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected TrackedVital createInstance() {
		TrackedVital instance = super.createInstance();

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

	public TrackedVital getDefinedInstance() {
		return (TrackedVital) (isIdDefined() ? getInstance() : null);
	}

	public void setTrackedVital(TrackedVital t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setTrackedVitalId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<TrackedVital> getEntityClass() {
		return TrackedVital.class;
	}

	public com.oreon.cerebrum.patient.TrackedVital findByUnqName(String name) {
		return executeSingleResultNamedQuery("trackedVital.findByUnqName", name);
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

	public String viewTrackedVital() {
		load(currentEntityId);
		return "viewTrackedVital";
	}

}
