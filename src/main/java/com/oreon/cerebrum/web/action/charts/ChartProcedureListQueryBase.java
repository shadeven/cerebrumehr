package com.oreon.cerebrum.web.action.charts;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.charts.ChartProcedure;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ChartProcedureListQueryBase
		extends
			BaseQuery<ChartProcedure, Long> {

	private static final String EJBQL = "select chartProcedure from ChartProcedure chartProcedure";

	protected ChartProcedure chartProcedure = new ChartProcedure();

	public ChartProcedureListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public ChartProcedure getChartProcedure() {
		return chartProcedure;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public ChartProcedure getInstance() {
		return getChartProcedure();
	}

	@Override
	//@Restrict("#{s:hasPermission('chartProcedure', 'view')}")
	public List<ChartProcedure> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<ChartProcedure> getEntityClass() {
		return ChartProcedure.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dueDateRange = new Range<Date>();

	public Range<Date> getDueDateRange() {
		return dueDateRange;
	}
	public void setDueDate(Range<Date> dueDateRange) {
		this.dueDateRange = dueDateRange;
	}

	private Range<Date> datePerformedRange = new Range<Date>();

	public Range<Date> getDatePerformedRange() {
		return datePerformedRange;
	}
	public void setDatePerformed(Range<Date> datePerformedRange) {
		this.datePerformedRange = datePerformedRange;
	}

	private static final String[] RESTRICTIONS = {
			"chartProcedure.id = #{chartProcedureList.chartProcedure.id}",

			"chartProcedure.archived = #{chartProcedureList.chartProcedure.archived}",

			"chartProcedure.patient.id = #{chartProcedureList.chartProcedure.patient.id}",

			"chartProcedure.chartItem.id = #{chartProcedureList.chartProcedure.chartItem.id}",

			"chartProcedure.dueDate >= #{chartProcedureList.dueDateRange.begin}",
			"chartProcedure.dueDate <= #{chartProcedureList.dueDateRange.end}",

			"chartProcedure.datePerformed >= #{chartProcedureList.datePerformedRange.begin}",
			"chartProcedure.datePerformed <= #{chartProcedureList.datePerformedRange.end}",

			"lower(chartProcedure.remarks) like concat(lower(#{chartProcedureList.chartProcedure.remarks}),'%')",

			"chartProcedure.dateCreated <= #{chartProcedureList.dateCreatedRange.end}",
			"chartProcedure.dateCreated >= #{chartProcedureList.dateCreatedRange.begin}",};

	/** 
	 * List of all ChartProcedures for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<ChartProcedure> getAllChartProceduresByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		chartProcedure.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<ChartProcedure> getChartProceduresByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<ChartProcedure, Long> chartProcedureLazyDataModel = new EntityLazyDataModel<ChartProcedure, Long>(
				this) {

			@Override
			public List<ChartProcedure> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				chartProcedure.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return chartProcedureLazyDataModel;

	}

	@Observer("archivedChartProcedure")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (chartProcedure.getPatient() == null) {
			chartProcedure.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		chartProcedure.getPatient().setId(id);
	}

	public Long getPatientId() {
		return chartProcedure.getPatient() == null ? null : chartProcedure
				.getPatient().getId();
	}

	public void setChartItemId(Long id) {
		if (chartProcedure.getChartItem() == null) {
			chartProcedure
					.setChartItem(new com.oreon.cerebrum.charts.ChartItem());
		}
		chartProcedure.getChartItem().setId(id);
	}

	public Long getChartItemId() {
		return chartProcedure.getChartItem() == null ? null : chartProcedure
				.getChartItem().getId();
	}

}
