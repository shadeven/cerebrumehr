package com.oreon.cerebrum.web.action.unusualoccurences;

import com.oreon.cerebrum.unusualoccurences.UnusualOccurence;

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

import com.oreon.cerebrum.unusualoccurences.UnusualOccurence;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class UnusualOccurenceListQueryBase
		extends
			BaseQuery<UnusualOccurence, Long> {

	private static final String EJBQL = "select unusualOccurence from UnusualOccurence unusualOccurence";

	protected UnusualOccurence unusualOccurence = new UnusualOccurence();

	public UnusualOccurenceListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public UnusualOccurence getUnusualOccurence() {
		return unusualOccurence;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public UnusualOccurence getInstance() {
		return getUnusualOccurence();
	}

	@Override
	//@Restrict("#{s:hasPermission('unusualOccurence', 'view')}")
	public List<UnusualOccurence> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<UnusualOccurence> getEntityClass() {
		return UnusualOccurence.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"unusualOccurence.id = #{unusualOccurenceList.unusualOccurence.id}",

			"unusualOccurence.archived = #{unusualOccurenceList.unusualOccurence.archived}",

			"unusualOccurence.occurenceType.id = #{unusualOccurenceList.unusualOccurence.occurenceType.id}",

			"unusualOccurence.category = #{unusualOccurenceList.unusualOccurence.category}",

			"unusualOccurence.severity = #{unusualOccurenceList.unusualOccurence.severity}",

			"lower(unusualOccurence.description) like concat(lower(#{unusualOccurenceList.unusualOccurence.description}),'%')",

			"unusualOccurence.patient.id = #{unusualOccurenceList.unusualOccurence.patient.id}",

			"unusualOccurence.dateCreated <= #{unusualOccurenceList.dateCreatedRange.end}",
			"unusualOccurence.dateCreated >= #{unusualOccurenceList.dateCreatedRange.begin}",};

	/** 
	 * List of all UnusualOccurences for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<UnusualOccurence> getAllUnusualOccurencesByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		unusualOccurence.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<UnusualOccurence> getUnusualOccurencesByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<UnusualOccurence, Long> unusualOccurenceLazyDataModel = new EntityLazyDataModel<UnusualOccurence, Long>(
				this) {

			@Override
			public List<UnusualOccurence> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				unusualOccurence.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return unusualOccurenceLazyDataModel;

	}

	@Observer("archivedUnusualOccurence")
	public void onArchive() {
		refresh();
	}

	public void setOccurenceTypeId(Long id) {
		if (unusualOccurence.getOccurenceType() == null) {
			unusualOccurence
					.setOccurenceType(new com.oreon.cerebrum.unusualoccurences.OccurenceType());
		}
		unusualOccurence.getOccurenceType().setId(id);
	}

	public Long getOccurenceTypeId() {
		return unusualOccurence.getOccurenceType() == null
				? null
				: unusualOccurence.getOccurenceType().getId();
	}

	public void setPatientId(Long id) {
		if (unusualOccurence.getPatient() == null) {
			unusualOccurence
					.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		unusualOccurence.getPatient().setId(id);
	}

	public Long getPatientId() {
		return unusualOccurence.getPatient() == null ? null : unusualOccurence
				.getPatient().getId();
	}

}
