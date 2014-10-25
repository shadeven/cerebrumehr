package com.oreon.cerebrum.web.action.employee;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.employee.Employee;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class EmployeeListQueryBase<T extends Employee>
		extends
			BaseQuery<T, Long> {

	private static final String EJBQL = "select employee from Employee employee";

	protected Employee employee = new com.oreon.cerebrum.employee.Physician();

	public EmployeeListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Employee getEmployee() {
		return employee;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public T getInstance() {
		return (T) getEmployee();
	}

	@Override
	//@Restrict("#{s:hasPermission('employee', 'view')}")
	public List<T> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<T> getEntityClass() {
		return (Class<T>) Employee.class;
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
			"employee.id = #{employeeList.employee.id}",

			"employee.archived = #{employeeList.employee.archived}",

			"lower(employee.firstName) like concat(lower(#{employeeList.employee.firstName}),'%')",

			"lower(employee.lastName) like concat(lower(#{employeeList.employee.lastName}),'%')",

			"employee.dateOfBirth >= #{employeeList.dateOfBirthRange.begin}",
			"employee.dateOfBirth <= #{employeeList.dateOfBirthRange.end}",

			"employee.gender = #{employeeList.employee.gender}",

			"lower(employee.contactDetails.primaryPhone) like concat(lower(#{employeeList.employee.contactDetails.primaryPhone}),'%')",

			"lower(employee.contactDetails.secondaryPhone) like concat(lower(#{employeeList.employee.contactDetails.secondaryPhone}),'%')",

			"lower(employee.contactDetails.email) like concat(lower(#{employeeList.employee.contactDetails.email}),'%')",

			"employee.title = #{employeeList.employee.title}",

			"lower(employee.appUser.userName) like concat(lower(#{employeeList.employee.appUser.userName}),'%')",

			"employee.appUser.enabled = #{employeeList.employee.appUser.enabled}",

			"employee.facility.id = #{employeeList.employee.facility.id}",

			"employee.department.id = #{employeeList.employee.department.id}",

			"employee.dateCreated <= #{employeeList.dateCreatedRange.end}",
			"employee.dateCreated >= #{employeeList.dateCreatedRange.begin}",};

	/** 
	 * List of all Employees for the given Department
	 * @param patient
	 * @return 
	 */
	public List<T> getAllEmployeesByDepartment(
			final com.oreon.cerebrum.employee.Department department) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		employee.setDepartment(department);
		return getResultListTable();
	}

	public LazyDataModel<T> getEmployeesByDepartment(
			final com.oreon.cerebrum.employee.Department department) {

		EntityLazyDataModel<T, Long> employeeLazyDataModel = new EntityLazyDataModel<T, Long>(
				this) {

			@Override
			public List<T> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				employee.setDepartment(department);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return employeeLazyDataModel;

	}

	@Observer("archivedEmployee")
	public void onArchive() {
		refresh();
	}

	public void setAppUserId(Long id) {
		if (employee.getAppUser() == null) {
			employee.setAppUser(new com.oreon.cerebrum.users.AppUser());
		}
		employee.getAppUser().setId(id);
	}

	public Long getAppUserId() {
		return employee.getAppUser() == null ? null : employee.getAppUser()
				.getId();
	}

	public void setFacilityId(Long id) {
		if (employee.getFacility() == null) {
			employee.setFacility(new com.oreon.cerebrum.facility.Facility());
		}
		employee.getFacility().setId(id);
	}

	public Long getFacilityId() {
		return employee.getFacility() == null ? null : employee.getFacility()
				.getId();
	}

	public void setDepartmentId(Long id) {
		if (employee.getDepartment() == null) {
			employee
					.setDepartment(new com.oreon.cerebrum.employee.Department());
		}
		employee.getDepartment().setId(id);
	}

	public Long getDepartmentId() {
		return employee.getDepartment() == null ? null : employee
				.getDepartment().getId();
	}

}
