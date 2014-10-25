package com.oreon.cerebrum.web.action.employee;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.employee.Department;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DepartmentListQueryBase
		extends
			BaseQuery<Department, Long> {

	private static final String EJBQL = "select department from Department department";

	protected Department department = new Department();

	public DepartmentListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Department getDepartment() {
		return department;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Department getInstance() {
		return getDepartment();
	}

	@Override
	//@Restrict("#{s:hasPermission('department', 'view')}")
	public List<Department> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Department> getEntityClass() {
		return Department.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"department.id = #{departmentList.department.id}",

			"department.archived = #{departmentList.department.archived}",

			"lower(department.name) like concat(lower(#{departmentList.department.name}),'%')",

			"department.dateCreated <= #{departmentList.dateCreatedRange.end}",
			"department.dateCreated >= #{departmentList.dateCreatedRange.begin}",};

	@Observer("archivedDepartment")
	public void onArchive() {
		refresh();
	}

}
