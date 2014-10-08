package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Clerk;

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

import com.oreon.cerebrum.employee.Clerk;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ClerkListQueryBase extends

EmployeeListQueryBase<Clerk> {

	private static final String EJBQL = "select clerk from Clerk clerk";

	protected Clerk clerk = new Clerk();

	public ClerkListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Clerk getClerk() {
		return clerk;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Clerk getInstance() {
		return getClerk();
	}

	@Override
	//@Restrict("#{s:hasPermission('clerk', 'view')}")
	public List<Clerk> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Clerk> getEntityClass() {
		return Clerk.class;
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
			"clerk.id = #{clerkList.clerk.id}",

			"clerk.archived = #{clerkList.clerk.archived}",

			"lower(clerk.appUser.userName) like concat(lower(#{clerkList.clerk.appUser.userName}),'%')",

			"clerk.appUser.enabled = #{clerkList.clerk.appUser.enabled}",

			"clerk.facility.id = #{clerkList.clerk.facility.id}",

			"clerk.department.id = #{clerkList.clerk.department.id}",

			"lower(clerk.firstName) like concat(lower(#{clerkList.clerk.firstName}),'%')",

			"lower(clerk.lastName) like concat(lower(#{clerkList.clerk.lastName}),'%')",

			"clerk.dateOfBirth >= #{clerkList.dateOfBirthRange.begin}",
			"clerk.dateOfBirth <= #{clerkList.dateOfBirthRange.end}",

			"clerk.gender = #{clerkList.clerk.gender}",

			"lower(clerk.contactDetails.primaryPhone) like concat(lower(#{clerkList.clerk.contactDetails.primaryPhone}),'%')",

			"lower(clerk.contactDetails.secondaryPhone) like concat(lower(#{clerkList.clerk.contactDetails.secondaryPhone}),'%')",

			"lower(clerk.contactDetails.email) like concat(lower(#{clerkList.clerk.contactDetails.email}),'%')",

			"clerk.title = #{clerkList.clerk.title}",

			"clerk.dateCreated <= #{clerkList.dateCreatedRange.end}",
			"clerk.dateCreated >= #{clerkList.dateCreatedRange.begin}",};

	@Observer("archivedClerk")
	public void onArchive() {
		refresh();
	}

}
