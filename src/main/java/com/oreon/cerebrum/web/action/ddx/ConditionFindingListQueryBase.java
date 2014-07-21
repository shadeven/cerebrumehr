package com.oreon.cerebrum.web.action.ddx;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.ddx.ConditionFinding;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ConditionFindingListQueryBase
		extends
			BaseQuery<ConditionFinding, Long> {

	private static final String EJBQL = "select conditionFinding from ConditionFinding conditionFinding";

	protected ConditionFinding conditionFinding = new ConditionFinding();

	public ConditionFindingListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public ConditionFinding getConditionFinding() {
		return conditionFinding;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public ConditionFinding getInstance() {
		return getConditionFinding();
	}

	@Override
	//@Restrict("#{s:hasPermission('conditionFinding', 'view')}")
	public List<ConditionFinding> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<ConditionFinding> getEntityClass() {
		return ConditionFinding.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"conditionFinding.id = #{conditionFindingList.conditionFinding.id}",

			"conditionFinding.archived = #{conditionFindingList.conditionFinding.archived}",

			"conditionFinding.disease.id = #{conditionFindingList.conditionFinding.disease.id}",

			"conditionFinding.falsePositive = #{conditionFindingList.conditionFinding.falsePositive}",

			"conditionFinding.dateCreated <= #{conditionFindingList.dateCreatedRange.end}",
			"conditionFinding.dateCreated >= #{conditionFindingList.dateCreatedRange.begin}",};

	@Observer("archivedConditionFinding")
	public void onArchive() {
		refresh();
	}

	public void setDiseaseId(Long id) {
		if (conditionFinding.getDisease() == null) {
			conditionFinding.setDisease(new com.oreon.cerebrum.ddx.Disease());
		}
		conditionFinding.getDisease().setId(id);
	}

	public Long getDiseaseId() {
		return conditionFinding.getDisease() == null ? null : conditionFinding
				.getDisease().getId();
	}

}
