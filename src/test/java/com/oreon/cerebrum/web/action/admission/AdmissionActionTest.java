

package com.oreon.cerebrum.web.action.admission;

import java.util.List;

import javax.persistence.Query;

import org.junit.Test;

import com.oreon.cerebrum.patient.Patient;


	

public class AdmissionActionTest extends AdmissionActionTestBase{
	
	@Test
	public void getPatientsByGenderAge(){
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
		
		
		Query query = getEntityManagerFactory().createEntityManager().createQuery("select count(p.gender), p.gender from Patient p group by p.gender");
		 
		List listExpected = query.getResultList();
		
		for (Object object : listExpected) {
			System.out.println(object);
		}
		
		//return null;
		
	}
	
}
