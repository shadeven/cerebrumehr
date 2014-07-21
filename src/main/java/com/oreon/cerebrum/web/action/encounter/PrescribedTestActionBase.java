package com.oreon.cerebrum.web.action.encounter;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.encounter.PrescribedTest;

//
public abstract class PrescribedTestActionBase
		extends
			BaseAction<PrescribedTest> implements java.io.Serializable {

	@RequestParameter
	protected Long prescribedTestId;

	@In(create = true, value = "dxTestAction")
	com.oreon.cerebrum.web.action.ddx.DxTestAction dxTestAction;

	@In(create = true, value = "encounterAction")
	com.oreon.cerebrum.web.action.encounter.EncounterAction encounterAction;

	public void setPrescribedTestId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPrescribedTestIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setDxTestId(Long id) {

		if (id != null && id > 0)
			getInstance().setDxTest(dxTestAction.loadFromId(id));

	}

	public Long getDxTestId() {
		if (getInstance().getDxTest() != null)
			return getInstance().getDxTest().getId();
		return 0L;
	}

	public void setEncounterId(Long id) {

		if (id != null && id > 0)
			getInstance().setEncounter(encounterAction.loadFromId(id));

	}

	public Long getEncounterId() {
		if (getInstance().getEncounter() != null)
			return getInstance().getEncounter().getId();
		return 0L;
	}

	public Long getPrescribedTestId() {
		return (Long) getId();
	}

	public PrescribedTest getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(PrescribedTest t) {
		this.instance = t;
		loadAssociations();
	}

	public PrescribedTest getPrescribedTest() {
		return (PrescribedTest) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('prescribedTest', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('prescribedTest', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected PrescribedTest createInstance() {
		PrescribedTest instance = super.createInstance();

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

		com.oreon.cerebrum.ddx.DxTest dxTest = dxTestAction
				.getDefinedInstance();
		if (dxTest != null && isNew()) {
			getInstance().setDxTest(dxTest);
		}

		com.oreon.cerebrum.encounter.Encounter encounter = encounterAction
				.getDefinedInstance();
		if (encounter != null && isNew()) {
			getInstance().setEncounter(encounter);
		}

	}

	public PrescribedTest getDefinedInstance() {
		return (PrescribedTest) (isIdDefined() ? getInstance() : null);
	}

	public void setPrescribedTest(PrescribedTest t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setPrescribedTestId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<PrescribedTest> getEntityClass() {
		return PrescribedTest.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getDxTest() != null) {
			criteria = criteria.add(Restrictions.eq("dxTest.id", instance
					.getDxTest().getId()));
		}

		if (instance.getEncounter() != null) {
			criteria = criteria.add(Restrictions.eq("encounter.id", instance
					.getEncounter().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getDxTest() != null) {
			dxTestAction.setInstance(getInstance().getDxTest());

			dxTestAction.loadAssociations();

		}

		if (getInstance().getEncounter() != null) {
			encounterAction.setInstance(getInstance().getEncounter());

			encounterAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewPrescribedTest() {
		load(currentEntityId);
		return "viewPrescribedTest";
	}

}
