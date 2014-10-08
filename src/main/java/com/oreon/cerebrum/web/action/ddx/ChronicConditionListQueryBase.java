package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.ChronicCondition;

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

import com.oreon.cerebrum.ddx.ChronicCondition;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ChronicConditionListQueryBase
		extends
			BaseQuery<ChronicCondition, Long> {

	private static final String EJBQL = "select chronicCondition from ChronicCondition chronicCondition";

	protected ChronicCondition chronicCondition = new ChronicCondition();

	public ChronicConditionListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public ChronicCondition getChronicCondition() {
		return chronicCondition;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	private com.oreon.cerebrum.patient.Patient patientsToSearch;

	public void setPatientsToSearch(
			com.oreon.cerebrum.patient.Patient patientToSearch) {
		this.patientsToSearch = patientToSearch;
	}

	public com.oreon.cerebrum.patient.Patient getPatientsToSearch() {
		return patientsToSearch;
	}

	@Override
	public ChronicCondition getInstance() {
		return getChronicCondition();
	}

	@Override
	//@Restrict("#{s:hasPermission('chronicCondition', 'view')}")
	public List<ChronicCondition> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<ChronicCondition> getEntityClass() {
		return ChronicCondition.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"chronicCondition.id = #{chronicConditionList.chronicCondition.id}",

			"chronicCondition.archived = #{chronicConditionList.chronicCondition.archived}",

			"lower(chronicCondition.name) like concat(lower(#{chronicConditionList.chronicCondition.name}),'%')",

			"#{chronicConditionList.patientsToSearch} in elements(chronicCondition.patients)",

			"chronicCondition.dateCreated <= #{chronicConditionList.dateCreatedRange.end}",
			"chronicCondition.dateCreated >= #{chronicConditionList.dateCreatedRange.begin}",};

	@Observer("archivedChronicCondition")
	public void onArchive() {
		refresh();
	}

}
