package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.Immunization;

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

import com.oreon.cerebrum.patient.Immunization;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ImmunizationListQueryBase
		extends
			BaseQuery<Immunization, Long> {

	private static final String EJBQL = "select immunization from Immunization immunization";

	protected Immunization immunization = new Immunization();

	public ImmunizationListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Immunization getImmunization() {
		return immunization;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Immunization getInstance() {
		return getImmunization();
	}

	@Override
	//@Restrict("#{s:hasPermission('immunization', 'view')}")
	public List<Immunization> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Immunization> getEntityClass() {
		return Immunization.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateRange = new Range<Date>();

	public Range<Date> getDateRange() {
		return dateRange;
	}
	public void setDate(Range<Date> dateRange) {
		this.dateRange = dateRange;
	}

	private static final String[] RESTRICTIONS = {
			"immunization.id = #{immunizationList.immunization.id}",

			"immunization.archived = #{immunizationList.immunization.archived}",

			"immunization.date >= #{immunizationList.dateRange.begin}",
			"immunization.date <= #{immunizationList.dateRange.end}",

			"immunization.patient.id = #{immunizationList.immunization.patient.id}",

			"immunization.vaccine.id = #{immunizationList.immunization.vaccine.id}",

			"immunization.dateCreated <= #{immunizationList.dateCreatedRange.end}",
			"immunization.dateCreated >= #{immunizationList.dateCreatedRange.begin}",};

	/** 
	 * List of all Immunizations for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<Immunization> getAllImmunizationsByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		immunization.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<Immunization> getImmunizationsByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<Immunization, Long> immunizationLazyDataModel = new EntityLazyDataModel<Immunization, Long>(
				this) {

			@Override
			public List<Immunization> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				immunization.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return immunizationLazyDataModel;

	}

	@Observer("archivedImmunization")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (immunization.getPatient() == null) {
			immunization.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		immunization.getPatient().setId(id);
	}

	public Long getPatientId() {
		return immunization.getPatient() == null ? null : immunization
				.getPatient().getId();
	}

	public void setVaccineId(Long id) {
		if (immunization.getVaccine() == null) {
			immunization.setVaccine(new com.oreon.cerebrum.patient.Vaccine());
		}
		immunization.getVaccine().setId(id);
	}

	public Long getVaccineId() {
		return immunization.getVaccine() == null ? null : immunization
				.getVaccine().getId();
	}

}
