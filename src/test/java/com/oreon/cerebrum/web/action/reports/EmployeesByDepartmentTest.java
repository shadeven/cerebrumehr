package com.oreon.cerebrum.web.action.reports;

import org.testng.annotations.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

import org.jboss.seam.Component;

import com.oreon.cerebrum.web.action.reports.EmployeesByDepartmentAction;

public class EmployeesByDepartmentTest extends BaseReportsTest {

	@Test
	public void testEmployeesByDepartmentReport() {
		try {
			runReportTest("EmployeesByDepartment");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	//@Test
	public void testEmployeesByDepartmentReportAction() throws Exception {
		new ComponentTest() {
			protected void testComponents() throws Exception {
				EmployeesByDepartmentAction employeesByDepartmentAction = (EmployeesByDepartmentAction) Component
						.getInstance("employeesByDepartmentAction");
				employeesByDepartmentAction.runReport("EmployeesByDepartment",
						BaseReportAction.REPORT_TYPE.LOCAL.name());
			}
		}.run();
	}

}
