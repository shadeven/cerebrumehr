package com.oreon.cerebrum.web.action.users;

import com.oreon.cerebrum.users.AppUser;

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
public abstract class AppUserActionBase extends BaseAction<AppUser>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long appUserId;

	public void setAppUserId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAppUserIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getAppUserId() {
		return (Long) getId();
	}

	public AppUser getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(AppUser t) {
		this.instance = t;
		loadAssociations();
	}

	public AppUser getAppUser() {
		return (AppUser) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('appUser', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('appUser', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected AppUser createInstance() {
		AppUser instance = super.createInstance();

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

	}

	public AppUser getDefinedInstance() {
		return (AppUser) (isIdDefined() ? getInstance() : null);
	}

	public void setAppUser(AppUser t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setAppUserId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<AppUser> getEntityClass() {
		return AppUser.class;
	}

	public com.oreon.cerebrum.users.AppUser findByUnqUserName(String userName) {
		return executeSingleResultNamedQuery("appUser.findByUnqUserName",
				userName);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListAppRoles();
		initListAvailableAppRoles();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.users.AppRole> listAppRoles = new ArrayList<com.oreon.cerebrum.users.AppRole>();

	void initListAppRoles() {

		if (listAppRoles.isEmpty())
			listAppRoles.addAll(getInstance().getAppRoles());

	}

	public List<com.oreon.cerebrum.users.AppRole> getListAppRoles() {

		prePopulateListAppRoles();
		return listAppRoles;
	}

	public void prePopulateListAppRoles() {
	}

	public void setListAppRoles(
			List<com.oreon.cerebrum.users.AppRole> listAppRoles) {
		this.listAppRoles = listAppRoles;
	}

	protected DualListModel<com.oreon.cerebrum.users.AppRole> listAvailableAppRoles;

	void initListAvailableAppRoles() {

		List<com.oreon.cerebrum.users.AppRole> availableappRoles = ((com.oreon.cerebrum.web.action.users.AppRoleListQuery) Component
				.getInstance("appRoleList")).getAll();

		List<com.oreon.cerebrum.users.AppRole> currentAppRoles = getInstance()
				.getListAppRoles();

		if (availableappRoles == null)
			availableappRoles = new ArrayList<com.oreon.cerebrum.users.AppRole>();

		if (getEntity() != null)
			availableappRoles.removeAll(getEntity().getAppRoles());

		listAvailableAppRoles = new DualListModel<com.oreon.cerebrum.users.AppRole>(
				availableappRoles, currentAppRoles);
	}

	public DualListModel<com.oreon.cerebrum.users.AppRole> getListAvailableAppRoles() {
		if (listAvailableAppRoles == null)
			initListAvailableAppRoles();

		return listAvailableAppRoles;
	}

	public void setListAvailableAppRoles(
			DualListModel<com.oreon.cerebrum.users.AppRole> listAvailableAppRoles) {
		this.listAvailableAppRoles = listAvailableAppRoles;
	}

	public void updateComposedAssociations() {

		if (listAvailableAppRoles != null) {
			getInstance().getAppRoles().clear();
			getInstance().getAppRoles().addAll(
					listAvailableAppRoles.getTarget());
		}

	}

	public void clearLists() {

		listAppRoles.clear();

	}

	public String viewAppUser() {
		load(currentEntityId);
		return "viewAppUser";
	}

}
