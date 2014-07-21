package com.oreon.cerebrum.web.action.ddx;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.ddx.PatientFinding;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class PatientFindingListQueryBase
		extends
			BaseQuery<PatientFinding, Long> {

	private static final String EJBQL = "select patientFinding from PatientFinding patientFinding";

	protected PatientFinding patientFinding = new PatientFinding();

	public PatientFindingListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public PatientFinding getPatientFinding() {
		return patientFinding;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public PatientFinding getInstance() {
		return getPatientFinding();
	}

	@Override
	//@Restrict("#{s:hasPermission('patientFinding', 'view')}")
	public List<PatientFinding> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<PatientFinding> getEntityClass() {
		return PatientFinding.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"patientFinding.id = #{patientFindingList.patientFinding.id}",

			"patientFinding.archived = #{patientFindingList.patientFinding.archived}",

			"patientFinding.finding.id = #{patientFindingList.patientFinding.finding.id}",

			"patientFinding.patientDiffDx.id = #{patientFindingList.patientFinding.patientDiffDx.id}",

			"patientFinding.dateCreated <= #{patientFindingList.dateCreatedRange.end}",
			"patientFinding.dateCreated >= #{patientFindingList.dateCreatedRange.begin}",};

	/** 
	 * List of all PatientFindings for the given PatientDiffDx
	 * @param patient
	 * @return 
	 */
	public List<PatientFinding> getAllPatientFindingsByPatientDiffDx(
			final com.oreon.cerebrum.ddx.PatientDiffDx patientDiffDx) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		patientFinding.setPatientDiffDx(patientDiffDx);
		return getResultListTable();
	}

	public LazyDataModel<PatientFinding> getPatientFindingsByPatientDiffDx(
			final com.oreon.cerebrum.ddx.PatientDiffDx patientDiffDx) {

		EntityLazyDataModel<PatientFinding, Long> patientFindingLazyDataModel = new EntityLazyDataModel<PatientFinding, Long>(
				this) {

			@Override
			public List<PatientFinding> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				patientFinding.setPatientDiffDx(patientDiffDx);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return patientFindingLazyDataModel;

	}

	@Observer("archivedPatientFinding")
	public void onArchive() {
		refresh();
	}

	public void setFindingId(Long id) {
		if (patientFinding.getFinding() == null) {
			patientFinding.setFinding(new com.oreon.cerebrum.ddx.Finding());
		}
		patientFinding.getFinding().setId(id);
	}

	public Long getFindingId() {
		return patientFinding.getFinding() == null ? null : patientFinding
				.getFinding().getId();
	}

	public void setPatientDiffDxId(Long id) {
		if (patientFinding.getPatientDiffDx() == null) {
			patientFinding
					.setPatientDiffDx(new com.oreon.cerebrum.ddx.PatientDiffDx());
		}
		patientFinding.getPatientDiffDx().setId(id);
	}

	public Long getPatientDiffDxId() {
		return patientFinding.getPatientDiffDx() == null
				? null
				: patientFinding.getPatientDiffDx().getId();
	}

}
