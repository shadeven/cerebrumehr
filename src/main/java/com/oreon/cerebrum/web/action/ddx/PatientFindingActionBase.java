package com.oreon.cerebrum.web.action.ddx;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.PatientFinding;

//
public abstract class PatientFindingActionBase
		extends
			BaseAction<PatientFinding> implements java.io.Serializable {

	@RequestParameter
	protected Long patientFindingId;

	@In(create = true, value = "findingAction")
	com.oreon.cerebrum.web.action.ddx.FindingAction findingAction;

	@In(create = true, value = "patientDiffDxAction")
	com.oreon.cerebrum.web.action.ddx.PatientDiffDxAction patientDiffDxAction;

	public void setPatientFindingId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPatientFindingIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setFindingId(Long id) {

		if (id != null && id > 0)
			getInstance().setFinding(findingAction.loadFromId(id));

	}

	public Long getFindingId() {
		if (getInstance().getFinding() != null)
			return getInstance().getFinding().getId();
		return 0L;
	}

	public void setPatientDiffDxId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatientDiffDx(patientDiffDxAction.loadFromId(id));

	}

	public Long getPatientDiffDxId() {
		if (getInstance().getPatientDiffDx() != null)
			return getInstance().getPatientDiffDx().getId();
		return 0L;
	}

	public Long getPatientFindingId() {
		return (Long) getId();
	}

	public PatientFinding getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(PatientFinding t) {
		this.instance = t;
		loadAssociations();
	}

	public PatientFinding getPatientFinding() {
		return (PatientFinding) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('patientFinding', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('patientFinding', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected PatientFinding createInstance() {
		PatientFinding instance = super.createInstance();

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

		com.oreon.cerebrum.ddx.Finding finding = findingAction
				.getDefinedInstance();
		if (finding != null && isNew()) {
			getInstance().setFinding(finding);
		}

		com.oreon.cerebrum.ddx.PatientDiffDx patientDiffDx = patientDiffDxAction
				.getDefinedInstance();
		if (patientDiffDx != null && isNew()) {
			getInstance().setPatientDiffDx(patientDiffDx);
		}

	}

	public PatientFinding getDefinedInstance() {
		return (PatientFinding) (isIdDefined() ? getInstance() : null);
	}

	public void setPatientFinding(PatientFinding t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setPatientFindingId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<PatientFinding> getEntityClass() {
		return PatientFinding.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getFinding() != null) {
			criteria = criteria.add(Restrictions.eq("finding.id", instance
					.getFinding().getId()));
		}

		if (instance.getPatientDiffDx() != null) {
			criteria = criteria.add(Restrictions.eq("patientDiffDx.id",
					instance.getPatientDiffDx().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getFinding() != null) {
			findingAction.setInstance(getInstance().getFinding());

			findingAction.loadAssociations();

		}

		if (getInstance().getPatientDiffDx() != null) {
			patientDiffDxAction.setInstance(getInstance().getPatientDiffDx());

			patientDiffDxAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewPatientFinding() {
		load(currentEntityId);
		return "viewPatientFinding";
	}

}
