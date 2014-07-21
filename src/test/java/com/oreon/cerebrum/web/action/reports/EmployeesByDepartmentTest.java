package com.oreon.cerebrum.web.action.reports;

import org.jboss.seam.Component;
import org.junit.Test;
import org.witchcraft.action.test.BaseReportsTest;
import org.witchcraft.jasperreports.BaseReportAction;

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
