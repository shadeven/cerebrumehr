package com.oreon.cerebrum.web.action.ddx;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.ddx.DxTest;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DxTestListQueryBase extends BaseQuery<DxTest, Long> {

	private static final String EJBQL = "select dxTest from DxTest dxTest";

	protected DxTest dxTest = new DxTest();

	public DxTestListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public DxTest getDxTest() {
		return dxTest;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public DxTest getInstance() {
		return getDxTest();
	}

	@Override
	//@Restrict("#{s:hasPermission('dxTest', 'view')}")
	public List<DxTest> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<DxTest> getEntityClass() {
		return DxTest.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"dxTest.id = #{dxTestList.dxTest.id}",

			"dxTest.archived = #{dxTestList.dxTest.archived}",

			"lower(dxTest.name) like concat(lower(#{dxTestList.dxTest.name}),'%')",

			"lower(dxTest.description) like concat(lower(#{dxTestList.dxTest.description}),'%')",

			"dxTest.dateCreated <= #{dxTestList.dateCreatedRange.end}",
			"dxTest.dateCreated >= #{dxTestList.dateCreatedRange.begin}",};

	@Observer("archivedDxTest")
	public void onArchive() {
		refresh();
	}

}
