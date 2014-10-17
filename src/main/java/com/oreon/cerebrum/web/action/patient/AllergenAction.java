
	
package com.oreon.cerebrum.web.action.patient;
	

import java.util.List;

import javax.persistence.Query;

import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.patient.Patient;

	
//@Scope(ScoPatientype.CONVERSATION)
@Name("allergenAction")
public class AllergenAction extends AllergenActionBase implements java.io.Serializable{
	
	
	public List<Patient> getPatientsByGenderAge(){
		/*
		Session session = (Session) getEntityManager().getDelegate();
		
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		
		CriteriaQuery<Patient> cq = cb.createQuery(Patient.class);
		Root<Patient> ptroot = cq.from(Patient.class);
		Expression minExpression = cb.count(ptroot);
		cq.groupBy(ptroot.get("dateOfBirth.year"));
		
		TypedQuery<Object> typedQuery = entityManager.createQuery(cq);
		List listActual = typedQuery.getResultList();
		*/
		
		
		Query query = entityManager.createQuery("select count(p.gender), p.gender from Patient p group by p.gender");
		 
		List listExpected = query.getResultList();
		
		return null;
		
	}
	
}
	