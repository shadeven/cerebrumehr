package com.oreon.cerebrum.web.action.employee;

import com.oreon.cerebrum.employee.Physician;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

//
public abstract class PhysicianActionBase
		extends
			com.oreon.cerebrum.web.action.employee.AbstractEmployeeAction<Physician>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long physicianId;

	@In(create = true, value = "specializationAction")
	com.oreon.cerebrum.web.action.employee.SpecializationAction specializationAction;

	public static final String DEFAULT_ROLE_NAME = "physician";

	public void setPhysicianId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setPhysicianIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setSpecializationId(Long id) {

		if (id != null && id > 0)
			getInstance()
					.setSpecialization(specializationAction.loadFromId(id));

	}

	public Long getSpecializationId() {
		if (getInstance().getSpecialization() != null)
			return getInstance().getSpecialization().getId();
		return 0L;
	}

	public Long getPhysicianId() {
		return (Long) getId();
	}

	public Physician getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Physician t) {
		this.instance = t;
		loadAssociations();
	}

	public Physician getPhysician() {
		return (Physician) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('physician', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('physician', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Physician createInstance() {
		Physician instance = super.createInstance();

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

		com.oreon.cerebrum.employee.Specialization specialization = specializationAction
				.getDefinedInstance();
		if (specialization != null && isNew()) {
			getInstance().setSpecialization(specialization);
		}

	}

	public Physician getDefinedInstance() {
		return (Physician) (isIdDefined() ? getInstance() : null);
	}

	public void setPhysician(Physician t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setPhysicianId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Physician> getEntityClass() {
		return Physician.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getSpecialization() != null) {
			criteria = criteria.add(Restrictions.eq("specialization.id",
					instance.getSpecialization().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getSpecialization() != null) {
			specializationAction.setInstance(getInstance().getSpecialization());

			specializationAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public Physician getCurrentLoggedInPhysician() {
		String query = "Select e from Physician e where e.appUser.userName = ?1";
		return (Physician) executeSingleResultQuery(query, Identity.instance()
				.getCredentials().getUsername());
	}

	public String getDefaultRoleName() {
		return DEFAULT_ROLE_NAME;
	}

	public String viewPhysician() {
		load(currentEntityId);
		return "viewPhysician";
	}

}
