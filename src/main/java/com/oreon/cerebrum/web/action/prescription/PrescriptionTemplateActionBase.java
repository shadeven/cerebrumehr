package com.oreon.cerebrum.web.action.prescription;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.prescription.PrescriptionItemTemplate;
import com.oreon.cerebrum.prescription.PrescriptionTemplate;

//
public abstract class PrescriptionTemplateActionBase
		extends
			BaseAction<PrescriptionTemplate> implements java.io.Serializable {

	@RequestParameter
	protected Long prescriptionTemplateId;

	public void setPrescriptionTemplateId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPrescriptionTemplateIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getPrescriptionTemplateId() {
		return (Long) getId();
	}

	public PrescriptionTemplate getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(PrescriptionTemplate t) {
		this.instance = t;
		loadAssociations();
	}

	public PrescriptionTemplate getPrescriptionTemplate() {
		return (PrescriptionTemplate) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('prescriptionTemplate', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('prescriptionTemplate', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected PrescriptionTemplate createInstance() {
		PrescriptionTemplate instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

		if (isNew() && instance.getPrescriptionItemTemplates().isEmpty()) {
			for (int i = 0; i < 1; i++)
				getListPrescriptionItemTemplates()
						.add(
								new com.oreon.cerebrum.prescription.PrescriptionItemTemplate());
		}

	}

	public void wire() {
		getInstance();

	}

	public PrescriptionTemplate getDefinedInstance() {
		return (PrescriptionTemplate) (isIdDefined() ? getInstance() : null);
	}

	public void setPrescriptionTemplate(PrescriptionTemplate t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setPrescriptionTemplateId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<PrescriptionTemplate> getEntityClass() {
		return PrescriptionTemplate.class;
	}

	public com.oreon.cerebrum.prescription.PrescriptionTemplate findByUnqName(
			String name) {
		return executeSingleResultNamedQuery(
				"prescriptionTemplate.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListPrescriptionItemTemplates();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.prescription.PrescriptionItemTemplate> listPrescriptionItemTemplates = new ArrayList<com.oreon.cerebrum.prescription.PrescriptionItemTemplate>();

	void initListPrescriptionItemTemplates() {

		if (listPrescriptionItemTemplates.isEmpty())
			listPrescriptionItemTemplates.addAll(getInstance()
					.getPrescriptionItemTemplates());

	}

	public List<com.oreon.cerebrum.prescription.PrescriptionItemTemplate> getListPrescriptionItemTemplates() {

		prePopulateListPrescriptionItemTemplates();
		return listPrescriptionItemTemplates;
	}

	public void prePopulateListPrescriptionItemTemplates() {
	}

	public void setListPrescriptionItemTemplates(
			List<com.oreon.cerebrum.prescription.PrescriptionItemTemplate> listPrescriptionItemTemplates) {
		this.listPrescriptionItemTemplates = listPrescriptionItemTemplates;
	}

	public void deletePrescriptionItemTemplates(int index) {
		listPrescriptionItemTemplates.remove(index);
	}

	@Begin(join = true)
	public void addPrescriptionItemTemplates() {

		initListPrescriptionItemTemplates();
		PrescriptionItemTemplate prescriptionItemTemplates = new PrescriptionItemTemplate();

		prescriptionItemTemplates.setPrescriptionTemplate(getInstance());

		getListPrescriptionItemTemplates().add(prescriptionItemTemplates);

	}

	public void updateComposedAssociations() {

		if (listPrescriptionItemTemplates != null) {

			java.util.Set<PrescriptionItemTemplate> items = getInstance()
					.getPrescriptionItemTemplates();
			for (PrescriptionItemTemplate item : items) {
				if (!listPrescriptionItemTemplates.contains(item))
					getEntityManager().remove(item);
			}

			for (PrescriptionItemTemplate item : listPrescriptionItemTemplates) {
				item.setPrescriptionTemplate(getInstance());
			}

			getInstance().getPrescriptionItemTemplates().clear();
			getInstance().getPrescriptionItemTemplates().addAll(
					listPrescriptionItemTemplates);
		}
	}

	public void clearLists() {
		listPrescriptionItemTemplates.clear();

	}

	public String viewPrescriptionTemplate() {
		load(currentEntityId);
		return "viewPrescriptionTemplate";
	}

}
