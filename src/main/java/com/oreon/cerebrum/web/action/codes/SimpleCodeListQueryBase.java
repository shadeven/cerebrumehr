package com.oreon.cerebrum.web.action.codes;

import com.oreon.cerebrum.codes.SimpleCode;

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

import com.oreon.cerebrum.codes.SimpleCode;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SimpleCodeListQueryBase
		extends
			BaseQuery<SimpleCode, Long> {

	private static final String EJBQL = "select simpleCode from SimpleCode simpleCode";

	protected SimpleCode simpleCode = new SimpleCode();

	public SimpleCodeListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public SimpleCode getSimpleCode() {
		return simpleCode;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	private com.oreon.cerebrum.encounter.Encounter encountersToSearch;

	public void setEncountersToSearch(
			com.oreon.cerebrum.encounter.Encounter encounterToSearch) {
		this.encountersToSearch = encounterToSearch;
	}

	public com.oreon.cerebrum.encounter.Encounter getEncountersToSearch() {
		return encountersToSearch;
	}

	@Override
	public SimpleCode getInstance() {
		return getSimpleCode();
	}

	@Override
	//@Restrict("#{s:hasPermission('simpleCode', 'view')}")
	public List<SimpleCode> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<SimpleCode> getEntityClass() {
		return SimpleCode.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"simpleCode.id = #{simpleCodeList.simpleCode.id}",

			"simpleCode.archived = #{simpleCodeList.simpleCode.archived}",

			"lower(simpleCode.name) like concat(lower(#{simpleCodeList.simpleCode.name}),'%')",

			"#{simpleCodeList.encountersToSearch} in elements(simpleCode.encounters)",

			"simpleCode.dateCreated <= #{simpleCodeList.dateCreatedRange.end}",
			"simpleCode.dateCreated >= #{simpleCodeList.dateCreatedRange.begin}",};

	@Observer("archivedSimpleCode")
	public void onArchive() {
		refresh();
	}

}
