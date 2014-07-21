package com.oreon.cerebrum.web.action.unusualoccurences;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.unusualoccurences.OccurenceType;

//
public abstract class OccurenceTypeActionBase extends BaseAction<OccurenceType>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long occurenceTypeId;

	public void setOccurenceTypeId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setOccurenceTypeIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getOccurenceTypeId() {
		return (Long) getId();
	}

	public OccurenceType getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(OccurenceType t) {
		this.instance = t;
		loadAssociations();
	}

	public OccurenceType getOccurenceType() {
		return (OccurenceType) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('occurenceType', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('occurenceType', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected OccurenceType createInstance() {
		OccurenceType instance = super.createInstance();

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

	public OccurenceType getDefinedInstance() {
		return (OccurenceType) (isIdDefined() ? getInstance() : null);
	}

	public void setOccurenceType(OccurenceType t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setOccurenceTypeId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<OccurenceType> getEntityClass() {
		return OccurenceType.class;
	}

	public com.oreon.cerebrum.unusualoccurences.OccurenceType findByUnqName(
			String name) {
		return executeSingleResultNamedQuery("occurenceType.findByUnqName",
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

	public String viewOccurenceType() {
		load(currentEntityId);
		return "viewOccurenceType";
	}

}
