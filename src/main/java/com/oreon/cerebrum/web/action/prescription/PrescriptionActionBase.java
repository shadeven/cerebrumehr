package com.oreon.cerebrum.web.action.prescription;

import com.oreon.cerebrum.prescription.Prescription;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.prescription.PrescriptionItem;

//
public abstract class PrescriptionActionBase extends BaseAction<Prescription>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long prescriptionId;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	public void setPrescriptionId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPrescriptionIdForModalDlg(Long id) {
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

	public Long getPrescriptionId() {
		return (Long) getId();
	}

	public Prescription getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Prescription t) {
		this.instance = t;
		loadAssociations();
	}

	public Prescription getPrescription() {
		return (Prescription) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('prescription', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('prescription', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Prescription createInstance() {
		Prescription instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

		if (isNew() && instance.getPrescriptionItems().isEmpty()) {
			for (int i = 0; i < 1; i++)
				getListPrescriptionItems().add(
						new com.oreon.cerebrum.prescription.PrescriptionItem());
		}

	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public Prescription getDefinedInstance() {
		return (Prescription) (isIdDefined() ? getInstance() : null);
	}

	public void setPrescription(Prescription t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setPrescriptionId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Prescription> getEntityClass() {
		return Prescription.class;
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

		initListPrescriptionItems();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.prescription.PrescriptionItem> listPrescriptionItems = new ArrayList<com.oreon.cerebrum.prescription.PrescriptionItem>();

	void initListPrescriptionItems() {

		if (listPrescriptionItems.isEmpty())
			listPrescriptionItems.addAll(getInstance().getPrescriptionItems());

	}

	public List<com.oreon.cerebrum.prescription.PrescriptionItem> getListPrescriptionItems() {

		prePopulateListPrescriptionItems();
		return listPrescriptionItems;
	}

	public void prePopulateListPrescriptionItems() {
	}

	public void setListPrescriptionItems(
			List<com.oreon.cerebrum.prescription.PrescriptionItem> listPrescriptionItems) {
		this.listPrescriptionItems = listPrescriptionItems;
	}

	public void deletePrescriptionItems(int index) {
		listPrescriptionItems.remove(index);
	}

	@Begin(join = true)
	public void addPrescriptionItems() {

		initListPrescriptionItems();
		PrescriptionItem prescriptionItems = new PrescriptionItem();

		prescriptionItems.setPrescription(getInstance());

		getListPrescriptionItems().add(prescriptionItems);

	}

	public void updateComposedAssociations() {

		if (listPrescriptionItems != null) {

			java.util.Set<PrescriptionItem> items = getInstance()
					.getPrescriptionItems();
			for (PrescriptionItem item : items) {
				if (!listPrescriptionItems.contains(item))
					getEntityManager().remove(item);
			}

			for (PrescriptionItem item : listPrescriptionItems) {
				item.setPrescription(getInstance());
			}

			getInstance().getPrescriptionItems().clear();
			getInstance().getPrescriptionItems().addAll(listPrescriptionItems);
		}
	}

	public void clearLists() {
		listPrescriptionItems.clear();

	}

	public String viewPrescription() {
		load(currentEntityId);
		return "viewPrescription";
	}

}
