package com.oreon.cerebrum.web.action.charts;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.charts.AppliedChart;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AppliedChartListQueryBase
		extends
			BaseQuery<AppliedChart, Long> {

	private static final String EJBQL = "select appliedChart from AppliedChart appliedChart";

	protected AppliedChart appliedChart = new AppliedChart();

	public AppliedChartListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public AppliedChart getAppliedChart() {
		return appliedChart;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public AppliedChart getInstance() {
		return getAppliedChart();
	}

	@Override
	//@Restrict("#{s:hasPermission('appliedChart', 'view')}")
	public List<AppliedChart> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<AppliedChart> getEntityClass() {
		return AppliedChart.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"appliedChart.id = #{appliedChartList.appliedChart.id}",

			"appliedChart.archived = #{appliedChartList.appliedChart.archived}",

			"appliedChart.patient.id = #{appliedChartList.appliedChart.patient.id}",

			"appliedChart.chart.id = #{appliedChartList.appliedChart.chart.id}",

			"appliedChart.dateCreated <= #{appliedChartList.dateCreatedRange.end}",
			"appliedChart.dateCreated >= #{appliedChartList.dateCreatedRange.begin}",};

	/** 
	 * List of all AppliedCharts for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<AppliedChart> getAllAppliedChartsByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		appliedChart.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<AppliedChart> getAppliedChartsByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<AppliedChart, Long> appliedChartLazyDataModel = new EntityLazyDataModel<AppliedChart, Long>(
				this) {

			@Override
			public List<AppliedChart> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				appliedChart.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return appliedChartLazyDataModel;

	}

	@Observer("archivedAppliedChart")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (appliedChart.getPatient() == null) {
			appliedChart.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		appliedChart.getPatient().setId(id);
	}

	public Long getPatientId() {
		return appliedChart.getPatient() == null ? null : appliedChart
				.getPatient().getId();
	}

	public void setChartId(Long id) {
		if (appliedChart.getChart() == null) {
			appliedChart.setChart(new com.oreon.cerebrum.charts.Chart());
		}
		appliedChart.getChart().setId(id);
	}

	public Long getChartId() {
		return appliedChart.getChart() == null ? null : appliedChart.getChart()
				.getId();
	}

}
