package com.oreon.cerebrum;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang.StringUtils;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.ddx.DifferentialDx;
import com.oreon.cerebrum.ddx.LabFinding;

public class CommaDelimitedPersister {

	public static final String dirPath = "C:\\md\\FerrisAdvisor\\0323056091\\IV";

	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";

	private static EntityManager em;
	static EntityManagerFactory emf ;

	// @Create
	public static synchronized void start() {

		emf = Persistence
				.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}

	public static void main(String[] args) throws Exception {
/*
		start();
		
		String line = "Heliminthic Parasites, Pulmonary aspergillosis, Severe scabies, Asthma, Hay Fever, Atopic dermatitis, Wiskott-Aldrich syndrome, 	Polyarteritis nodosa ,Necrotizing vasculitis, Eosinophilic fasciitis ,Pemphigus ,Addison�s disease ,Inflammatory bowel disease, Dermatitis herpetiformis " +
				"Hodgkins disease, Mycosis fungoidesm,	 Chronic myelocytic leukemia ,Eosinophilic leukemia ,Polycythemia vera ,Mucin-secreting adenocarcinomas , Hyperimmunoglobulin E with recurrent infection, ";
		lineParserLabValue(line,
				"ELEVATED EOSINOPHIL COUNT" ,"EOSINOPHIL COUNT");
		
		em.close();
		emf.close();
*/
	}




	
	 
	public static void lineParserLabValue(String line,
			String findingName, String testName) {
		
		LabFinding finding = new LabFinding();
		finding.setName(findingName);
		finding.setTestName(testName);
		// String line =
		// "Infections: viral or bacterial enteritis, viral hepatitis, food poisoning, gastroenteritis.";

		String[] tokens = line.split(",|\\.");

		for (String tkn : tokens) {
			if (StringUtils.isEmpty(tkn.trim()))
				continue;
			DifferentialDx differentialDx = new DifferentialDx();
			differentialDx.setName(tkn.trim());
			// differentialDx.setDxCategory(dxCategory);
			// differentialDx.setFinding(physicalFinding);

			// differentialDx = (DifferentialDx) persist(differentialDx);
			finding.addDifferentialDx(differentialDx);
			System.out.println(finding.getName() + " " + tkn.trim());
		}
		
		persist(finding);
	}


	private static void persist(BaseEntity differentialDx) {
		try {
			if (!em.getTransaction().isActive())
				em.getTransaction().begin();
			em.persist(differentialDx);
			em.getTransaction().commit();

		} catch (Exception e) {
			// e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println(e.getMessage());
			System.out.println("failed to  save "
					+ differentialDx.getDisplayName());
		}

		// return null;

	}
}


/*eosionphil count
protien el
present in ...?
*/