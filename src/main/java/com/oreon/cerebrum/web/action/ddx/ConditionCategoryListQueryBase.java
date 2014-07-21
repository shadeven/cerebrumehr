package com.oreon.cerebrum.web.action.ddx;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.ddx.ConditionCategory;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ConditionCategoryListQueryBase
		extends
			BaseQuery<ConditionCategory, Long> {

	private static final String EJBQL = "select conditionCategory from ConditionCategory conditionCategory";

	protected ConditionCategory conditionCategory = new ConditionCategory();

	public ConditionCategoryListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public ConditionCategory getConditionCategory() {
		return conditionCategory;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public ConditionCategory getInstance() {
		return getConditionCategory();
	}

	@Override
	//@Restrict("#{s:hasPermission('conditionCategory', 'view')}")
	public List<ConditionCategory> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<ConditionCategory> getEntityClass() {
		return ConditionCategory.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"conditionCategory.id = #{conditionCategoryList.conditionCategory.id}",

			"conditionCategory.archived = #{conditionCategoryList.conditionCategory.archived}",

			"lower(conditionCategory.name) like concat(lower(#{conditionCategoryList.conditionCategory.name}),'%')",

			"conditionCategory.dateCreated <= #{conditionCategoryList.dateCreatedRange.end}",
			"conditionCategory.dateCreated >= #{conditionCategoryList.dateCreatedRange.begin}",};

	@Observer("archivedConditionCategory")
	public void onArchive() {
		refresh();
	}

}
