package com.oreon.cerebrum.web.action.admission;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.admission.Admission;
import com.oreon.cerebrum.admission.BedStay;

//
public abstract class AdmissionActionBase extends BaseAction<Admission>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long admissionId;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	@In(create = true, value = "invoiceAction")
	com.oreon.cerebrum.web.action.billing.InvoiceAction invoiceAction;

	public void setAdmissionId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAdmissionIdForModalDlg(Long id) {
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

	public void setInvoiceId(Long id) {

		if (id != null && id > 0)
			getInstance().setInvoice(invoiceAction.loadFromId(id));

	}

	public Long getInvoiceId() {
		if (getInstance().getInvoice() != null)
			return getInstance().getInvoice().getId();
		return 0L;
	}

	public Long getAdmissionId() {
		return (Long) getId();
	}

	public Admission getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Admission t) {
		this.instance = t;
		loadAssociations();
	}

	public Admission getAdmission() {
		return (Admission) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('admission', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('admission', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Admission createInstance() {
		Admission instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

		if (isNew() && instance.getBedStays().isEmpty()) {
			for (int i = 0; i < 1; i++)
				getListBedStays().add(
						new com.oreon.cerebrum.admission.BedStay());
		}

	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

		com.oreon.cerebrum.billing.Invoice invoice = invoiceAction
				.getDefinedInstance();
		if (invoice != null && isNew()) {
			getInstance().setInvoice(invoice);
		}

	}

	public Admission getDefinedInstance() {
		return (Admission) (isIdDefined() ? getInstance() : null);
	}

	public void setAdmission(Admission t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setAdmissionId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Admission> getEntityClass() {
		return Admission.class;
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

		if (instance.getInvoice() != null) {
			criteria = criteria.add(Restrictions.eq("invoice.id", instance
					.getInvoice().getId()));
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

		if (getInstance().getInvoice() != null) {
			invoiceAction.setInstance(getInstance().getInvoice());

			invoiceAction.loadAssociations();

		}

		initListBedStays();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.admission.BedStay> listBedStays = new ArrayList<com.oreon.cerebrum.admission.BedStay>();

	void initListBedStays() {

		if (listBedStays.isEmpty())
			listBedStays.addAll(getInstance().getBedStays());

	}

	public List<com.oreon.cerebrum.admission.BedStay> getListBedStays() {

		prePopulateListBedStays();
		return listBedStays;
	}

	public void prePopulateListBedStays() {
	}

	public void setListBedStays(
			List<com.oreon.cerebrum.admission.BedStay> listBedStays) {
		this.listBedStays = listBedStays;
	}

	public void deleteBedStays(int index) {
		listBedStays.remove(index);
	}

	@Begin(join = true)
	public void addBedStays() {

		initListBedStays();
		BedStay bedStays = new BedStay();

		bedStays.setAdmission(getInstance());

		getListBedStays().add(bedStays);

	}

	public void updateComposedAssociations() {

		if (listBedStays != null) {

			java.util.Set<BedStay> items = getInstance().getBedStays();
			for (BedStay item : items) {
				if (!listBedStays.contains(item))
					getEntityManager().remove(item);
			}

			for (BedStay item : listBedStays) {
				item.setAdmission(getInstance());
			}

			getInstance().getBedStays().clear();
			getInstance().getBedStays().addAll(listBedStays);
		}
	}

	public void clearLists() {
		listBedStays.clear();

	}

	/** 
	 * []
	 */
	public void transfer() {

	}

	public boolean isTransferAllowed() {
		return true;
	}

	/** 
	 * []
	 */
	public void discharge() {

	}

	public boolean isDischargeAllowed() {
		return true;
	}

	public String viewAdmission() {
		load(currentEntityId);
		return "viewAdmission";
	}

}
