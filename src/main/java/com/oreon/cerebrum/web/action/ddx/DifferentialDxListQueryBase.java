package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.DifferentialDx;

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

import com.oreon.cerebrum.ddx.DifferentialDx;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class DifferentialDxListQueryBase
		extends
			BaseQuery<DifferentialDx, Long> {

	private static final String EJBQL = "select differentialDx from DifferentialDx differentialDx";

	protected DifferentialDx differentialDx = new DifferentialDx();

	public DifferentialDxListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public DifferentialDx getDifferentialDx() {
		return differentialDx;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public DifferentialDx getInstance() {
		return getDifferentialDx();
	}

	@Override
	//@Restrict("#{s:hasPermission('differentialDx', 'view')}")
	public List<DifferentialDx> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<DifferentialDx> getEntityClass() {
		return DifferentialDx.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"differentialDx.id = #{differentialDxList.differentialDx.id}",

			"differentialDx.archived = #{differentialDxList.differentialDx.archived}",

			"lower(differentialDx.name) like concat(lower(#{differentialDxList.differentialDx.name}),'%')",

			"differentialDx.dxCategory.id = #{differentialDxList.differentialDx.dxCategory.id}",

			"differentialDx.finding.id = #{differentialDxList.differentialDx.finding.id}",

			"differentialDx.dateCreated <= #{differentialDxList.dateCreatedRange.end}",
			"differentialDx.dateCreated >= #{differentialDxList.dateCreatedRange.begin}",};

	/** 
	 * List of all DifferentialDxs for the given Finding
	 * @param patient
	 * @return 
	 */
	public List<DifferentialDx> getAllDifferentialDxsByFinding(
			final com.oreon.cerebrum.ddx.Finding finding) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		differentialDx.setFinding(finding);
		return getResultListTable();
	}

	public LazyDataModel<DifferentialDx> getDifferentialDxsByFinding(
			final com.oreon.cerebrum.ddx.Finding finding) {

		EntityLazyDataModel<DifferentialDx, Long> differentialDxLazyDataModel = new EntityLazyDataModel<DifferentialDx, Long>(
				this) {

			@Override
			public List<DifferentialDx> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				differentialDx.setFinding(finding);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return differentialDxLazyDataModel;

	}

	@Observer("archivedDifferentialDx")
	public void onArchive() {
		refresh();
	}

	public void setDxCategoryId(Long id) {
		if (differentialDx.getDxCategory() == null) {
			differentialDx
					.setDxCategory(new com.oreon.cerebrum.ddx.DxCategory());
		}
		differentialDx.getDxCategory().setId(id);
	}

	public Long getDxCategoryId() {
		return differentialDx.getDxCategory() == null ? null : differentialDx
				.getDxCategory().getId();
	}

	public void setFindingId(Long id) {
		if (differentialDx.getFinding() == null) {
			differentialDx.setFinding(new com.oreon.cerebrum.ddx.Finding());
		}
		differentialDx.getFinding().setId(id);
	}

	public Long getFindingId() {
		return differentialDx.getFinding() == null ? null : differentialDx
				.getFinding().getId();
	}

}
