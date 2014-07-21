package com.oreon.cerebrum.web.action.employee;

import java.util.Date;
import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.base.entity.Range;

import com.oreon.cerebrum.employee.Physician;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PhysicianListQueryBase extends

EmployeeListQueryBase<Physician> {

	private static final String EJBQL = "select physician from Physician physician";

	protected Physician physician = new Physician();

	public PhysicianListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Physician getPhysician() {
		return physician;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Physician getInstance() {
		return getPhysician();
	}

	@Override
	//@Restrict("#{s:hasPermission('physician', 'view')}")
	public List<Physician> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Physician> getEntityClass() {
		return Physician.class;
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

	private static final String[] RESTRICTIONS = {
			"physician.id = #{physicianList.physician.id}",

			"physician.archived = #{physicianList.physician.archived}",

			"lower(physician.appUser.userName) like concat(lower(#{physicianList.physician.appUser.userName}),'%')",

			"physician.appUser.enabled = #{physicianList.physician.appUser.enabled}",

			"physician.facility.id = #{physicianList.physician.facility.id}",

			"physician.department.id = #{physicianList.physician.department.id}",

			"lower(physician.firstName) like concat(lower(#{physicianList.physician.firstName}),'%')",

			"lower(physician.lastName) like concat(lower(#{physicianList.physician.lastName}),'%')",

			"physician.dateOfBirth >= #{physicianList.dateOfBirthRange.begin}",
			"physician.dateOfBirth <= #{physicianList.dateOfBirthRange.end}",

			"physician.gender = #{physicianList.physician.gender}",

			"lower(physician.contactDetails.primaryPhone) like concat(lower(#{physicianList.physician.contactDetails.primaryPhone}),'%')",

			"lower(physician.contactDetails.secondaryPhone) like concat(lower(#{physicianList.physician.contactDetails.secondaryPhone}),'%')",

			"lower(physician.contactDetails.email) like concat(lower(#{physicianList.physician.contactDetails.email}),'%')",

			"physician.title = #{physicianList.physician.title}",

			"physician.specialization.id = #{physicianList.physician.specialization.id}",

			"physician.dateCreated <= #{physicianList.dateCreatedRange.end}",
			"physician.dateCreated >= #{physicianList.dateCreatedRange.begin}",};

	@Observer("archivedPhysician")
	public void onArchive() {
		refresh();
	}

	public void setSpecializationId(Long id) {
		if (physician.getSpecialization() == null) {
			physician
					.setSpecialization(new com.oreon.cerebrum.employee.Specialization());
		}
		physician.getSpecialization().setId(id);
	}

	public Long getSpecializationId() {
		return physician.getSpecialization() == null ? null : physician
				.getSpecialization().getId();
	}

}
