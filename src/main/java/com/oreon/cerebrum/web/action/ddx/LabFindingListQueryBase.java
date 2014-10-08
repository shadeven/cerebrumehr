package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.LabFinding;

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

import com.oreon.cerebrum.ddx.LabFinding;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class LabFindingListQueryBase
		extends
			BaseQuery<LabFinding, Long> {

	private static final String EJBQL = "select labFinding from LabFinding labFinding";

	protected LabFinding labFinding = new LabFinding();

	public LabFindingListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public LabFinding getLabFinding() {
		return labFinding;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public LabFinding getInstance() {
		return getLabFinding();
	}

	@Override
	//@Restrict("#{s:hasPermission('labFinding', 'view')}")
	public List<LabFinding> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<LabFinding> getEntityClass() {
		return LabFinding.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"labFinding.id = #{labFindingList.labFinding.id}",

			"labFinding.archived = #{labFindingList.labFinding.archived}",

			"lower(labFinding.name) like concat(lower(#{labFindingList.labFinding.name}),'%')",

			"lower(labFinding.testName) like concat(lower(#{labFindingList.labFinding.testName}),'%')",

			"labFinding.dateCreated <= #{labFindingList.dateCreatedRange.end}",
			"labFinding.dateCreated >= #{labFindingList.dateCreatedRange.begin}",};

	@Observer("archivedLabFinding")
	public void onArchive() {
		refresh();
	}

}
