package com.oreon.cerebrum.web.action.patient;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.patient.Allergy;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AllergyListQueryBase extends BaseQuery<Allergy, Long> {

	private static final String EJBQL = "select allergy from Allergy allergy";

	protected Allergy allergy = new Allergy();

	public AllergyListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Allergy getAllergy() {
		return allergy;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Allergy getInstance() {
		return getAllergy();
	}

	@Override
	//@Restrict("#{s:hasPermission('allergy', 'view')}")
	public List<Allergy> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Allergy> getEntityClass() {
		return Allergy.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"allergy.id = #{allergyList.allergy.id}",

			"allergy.archived = #{allergyList.allergy.archived}",

			"allergy.patient.id = #{allergyList.allergy.patient.id}",

			"allergy.severity = #{allergyList.allergy.severity}",

			"allergy.drug.id = #{allergyList.allergy.drug.id}",

			"allergy.dateCreated <= #{allergyList.dateCreatedRange.end}",
			"allergy.dateCreated >= #{allergyList.dateCreatedRange.begin}",};

	/** 
	 * List of all Allergys for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<Allergy> getAllAllergysByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		allergy.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<Allergy> getAllergysByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<Allergy, Long> allergyLazyDataModel = new EntityLazyDataModel<Allergy, Long>(
				this) {

			@Override
			public List<Allergy> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				allergy.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return allergyLazyDataModel;

	}

	@Observer("archivedAllergy")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (allergy.getPatient() == null) {
			allergy.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		allergy.getPatient().setId(id);
	}

	public Long getPatientId() {
		return allergy.getPatient() == null ? null : allergy.getPatient()
				.getId();
	}

	public void setDrugId(Long id) {
		if (allergy.getDrug() == null) {
			allergy.setDrug(new com.oreon.cerebrum.drugs.Drug());
		}
		allergy.getDrug().setId(id);
	}

	public Long getDrugId() {
		return allergy.getDrug() == null ? null : allergy.getDrug().getId();
	}

}
