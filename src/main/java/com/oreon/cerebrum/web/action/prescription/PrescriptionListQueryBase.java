package com.oreon.cerebrum.web.action.prescription;

import com.oreon.cerebrum.prescription.Prescription;

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

import com.oreon.cerebrum.prescription.Prescription;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PrescriptionListQueryBase
		extends
			BaseQuery<Prescription, Long> {

	private static final String EJBQL = "select prescription from Prescription prescription";

	protected Prescription prescription = new Prescription();

	public PrescriptionListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Prescription getPrescription() {
		return prescription;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Prescription getInstance() {
		return getPrescription();
	}

	@Override
	//@Restrict("#{s:hasPermission('prescription', 'view')}")
	public List<Prescription> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Prescription> getEntityClass() {
		return Prescription.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"prescription.id = #{prescriptionList.prescription.id}",

			"prescription.archived = #{prescriptionList.prescription.archived}",

			"prescription.patient.id = #{prescriptionList.prescription.patient.id}",

			"lower(prescription.directivesForPatient) like concat(lower(#{prescriptionList.prescription.directivesForPatient}),'%')",

			"prescription.active = #{prescriptionList.prescription.active}",

			"prescription.dateCreated <= #{prescriptionList.dateCreatedRange.end}",
			"prescription.dateCreated >= #{prescriptionList.dateCreatedRange.begin}",};

	/** 
	 * List of all Prescriptions for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<Prescription> getAllPrescriptionsByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		prescription.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<Prescription> getPrescriptionsByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<Prescription, Long> prescriptionLazyDataModel = new EntityLazyDataModel<Prescription, Long>(
				this) {

			@Override
			public List<Prescription> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				prescription.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return prescriptionLazyDataModel;

	}

	@Observer("archivedPrescription")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (prescription.getPatient() == null) {
			prescription.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		prescription.getPatient().setId(id);
	}

	public Long getPatientId() {
		return prescription.getPatient() == null ? null : prescription
				.getPatient().getId();
	}

}
