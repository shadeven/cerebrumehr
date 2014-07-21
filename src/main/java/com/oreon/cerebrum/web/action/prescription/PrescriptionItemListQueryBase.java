package com.oreon.cerebrum.web.action.prescription;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.prescription.PrescriptionItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PrescriptionItemListQueryBase
		extends
			BaseQuery<PrescriptionItem, Long> {

	private static final String EJBQL = "select prescriptionItem from PrescriptionItem prescriptionItem";

	protected PrescriptionItem prescriptionItem = new PrescriptionItem();

	public PrescriptionItemListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PrescriptionItem getPrescriptionItem() {
		return prescriptionItem;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public PrescriptionItem getInstance() {
		return getPrescriptionItem();
	}

	@Override
	//@Restrict("#{s:hasPermission('prescriptionItem', 'view')}")
	public List<PrescriptionItem> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PrescriptionItem> getEntityClass() {
		return PrescriptionItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> qtyRange = new Range<Double>();

	public Range<Double> getQtyRange() {
		return qtyRange;
	}
	public void setQty(Range<Double> qtyRange) {
		this.qtyRange = qtyRange;
	}

	private Range<Integer> durationRange = new Range<Integer>();

	public Range<Integer> getDurationRange() {
		return durationRange;
	}
	public void setDuration(Range<Integer> durationRange) {
		this.durationRange = durationRange;
	}

	private static final String[] RESTRICTIONS = {
			"prescriptionItem.id = #{prescriptionItemList.prescriptionItem.id}",

			"prescriptionItem.archived = #{prescriptionItemList.prescriptionItem.archived}",

			"prescriptionItem.drug.id = #{prescriptionItemList.prescriptionItem.drug.id}",

			"prescriptionItem.qty >= #{prescriptionItemList.qtyRange.begin}",
			"prescriptionItem.qty <= #{prescriptionItemList.qtyRange.end}",

			"lower(prescriptionItem.strength) like concat(lower(#{prescriptionItemList.prescriptionItem.strength}),'%')",

			"prescriptionItem.prescription.id = #{prescriptionItemList.prescriptionItem.prescription.id}",

			"prescriptionItem.route = #{prescriptionItemList.prescriptionItem.route}",

			"prescriptionItem.duration >= #{prescriptionItemList.durationRange.begin}",
			"prescriptionItem.duration <= #{prescriptionItemList.durationRange.end}",

			"prescriptionItem.frequency.id = #{prescriptionItemList.prescriptionItem.frequency.id}",

			"lower(prescriptionItem.remarks) like concat(lower(#{prescriptionItemList.prescriptionItem.remarks}),'%')",

			"lower(prescriptionItem.brandName) like concat(lower(#{prescriptionItemList.prescriptionItem.brandName}),'%')",

			"prescriptionItem.dateCreated <= #{prescriptionItemList.dateCreatedRange.end}",
			"prescriptionItem.dateCreated >= #{prescriptionItemList.dateCreatedRange.begin}",};

	/** 
	 * List of all PrescriptionItems for the given Prescription
	 * @param patient
	 * @return 
	 */
	public List<PrescriptionItem> getAllPrescriptionItemsByPrescription(
			final com.oreon.cerebrum.prescription.Prescription prescription) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		prescriptionItem.setPrescription(prescription);
		return getResultListTable();
	}

	public LazyDataModel<PrescriptionItem> getPrescriptionItemsByPrescription(
			final com.oreon.cerebrum.prescription.Prescription prescription) {

		EntityLazyDataModel<PrescriptionItem, Long> prescriptionItemLazyDataModel = new EntityLazyDataModel<PrescriptionItem, Long>(
				this) {

			@Override
			public List<PrescriptionItem> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				prescriptionItem.setPrescription(prescription);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return prescriptionItemLazyDataModel;

	}

	@Observer("archivedPrescriptionItem")
	public void onArchive() {
		refresh();
	}

	public void setDrugId(Long id) {
		if (prescriptionItem.getDrug() == null) {
			prescriptionItem.setDrug(new com.oreon.cerebrum.drugs.Drug());
		}
		prescriptionItem.getDrug().setId(id);
	}

	public Long getDrugId() {
		return prescriptionItem.getDrug() == null ? null : prescriptionItem
				.getDrug().getId();
	}

	public void setPrescriptionId(Long id) {
		if (prescriptionItem.getPrescription() == null) {
			prescriptionItem
					.setPrescription(new com.oreon.cerebrum.prescription.Prescription());
		}
		prescriptionItem.getPrescription().setId(id);
	}

	public Long getPrescriptionId() {
		return prescriptionItem.getPrescription() == null
				? null
				: prescriptionItem.getPrescription().getId();
	}

	public void setFrequencyId(Long id) {
		if (prescriptionItem.getFrequency() == null) {
			prescriptionItem
					.setFrequency(new com.oreon.cerebrum.prescription.Frequency());
		}
		prescriptionItem.getFrequency().setId(id);
	}

	public Long getFrequencyId() {
		return prescriptionItem.getFrequency() == null
				? null
				: prescriptionItem.getFrequency().getId();
	}

}
