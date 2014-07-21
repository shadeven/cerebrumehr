package com.oreon.cerebrum.web.action.prescription;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.security.permission.PermissionCheck;
import org.witchcraft.exceptions.BusinessException;

import com.oreon.cerebrum.drugs.Drug;
import com.oreon.cerebrum.drugs.DrugInteraction;
import com.oreon.cerebrum.prescription.PrescriptionItem;
import com.oreon.cerebrum.prescription.PrescriptionItemTemplate;
import com.oreon.cerebrum.prescription.PrescriptionTemplate;
import com.oreon.cerebrum.web.action.patient.PatientAction;

//@Scope(ScopeType.CONVERSATION)
@Name("prescriptionAction")
// @Restrict("#{s:hasRole('Physician')}")
public class PrescriptionAction extends PrescriptionActionBase implements
		java.io.Serializable {

	private List<DrugInteraction> interactions = new ArrayList<DrugInteraction>();

	private PrescriptionTemplate currentPrescriptionTemplate;

	private Drug newDrug;

	PermissionCheck pc;

	@In(create = true)
	PatientAction patientAction;

	@Override
	@Restrict("#{s:hasPermission('prescription', 'edit')}")
	public String save(boolean endconv) {
		if (getInstance().getPatient() == null) {
			if(patientAction.getInstance() == null || patientAction.getInstance().getId() == null)
				throw new BusinessException("Must Select a patient");
			getInstance().setPatient(patientAction.getInstance());
		}
		return super.save(endconv);
	}

	/*
	 * @Override
	 * 
	 * @Restrict("#{s:hasPermission('prescription', 'delete'}") public void
	 * archiveById() { // TODO Auto-generated method stub super.archiveById(); }
	 */

	public String addDrug(Drug drug) {
		StringBuilder warnings = new StringBuilder();

		List<PrescriptionItem> items = getListPrescriptionItems();

		for (PrescriptionItem prescriptionItem : items) {
			DrugInteraction interaction = findInteraction(prescriptionItem
					.getDrug(), drug);
			if (interaction != null) {
				warnings.append(interaction.getDescription());
				interactions.add(interaction);
			}
		}

		if (!StringUtils.isEmpty(warnings.toString())) {
			addErrorMessage(warnings.toString());
			System.out.println(warnings.toString());
		}
		return "success";
	}

	public DrugInteraction findInteraction(Drug one, Drug two) {
		Set<DrugInteraction> interactions = one.getDrugInteractions();
		for (DrugInteraction drugInteraction : interactions) {
			if (drugInteraction.getInteractingDrug().getId()
					.equals(two.getId())) {
				return drugInteraction;
			}
		}
		return null;
	}

	// @Override
	public void setNewDrug(Drug newDrug) {
		addDrug(newDrug);
		this.newDrug = newDrug;
	}

	public Drug getNewDrug() {
		return newDrug;
	}

	public void setInteractions(List<DrugInteraction> interactions) {
		this.interactions = interactions;
	}

	public List<DrugInteraction> getInteractions() {
		//System.out.println("elements " + interactions.size());
		return interactions;
	}

	@Begin(join = true)
	public void loadDrugsFromTemplate() {
		
		System.out.println("Current " + org.jboss.seam.core.Conversation.instance().getId());

		if (currentPrescriptionTemplate == null)
			return;
		
		
		getInstance().setDirectivesForPatient(currentPrescriptionTemplate.getDirectivesForPatient());

		Set<PrescriptionItemTemplate> items = currentPrescriptionTemplate
				.getPrescriptionItemTemplates();

		for (PrescriptionItemTemplate prescriptionItemTemplate : items) {
			if (containsDrug(prescriptionItemTemplate.getDrug()))
				continue;

			prescriptionItemTemplate = initializeAndUnproxy(prescriptionItemTemplate);
			PrescriptionItem prescriptionItem = new PrescriptionItem();

			try {
				// todo dont copy the id
				BeanUtils.copyProperties(prescriptionItem,
						prescriptionItemTemplate);
				prescriptionItem.setId(null);

			} catch (Exception e) {
				e.printStackTrace();
			}
			prescriptionItem.setPrescription(getInstance());
			listPrescriptionItems.add(prescriptionItem);
		}

	}

	private boolean containsDrug(Drug drug) {
		for (PrescriptionItem prescriptionItem : listPrescriptionItems) {
			
			if (prescriptionItem == null || prescriptionItem.getDrug() == null  || prescriptionItem.getDrug().getName() == null)
				continue;
			
			System.out.println(" drug " + drug.getName() + " " + prescriptionItem.getDrug().getName());
			

			if (prescriptionItem.getDrug().getName().equals(drug.getName())) {
				System.out.println("found drug " + drug.getName());
				return true;
			}
		}
		return false;
	}

	public void setCurrentPrescriptionTemplate(
			PrescriptionTemplate currentPrescriptionTemplate) {
		this.currentPrescriptionTemplate = currentPrescriptionTemplate;
	}

	public PrescriptionTemplate getCurrentPrescriptionTemplate() {
		return currentPrescriptionTemplate;
	}

}
