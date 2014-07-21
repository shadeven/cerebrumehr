package com.oreon.cerebrum.ddx;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
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
public class ConditionFindingBase extends BaseEntity {
	private static final long serialVersionUID = 76310456L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "disease_id", nullable = false, updatable = true)
	protected Disease disease

	;

	@Column(unique = false)
	protected Boolean falsePositive

	;

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Disease getDisease() {

		return disease;

	}

	public void setFalsePositive(Boolean falsePositive) {
		this.falsePositive = falsePositive;
	}

	public Boolean getFalsePositive() {

		return falsePositive;

	}

	@Transient
	public String getDisplayName() {
		try {
			return disease + "";
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

		if (getDisease() != null)
			builder.append("disease:" + getDisease().getDisplayName() + " ");

		return builder.toString();
	}

	/*
	<param name="diseaseId" value="#{diseaseId}" />
	
	 */

}