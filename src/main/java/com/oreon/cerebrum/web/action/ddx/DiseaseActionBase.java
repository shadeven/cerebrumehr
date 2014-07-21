package com.oreon.cerebrum.web.action.ddx;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.Disease;

//
public abstract class DiseaseActionBase extends BaseAction<Disease>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long diseaseId;

	@In(create = true, value = "diseaseAction")
	com.oreon.cerebrum.web.action.ddx.DiseaseAction relatedDiseaseAction;

	@In(create = true, value = "conditionCategoryAction")
	com.oreon.cerebrum.web.action.ddx.ConditionCategoryAction conditionCategoryAction;

	@In(create = true, value = "diseaseAction")
	com.oreon.cerebrum.web.action.ddx.DiseaseAction differentialDiagnosesAction;

	public void setDiseaseId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDiseaseIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setRelatedDiseaseId(Long id) {

		if (id != null && id > 0)
			getInstance()
					.setRelatedDisease(relatedDiseaseAction.loadFromId(id));

	}

	public Long getRelatedDiseaseId() {
		if (getInstance().getRelatedDisease() != null)
			return getInstance().getRelatedDisease().getId();
		return 0L;
	}

	public void setConditionCategoryId(Long id) {

		if (id != null && id > 0)
			getInstance().setConditionCategory(
					conditionCategoryAction.loadFromId(id));

	}

	public Long getConditionCategoryId() {
		if (getInstance().getConditionCategory() != null)
			return getInstance().getConditionCategory().getId();
		return 0L;
	}

	public Long getDiseaseId() {
		return (Long) getId();
	}

	public Disease getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Disease t) {
		this.instance = t;
		loadAssociations();
	}

	public Disease getDisease() {
		return (Disease) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('disease', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('disease', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Disease createInstance() {
		Disease instance = super.createInstance();

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

		com.oreon.cerebrum.ddx.Disease relatedDisease = relatedDiseaseAction
				.getDefinedInstance();
		if (relatedDisease != null && isNew()) {
			getInstance().setRelatedDisease(relatedDisease);
		}

		com.oreon.cerebrum.ddx.ConditionCategory conditionCategory = conditionCategoryAction
				.getDefinedInstance();
		if (conditionCategory != null && isNew()) {
			getInstance().setConditionCategory(conditionCategory);
		}

	}

	public Disease getDefinedInstance() {
		return (Disease) (isIdDefined() ? getInstance() : null);
	}

	public void setDisease(Disease t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setDiseaseId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Disease> getEntityClass() {
		return Disease.class;
	}

	public com.oreon.cerebrum.ddx.Disease findByUnqName(String name) {
		return executeSingleResultNamedQuery("disease.findByUnqName", name);
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getRelatedDisease() != null) {
			criteria = criteria.add(Restrictions.eq("relatedDisease.id",
					instance.getRelatedDisease().getId()));
		}

		if (instance.getConditionCategory() != null) {
			criteria = criteria.add(Restrictions.eq("conditionCategory.id",
					instance.getConditionCategory().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getRelatedDisease() != null) {
			relatedDiseaseAction.setInstance(getInstance().getRelatedDisease());

			relatedDiseaseAction.loadAssociations();

		}

		if (getInstance().getConditionCategory() != null) {
			conditionCategoryAction.setInstance(getInstance()
					.getConditionCategory());

			conditionCategoryAction.loadAssociations();

		}

		initListDifferentialDiagnoses();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.ddx.Disease> listDifferentialDiagnoses = new ArrayList<com.oreon.cerebrum.ddx.Disease>();

	void initListDifferentialDiagnoses() {

		if (listDifferentialDiagnoses.isEmpty())
			listDifferentialDiagnoses.addAll(getInstance()
					.getDifferentialDiagnoses());

	}

	public List<com.oreon.cerebrum.ddx.Disease> getListDifferentialDiagnoses() {

		prePopulateListDifferentialDiagnoses();
		return listDifferentialDiagnoses;
	}

	public void prePopulateListDifferentialDiagnoses() {
	}

	public void setListDifferentialDiagnoses(
			List<com.oreon.cerebrum.ddx.Disease> listDifferentialDiagnoses) {
		this.listDifferentialDiagnoses = listDifferentialDiagnoses;
	}

	public void deleteDifferentialDiagnoses(int index) {
		listDifferentialDiagnoses.remove(index);
	}

	@Begin(join = true)
	public void addDifferentialDiagnoses() {

		initListDifferentialDiagnoses();
		Disease differentialDiagnoses = new Disease();

		differentialDiagnoses.setRelatedDisease(getInstance());

		getListDifferentialDiagnoses().add(differentialDiagnoses);

	}

	public void updateComposedAssociations() {

		if (listDifferentialDiagnoses != null) {

			java.util.Set<Disease> items = getInstance()
					.getDifferentialDiagnoses();
			for (Disease item : items) {
				if (!listDifferentialDiagnoses.contains(item))
					getEntityManager().remove(item);
			}

			for (Disease item : listDifferentialDiagnoses) {
				item.setRelatedDisease(getInstance());
			}

			getInstance().getDifferentialDiagnoses().clear();
			getInstance().getDifferentialDiagnoses().addAll(
					listDifferentialDiagnoses);
		}
	}

	public void clearLists() {
		listDifferentialDiagnoses.clear();

	}

	public String viewDisease() {
		load(currentEntityId);
		return "viewDisease";
	}

}
