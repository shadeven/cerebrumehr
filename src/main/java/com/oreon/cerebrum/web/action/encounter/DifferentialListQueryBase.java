package com.oreon.cerebrum.web.action.encounter;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.encounter.Differential;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DifferentialListQueryBase
		extends
			BaseQuery<Differential, Long> {

	private static final String EJBQL = "select differential from Differential differential";

	protected Differential differential = new Differential();

	public DifferentialListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Differential getDifferential() {
		return differential;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Differential getInstance() {
		return getDifferential();
	}

	@Override
	//@Restrict("#{s:hasPermission('differential', 'view')}")
	public List<Differential> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Differential> getEntityClass() {
		return Differential.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"differential.id = #{differentialList.differential.id}",

			"differential.archived = #{differentialList.differential.archived}",

			"lower(differential.remarks) like concat(lower(#{differentialList.differential.remarks}),'%')",

			"differential.dateCreated <= #{differentialList.dateCreatedRange.end}",
			"differential.dateCreated >= #{differentialList.dateCreatedRange.begin}",};

	@Observer("archivedDifferential")
	public void onArchive() {
		refresh();
	}

}
