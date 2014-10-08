package com.oreon.cerebrum.web.action.drugs;

import com.oreon.cerebrum.drugs.DrugInteraction;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.cerebrum.drugs.DrugInteraction;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DrugInteractionListQueryBase
		extends
			BaseQuery<DrugInteraction, Long> {

	private static final String EJBQL = "select drugInteraction from DrugInteraction drugInteraction";

	protected DrugInteraction drugInteraction = new DrugInteraction();

	public DrugInteractionListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public DrugInteraction getDrugInteraction() {
		return drugInteraction;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public DrugInteraction getInstance() {
		return getDrugInteraction();
	}

	@Override
	//@Restrict("#{s:hasPermission('drugInteraction', 'view')}")
	public List<DrugInteraction> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<DrugInteraction> getEntityClass() {
		return DrugInteraction.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"drugInteraction.id = #{drugInteractionList.drugInteraction.id}",

			"drugInteraction.archived = #{drugInteractionList.drugInteraction.archived}",

			"lower(drugInteraction.description) like concat(lower(#{drugInteractionList.drugInteraction.description}),'%')",

			"drugInteraction.drug.id = #{drugInteractionList.drugInteraction.drug.id}",

			"drugInteraction.interactingDrug.id = #{drugInteractionList.drugInteraction.interactingDrug.id}",

			"drugInteraction.severity = #{drugInteractionList.drugInteraction.severity}",

			"drugInteraction.dateCreated <= #{drugInteractionList.dateCreatedRange.end}",
			"drugInteraction.dateCreated >= #{drugInteractionList.dateCreatedRange.begin}",};

	/** 
	 * List of all DrugInteractions for the given Drug
	 * @param patient
	 * @return 
	 */
	public List<DrugInteraction> getAllDrugInteractionsByDrug(
			final com.oreon.cerebrum.drugs.Drug drug) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		drugInteraction.setDrug(drug);
		return getResultListTable();
	}

	public LazyDataModel<DrugInteraction> getDrugInteractionsByDrug(
			final com.oreon.cerebrum.drugs.Drug drug) {

		EntityLazyDataModel<DrugInteraction, Long> drugInteractionLazyDataModel = new EntityLazyDataModel<DrugInteraction, Long>(
				this) {

			@Override
			public List<DrugInteraction> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				drugInteraction.setDrug(drug);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return drugInteractionLazyDataModel;

	}

	@Observer("archivedDrugInteraction")
	public void onArchive() {
		refresh();
	}

	public void setDrugId(Long id) {
		if (drugInteraction.getDrug() == null) {
			drugInteraction.setDrug(new com.oreon.cerebrum.drugs.Drug());
		}
		drugInteraction.getDrug().setId(id);
	}

	public Long getDrugId() {
		return drugInteraction.getDrug() == null ? null : drugInteraction
				.getDrug().getId();
	}

	public void setInteractingDrugId(Long id) {
		if (drugInteraction.getInteractingDrug() == null) {
			drugInteraction
					.setInteractingDrug(new com.oreon.cerebrum.drugs.Drug());
		}
		drugInteraction.getInteractingDrug().setId(id);
	}

	public Long getInteractingDrugId() {
		return drugInteraction.getInteractingDrug() == null
				? null
				: drugInteraction.getInteractingDrug().getId();
	}

}
