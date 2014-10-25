package com.oreon.cerebrum.web.action.employee;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import com.oreon.cerebrum.employee.Employee;

//
public abstract class EmployeeActionBase
		extends
			com.oreon.cerebrum.web.action.patient.PersonAction<Employee>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long employeeId;

	@In(create = true, value = "appUserAction")
	com.oreon.cerebrum.web.action.users.AppUserAction appUserAction;

	@In(create = true, value = "facilityAction")
	com.oreon.cerebrum.web.action.facility.FacilityAction facilityAction;

	@In(create = true, value = "departmentAction")
	com.oreon.cerebrum.web.action.employee.DepartmentAction departmentAction;

	public void setEmployeeId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setEmployeeIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	@Override
	public Class<Employee> getEntityClass() {
		return Employee.class;
	}

	public void setAppUserId(Long id) {

		if (id != null && id > 0)
			getInstance().setAppUser(appUserAction.loadFromId(id));

	}

	public Long getAppUserId() {
		if (getInstance().getAppUser() != null)
			return getInstance().getAppUser().getId();
		return 0L;
	}

	public void setFacilityId(Long id) {

		if (id != null && id > 0)
			getInstance().setFacility(facilityAction.loadFromId(id));

	}

	public Long getFacilityId() {
		if (getInstance().getFacility() != null)
			return getInstance().getFacility().getId();
		return 0L;
	}

	public void setDepartmentId(Long id) {

		if (id != null && id > 0)
			getInstance().setDepartment(departmentAction.loadFromId(id));

	}

	public Long getDepartmentId() {
		if (getInstance().getDepartment() != null)
			return getInstance().getDepartment().getId();
		return 0L;
	}

	public Long getEmployeeId() {
		return (Long) getId();
	}

	public Employee getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Employee t) {
		this.instance = t;
		loadAssociations();
	}

	public Employee getEmployee() {
		return (Employee) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('employee', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('employee', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

}
