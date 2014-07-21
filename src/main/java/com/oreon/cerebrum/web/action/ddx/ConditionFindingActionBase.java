package com.oreon.cerebrum.web.action.ddx;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.ConditionFinding;

//
public abstract class ConditionFindingActionBase
		extends
			BaseAction<ConditionFinding> implements java.io.Serializable {

	@RequestParameter
	protected Long conditionFindingId;

	@In(create = true, value = "diseaseAction")
	com.oreon.cerebrum.web.action.ddx.DiseaseAction diseaseAction;

	public void setConditionFindingId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setConditionFindingIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setDiseaseId(Long id) {

		if (id != null && id > 0)
			getInstance().setDisease(diseaseAction.loadFromId(id));

	}

	public Long getDiseaseId() {
		if (getInstance().getDisease() != null)
			return getInstance().getDisease().getId();
		return 0L;
	}

	public Long getConditionFindingId() {
		return (Long) getId();
	}

	public ConditionFinding getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(ConditionFinding t) {
		this.instance = t;
		loadAssociations();
	}

	public ConditionFinding getConditionFinding() {
		return (ConditionFinding) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('conditionFinding', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('conditionFinding', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected ConditionFinding createInstance() {
		ConditionFinding instance = super.createInstance();

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

		com.oreon.cerebrum.ddx.Disease disease = diseaseAction
				.getDefinedInstance();
		if (disease != null && isNew()) {
			getInstance().setDisease(disease);
		}

	}

	public ConditionFinding getDefinedInstance() {
		return (ConditionFinding) (isIdDefined() ? getInstance() : null);
	}

	public void setConditionFinding(ConditionFinding t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setConditionFindingId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<ConditionFinding> getEntityClass() {
		return ConditionFinding.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getDisease() != null) {
			criteria = criteria.add(Restrictions.eq("disease.id", instance
					.getDisease().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getDisease() != null) {
			diseaseAction.setInstance(getInstance().getDisease());

			diseaseAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewConditionFinding() {
		load(currentEntityId);
		return "viewConditionFinding";
	}

}
