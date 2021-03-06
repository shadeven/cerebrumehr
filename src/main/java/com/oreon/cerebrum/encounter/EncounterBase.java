package com.oreon.cerebrum.encounter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import com.google.common.base.Objects;

import javax.persistence.*;
import org.hibernate.validator.*;

import com.google.common.base.Objects;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.annotations.Filter;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.model.support.audit.Auditable;

import org.witchcraft.utils.*;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.ProjectUtils;

//Impl 

/**
 * This file is generated by Witchcraftmda.
 * DO NOT MODIFY any changes will be overwritten with the next run of the code generator.
 *
 */

/**
 * 
 *
 */

@MappedSuperclass
public class EncounterBase extends BaseEntity {
	private static final long serialVersionUID = -1171400456L;

	@Lob
	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String patientNote

	;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "encounter_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<PrescribedTest> prescribedTests = new HashSet<PrescribedTest>();

	public void addPrescribedTest(PrescribedTest prescribedTest) {

		prescribedTest.setEncounter((Encounter) this);

		this.prescribedTests.add(prescribedTest);
	}

	@Transient
	public List<com.oreon.cerebrum.encounter.PrescribedTest> getListPrescribedTests() {
		return new ArrayList<com.oreon.cerebrum.encounter.PrescribedTest>(
				prescribedTests);
	}

	@Transient
	public String getListPrescribedTestsAsString() {
		StringBuilder result = new StringBuilder();

		List<com.oreon.cerebrum.encounter.PrescribedTest> tempList = getListPrescribedTests();
		int count = 0;
		for (com.oreon.cerebrum.encounter.PrescribedTest prescribedTest : tempList) {
			++count;
			result.append(prescribedTest.getDisplayName());
			if (count < tempList.size())
				result.append(", ");
		}

		return result.toString();
	}

	//JSF Friendly function to get count of collections
	public int getPrescribedTestsCount() {
		return prescribedTests.size();
	}

	@IndexedEmbedded
	@AttributeOverrides({

			@AttributeOverride(name = "SysBP", column = @Column(name = "vitals_SysBP")),

			@AttributeOverride(name = "DiasBP", column = @Column(name = "vitals_DiasBP")),

			@AttributeOverride(name = "Temperature", column = @Column(name = "vitals_Temperature")),

			@AttributeOverride(name = "Pulse", column = @Column(name = "vitals_Pulse")),

			@AttributeOverride(name = "RespirationRate", column = @Column(name = "vitals_RespirationRate"))

	})
	protected Vitals vitals = new Vitals();

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "prescription_id", nullable = true, updatable = true)
	protected com.oreon.cerebrum.prescription.Prescription prescription

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.patient.Patient patient

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.employee.Employee creator

	;

	@Transient
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String tests

	;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "encounters_simpleCodes", joinColumns = @JoinColumn(name = "encounters_ID"), inverseJoinColumns = @JoinColumn(name = "simpleCodes_ID"))
	private Set<com.oreon.cerebrum.codes.SimpleCode> simpleCodes = new HashSet<com.oreon.cerebrum.codes.SimpleCode>();

	public void addSimpleCode(com.oreon.cerebrum.codes.SimpleCode simpleCode) {

		this.simpleCodes.add(simpleCode);
	}

	@Transient
	public List<com.oreon.cerebrum.codes.SimpleCode> getListSimpleCodes() {
		return new ArrayList<com.oreon.cerebrum.codes.SimpleCode>(simpleCodes);
	}

	@Transient
	public String getListSimpleCodesAsString() {
		StringBuilder result = new StringBuilder();

		List<com.oreon.cerebrum.codes.SimpleCode> tempList = getListSimpleCodes();
		int count = 0;
		for (com.oreon.cerebrum.codes.SimpleCode simpleCode : tempList) {
			++count;
			result.append(simpleCode.getDisplayName());
			if (count < tempList.size())
				result.append(", ");
		}

		return result.toString();
	}

	//JSF Friendly function to get count of collections
	public int getSimpleCodesCount() {
		return simpleCodes.size();
	}

	public void setPatientNote(String patientNote) {
		this.patientNote = patientNote;
	}

	public String getPatientNote() {

		return patientNote;

	}

	public void setPrescribedTests(Set<PrescribedTest> prescribedTests) {
		this.prescribedTests = prescribedTests;
	}

	public Set<PrescribedTest> getPrescribedTests() {
		return prescribedTests;
	}

	public void setVitals(Vitals vitals) {
		this.vitals = vitals;
	}

	public Vitals getVitals() {

		if (vitals == null)
			vitals = new com.oreon.cerebrum.encounter.Vitals();
		return vitals;

	}

	public void setPrescription(
			com.oreon.cerebrum.prescription.Prescription prescription) {
		this.prescription = prescription;
	}

	public com.oreon.cerebrum.prescription.Prescription getPrescription() {

		return prescription;

	}

	public void setPatient(com.oreon.cerebrum.patient.Patient patient) {
		this.patient = patient;
	}

	public com.oreon.cerebrum.patient.Patient getPatient() {

		return patient;

	}

	public void setCreator(com.oreon.cerebrum.employee.Employee creator) {
		this.creator = creator;
	}

	public com.oreon.cerebrum.employee.Employee getCreator() {

		return creator;

	}

	public void setTests(String tests) {
		this.tests = tests;
	}

	public String getTests() {

		try {
			return ProjectUtils.getTests(this);
		} catch (Exception e) {

			return "";

		}

	}

	public void setSimpleCodes(
			Set<com.oreon.cerebrum.codes.SimpleCode> simpleCodes) {
		this.simpleCodes = simpleCodes;
	}

	public Set<com.oreon.cerebrum.codes.SimpleCode> getSimpleCodes() {
		return simpleCodes;
	}

	@Transient
	public String getDisplayName() {
		try {
			return tests;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	@Transient
	public String getPatientNoteAbbreviated() {
		try {
			return org.apache.commons.lang.WordUtils.abbreviate(patientNote
					.trim(), 100, 200, "...");
		} catch (Exception e) {
			return patientNote != null ? patientNote : "";
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BaseEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("patientNote");

		listSearchableFields.add("tests");

		listSearchableFields.add("prescribedTests.remarks");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getPatientNote() + " ");

		builder.append(getTests() + " ");

		if (getPrescription() != null)
			builder.append("prescription:" + getPrescription().getDisplayName()
					+ " ");

		if (getPatient() != null)
			builder.append("patient:" + getPatient().getDisplayName() + " ");

		if (getCreator() != null)
			builder.append("creator:" + getCreator().getDisplayName() + " ");

		for (BaseEntity e : prescribedTests) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

	/*
	<param name="prescriptionId" value="#{prescriptionId}" />
	<param name="patientId" value="#{patientId}" />
	<param name="creatorId" value="#{creatorId}" />
	
	 */

}
