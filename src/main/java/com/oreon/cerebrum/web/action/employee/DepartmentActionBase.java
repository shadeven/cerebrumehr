package com.oreon.cerebrum.web.action.employee;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.employee.Department;
import com.oreon.cerebrum.employee.Employee;

//
public abstract class DepartmentActionBase extends BaseAction<Department>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long departmentId;

	public void setDepartmentId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setDepartmentIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getDepartmentId() {
		return (Long) getId();
	}

	public Department getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Department t) {
		this.instance = t;
		loadAssociations();
	}

	public Department getDepartment() {
		return (Department) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('department', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('department', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Department createInstance() {
		Department instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

	}

	public Department getDefinedInstance() {
		return (Department) (isIdDefined() ? getInstance() : null);
	}

	public void setDepartment(Department t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setDepartmentId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Department> getEntityClass() {
		return Department.class;
	}

	public com.oreon.cerebrum.employee.Department findByUnqName(String name) {
		return executeSingleResultNamedQuery("department.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListEmployees();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.employee.Employee> listEmployees = new ArrayList<com.oreon.cerebrum.employee.Employee>();

	void initListEmployees() {

		if (listEmployees.isEmpty())
			listEmployees.addAll(getInstance().getEmployees());

	}

	public List<com.oreon.cerebrum.employee.Employee> getListEmployees() {

		prePopulateListEmployees();
		return listEmployees;
	}

	public void prePopulateListEmployees() {
	}

	public void setListEmployees(
			List<com.oreon.cerebrum.employee.Employee> listEmployees) {
		this.listEmployees = listEmployees;
	}

	public void deleteEmployees(int index) {
		listEmployees.remove(index);
	}

	@Begin(join = true)
	public void addEmployees() {

	}

	public void updateComposedAssociations() {

		if (listEmployees != null) {

			java.util.Set<Employee> items = getInstance().getEmployees();
			for (Employee item : items) {
				if (!listEmployees.contains(item))
					getEntityManager().remove(item);
			}

			for (Employee item : listEmployees) {
				item.setDepartment(getInstance());
			}

			getInstance().getEmployees().clear();
			getInstance().getEmployees().addAll(listEmployees);
		}
	}

	public void clearLists() {
		listEmployees.clear();

	}

	public String viewDepartment() {
		load(currentEntityId);
		return "viewDepartment";
	}

}
