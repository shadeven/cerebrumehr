package com.oreon.cerebrum.codes;

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
public class ChapterBase extends com.oreon.cerebrum.codes.AbstractCode {
	private static final long serialVersionUID = -485514617L;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "chapter_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<Section> sections = new HashSet<Section>();

	public void addSection(Section section) {

		section.setChapter((Chapter) this);

		this.sections.add(section);
	}

	@Transient
	public List<com.oreon.cerebrum.codes.Section> getListSections() {
		return new ArrayList<com.oreon.cerebrum.codes.Section>(sections);
	}

	@Transient
	public String getListSectionsAsString() {
		StringBuilder result = new StringBuilder();

		List<com.oreon.cerebrum.codes.Section> tempList = getListSections();
		int count = 0;
		for (com.oreon.cerebrum.codes.Section section : tempList) {
			++count;
			result.append(section.getDisplayName());
			if (count < tempList.size())
				result.append(", ");
		}

		return result.toString();
	}

	//JSF Friendly function to get count of collections
	public int getSectionsCount() {
		return sections.size();
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}

	public Set<Section> getSections() {
		return sections;
	}

	@Transient
	public String getDisplayName() {
		try {
			return super.getDisplayName();
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

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		for (BaseEntity e : sections) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

	/*
	
	 */

}
