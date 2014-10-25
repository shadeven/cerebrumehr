package com.oreon.cerebrum.web.action.facility;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.facility.Bed;

//
public abstract class BedActionBase extends BaseAction<Bed>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long bedId;

	@In(create = true, value = "roomAction")
	com.oreon.cerebrum.web.action.facility.RoomAction roomAction;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	public void setBedId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setBedIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setRoomId(Long id) {

		if (id != null && id > 0)
			getInstance().setRoom(roomAction.loadFromId(id));

	}

	public Long getRoomId() {
		if (getInstance().getRoom() != null)
			return getInstance().getRoom().getId();
		return 0L;
	}

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
		return 0L;
	}

	public Long getBedId() {
		return (Long) getId();
	}

	public Bed getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Bed t) {
		this.instance = t;
		loadAssociations();
	}

	public Bed getBed() {
		return (Bed) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('bed', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('bed', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Bed createInstance() {
		Bed instance = super.createInstance();

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

		com.oreon.cerebrum.facility.Room room = roomAction.getDefinedInstance();
		if (room != null && isNew()) {
			getInstance().setRoom(room);
		}

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

	}

	public Bed getDefinedInstance() {
		return (Bed) (isIdDefined() ? getInstance() : null);
	}

	public void setBed(Bed t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setBedId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Bed> getEntityClass() {
		return Bed.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getRoom() != null) {
			criteria = criteria.add(Restrictions.eq("room.id", instance
					.getRoom().getId()));
		}

		if (instance.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", instance
					.getPatient().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getRoom() != null) {
			roomAction.setInstance(getInstance().getRoom());

			roomAction.loadAssociations();

		}

		if (getInstance().getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public List getAvailableBeds() {

		return executeNamedQuery("getAvailableBeds");

	}

	public String viewBed() {
		load(currentEntityId);
		return "viewBed";
	}

}
