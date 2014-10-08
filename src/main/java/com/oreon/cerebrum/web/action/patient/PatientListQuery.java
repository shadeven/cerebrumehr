package com.oreon.cerebrum.web.action.patient;

import java.util.List;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.oreon.cerebrum.patient.Patient;


@Name("patientList")
@Scope(ScopeType.CONVERSATION)
public class PatientListQuery extends PatientListQueryBase
		implements
			java.io.Serializable {
	
	String searchQuery;
	
	boolean hasMore = false;
    int numberOfResults;
    int pageSize=10;
	
	public String getSearchQuery() {
        return searchQuery;
    }
	
	@Override
	public List<Patient> getAll() {
		setOrderColumn("lastName");
		return super.getAll();
	}
    
    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
	
	
	 private FullTextQuery createSearchQuery(String textQuery) {
	        QueryBuilder queryBuilder = entityManager.getSearchFactory()
	           .buildQueryBuilder().forEntity(Patient.class).get();
	        
	        //Hibernate Search fulltext query example:
	        
	        //query to match exact terms occurrence, using custom boosts:
	        Query queryToFindExactTerms = queryBuilder.keyword()
	           .onFields("firstName").boostedTo(2f)
	           .andField("lastName").boostedTo(4f)
	           .andField("contactDetails.primaryPhone").boostedTo(2f)
	           .matching(textQuery)
	           .createQuery();
	        
	        //Similar query, but using NGram matching instead of exact terms:
	        
	        Query queryToFindMathingNGrams = queryBuilder.keyword()
	           .onFields("lastName:ngrams").boostedTo(2f)
	           .andField("firstName:ngrams")
	           .andField("contactDetails.phone:ngrams")
	           .matching(textQuery)
	           .createQuery();
	         
	        
	        //Combine them for best results, note exact uses an higher boost:
	        Query fullTextQuery = queryBuilder.bool()
	           .should(queryToFindMathingNGrams)
	           .should(queryToFindExactTerms)
	           .createQuery();
	        
	        log.info("Executing fulltext query {0}", fullTextQuery);
	        return entityManager.createFullTextQuery(fullTextQuery, Patient.class);
	    }
	 
	 @Begin(join=true)
	 public List<Patient> doSearch(String qry){
		 return updateResults(qry);
	 }
	       
	 
	 private List<Patient> updateResults(String qry) {
		 
		 	qry = qry.trim();
	       
	       javax.persistence.Query query = null;  
	       if (qry == null || "".equals(qry))
	       {
	          query = entityManager.createQuery("from Patient");  
	          numberOfResults =query.getResultList().size();
	       }
	       else
	       {
	          query = createSearchQuery(qry);
	          numberOfResults =( (FullTextQuery) query).getResultSize();
	       }
	       
	        List<Patient> items = query
	            .setMaxResults(pageSize + 1)
	            //.setFirstResult(pageSize * currentPage)
	            .getResultList(); 
	        
	        return items;
	        
	        /*
	        if (items.size() > pageSize) {
	            searchResults = new ArrayList(items.subList(0, pageSize));
	            hasMore = true;
	        } else {
	            searchResults = items;
	            hasMore = false;
	        }*/

	        //searchSelections = new HashMap<Product, Boolean>();
	    }

}
