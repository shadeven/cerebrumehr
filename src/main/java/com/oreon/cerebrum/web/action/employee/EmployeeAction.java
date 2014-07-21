package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.employee.Employee;

//@Scope(ScopeType.CONVERSATION)
@Name("employeeAction")
public class EmployeeAction extends EmployeeActionBase
		implements
			java.io.Serializable {
	
	
	public Employee findEmployeeByUserName(String userName){
		String qry = " select e from Employee e where e.appUser.userName = ? ";
		Employee emp = executeSingleResultQuery(qry, userName);
		return emp;
	}
	
	public String getEmployeeName(String userName){
		Employee emp = findEmployeeByUserName(userName);
		return emp.getFirstName() + " " + emp.getLastName();
	}

}
