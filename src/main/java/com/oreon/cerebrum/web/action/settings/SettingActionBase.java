package com.oreon.cerebrum.web.action.settings;

import com.oreon.cerebrum.settings.Setting;

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
public abstract class SettingActionBase extends BaseAction<Setting>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long settingId;

	@In(create = true, value = "settingNameAction")
	com.oreon.cerebrum.web.action.settings.SettingNameAction settingNameAction;

	public void setSettingId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setSettingIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setSettingNameId(Long id) {

		if (id != null && id > 0)
			getInstance().setSettingName(settingNameAction.loadFromId(id));

	}

	public Long getSettingNameId() {
		if (getInstance().getSettingName() != null)
			return getInstance().getSettingName().getId();
		return 0L;
	}

	public Long getSettingId() {
		return (Long) getId();
	}

	public Setting getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Setting t) {
		this.instance = t;
		loadAssociations();
	}

	public Setting getSetting() {
		return (Setting) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('setting', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('setting', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Setting createInstance() {
		Setting instance = super.createInstance();

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

		com.oreon.cerebrum.settings.SettingName settingName = settingNameAction
				.getDefinedInstance();
		if (settingName != null && isNew()) {
			getInstance().setSettingName(settingName);
		}

	}

	public Setting getDefinedInstance() {
		return (Setting) (isIdDefined() ? getInstance() : null);
	}

	public void setSetting(Setting t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setSettingId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Setting> getEntityClass() {
		return Setting.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getSettingName() != null) {
			criteria = criteria.add(Restrictions.eq("settingName.id", instance
					.getSettingName().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getSettingName() != null) {
			settingNameAction.setInstance(getInstance().getSettingName());

			settingNameAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewSetting() {
		load(currentEntityId);
		return "viewSetting";
	}

}
