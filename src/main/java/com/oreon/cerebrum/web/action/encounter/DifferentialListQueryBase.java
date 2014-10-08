package com.oreon.cerebrum.web.action.encounter;

import com.oreon.cerebrum.encounter.Differential;

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

import com.oreon.cerebrum.encounter.Differential;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DifferentialListQueryBase
		extends
			BaseQuery<Differential, Long> {

	private static final String EJBQL = "select differential from Differential differential";

	protected Differential differential = new Differential();

	public DifferentialListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Differential getDifferential() {
		return differential;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Differential getInstance() {
		return getDifferential();
	}

	@Override
	//@Restrict("#{s:hasPermission('differential', 'view')}")
	public List<Differential> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Differential> getEntityClass() {
		return Differential.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"differential.id = #{differentialList.differential.id}",

			"differential.archived = #{differentialList.differential.archived}",

			"lower(differential.remarks) like concat(lower(#{differentialList.differential.remarks}),'%')",

			"differential.dateCreated <= #{differentialList.dateCreatedRange.end}",
			"differential.dateCreated >= #{differentialList.dateCreatedRange.begin}",};

	@Observer("archivedDifferential")
	public void onArchive() {
		refresh();
	}

}
