package com.oreon.cerebrum.web.action.admission;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.admission.Admission;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AdmissionListQueryBase extends BaseQuery<Admission, Long> {

	private static final String EJBQL = "select admission from Admission admission";

	protected Admission admission = new Admission();

	public AdmissionListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Admission getAdmission() {
		return admission;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Admission getInstance() {
		return getAdmission();
	}

	@Override
	//@Restrict("#{s:hasPermission('admission', 'view')}")
	public List<Admission> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Admission> getEntityClass() {
		return Admission.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Date> dischargeDateRange = new Range<Date>();

	public Range<Date> getDischargeDateRange() {
		return dischargeDateRange;
	}
	public void setDischargeDate(Range<Date> dischargeDateRange) {
		this.dischargeDateRange = dischargeDateRange;
	}

	private static final String[] RESTRICTIONS = {
			"admission.id = #{admissionList.admission.id}",

			"admission.archived = #{admissionList.admission.archived}",

			"admission.patient.id = #{admissionList.admission.patient.id}",

			"lower(admission.admissionNote) like concat(lower(#{admissionList.admission.admissionNote}),'%')",

			"admission.dischargeDate >= #{admissionList.dischargeDateRange.begin}",
			"admission.dischargeDate <= #{admissionList.dischargeDateRange.end}",

			"lower(admission.dischargeNote) like concat(lower(#{admissionList.admission.dischargeNote}),'%')",

			"admission.dischargeCode = #{admissionList.admission.dischargeCode}",

			"admission.isCurrent = #{admissionList.admission.isCurrent}",

			"admission.invoice.id = #{admissionList.admission.invoice.id}",

			"admission.dateCreated <= #{admissionList.dateCreatedRange.end}",
			"admission.dateCreated >= #{admissionList.dateCreatedRange.begin}",};

	/** 
	 * List of all Admissions for the given Patient
	 * @param patient
	 * @return 
	 */
	public List<Admission> getAllAdmissionsByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		admission.setPatient(patient);
		return getResultListTable();
	}

	public LazyDataModel<Admission> getAdmissionsByPatient(
			final com.oreon.cerebrum.patient.Patient patient) {

		EntityLazyDataModel<Admission, Long> admissionLazyDataModel = new EntityLazyDataModel<Admission, Long>(
				this) {

			@Override
			public List<Admission> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				admission.setPatient(patient);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return admissionLazyDataModel;

	}

	@Observer("archivedAdmission")
	public void onArchive() {
		refresh();
	}

	public void setPatientId(Long id) {
		if (admission.getPatient() == null) {
			admission.setPatient(new com.oreon.cerebrum.patient.Patient());
		}
		admission.getPatient().setId(id);
	}

	public Long getPatientId() {
		return admission.getPatient() == null ? null : admission.getPatient()
				.getId();
	}

	public void setInvoiceId(Long id) {
		if (admission.getInvoice() == null) {
			admission.setInvoice(new com.oreon.cerebrum.billing.Invoice());
		}
		admission.getInvoice().setId(id);
	}

	public Long getInvoiceId() {
		return admission.getInvoice() == null ? null : admission.getInvoice()
				.getId();
	}

}
