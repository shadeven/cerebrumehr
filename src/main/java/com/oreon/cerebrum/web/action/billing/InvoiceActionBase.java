package com.oreon.cerebrum.web.action.billing;

import com.oreon.cerebrum.billing.Invoice;

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

import com.oreon.cerebrum.billing.InvoiceItem;

//
public abstract class InvoiceActionBase extends BaseAction<Invoice>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long invoiceId;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	public void setInvoiceId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setInvoiceIdForModalDlg(Long id) {
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

	public Long getInvoiceId() {
		return (Long) getId();
	}

	public Invoice getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Invoice t) {
		this.instance = t;
		loadAssociations();
	}

	public Invoice getInvoice() {
		return (Invoice) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('invoice', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('invoice', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Invoice createInstance() {
		Invoice instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

		if (isNew() && instance.getInvoiceItems().isEmpty()) {
			for (int i = 0; i < 1; i++)
				getListInvoiceItems().add(
						new com.oreon.cerebrum.billing.InvoiceItem());
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

	public Invoice getDefinedInstance() {
		return (Invoice) (isIdDefined() ? getInstance() : null);
	}

	public void setInvoice(Invoice t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setInvoiceId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Invoice> getEntityClass() {
		return Invoice.class;
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

		initListInvoiceItems();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.billing.InvoiceItem> listInvoiceItems = new ArrayList<com.oreon.cerebrum.billing.InvoiceItem>();

	void initListInvoiceItems() {

		if (listInvoiceItems.isEmpty())
			listInvoiceItems.addAll(getInstance().getInvoiceItems());

	}

	public List<com.oreon.cerebrum.billing.InvoiceItem> getListInvoiceItems() {

		prePopulateListInvoiceItems();
		return listInvoiceItems;
	}

	public void prePopulateListInvoiceItems() {
	}

	public void setListInvoiceItems(
			List<com.oreon.cerebrum.billing.InvoiceItem> listInvoiceItems) {
		this.listInvoiceItems = listInvoiceItems;
	}

	public void deleteInvoiceItems(int index) {
		listInvoiceItems.remove(index);
	}

	@Begin(join = true)
	public void addInvoiceItems() {

		initListInvoiceItems();
		InvoiceItem invoiceItems = new InvoiceItem();

		invoiceItems.setInvoice(getInstance());

		getListInvoiceItems().add(invoiceItems);

	}

	public void updateComposedAssociations() {

		if (listInvoiceItems != null) {

			java.util.Set<InvoiceItem> items = getInstance().getInvoiceItems();
			for (InvoiceItem item : items) {
				if (!listInvoiceItems.contains(item))
					getEntityManager().remove(item);
			}

			for (InvoiceItem item : listInvoiceItems) {
				item.setInvoice(getInstance());
			}

			getInstance().getInvoiceItems().clear();
			getInstance().getInvoiceItems().addAll(listInvoiceItems);
		}
	}

	public void clearLists() {
		listInvoiceItems.clear();

	}

	public String viewInvoice() {
		load(currentEntityId);
		return "viewInvoice";
	}

}
