package com.oreon.cerebrum.web.action.encounter;

import com.oreon.cerebrum.encounter.Reason;

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

import com.oreon.cerebrum.encounter.Reason;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ReasonListQueryBase extends BaseQuery<Reason, Long> {

	private static final String EJBQL = "select reason from Reason reason";

	protected Reason reason = new Reason();

	public ReasonListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Reason getReason() {
		return reason;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Reason getInstance() {
		return getReason();
	}

	@Override
	//@Restrict("#{s:hasPermission('reason', 'view')}")
	public List<Reason> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Reason> getEntityClass() {
		return Reason.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"reason.id = #{reasonList.reason.id}",

			"reason.archived = #{reasonList.reason.archived}",

			"reason.code.id = #{reasonList.reason.code.id}",

			"lower(reason.remarks) like concat(lower(#{reasonList.reason.remarks}),'%')",

			"reason.dateCreated <= #{reasonList.dateCreatedRange.end}",
			"reason.dateCreated >= #{reasonList.dateCreatedRange.begin}",};

	@Observer("archivedReason")
	public void onArchive() {
		refresh();
	}

	public void setCodeId(Long id) {
		if (reason.getCode() == null) {
			reason.setCode(new com.oreon.cerebrum.codes.Code());
		}
		reason.getCode().setId(id);
	}

	public Long getCodeId() {
		return reason.getCode() == null ? null : reason.getCode().getId();
	}

}
