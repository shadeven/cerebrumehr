package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.Vaccine;

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

import com.oreon.cerebrum.patient.Vaccine;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class VaccineListQueryBase extends BaseQuery<Vaccine, Long> {

	private static final String EJBQL = "select vaccine from Vaccine vaccine";

	protected Vaccine vaccine = new Vaccine();

	public VaccineListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Vaccine getVaccine() {
		return vaccine;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Vaccine getInstance() {
		return getVaccine();
	}

	@Override
	//@Restrict("#{s:hasPermission('vaccine', 'view')}")
	public List<Vaccine> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Vaccine> getEntityClass() {
		return Vaccine.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"vaccine.id = #{vaccineList.vaccine.id}",

			"vaccine.archived = #{vaccineList.vaccine.archived}",

			"lower(vaccine.name) like concat(lower(#{vaccineList.vaccine.name}),'%')",

			"vaccine.dateCreated <= #{vaccineList.dateCreatedRange.end}",
			"vaccine.dateCreated >= #{vaccineList.dateCreatedRange.begin}",};

	@Observer("archivedVaccine")
	public void onArchive() {
		refresh();
	}

}
