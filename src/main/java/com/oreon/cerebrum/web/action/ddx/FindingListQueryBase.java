package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.Finding;

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

import com.oreon.cerebrum.ddx.Finding;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FindingListQueryBase extends BaseQuery<Finding, Long> {

	private static final String EJBQL = "select finding from Finding finding";

	protected Finding finding = new Finding();

	public FindingListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Finding getFinding() {
		return finding;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Finding getInstance() {
		return getFinding();
	}

	@Override
	//@Restrict("#{s:hasPermission('finding', 'view')}")
	public List<Finding> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Finding> getEntityClass() {
		return Finding.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"finding.id = #{findingList.finding.id}",

			"finding.archived = #{findingList.finding.archived}",

			"lower(finding.name) like concat(lower(#{findingList.finding.name}),'%')",

			"finding.dateCreated <= #{findingList.dateCreatedRange.end}",
			"finding.dateCreated >= #{findingList.dateCreatedRange.begin}",};

	@Observer("archivedFinding")
	public void onArchive() {
		refresh();
	}

	@Override
	protected void setupForAutoComplete(String input) {

		finding.setName(input);

	}

}
