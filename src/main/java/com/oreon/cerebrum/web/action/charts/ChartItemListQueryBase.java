package com.oreon.cerebrum.web.action.charts;

import com.oreon.cerebrum.charts.ChartItem;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.cerebrum.charts.ChartItem;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ChartItemListQueryBase extends BaseQuery<ChartItem, Long> {

	private static final String EJBQL = "select chartItem from ChartItem chartItem";

	protected ChartItem chartItem = new ChartItem();

	public ChartItemListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public ChartItem getChartItem() {
		return chartItem;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public ChartItem getInstance() {
		return getChartItem();
	}

	@Override
	//@Restrict("#{s:hasPermission('chartItem', 'view')}")
	public List<ChartItem> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<ChartItem> getEntityClass() {
		return ChartItem.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> durationRange = new Range<Integer>();

	public Range<Integer> getDurationRange() {
		return durationRange;
	}
	public void setDuration(Range<Integer> durationRange) {
		this.durationRange = durationRange;
	}

	private static final String[] RESTRICTIONS = {
			"chartItem.id = #{chartItemList.chartItem.id}",

			"chartItem.archived = #{chartItemList.chartItem.archived}",

			"lower(chartItem.name) like concat(lower(#{chartItemList.chartItem.name}),'%')",

			"chartItem.duration >= #{chartItemList.durationRange.begin}",
			"chartItem.duration <= #{chartItemList.durationRange.end}",

			"chartItem.chart.id = #{chartItemList.chartItem.chart.id}",

			"chartItem.frequencyPeriod = #{chartItemList.chartItem.frequencyPeriod}",

			"chartItem.dateCreated <= #{chartItemList.dateCreatedRange.end}",
			"chartItem.dateCreated >= #{chartItemList.dateCreatedRange.begin}",};

	/** 
	 * List of all ChartItems for the given Chart
	 * @param patient
	 * @return 
	 */
	public List<ChartItem> getAllChartItemsByChart(
			final com.oreon.cerebrum.charts.Chart chart) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		chartItem.setChart(chart);
		return getResultListTable();
	}

	public LazyDataModel<ChartItem> getChartItemsByChart(
			final com.oreon.cerebrum.charts.Chart chart) {

		EntityLazyDataModel<ChartItem, Long> chartItemLazyDataModel = new EntityLazyDataModel<ChartItem, Long>(
				this) {

			@Override
			public List<ChartItem> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				chartItem.setChart(chart);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return chartItemLazyDataModel;

	}

	@Observer("archivedChartItem")
	public void onArchive() {
		refresh();
	}

	public void setChartId(Long id) {
		if (chartItem.getChart() == null) {
			chartItem.setChart(new com.oreon.cerebrum.charts.Chart());
		}
		chartItem.getChart().setId(id);
	}

	public Long getChartId() {
		return chartItem.getChart() == null ? null : chartItem.getChart()
				.getId();
	}

}
