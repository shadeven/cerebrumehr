package com.oreon.cerebrum.settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Date;

import com.google.common.base.Objects;

import javax.persistence.*;
import org.hibernate.validator.*;

import com.google.common.base.Objects;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Cascade;

import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.IndexedEmbedded;

import org.hibernate.annotations.Filter;

import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.jboss.seam.annotations.Name;

import org.witchcraft.model.support.audit.Auditable;

import org.witchcraft.utils.*;

import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.ProjectUtils;

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
public class SettingGroupBase extends BaseEntity {
	private static final long serialVersionUID = 1752499712L;

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "settingGroup_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<SettingName> settingNames = new HashSet<SettingName>();

	public void addSettingName(SettingName settingName) {

		settingName.setSettingGroup((SettingGroup) this);

		this.settingNames.add(settingName);
	}

	@Transient
	public List<com.oreon.cerebrum.settings.SettingName> getListSettingNames() {
		return new ArrayList<com.oreon.cerebrum.settings.SettingName>(
				settingNames);
	}

	@Transient
	public String getListSettingNamesAsString() {
		StringBuilder result = new StringBuilder();

		List<com.oreon.cerebrum.settings.SettingName> tempList = getListSettingNames();
		int count = 0;
		for (com.oreon.cerebrum.settings.SettingName settingName : tempList) {
			++count;
			result.append(settingName.getDisplayName());
			if (count < tempList.size())
				result.append(", ");
		}

		return result.toString();
	}

	//JSF Friendly function to get count of collections
	public int getSettingNamesCount() {
		return settingNames.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setSettingNames(Set<SettingName> settingNames) {
		this.settingNames = settingNames;
	}

	public Set<SettingName> getSettingNames() {
		return settingNames;
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

		listSearchableFields.add("settingNames.name");

		listSearchableFields.add("settingNames.defaultValue");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		for (BaseEntity e : settingNames) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

	/*
	
	 */

}
