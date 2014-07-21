package com.oreon.cerebrum.web.action.admission;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.admission.BedStay;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class BedStayListQueryBase extends BaseQuery<BedStay, Long> {

	private static final String EJBQL = "select bedStay from BedStay bedStay";

	protected BedStay bedStay = new BedStay();

	public BedStayListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public BedStay getBedStay() {
		return bedStay;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public BedStay getInstance() {
		return getBedStay();
	}

	@Override
	//@Restrict("#{s:hasPermission('bedStay', 'view')}")
	public List<BedStay> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<BedStay> getEntityClass() {
		return BedStay.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> fromDateRange = new Range<Date>();

	public Range<Date> getFromDateRange() {
		return fromDateRange;
	}
	public void setFromDate(Range<Date> fromDateRange) {
		this.fromDateRange = fromDateRange;
	}

	private Range<Date> toDateRange = new Range<Date>();

	public Range<Date> getToDateRange() {
		return toDateRange;
	}
	public void setToDate(Range<Date> toDateRange) {
		this.toDateRange = toDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"bedStay.id = #{bedStayList.bedStay.id}",

			"bedStay.archived = #{bedStayList.bedStay.archived}",

			"bedStay.fromDate >= #{bedStayList.fromDateRange.begin}",
			"bedStay.fromDate <= #{bedStayList.fromDateRange.end}",

			"bedStay.toDate >= #{bedStayList.toDateRange.begin}",
			"bedStay.toDate <= #{bedStayList.toDateRange.end}",

			"bedStay.admission.id = #{bedStayList.bedStay.admission.id}",

			"bedStay.bed.id = #{bedStayList.bedStay.bed.id}",

			"bedStay.dateCreated <= #{bedStayList.dateCreatedRange.end}",
			"bedStay.dateCreated >= #{bedStayList.dateCreatedRange.begin}",};

	/** 
	 * List of all BedStays for the given Admission
	 * @param patient
	 * @return 
	 */
	public List<BedStay> getAllBedStaysByAdmission(
			final com.oreon.cerebrum.admission.Admission admission) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		bedStay.setAdmission(admission);
		return getResultListTable();
	}

	public LazyDataModel<BedStay> getBedStaysByAdmission(
			final com.oreon.cerebrum.admission.Admission admission) {

		EntityLazyDataModel<BedStay, Long> bedStayLazyDataModel = new EntityLazyDataModel<BedStay, Long>(
				this) {

			@Override
			public List<BedStay> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				bedStay.setAdmission(admission);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return bedStayLazyDataModel;

	}

	@Observer("archivedBedStay")
	public void onArchive() {
		refresh();
	}

	public void setAdmissionId(Long id) {
		if (bedStay.getAdmission() == null) {
			bedStay.setAdmission(new com.oreon.cerebrum.admission.Admission());
		}
		bedStay.getAdmission().setId(id);
	}

	public Long getAdmissionId() {
		return bedStay.getAdmission() == null ? null : bedStay.getAdmission()
				.getId();
	}

	public void setBedId(Long id) {
		if (bedStay.getBed() == null) {
			bedStay.setBed(new com.oreon.cerebrum.facility.Bed());
		}
		bedStay.getBed().setId(id);
	}

	public Long getBedId() {
		return bedStay.getBed() == null ? null : bedStay.getBed().getId();
	}

}
