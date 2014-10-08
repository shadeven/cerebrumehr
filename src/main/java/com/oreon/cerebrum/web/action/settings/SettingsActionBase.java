package com.oreon.cerebrum.web.action.settings;

import com.oreon.cerebrum.settings.Settings;

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
public abstract class SettingsActionBase extends BaseAction<Settings>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long settingsId;

	public void setSettingsId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setSettingsIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getSettingsId() {
		return (Long) getId();
	}

	public Settings getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Settings t) {
		this.instance = t;
		loadAssociations();
	}

	public Settings getSettings() {
		return (Settings) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('settings', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('settings', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Settings createInstance() {
		Settings instance = super.createInstance();

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

	public Settings getDefinedInstance() {
		return (Settings) (isIdDefined() ? getInstance() : null);
	}

	public void setSettings(Settings t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setSettingsId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Settings> getEntityClass() {
		return Settings.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewSettings() {
		load(currentEntityId);
		return "viewSettings";
	}

}
