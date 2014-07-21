package com.oreon.cerebrum.charts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.validator.constraints.Length;
import org.witchcraft.base.entity.BaseEntity;

//Impl 

/**
 * This file is generated by Witchcraftmda.
 * DO NOT MODIFY any changes will be overwritten with the next run of the code generator.
 *
 */

/**
 * 
 *
 */

@MappedSuperclass
public class ChartBase extends BaseEntity {
	private static final long serialVersionUID = 604771653L;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "chart_ID", nullable = false)
	@OrderBy("id DESC")
	private Set<ChartItem> chartItems = new HashSet<ChartItem>();

	public void addChartItem(ChartItem chartItem) {

		chartItem.setChart((Chart) this);

		this.chartItems.add(chartItem);
	}

	@Transient
	public List<com.oreon.cerebrum.charts.ChartItem> getListChartItems() {
		return new ArrayList<com.oreon.cerebrum.charts.ChartItem>(chartItems);
	}

	//JSF Friendly function to get count of collections
	public int getChartItemsCount() {
		return chartItems.size();
	}

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "chronicCondition_id", nullable = false, updatable = true)
	protected com.oreon.cerebrum.ddx.ChronicCondition chronicCondition

	;

	public void setChartItems(Set<ChartItem> chartItems) {
		this.chartItems = chartItems;
	}

	public Set<ChartItem> getChartItems() {
		return chartItems;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setChronicCondition(
			com.oreon.cerebrum.ddx.ChronicCondition chronicCondition) {
		this.chronicCondition = chronicCondition;
	}

	public com.oreon.cerebrum.ddx.ChronicCondition getChronicCondition() {

		return chronicCondition;

	}

	@Transient
	public String getDisplayName() {
		try {
			return name;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

	//Empty setter , needed for richfaces autocomplete to work 
	public void setDisplayName(String name) {
	}

	/** This method is used by hibernate full text search - override to add additional fields
	 * @see org.witchcraft.model.support.BaseEntity#retrieveSearchableFieldsArray()
	 */
	@Override
	public List<String> listSearchableFields() {
		List<String> listSearchableFields = new ArrayList<String>();
		listSearchableFields.addAll(super.listSearchableFields());

		listSearchableFields.add("name");

		listSearchableFields.add("chartItems.name");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		if (getChronicCondition() != null)
			builder.append("chronicCondition:"
					+ getChronicCondition().getDisplayName() + " ");

		for (BaseEntity e : chartItems) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

	/*
	<param name="chronicConditionId" value="#{chronicConditionId}" />
	
	 */

}