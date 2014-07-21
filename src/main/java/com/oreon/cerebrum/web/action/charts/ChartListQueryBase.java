package com.oreon.cerebrum.web.action.charts;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.charts.Chart;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ChartListQueryBase extends BaseQuery<Chart, Long> {

	private static final String EJBQL = "select chart from Chart chart";

	protected Chart chart = new Chart();

	public ChartListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Chart getChart() {
		return chart;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Chart getInstance() {
		return getChart();
	}

	@Override
	//@Restrict("#{s:hasPermission('chart', 'view')}")
	public List<Chart> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Chart> getEntityClass() {
		return Chart.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"chart.id = #{chartList.chart.id}",

			"chart.archived = #{chartList.chart.archived}",

			"lower(chart.name) like concat(lower(#{chartList.chart.name}),'%')",

			"chart.chronicCondition.id = #{chartList.chart.chronicCondition.id}",

			"chart.dateCreated <= #{chartList.dateCreatedRange.end}",
			"chart.dateCreated >= #{chartList.dateCreatedRange.begin}",};

	/** 
	 * List of all Charts for the given ChronicCondition
	 * @param patient
	 * @return 
	 */
	public List<Chart> getAllChartsByChronicCondition(
			final com.oreon.cerebrum.ddx.ChronicCondition chronicCondition) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		chart.setChronicCondition(chronicCondition);
		return getResultListTable();
	}

	public LazyDataModel<Chart> getChartsByChronicCondition(
			final com.oreon.cerebrum.ddx.ChronicCondition chronicCondition) {

		EntityLazyDataModel<Chart, Long> chartLazyDataModel = new EntityLazyDataModel<Chart, Long>(
				this) {

			@Override
			public List<Chart> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				chart.setChronicCondition(chronicCondition);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return chartLazyDataModel;

	}

	@Observer("archivedChart")
	public void onArchive() {
		refresh();
	}

	public void setChronicConditionId(Long id) {
		if (chart.getChronicCondition() == null) {
			chart
					.setChronicCondition(new com.oreon.cerebrum.ddx.ChronicCondition());
		}
		chart.getChronicCondition().setId(id);
	}

	public Long getChronicConditionId() {
		return chart.getChronicCondition() == null ? null : chart
				.getChronicCondition().getId();
	}

}
