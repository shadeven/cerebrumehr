package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Employee;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

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
