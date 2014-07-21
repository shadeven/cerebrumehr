package com.oreon.cerebrum.web.action.patient;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.patient.Patient;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PatientListQueryBase extends BaseQuery<Patient, Long> {

	private static final String EJBQL = "select patient from Patient patient";

	protected Patient patient = new Patient();

	public PatientListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Patient getPatient() {
		return patient;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	private com.oreon.cerebrum.ddx.ChronicCondition chronicConditionsToSearch;

	public void setChronicConditionsToSearch(
			com.oreon.cerebrum.ddx.ChronicCondition chronicConditionToSearch) {
		this.chronicConditionsToSearch = chronicConditionToSearch;
	}

	public com.oreon.cerebrum.ddx.ChronicCondition getChronicConditionsToSearch() {
		return chronicConditionsToSearch;
	}

	@Override
	public Patient getInstance() {
		return getPatient();
	}

	@Override
	//@Restrict("#{s:hasPermission('patient', 'view')}")
	public List<Patient> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Patient> getEntityClass() {
		return Patient.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateOfBirthRange = new Range<Date>();

	public Range<Date> getDateOfBirthRange() {
		return dateOfBirthRange;
	}
	public void setDateOfBirth(Range<Date> dateOfBirthRange) {
		this.dateOfBirthRange = dateOfBirthRange;
	}

	private static final String[] RESTRICTIONS = {
			"patient.id = #{patientList.patient.id}",

			"patient.archived = #{patientList.patient.archived}",

			"lower(patient.firstName) like concat(lower(#{patientList.patient.firstName}),'%')",

			"lower(patient.lastName) like concat(lower(#{patientList.patient.lastName}),'%')",

			"patient.dateOfBirth >= #{patientList.dateOfBirthRange.begin}",
			"patient.dateOfBirth <= #{patientList.dateOfBirthRange.end}",

			"patient.gender = #{patientList.patient.gender}",

			"lower(patient.contactDetails.primaryPhone) like concat(lower(#{patientList.patient.contactDetails.primaryPhone}),'%')",

			"lower(patient.contactDetails.secondaryPhone) like concat(lower(#{patientList.patient.contactDetails.secondaryPhone}),'%')",

			"lower(patient.contactDetails.email) like concat(lower(#{patientList.patient.contactDetails.email}),'%')",

			"patient.title = #{patientList.patient.title}",

			"lower(patient.address.streetAddress) like concat(lower(#{patientList.patient.address.streetAddress}),'%')",

			"lower(patient.address.city) like concat(lower(#{patientList.patient.address.city}),'%')",

			"lower(patient.address.state) like concat(lower(#{patientList.patient.address.state}),'%')",

			"lower(patient.address.phone) like concat(lower(#{patientList.patient.address.phone}),'%')",

			"lower(patient.healthNumber) like concat(lower(#{patientList.patient.healthNumber}),'%')",

			"lower(patient.history.medicalHistory) like concat(lower(#{patientList.patient.history.medicalHistory}),'%')",

			"lower(patient.history.socialHistory) like concat(lower(#{patientList.patient.history.socialHistory}),'%')",

			"lower(patient.history.familyHistory) like concat(lower(#{patientList.patient.history.familyHistory}),'%')",

			"lower(patient.history.medications) like concat(lower(#{patientList.patient.history.medications}),'%')",

			"lower(patient.history.allergies) like concat(lower(#{patientList.patient.history.allergies}),'%')",

			"patient.bed.id = #{patientList.patient.bed.id}",

			"#{patientList.chronicConditionsToSearch} in elements(patient.chronicConditions)",

			"patient.dateCreated <= #{patientList.dateCreatedRange.end}",
			"patient.dateCreated >= #{patientList.dateCreatedRange.begin}",};

	/** 
	 * List of all Patients for the given Bed
	 * @param patient
	 * @return 
	 */
	public List<Patient> getAllPatientByBed(
			final com.oreon.cerebrum.facility.Bed bed) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		patient.setBed(bed);
		return getResultListTable();
	}

	public LazyDataModel<Patient> getPatientByBed(
			final com.oreon.cerebrum.facility.Bed bed) {

		EntityLazyDataModel<Patient, Long> patientLazyDataModel = new EntityLazyDataModel<Patient, Long>(
				this) {

			@Override
			public List<Patient> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				patient.setBed(bed);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return patientLazyDataModel;

	}

	@Observer("archivedPatient")
	public void onArchive() {
		refresh();
	}

	@Override
	protected void setupForAutoComplete(String input) {

		patient.setFirstName(input);

		patient.setLastName(input);

		patient.getAddress().setPhone(input);

	}

	public void setBedId(Long id) {
		if (patient.getBed() == null) {
			patient.setBed(new com.oreon.cerebrum.facility.Bed());
		}
		patient.getBed().setId(id);
	}

	public Long getBedId() {
		return patient.getBed() == null ? null : patient.getBed().getId();
	}

}
