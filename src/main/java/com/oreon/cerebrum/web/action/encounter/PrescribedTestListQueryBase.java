package com.oreon.cerebrum.web.action.encounter;

import com.oreon.cerebrum.encounter.PrescribedTest;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.cerebrum.encounter.PrescribedTest;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PrescribedTestListQueryBase
		extends
			BaseQuery<PrescribedTest, Long> {

	private static final String EJBQL = "select prescribedTest from PrescribedTest prescribedTest";

	protected PrescribedTest prescribedTest = new PrescribedTest();

	public PrescribedTestListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PrescribedTest getPrescribedTest() {
		return prescribedTest;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public PrescribedTest getInstance() {
		return getPrescribedTest();
	}

	@Override
	//@Restrict("#{s:hasPermission('prescribedTest', 'view')}")
	public List<PrescribedTest> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PrescribedTest> getEntityClass() {
		return PrescribedTest.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"prescribedTest.id = #{prescribedTestList.prescribedTest.id}",

			"prescribedTest.archived = #{prescribedTestList.prescribedTest.archived}",

			"prescribedTest.dxTest.id = #{prescribedTestList.prescribedTest.dxTest.id}",

			"prescribedTest.encounter.id = #{prescribedTestList.prescribedTest.encounter.id}",

			"lower(prescribedTest.remarks) like concat(lower(#{prescribedTestList.prescribedTest.remarks}),'%')",

			"lower(prescribedTest.testResults.results) like concat(lower(#{prescribedTestList.prescribedTest.testResults.results}),'%')",

			"prescribedTest.dateCreated <= #{prescribedTestList.dateCreatedRange.end}",
			"prescribedTest.dateCreated >= #{prescribedTestList.dateCreatedRange.begin}",};

	/** 
	 * List of all PrescribedTests for the given Encounter
	 * @param patient
	 * @return 
	 */
	public List<PrescribedTest> getAllPrescribedTestsByEncounter(
			final com.oreon.cerebrum.encounter.Encounter encounter) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		prescribedTest.setEncounter(encounter);
		return getResultListTable();
	}

	public LazyDataModel<PrescribedTest> getPrescribedTestsByEncounter(
			final com.oreon.cerebrum.encounter.Encounter encounter) {

		EntityLazyDataModel<PrescribedTest, Long> prescribedTestLazyDataModel = new EntityLazyDataModel<PrescribedTest, Long>(
				this) {

			@Override
			public List<PrescribedTest> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				prescribedTest.setEncounter(encounter);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return prescribedTestLazyDataModel;

	}

	@Observer("archivedPrescribedTest")
	public void onArchive() {
		refresh();
	}

	public void setDxTestId(Long id) {
		if (prescribedTest.getDxTest() == null) {
			prescribedTest.setDxTest(new com.oreon.cerebrum.ddx.DxTest());
		}
		prescribedTest.getDxTest().setId(id);
	}

	public Long getDxTestId() {
		return prescribedTest.getDxTest() == null ? null : prescribedTest
				.getDxTest().getId();
	}

	public void setEncounterId(Long id) {
		if (prescribedTest.getEncounter() == null) {
			prescribedTest
					.setEncounter(new com.oreon.cerebrum.encounter.Encounter());
		}
		prescribedTest.getEncounter().setId(id);
	}

	public Long getEncounterId() {
		return prescribedTest.getEncounter() == null ? null : prescribedTest
				.getEncounter().getId();
	}

}
