package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.NurseSpecialty;

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

import com.oreon.cerebrum.employee.NurseSpecialty;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class NurseSpecialtyListQueryBase
		extends
			BaseQuery<NurseSpecialty, Long> {

	private static final String EJBQL = "select nurseSpecialty from NurseSpecialty nurseSpecialty";

	protected NurseSpecialty nurseSpecialty = new NurseSpecialty();

	public NurseSpecialtyListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public NurseSpecialty getNurseSpecialty() {
		return nurseSpecialty;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public NurseSpecialty getInstance() {
		return getNurseSpecialty();
	}

	@Override
	//@Restrict("#{s:hasPermission('nurseSpecialty', 'view')}")
	public List<NurseSpecialty> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<NurseSpecialty> getEntityClass() {
		return NurseSpecialty.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"nurseSpecialty.id = #{nurseSpecialtyList.nurseSpecialty.id}",

			"nurseSpecialty.archived = #{nurseSpecialtyList.nurseSpecialty.archived}",

			"lower(nurseSpecialty.name) like concat(lower(#{nurseSpecialtyList.nurseSpecialty.name}),'%')",

			"nurseSpecialty.dateCreated <= #{nurseSpecialtyList.dateCreatedRange.end}",
			"nurseSpecialty.dateCreated >= #{nurseSpecialtyList.dateCreatedRange.begin}",};

	@Observer("archivedNurseSpecialty")
	public void onArchive() {
		refresh();
	}

}
