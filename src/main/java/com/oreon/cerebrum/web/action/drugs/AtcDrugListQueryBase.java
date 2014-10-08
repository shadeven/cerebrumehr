package com.oreon.cerebrum.web.action.drugs;

import com.oreon.cerebrum.drugs.AtcDrug;

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

import com.oreon.cerebrum.drugs.AtcDrug;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AtcDrugListQueryBase extends BaseQuery<AtcDrug, Long> {

	private static final String EJBQL = "select atcDrug from AtcDrug atcDrug";

	protected AtcDrug atcDrug = new AtcDrug();

	public AtcDrugListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public AtcDrug getAtcDrug() {
		return atcDrug;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public AtcDrug getInstance() {
		return getAtcDrug();
	}

	@Override
	//@Restrict("#{s:hasPermission('atcDrug', 'view')}")
	public List<AtcDrug> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<AtcDrug> getEntityClass() {
		return AtcDrug.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"atcDrug.id = #{atcDrugList.atcDrug.id}",

			"atcDrug.archived = #{atcDrugList.atcDrug.archived}",

			"lower(atcDrug.code) like concat(lower(#{atcDrugList.atcDrug.code}),'%')",

			"lower(atcDrug.name) like concat(lower(#{atcDrugList.atcDrug.name}),'%')",

			"atcDrug.drug.id = #{atcDrugList.atcDrug.drug.id}",

			"atcDrug.parent.id = #{atcDrugList.atcDrug.parent.id}",

			"atcDrug.dateCreated <= #{atcDrugList.dateCreatedRange.end}",
			"atcDrug.dateCreated >= #{atcDrugList.dateCreatedRange.begin}",};

	/** 
	 * List of all AtcDrugs for the given AtcDrug
	 * @param patient
	 * @return 
	 */
	public List<AtcDrug> getAllSubcategoriesByParent(
			final com.oreon.cerebrum.drugs.AtcDrug atcDrug) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		atcDrug.setParent(atcDrug);
		return getResultListTable();
	}

	public LazyDataModel<AtcDrug> getSubcategoriesByParent(
			final com.oreon.cerebrum.drugs.AtcDrug atcDrug) {

		EntityLazyDataModel<AtcDrug, Long> atcDrugLazyDataModel = new EntityLazyDataModel<AtcDrug, Long>(
				this) {

			@Override
			public List<AtcDrug> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				atcDrug.setParent(atcDrug);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return atcDrugLazyDataModel;

	}

	@Observer("archivedAtcDrug")
	public void onArchive() {
		refresh();
	}

	public void setDrugId(Long id) {
		if (atcDrug.getDrug() == null) {
			atcDrug.setDrug(new com.oreon.cerebrum.drugs.Drug());
		}
		atcDrug.getDrug().setId(id);
	}

	public Long getDrugId() {
		return atcDrug.getDrug() == null ? null : atcDrug.getDrug().getId();
	}

	public void setParentId(Long id) {
		if (atcDrug.getParent() == null) {
			atcDrug.setParent(new com.oreon.cerebrum.drugs.AtcDrug());
		}
		atcDrug.getParent().setId(id);
	}

	public Long getParentId() {
		return atcDrug.getParent() == null ? null : atcDrug.getParent().getId();
	}

}
