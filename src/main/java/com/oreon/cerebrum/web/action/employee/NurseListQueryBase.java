package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Nurse;

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

import com.oreon.cerebrum.employee.Nurse;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class NurseListQueryBase extends

EmployeeListQueryBase<Nurse> {

	private static final String EJBQL = "select nurse from Nurse nurse";

	protected Nurse nurse = new Nurse();

	public NurseListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Nurse getNurse() {
		return nurse;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Nurse getInstance() {
		return getNurse();
	}

	@Override
	//@Restrict("#{s:hasPermission('nurse', 'view')}")
	public List<Nurse> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Nurse> getEntityClass() {
		return Nurse.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dateOfBirthRange = new Range<Date>();

	public Range<Date> getDateOfBirthRange() {
		return dateOfBirthRange;
	}
	public void setDateOfBirth(Range<Date> dateOfBirthRange) {
		this.dateOfBirthRange = dateOfBirthRange;
	}

	private Range<Integer> ageRange = new Range<Integer>();

	public Range<Integer> getAgeRange() {
		return ageRange;
	}
	public void setAge(Range<Integer> ageRange) {
		this.ageRange = ageRange;
	}

	private static final String[] RESTRICTIONS = {
			"nurse.id = #{nurseList.nurse.id}",

			"nurse.archived = #{nurseList.nurse.archived}",

			"lower(nurse.appUser.userName) like concat(lower(#{nurseList.nurse.appUser.userName}),'%')",

			"nurse.appUser.enabled = #{nurseList.nurse.appUser.enabled}",

			"nurse.facility.id = #{nurseList.nurse.facility.id}",

			"nurse.department.id = #{nurseList.nurse.department.id}",

			"lower(nurse.firstName) like concat(lower(#{nurseList.nurse.firstName}),'%')",

			"lower(nurse.lastName) like concat(lower(#{nurseList.nurse.lastName}),'%')",

			"nurse.dateOfBirth >= #{nurseList.dateOfBirthRange.begin}",
			"nurse.dateOfBirth <= #{nurseList.dateOfBirthRange.end}",

			"nurse.gender = #{nurseList.nurse.gender}",

			"lower(nurse.contactDetails.primaryPhone) like concat(lower(#{nurseList.nurse.contactDetails.primaryPhone}),'%')",

			"lower(nurse.contactDetails.secondaryPhone) like concat(lower(#{nurseList.nurse.contactDetails.secondaryPhone}),'%')",

			"lower(nurse.contactDetails.email) like concat(lower(#{nurseList.nurse.contactDetails.email}),'%')",

			"nurse.age >= #{nurseList.ageRange.begin}",
			"nurse.age <= #{nurseList.ageRange.end}",

			"nurse.title = #{nurseList.nurse.title}",

			"nurse.nurseSpecialty.id = #{nurseList.nurse.nurseSpecialty.id}",

			"nurse.dateCreated <= #{nurseList.dateCreatedRange.end}",
			"nurse.dateCreated >= #{nurseList.dateCreatedRange.begin}",};

	@Observer("archivedNurse")
	public void onArchive() {
		refresh();
	}

	public void setNurseSpecialtyId(Long id) {
		if (nurse.getNurseSpecialty() == null) {
			nurse
					.setNurseSpecialty(new com.oreon.cerebrum.employee.NurseSpecialty());
		}
		nurse.getNurseSpecialty().setId(id);
	}

	public Long getNurseSpecialtyId() {
		return nurse.getNurseSpecialty() == null ? null : nurse
				.getNurseSpecialty().getId();
	}

}
