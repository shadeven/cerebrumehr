package com.oreon.cerebrum.web.action.ddx;

import com.oreon.cerebrum.ddx.PatientDiffDx;

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

import com.oreon.cerebrum.ddx.PatientDiffDx;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PatientDiffDxListQueryBase
		extends
			BaseQuery<PatientDiffDx, Long> {

	private static final String EJBQL = "select patientDiffDx from PatientDiffDx patientDiffDx";

	protected PatientDiffDx patientDiffDx = new PatientDiffDx();

	public PatientDiffDxListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PatientDiffDx getPatientDiffDx() {
		return patientDiffDx;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public PatientDiffDx getInstance() {
		return getPatientDiffDx();
	}

	@Override
	//@Restrict("#{s:hasPermission('patientDiffDx', 'view')}")
	public List<PatientDiffDx> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PatientDiffDx> getEntityClass() {
		return PatientDiffDx.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"patientDiffDx.id = #{patientDiffDxList.patientDiffDx.id}",

			"patientDiffDx.archived = #{patientDiffDxList.patientDiffDx.archived}",

			"patientDiffDx.patient.id = #{patientDiffDxList.patientDiffDx.patient.id}",

			"lower(patientDiffDx.notes) like concat(lower(#{patientDiffDxList.patientDiffDx.notes}),'%')",

			"patientDiffDx.dateCreated <= #{patientDiffDxList.dateCreatedRange.end}",
			"patientDiffDx.dateCreated >= #{patientDiffDxList.dateCreatedRange.begin}",};

	@Observer("archivedPatientDiffDx")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (patientDiffDx.getPatient() == null) {
			patientDiffDx.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		patientDiffDx.getPatient().setId(id);
	}

	public Long getPatientId() {
		return patientDiffDx.getPatient() == null ? null : patientDiffDx
				.getPatient().getId();
	}

}
