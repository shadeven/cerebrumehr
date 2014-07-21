package com.oreon.cerebrum;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.ddx.DifferentialDx;
import com.oreon.cerebrum.ddx.DxCategory;
import com.oreon.cerebrum.ddx.LabFinding;
import com.oreon.cerebrum.ddx.PhysicalFinding;

public class FileLoader {

	public static final String dirPath = "C:\\md\\FerrisAdvisor\\0323056091\\IV";

	private static final String NOMBRE_PERSISTENCE_UNIT = "appEntityManager";

	private static EntityManager em;

	// @Create
	public static synchronized void start() {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(NOMBRE_PERSISTENCE_UNIT);
		em = emf.createEntityManager();
	}

	public static void main(String[] args) throws Exception {

		// lineParser();
		start();

		File dir = new File(dirPath);

		String[] children = dir.list();
		if (children == null) {
			// Either dir does not exist or is not a directory
		} else {
			for (int i = 0; i < children.length; i++) {
				if (!children[i].endsWith("htm"))
					continue;

				// Get filename of file or directory
				try {
					// printLabValues(doc);
					printChildren(children[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void printChildren(String filename) throws Exception {

		File fXmlFile = new File(dirPath + "\\" + filename);

		String fileContents = FileUtils.readFileToString(fXmlFile);

		String output = "<?xml version=\"1.0\"?>\n"
				+ fileContents.substring(fileContents
						.indexOf("<div id=\"bodycontent\">"), fileContents
						.indexOf("</div><!-- END bodycontent -->") + 6);

		Document doc = Jsoup.parse(output);

		Element title = doc.getElementsByClass("section-title-1").first();

		if (title == null) {
			System.out.println("no title found for " + filename);
			return;
		}
		System.out.println("title" + title.ownText());

		// PhysicalFinding finding = new PhysicalFinding();
		LabFinding finding = new LabFinding();
		finding.setName(title.ownText().trim());

		Elements dzs = doc.getElementsByClass("text");

		/*
		 * for (Element element : dzs) { lineParser(element.ownText(), finding);
		 * }
		 */

		// persist(finding);
		printLabValues(doc, finding);

		// if (output.contains("Elevated in:")) {
		// System.out.println(output);
		System.out
				.println("---------------------------------------------------");
		// }

	}

	private static void printLabValues(Document doc, LabFinding finding) {
		Elements masthead = doc.getElementsByClass("section-title-3");
		Elements paraAll = doc.getElementsByTag("p");
		Elements para = new Elements();

		for (Element element : paraAll) {
			if (element.hasText())
				para.add(element);
		}

		int i = 1;
		
		String testName = finding.getName();
		finding.setTestName(testName);

		for (Element element : masthead) {
			String amount = element.ownText().trim();

			 if (amount.startsWith("Present"))
				finding.setName("PRESENT " + testName);
			else{
				continue;
			}
			
			try {
				lineParserLabValue(para.get(i++).ownText().trim(), finding);
				if(!finding.getDifferentialDxs().isEmpty())
					persist(finding);
				else{
					System.out.println("manual " + finding.getName());
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			}

		}
	}

	public static void lineParserLabValue(String line,
			LabFinding finding) {
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
	}

	public static void lineParser(String line, PhysicalFinding physicalFinding) {
		// String line =
		// "Infections: viral or bacterial enteritis, viral hepatitis, food poisoning, gastroenteritis.";

		String[] tokens = line.split(",|\\.");
		DxCategory dxCategory = null;
		for (String tkn : tokens) {
			if (StringUtils.isEmpty(tkn.trim()))
				continue;
			if (tkn.contains(":")) {
				String category = tkn.substring(0, tkn.indexOf(":"));
				dxCategory = new DxCategory();
				dxCategory.setName(category);
				System.out.println("CAT " + category);
				// dxCategory = (DxCategory) persist(dxCategory);
				tkn = tkn.substring(tkn.indexOf(":") + 1);
			}

			if (StringUtils.isEmpty(tkn.trim()) || tkn.contains("\\xCE"))
				continue;

			DifferentialDx differentialDx = new DifferentialDx();
			differentialDx.setName(tkn.trim());
			// differentialDx.setDxCategory(dxCategory);
			// differentialDx.setFinding(physicalFinding);

			// differentialDx = (DifferentialDx) persist(differentialDx);
			physicalFinding.addDifferentialDx(differentialDx);
			System.out.println(tkn.trim());
		}
	}

	private static void persist(BaseEntity differentialDx) {
		try {
			if (!em.getTransaction().isActive())
				em.getTransaction().begin();
			em.persist(differentialDx);
			em.getTransaction().commit();

			/*
			 * BaseEntity entity = null; try { entity = (BaseEntity)
			 * em.createQuery( "Select e from " +
			 * differentialDx.getClass().getSimpleName() +
			 * " e where e.name = ?1 ").setParameter(1,
			 * differentialDx.getDisplayName()).getSingleResult();
			 * differentialDx = entity;
			 * System.out.println("Found existing record - " +
			 * differentialDx.getDisplayName()); return entity; } catch
			 * (NoResultException e) { em.persist(differentialDx);
			 * em.getTransaction().commit(); }
			 * 
			 * }catch(InvalidStateException ise){ InvalidValue[] iv =
			 * ise.getInvalidValues(); for (InvalidValue invalidValue : iv) {
			 * System.out.println(invalidValue.getMessage() + " " +
			 * invalidValue.getPropertyName()); }
			 */
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
