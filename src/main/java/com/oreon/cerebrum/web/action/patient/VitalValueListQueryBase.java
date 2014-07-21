package com.oreon.cerebrum.web.action.patient;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.patient.VitalValue;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class VitalValueListQueryBase
		extends
			BaseQuery<VitalValue, Long> {

	private static final String EJBQL = "select vitalValue from VitalValue vitalValue";

	protected VitalValue vitalValue = new VitalValue();

	public VitalValueListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public VitalValue getVitalValue() {
		return vitalValue;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public VitalValue getInstance() {
		return getVitalValue();
	}

	@Override
	//@Restrict("#{s:hasPermission('vitalValue', 'view')}")
	public List<VitalValue> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<VitalValue> getEntityClass() {
		return VitalValue.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> valueRange = new Range<Double>();

	public Range<Double> getValueRange() {
		return valueRange;
	}
	public void setValue(Range<Double> valueRange) {
		this.valueRange = valueRange;
	}

	private static final String[] RESTRICTIONS = {
			"vitalValue.id = #{vitalValueList.vitalValue.id}",

			"vitalValue.archived = #{vitalValueList.vitalValue.archived}",

			"vitalValue.value >= #{vitalValueList.valueRange.begin}",
			"vitalValue.value <= #{vitalValueList.valueRange.end}",

			"vitalValue.trackedVital.id = #{vitalValueList.vitalValue.trackedVital.id}",

			"vitalValue.patient.id = #{vitalValueList.vitalValue.patient.id}",

			"lower(vitalValue.remarks) like concat(lower(#{vitalValueList.vitalValue.remarks}),'%')",

			"vitalValue.dateCreated <= #{vitalValueList.dateCreatedRange.end}",
			"vitalValue.dateCreated >= #{vitalValueList.dateCreatedRange.begin}",};

	/** 
	 * List of all VitalValues for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<VitalValue> getAllVitalValuesByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		vitalValue.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<VitalValue> getVitalValuesByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<VitalValue, Long> vitalValueLazyDataModel = new EntityLazyDataModel<VitalValue, Long>(
				this) {

			@Override
			public List<VitalValue> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				vitalValue.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return vitalValueLazyDataModel;

	}

	@Observer("archivedVitalValue")
	public void onArchive() {
		refresh();
	}

	public void setTrackedVitalId(Long id) {
		if (vitalValue.getTrackedVital() == null) {
			vitalValue
					.setTrackedVital(new com.oreon.cerebrum.patient.TrackedVital());
		}
		vitalValue.getTrackedVital().setId(id);
	}

	public Long getTrackedVitalId() {
		return vitalValue.getTrackedVital() == null ? null : vitalValue
				.getTrackedVital().getId();
	}

	public void setPatientId(Long id) {
		if (vitalValue.getPatient() == null) {
			vitalValue.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		vitalValue.getPatient().setId(id);
	}

	public Long getPatientId() {
		return vitalValue.getPatient() == null ? null : vitalValue.getPatient()
				.getId();
	}

}
