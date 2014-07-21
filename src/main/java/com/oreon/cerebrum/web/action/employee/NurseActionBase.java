package com.oreon.cerebrum.web.action.employee;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.security.Identity;

import com.oreon.cerebrum.employee.Nurse;

//
public abstract class NurseActionBase
		extends
			com.oreon.cerebrum.web.action.employee.AbstractEmployeeAction<Nurse>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long nurseId;

	@In(create = true, value = "nurseSpecialtyAction")
	com.oreon.cerebrum.web.action.employee.NurseSpecialtyAction nurseSpecialtyAction;

	public static final String DEFAULT_ROLE_NAME = "nurse";

	public void setNurseId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setNurseIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setNurseSpecialtyId(Long id) {

		if (id != null && id > 0)
			getInstance()
					.setNurseSpecialty(nurseSpecialtyAction.loadFromId(id));

	}

	public Long getNurseSpecialtyId() {
		if (getInstance().getNurseSpecialty() != null)
			return getInstance().getNurseSpecialty().getId();
		return 0L;
	}

	public Long getNurseId() {
		return (Long) getId();
	}

	public Nurse getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Nurse t) {
		this.instance = t;
		loadAssociations();
	}

	public Nurse getNurse() {
		return (Nurse) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('nurse', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('nurse', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Nurse createInstance() {
		Nurse instance = super.createInstance();

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

		com.oreon.cerebrum.employee.NurseSpecialty nurseSpecialty = nurseSpecialtyAction
				.getDefinedInstance();
		if (nurseSpecialty != null && isNew()) {
			getInstance().setNurseSpecialty(nurseSpecialty);
		}

	}

	public Nurse getDefinedInstance() {
		return (Nurse) (isIdDefined() ? getInstance() : null);
	}

	public void setNurse(Nurse t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setNurseId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Nurse> getEntityClass() {
		return Nurse.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getNurseSpecialty() != null) {
			criteria = criteria.add(Restrictions.eq("nurseSpecialty.id",
					instance.getNurseSpecialty().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getNurseSpecialty() != null) {
			nurseSpecialtyAction.setInstance(getInstance().getNurseSpecialty());

			nurseSpecialtyAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public Nurse getCurrentLoggedInNurse() {
		String query = "Select e from Nurse e where e.appUser.userName = ?1";
		return (Nurse) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

	public String getDefaultRoleName() {
		return DEFAULT_ROLE_NAME;
	}

	public String viewNurse() {
		load(currentEntityId);
		return "viewNurse";
	}

}
