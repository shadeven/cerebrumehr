package org.witchcraft.seam.action;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.security.Identity;

import com.oreon.cerebrum.employee.Employee;
import com.oreon.cerebrum.facility.Facility;
import com.oreon.cerebrum.patient.Patient;
import com.oreon.cerebrum.users.AppUser;
import com.oreon.cerebrum.web.action.employee.EmployeeAction;

@Name("userUtilAction")
@Scope(ScopeType.SESSION)
public class UserUtilAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3320546173691963806L;

	private AppUser currentUser;

	private Facility currentFacility;
	
	private Patient currentPatient;

	

	@In
	EntityManager entityManager;
	
	@In(create=true)
	EmployeeAction employeeAction;
	
	private Employee currentLoggedInEmployee;
	
	public static final String 	 CURRENT_EMP_QRY  = "Select e from Employee e where e.appUser.userName = ?1";
	
	
	public Employee getCurrentLoggedInEmployee() {
		return currentLoggedInEmployee;
	}
	
	public Employee fetchCurrentEmployee(){
		return employeeAction.executeSingleResultQuery(CURRENT_EMP_QRY, Identity.instance() .getCredentials().getUsername());
	}
	
	

	public AppUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(AppUser currentUser) {
		this.currentUser = entityManager.merge(currentUser);
		
		if(currentUser.getTenant() != null)
			currentFacility =  entityManager.find(Facility.class, currentUser.getTenant());
		
		currentLoggedInEmployee = fetchCurrentEmployee();
	}

	public Long getCurrentTenantId() {
		if(currentFacility != null)
			return currentFacility.getTenant();
		
		Long result = currentUser == null || currentUser.getTenant() == null ? 0
				: currentUser.getTenant();
		return result;
	}

	public void setCurrentFacility(Facility currentFacility) {
		this.currentFacility = currentFacility;
	}

	public Facility getCurrentFacility() {
		return currentFacility;
	}
	
	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	
	public void clearCurrentPatient() {
		this.currentPatient = null;
	}
	
}
