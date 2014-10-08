package com.oreon.cerebrum.web.action.patient;

import com.oreon.cerebrum.patient.PatientDocument;

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

import com.oreon.cerebrum.patient.PatientDocument;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PatientDocumentListQueryBase
		extends
			BaseQuery<PatientDocument, Long> {

	private static final String EJBQL = "select patientDocument from PatientDocument patientDocument";

	protected PatientDocument patientDocument = new PatientDocument();

	public PatientDocumentListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PatientDocument getPatientDocument() {
		return patientDocument;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public PatientDocument getInstance() {
		return getPatientDocument();
	}

	@Override
	//@Restrict("#{s:hasPermission('patientDocument', 'view')}")
	public List<PatientDocument> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PatientDocument> getEntityClass() {
		return PatientDocument.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"patientDocument.id = #{patientDocumentList.patientDocument.id}",

			"patientDocument.archived = #{patientDocumentList.patientDocument.archived}",

			"lower(patientDocument.name) like concat(lower(#{patientDocumentList.patientDocument.name}),'%')",

			"lower(patientDocument.notes) like concat(lower(#{patientDocumentList.patientDocument.notes}),'%')",

			"patientDocument.patient.id = #{patientDocumentList.patientDocument.patient.id}",

			"patientDocument.dateCreated <= #{patientDocumentList.dateCreatedRange.end}",
			"patientDocument.dateCreated >= #{patientDocumentList.dateCreatedRange.begin}",};

	/** 
	 * List of all PatientDocuments for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<PatientDocument> getAllPatientDocumentsByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		patientDocument.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<PatientDocument> getPatientDocumentsByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<PatientDocument, Long> patientDocumentLazyDataModel = new EntityLazyDataModel<PatientDocument, Long>(
				this) {

			@Override
			public List<PatientDocument> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				patientDocument.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return patientDocumentLazyDataModel;

	}

	@Observer("archivedPatientDocument")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (patientDocument.getPatient() == null) {
			patientDocument
					.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		patientDocument.getPatient().setId(id);
	}

	public Long getPatientId() {
		return patientDocument.getPatient() == null ? null : patientDocument
				.getPatient().getId();
	}

}
