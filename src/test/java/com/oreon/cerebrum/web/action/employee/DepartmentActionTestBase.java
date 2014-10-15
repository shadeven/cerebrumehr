package com.oreon.cerebrum.web.action.employee;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.employee.Department;

public class DepartmentActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Department> {

	DepartmentAction departmentAction = new DepartmentAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Department> getAction() {
		return departmentAction;
	}

}
