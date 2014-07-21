package com.oreon.cerebrum.web.action.admission;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.admission.BedStay;

//
public abstract class BedStayActionBase extends BaseAction<BedStay>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long bedStayId;

	@In(create = true, value = "admissionAction")
	com.oreon.cerebrum.web.action.admission.AdmissionAction admissionAction;

	@In(create = true, value = "bedAction")
	com.oreon.cerebrum.web.action.facility.BedAction bedAction;

	public void setBedStayId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setBedStayIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setAdmissionId(Long id) {

		if (id != null && id > 0)
			getInstance().setAdmission(admissionAction.loadFromId(id));

	}

	public Long getAdmissionId() {
		if (getInstance().getAdmission() != null)
			return getInstance().getAdmission().getId();
		return 0L;
	}

	public void setBedId(Long id) {

		if (id != null && id > 0)
			getInstance().setBed(bedAction.loadFromId(id));

	}

	public Long getBedId() {
		if (getInstance().getBed() != null)
			return getInstance().getBed().getId();
		return 0L;
	}

	public Long getBedStayId() {
		return (Long) getId();
	}

	public BedStay getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(BedStay t) {
		this.instance = t;
		loadAssociations();
	}

	public BedStay getBedStay() {
		return (BedStay) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('bedStay', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('bedStay', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected BedStay createInstance() {
		BedStay instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

		com.oreon.cerebrum.admission.Admission admission = admissionAction
				.getDefinedInstance();
		if (admission != null && isNew()) {
			getInstance().setAdmission(admission);
		}

		com.oreon.cerebrum.facility.Bed bed = bedAction.getDefinedInstance();
		if (bed != null && isNew()) {
			getInstance().setBed(bed);
		}

	}

	public BedStay getDefinedInstance() {
		return (BedStay) (isIdDefined() ? getInstance() : null);
	}

	public void setBedStay(BedStay t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setBedStayId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<BedStay> getEntityClass() {
		return BedStay.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getAdmission() != null) {
			criteria = criteria.add(Restrictions.eq("admission.id", instance
					.getAdmission().getId()));
		}

		if (instance.getBed() != null) {
			criteria = criteria.add(Restrictions.eq("bed.id", instance.getBed()
					.getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getAdmission() != null) {
			admissionAction.setInstance(getInstance().getAdmission());

			admissionAction.loadAssociations();

		}

		if (getInstance().getBed() != null) {
			bedAction.setInstance(getInstance().getBed());

			bedAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewBedStay() {
		load(currentEntityId);
		return "viewBedStay";
	}

}
