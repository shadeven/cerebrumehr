package com.oreon.cerebrum.facility;

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
public class WardBase extends BaseEntity {
	private static final long serialVersionUID = -515597643L;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "facility_id", nullable = false, updatable = true)
	protected Facility facility

	;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	//@JoinColumn(name = "ward_ID", nullable = true)
	@OrderBy("id DESC")
	private Set<Room> rooms = new HashSet<Room>();

	public void addRoom(Room room) {

		room.setWard((Ward) this);

		this.rooms.add(room);
	}

	@Transient
	public List<com.oreon.cerebrum.facility.Room> getListRooms() {
		return new ArrayList<com.oreon.cerebrum.facility.Room>(rooms);
	}

	//JSF Friendly function to get count of collections
	public int getRoomsCount() {
		return rooms.size();
	}

	@NotNull
	@Length(min = 1, max = 250)
	@Column(unique = true)
	@Field(index = Index.YES)
	@Analyzer(definition = "entityAnalyzer")
	protected String name

	;

	@Column(unique = false)
	protected com.oreon.cerebrum.patient.Gender gender

	;

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public Facility getFacility() {

		return facility;

	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {

		return name;

	}

	public void setGender(com.oreon.cerebrum.patient.Gender gender) {
		this.gender = gender;
	}

	public com.oreon.cerebrum.patient.Gender getGender() {

		return gender;

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

		listSearchableFields.add("rooms.name");

		return listSearchableFields;
	}

	@Field(index = Index.YES, name = "searchData")
	@Analyzer(definition = "entityAnalyzer")
	public String getSearchData() {
		StringBuilder builder = new StringBuilder();

		builder.append(getName() + " ");

		if (getFacility() != null)
			builder.append("facility:" + getFacility().getDisplayName() + " ");

		for (BaseEntity e : rooms) {
			builder.append(e.getDisplayName() + " ");
		}

		return builder.toString();
	}

	/*
	<param name="facilityId" value="#{facilityId}" />
	
	 */

}