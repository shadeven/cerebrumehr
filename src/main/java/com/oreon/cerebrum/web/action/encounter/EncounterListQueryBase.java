package com.oreon.cerebrum.web.action.encounter;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.encounter.Encounter;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class EncounterListQueryBase extends BaseQuery<Encounter, Long> {

	private static final String EJBQL = "select encounter from Encounter encounter";

	protected Encounter encounter = new Encounter();

	public EncounterListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Encounter getEncounter() {
		return encounter;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	private com.oreon.cerebrum.codes.SimpleCode simpleCodesToSearch;

	public void setSimpleCodesToSearch(
			com.oreon.cerebrum.codes.SimpleCode simpleCodeToSearch) {
		this.simpleCodesToSearch = simpleCodeToSearch;
	}

	public com.oreon.cerebrum.codes.SimpleCode getSimpleCodesToSearch() {
		return simpleCodesToSearch;
	}

	@Override
	public Encounter getInstance() {
		return getEncounter();
	}

	@Override
	//@Restrict("#{s:hasPermission('encounter', 'view')}")
	public List<Encounter> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Encounter> getEntityClass() {
		return Encounter.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> vitals_SysBPRange = new Range<Integer>();

	public Range<Integer> getVitals_SysBPRange() {
		return vitals_SysBPRange;
	}
	public void setVitals_SysBP(Range<Integer> vitals_SysBPRange) {
		this.vitals_SysBPRange = vitals_SysBPRange;
	}

	private Range<Integer> vitals_DiasBPRange = new Range<Integer>();

	public Range<Integer> getVitals_DiasBPRange() {
		return vitals_DiasBPRange;
	}
	public void setVitals_DiasBP(Range<Integer> vitals_DiasBPRange) {
		this.vitals_DiasBPRange = vitals_DiasBPRange;
	}

	private Range<Double> vitals_TemperatureRange = new Range<Double>();

	public Range<Double> getVitals_TemperatureRange() {
		return vitals_TemperatureRange;
	}
	public void setVitals_Temperature(Range<Double> vitals_TemperatureRange) {
		this.vitals_TemperatureRange = vitals_TemperatureRange;
	}

	private Range<Integer> vitals_PulseRange = new Range<Integer>();

	public Range<Integer> getVitals_PulseRange() {
		return vitals_PulseRange;
	}
	public void setVitals_Pulse(Range<Integer> vitals_PulseRange) {
		this.vitals_PulseRange = vitals_PulseRange;
	}

	private Range<Integer> vitals_RespirationRateRange = new Range<Integer>();

	public Range<Integer> getVitals_RespirationRateRange() {
		return vitals_RespirationRateRange;
	}
	public void setVitals_RespirationRate(
			Range<Integer> vitals_RespirationRateRange) {
		this.vitals_RespirationRateRange = vitals_RespirationRateRange;
	}

	private static final String[] RESTRICTIONS = {
			"encounter.id = #{encounterList.encounter.id}",

			"encounter.archived = #{encounterList.encounter.archived}",

			"lower(encounter.patientNote) like concat(lower(#{encounterList.encounter.patientNote}),'%')",

			"encounter.vitals.sysBP >= #{encounterList.vitals_SysBPRange.begin}",
			"encounter.vitals.sysBP <= #{encounterList.vitals_SysBPRange.end}",

			"encounter.vitals.diasBP >= #{encounterList.vitals_DiasBPRange.begin}",
			"encounter.vitals.diasBP <= #{encounterList.vitals_DiasBPRange.end}",

			"encounter.vitals.temperature >= #{encounterList.vitals_TemperatureRange.begin}",
			"encounter.vitals.temperature <= #{encounterList.vitals_TemperatureRange.end}",

			"encounter.vitals.pulse >= #{encounterList.vitals_PulseRange.begin}",
			"encounter.vitals.pulse <= #{encounterList.vitals_PulseRange.end}",

			"encounter.vitals.respirationRate >= #{encounterList.vitals_RespirationRateRange.begin}",
			"encounter.vitals.respirationRate <= #{encounterList.vitals_RespirationRateRange.end}",

			"encounter.prescription.id = #{encounterList.encounter.prescription.id}",

			"encounter.patient.id = #{encounterList.encounter.patient.id}",

			"encounter.creator.id = #{encounterList.encounter.creator.id}",

			"#{encounterList.simpleCodesToSearch} in elements(encounter.simpleCodes)",

			"encounter.dateCreated <= #{encounterList.dateCreatedRange.end}",
			"encounter.dateCreated >= #{encounterList.dateCreatedRange.begin}",};

	/** 
	 * List of all Encounters for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<Encounter> getAllEncountersByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		encounter.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<Encounter> getEncountersByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<Encounter, Long> encounterLazyDataModel = new EntityLazyDataModel<Encounter, Long>(
				this) {

			@Override
			public List<Encounter> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				encounter.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return encounterLazyDataModel;

	}

	@Observer("archivedEncounter")
	public void onArchive() {
		refresh();
	}

	public void setPrescriptionId(Long id) {
		if (encounter.getPrescription() == null) {
			encounter
					.setPrescription(new com.oreon.cerebrum.prescription.Prescription());
		}
		encounter.getPrescription().setId(id);
	}

	public Long getPrescriptionId() {
		return encounter.getPrescription() == null ? null : encounter
				.getPrescription().getId();
	}

	public void setPatientId(Long id) {
		if (encounter.getPatient() == null) {
			encounter.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		encounter.getPatient().setId(id);
	}

	public Long getPatientId() {
		return encounter.getPatient() == null ? null : encounter.getPatient()
				.getId();
	}

}
