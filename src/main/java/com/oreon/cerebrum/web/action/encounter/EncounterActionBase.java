package com.oreon.cerebrum.web.action.encounter;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.primefaces.model.DualListModel;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.encounter.Encounter;
import com.oreon.cerebrum.encounter.PrescribedTest;

//
public abstract class EncounterActionBase extends BaseAction<Encounter>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long encounterId;

	@In(create = true, value = "prescriptionAction")
	com.oreon.cerebrum.web.action.prescription.PrescriptionAction prescriptionAction;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	public void setEncounterId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setEncounterIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setPrescriptionId(Long id) {

		if (id != null && id > 0)
			getInstance().setPrescription(prescriptionAction.loadFromId(id));

	}

	public Long getPrescriptionId() {
		if (getInstance().getPrescription() != null)
			return getInstance().getPrescription().getId();
		return 0L;
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

	public void setCreatorId(Long id) {

	}

	public Long getCreatorId() {
		if (getInstance().getCreator() != null)
			return getInstance().getCreator().getId();
		return 0L;
	}

	public Long getEncounterId() {
		return (Long) getId();
	}

	public Encounter getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Encounter t) {
		this.instance = t;
		loadAssociations();
	}

	public Encounter getEncounter() {
		return (Encounter) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('encounter', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('encounter', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Encounter createInstance() {
		Encounter instance = super.createInstance();

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

		com.oreon.cerebrum.prescription.Prescription prescription = prescriptionAction
				.getDefinedInstance();
		if (prescription != null && isNew()) {
			getInstance().setPrescription(prescription);
		}

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public Encounter getDefinedInstance() {
		return (Encounter) (isIdDefined() ? getInstance() : null);
	}

	public void setEncounter(Encounter t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setEncounterId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Encounter> getEntityClass() {
		return Encounter.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getPrescription() != null) {
			criteria = criteria.add(Restrictions.eq("prescription.id", instance
					.getPrescription().getId()));
		}

		if (instance.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", instance
					.getPatient().getId()));
		}

		if (instance.getCreator() != null) {
			criteria = criteria.add(Restrictions.eq("creator.id", instance
					.getCreator().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getPrescription() != null) {
			prescriptionAction.setInstance(getInstance().getPrescription());

			prescriptionAction.loadAssociations();

		}

		if (getInstance().getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());

			patientAction.loadAssociations();

		}

		initListPrescribedTests();

		initListSimpleCodes();
		initListAvailableSimpleCodes();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.encounter.PrescribedTest> listPrescribedTests = new ArrayList<com.oreon.cerebrum.encounter.PrescribedTest>();

	void initListPrescribedTests() {

		if (listPrescribedTests.isEmpty())
			listPrescribedTests.addAll(getInstance().getPrescribedTests());

	}

	public List<com.oreon.cerebrum.encounter.PrescribedTest> getListPrescribedTests() {

		prePopulateListPrescribedTests();
		return listPrescribedTests;
	}

	public void prePopulateListPrescribedTests() {
	}

	public void setListPrescribedTests(
			List<com.oreon.cerebrum.encounter.PrescribedTest> listPrescribedTests) {
		this.listPrescribedTests = listPrescribedTests;
	}

	public void deletePrescribedTests(int index) {
		listPrescribedTests.remove(index);
	}

	@Begin(join = true)
	public void addPrescribedTests() {

		initListPrescribedTests();
		PrescribedTest prescribedTests = new PrescribedTest();

		prescribedTests.setEncounter(getInstance());

		getListPrescribedTests().add(prescribedTests);

	}

	protected List<com.oreon.cerebrum.codes.SimpleCode> listSimpleCodes = new ArrayList<com.oreon.cerebrum.codes.SimpleCode>();

	void initListSimpleCodes() {

		if (listSimpleCodes.isEmpty())
			listSimpleCodes.addAll(getInstance().getSimpleCodes());

	}

	public List<com.oreon.cerebrum.codes.SimpleCode> getListSimpleCodes() {

		prePopulateListSimpleCodes();
		return listSimpleCodes;
	}

	public void prePopulateListSimpleCodes() {
	}

	public void setListSimpleCodes(
			List<com.oreon.cerebrum.codes.SimpleCode> listSimpleCodes) {
		this.listSimpleCodes = listSimpleCodes;
	}

	protected DualListModel<com.oreon.cerebrum.codes.SimpleCode> listAvailableSimpleCodes;

	void initListAvailableSimpleCodes() {

		List<com.oreon.cerebrum.codes.SimpleCode> availablesimpleCodes = ((com.oreon.cerebrum.web.action.codes.SimpleCodeListQuery) Component
				.getInstance("simpleCodeList")).getAll();

		List<com.oreon.cerebrum.codes.SimpleCode> currentSimpleCodes = getInstance()
				.getListSimpleCodes();

		if (availablesimpleCodes == null)
			availablesimpleCodes = new ArrayList<com.oreon.cerebrum.codes.SimpleCode>();

		if (getEntity() != null)
			availablesimpleCodes.removeAll(getEntity().getSimpleCodes());

		listAvailableSimpleCodes = new DualListModel<com.oreon.cerebrum.codes.SimpleCode>(
				availablesimpleCodes, currentSimpleCodes);
	}

	public DualListModel<com.oreon.cerebrum.codes.SimpleCode> getListAvailableSimpleCodes() {
		if (listAvailableSimpleCodes == null)
			initListAvailableSimpleCodes();

		return listAvailableSimpleCodes;
	}

	public void setListAvailableSimpleCodes(
			DualListModel<com.oreon.cerebrum.codes.SimpleCode> listAvailableSimpleCodes) {
		this.listAvailableSimpleCodes = listAvailableSimpleCodes;
	}

	public void updateComposedAssociations() {

		if (listPrescribedTests != null) {

			java.util.Set<PrescribedTest> items = getInstance()
					.getPrescribedTests();
			for (PrescribedTest item : items) {
				if (!listPrescribedTests.contains(item))
					getEntityManager().remove(item);
			}

			for (PrescribedTest item : listPrescribedTests) {
				item.setEncounter(getInstance());
			}

			getInstance().getPrescribedTests().clear();
			getInstance().getPrescribedTests().addAll(listPrescribedTests);
		}

		if (listSimpleCodes != null) {
			getInstance().getSimpleCodes().clear();
			getInstance().getSimpleCodes().addAll(listSimpleCodes);
		}

	}

	public void clearLists() {
		listPrescribedTests.clear();

		listSimpleCodes.clear();

	}

	public String viewEncounter() {
		load(currentEntityId);
		return "viewEncounter";
	}

}
