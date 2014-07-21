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

import com.oreon.cerebrum.ddx.PatientDiffDx;
import com.oreon.cerebrum.ddx.PatientFinding;

//
public abstract class PatientDiffDxActionBase extends BaseAction<PatientDiffDx>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long patientDiffDxId;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	public void setPatientDiffDxId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPatientDiffDxIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
		return 0L;
	}

	public Long getPatientDiffDxId() {
		return (Long) getId();
	}

	public PatientDiffDx getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(PatientDiffDx t) {
		this.instance = t;
		loadAssociations();
	}

	public PatientDiffDx getPatientDiffDx() {
		return (PatientDiffDx) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('patientDiffDx', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('patientDiffDx', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected PatientDiffDx createInstance() {
		PatientDiffDx instance = super.createInstance();

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

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public PatientDiffDx getDefinedInstance() {
		return (PatientDiffDx) (isIdDefined() ? getInstance() : null);
	}

	public void setPatientDiffDx(PatientDiffDx t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setPatientDiffDxId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<PatientDiffDx> getEntityClass() {
		return PatientDiffDx.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", instance
					.getPatient().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());

			patientAction.loadAssociations();

		}

		initListPatientFindings();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.ddx.PatientFinding> listPatientFindings = new ArrayList<com.oreon.cerebrum.ddx.PatientFinding>();

	void initListPatientFindings() {

		if (listPatientFindings.isEmpty())
			listPatientFindings.addAll(getInstance().getPatientFindings());

	}

	public List<com.oreon.cerebrum.ddx.PatientFinding> getListPatientFindings() {

		prePopulateListPatientFindings();
		return listPatientFindings;
	}

	public void prePopulateListPatientFindings() {
	}

	public void setListPatientFindings(
			List<com.oreon.cerebrum.ddx.PatientFinding> listPatientFindings) {
		this.listPatientFindings = listPatientFindings;
	}

	public void deletePatientFindings(int index) {
		listPatientFindings.remove(index);
	}

	@Begin(join = true)
	public void addPatientFindings() {

		initListPatientFindings();
		PatientFinding patientFindings = new PatientFinding();

		patientFindings.setPatientDiffDx(getInstance());

		getListPatientFindings().add(patientFindings);

	}

	public void updateComposedAssociations() {

		if (listPatientFindings != null) {

			java.util.Set<PatientFinding> items = getInstance()
					.getPatientFindings();
			for (PatientFinding item : items) {
				if (!listPatientFindings.contains(item))
					getEntityManager().remove(item);
			}

			for (PatientFinding item : listPatientFindings) {
				item.setPatientDiffDx(getInstance());
			}

			getInstance().getPatientFindings().clear();
			getInstance().getPatientFindings().addAll(listPatientFindings);
		}
	}

	public void clearLists() {
		listPatientFindings.clear();

	}

	public String viewPatientDiffDx() {
		load(currentEntityId);
		return "viewPatientDiffDx";
	}

}
