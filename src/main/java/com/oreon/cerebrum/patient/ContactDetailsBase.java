package com.oreon.cerebrum.patient;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;

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
public class ContactDetailsBase {

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String primaryPhone

	;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String secondaryPhone

	;

	@Column(unique = false)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String email

	;

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getPrimaryPhone() {

		return primaryPhone;

	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public String getSecondaryPhone() {

		return secondaryPhone;

	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {

		return email;

	}

	@Transient
	public String getDisplayName() {
		try {
			return primaryPhone;
		} catch (Exception e) {
			return "Exception - " + e.getMessage();
		}
	}

}