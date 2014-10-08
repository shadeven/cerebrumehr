package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.PhysicalFinding;

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

import com.oreon.cerebrum.ddx.PhysicalFinding;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PhysicalFindingListQueryBase
		extends
			BaseQuery<PhysicalFinding, Long> {

	private static final String EJBQL = "select physicalFinding from PhysicalFinding physicalFinding";

	protected PhysicalFinding physicalFinding = new PhysicalFinding();

	public PhysicalFindingListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PhysicalFinding getPhysicalFinding() {
		return physicalFinding;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public PhysicalFinding getInstance() {
		return getPhysicalFinding();
	}

	@Override
	//@Restrict("#{s:hasPermission('physicalFinding', 'view')}")
	public List<PhysicalFinding> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PhysicalFinding> getEntityClass() {
		return PhysicalFinding.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"physicalFinding.id = #{physicalFindingList.physicalFinding.id}",

			"physicalFinding.archived = #{physicalFindingList.physicalFinding.archived}",

			"lower(physicalFinding.name) like concat(lower(#{physicalFindingList.physicalFinding.name}),'%')",

			"lower(physicalFinding.icdCode) like concat(lower(#{physicalFindingList.physicalFinding.icdCode}),'%')",

			"physicalFinding.dateCreated <= #{physicalFindingList.dateCreatedRange.end}",
			"physicalFinding.dateCreated >= #{physicalFindingList.dateCreatedRange.begin}",};

	@Observer("archivedPhysicalFinding")
	public void onArchive() {
		refresh();
	}

}
