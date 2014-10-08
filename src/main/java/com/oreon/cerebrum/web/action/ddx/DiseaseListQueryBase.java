package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.Disease;

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

import com.oreon.cerebrum.ddx.Disease;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DiseaseListQueryBase extends BaseQuery<Disease, Long> {

	private static final String EJBQL = "select disease from Disease disease";

	protected Disease disease = new Disease();

	public DiseaseListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Disease getDisease() {
		return disease;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Disease getInstance() {
		return getDisease();
	}

	@Override
	//@Restrict("#{s:hasPermission('disease', 'view')}")
	public List<Disease> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Disease> getEntityClass() {
		return Disease.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"disease.id = #{diseaseList.disease.id}",

			"disease.archived = #{diseaseList.disease.archived}",

			"disease.gender = #{diseaseList.disease.gender}",

			"lower(disease.name) like concat(lower(#{diseaseList.disease.name}),'%')",

			"disease.relatedDisease.id = #{diseaseList.disease.relatedDisease.id}",

			"disease.conditionCategory.id = #{diseaseList.disease.conditionCategory.id}",

			"disease.dateCreated <= #{diseaseList.dateCreatedRange.end}",
			"disease.dateCreated >= #{diseaseList.dateCreatedRange.begin}",};

	/** 
	 * List of all Diseases for the given Disease
	 * @param patient
	 * @return 
	 */
	public List<Disease> getAllDifferentialDiagnosesByRelatedDisease(
			final com.oreon.cerebrum.ddx.Disease disease) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		disease.setRelatedDisease(disease);
		return getResultListTable();
	}

	public LazyDataModel<Disease> getDifferentialDiagnosesByRelatedDisease(
			final com.oreon.cerebrum.ddx.Disease disease) {

		EntityLazyDataModel<Disease, Long> diseaseLazyDataModel = new EntityLazyDataModel<Disease, Long>(
				this) {

			@Override
			public List<Disease> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				disease.setRelatedDisease(disease);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return diseaseLazyDataModel;

	}

	@Observer("archivedDisease")
	public void onArchive() {
		refresh();
	}

	public void setRelatedDiseaseId(Long id) {
		if (disease.getRelatedDisease() == null) {
			disease.setRelatedDisease(new com.oreon.cerebrum.ddx.Disease());
		}
		disease.getRelatedDisease().setId(id);
	}

	public Long getRelatedDiseaseId() {
		return disease.getRelatedDisease() == null ? null : disease
				.getRelatedDisease().getId();
	}

	public void setConditionCategoryId(Long id) {
		if (disease.getConditionCategory() == null) {
			disease
					.setConditionCategory(new com.oreon.cerebrum.ddx.ConditionCategory());
		}
		disease.getConditionCategory().setId(id);
	}

	public Long getConditionCategoryId() {
		return disease.getConditionCategory() == null ? null : disease
				.getConditionCategory().getId();
	}

}
