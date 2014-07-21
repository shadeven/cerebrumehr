package com.oreon.cerebrum.web.action.ddx;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.ddx.DxCategory;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DxCategoryListQueryBase
		extends
			BaseQuery<DxCategory, Long> {

	private static final String EJBQL = "select dxCategory from DxCategory dxCategory";

	protected DxCategory dxCategory = new DxCategory();

	public DxCategoryListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public DxCategory getDxCategory() {
		return dxCategory;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public DxCategory getInstance() {
		return getDxCategory();
	}

	@Override
	//@Restrict("#{s:hasPermission('dxCategory', 'view')}")
	public List<DxCategory> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<DxCategory> getEntityClass() {
		return DxCategory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"dxCategory.id = #{dxCategoryList.dxCategory.id}",

			"dxCategory.archived = #{dxCategoryList.dxCategory.archived}",

			"lower(dxCategory.name) like concat(lower(#{dxCategoryList.dxCategory.name}),'%')",

			"dxCategory.dateCreated <= #{dxCategoryList.dateCreatedRange.end}",
			"dxCategory.dateCreated >= #{dxCategoryList.dateCreatedRange.begin}",};

	@Observer("archivedDxCategory")
	public void onArchive() {
		refresh();
	}

}