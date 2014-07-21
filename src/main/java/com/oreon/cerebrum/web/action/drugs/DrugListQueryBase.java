package com.oreon.cerebrum.web.action.drugs;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.drugs.Drug;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DrugListQueryBase extends BaseQuery<Drug, Long> {

	private static final String EJBQL = "select drug from Drug drug";

	protected Drug drug = new Drug();

	public DrugListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Drug getDrug() {
		return drug;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	private com.oreon.cerebrum.drugs.DrugCategory drugCategorysToSearch;

	public void setDrugCategorysToSearch(
			com.oreon.cerebrum.drugs.DrugCategory drugCategoryToSearch) {
		this.drugCategorysToSearch = drugCategoryToSearch;
	}

	public com.oreon.cerebrum.drugs.DrugCategory getDrugCategorysToSearch() {
		return drugCategorysToSearch;
	}

	@Override
	public Drug getInstance() {
		return getDrug();
	}

	@Override
	//@Restrict("#{s:hasPermission('drug', 'view')}")
	public List<Drug> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Drug> getEntityClass() {
		return Drug.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> halfLifeNumberOfHoursRange = new Range<Double>();

	public Range<Double> getHalfLifeNumberOfHoursRange() {
		return halfLifeNumberOfHoursRange;
	}
	public void setHalfLifeNumberOfHours(
			Range<Double> halfLifeNumberOfHoursRange) {
		this.halfLifeNumberOfHoursRange = halfLifeNumberOfHoursRange;
	}

	private static final String[] RESTRICTIONS = {
			"drug.id = #{drugList.drug.id}",

			"drug.archived = #{drugList.drug.archived}",

			"lower(drug.name) like concat(lower(#{drugList.drug.name}),'%')",

			"lower(drug.absorption) like concat(lower(#{drugList.drug.absorption}),'%')",

			"lower(drug.biotransformation) like concat(lower(#{drugList.drug.biotransformation}),'%')",

			"lower(drug.atcCodes) like concat(lower(#{drugList.drug.atcCodes}),'%')",

			"lower(drug.contraIndication) like concat(lower(#{drugList.drug.contraIndication}),'%')",

			"lower(drug.description) like concat(lower(#{drugList.drug.description}),'%')",

			"lower(drug.dosageForm) like concat(lower(#{drugList.drug.dosageForm}),'%')",

			"lower(drug.foodInteractions) like concat(lower(#{drugList.drug.foodInteractions}),'%')",

			"lower(drug.halfLife) like concat(lower(#{drugList.drug.halfLife}),'%')",

			"drug.halfLifeNumberOfHours >= #{drugList.halfLifeNumberOfHoursRange.begin}",
			"drug.halfLifeNumberOfHours <= #{drugList.halfLifeNumberOfHoursRange.end}",

			"lower(drug.indication) like concat(lower(#{drugList.drug.indication}),'%')",

			"lower(drug.mechanismOfAction) like concat(lower(#{drugList.drug.mechanismOfAction}),'%')",

			"lower(drug.patientInfo) like concat(lower(#{drugList.drug.patientInfo}),'%')",

			"lower(drug.pharmacology) like concat(lower(#{drugList.drug.pharmacology}),'%')",

			"#{drugList.drugCategorysToSearch} in elements(drug.drugCategorys)",

			"lower(drug.toxicity) like concat(lower(#{drugList.drug.toxicity}),'%')",

			"lower(drug.routeOfElimination) like concat(lower(#{drugList.drug.routeOfElimination}),'%')",

			"lower(drug.volumeOfDistribution) like concat(lower(#{drugList.drug.volumeOfDistribution}),'%')",

			"lower(drug.drugBankId) like concat(lower(#{drugList.drug.drugBankId}),'%')",

			"drug.dateCreated <= #{drugList.dateCreatedRange.end}",
			"drug.dateCreated >= #{drugList.dateCreatedRange.begin}",};

	@Observer("archivedDrug")
	public void onArchive() {
		refresh();
	}

	@Override
	protected void setupForAutoComplete(String input) {

		drug.setName(input);

	}

}
