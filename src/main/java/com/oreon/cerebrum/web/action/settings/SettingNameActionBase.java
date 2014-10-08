package com.oreon.cerebrum.web.action.settings;

import com.oreon.cerebrum.settings.SettingName;

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
public abstract class SettingNameActionBase extends BaseAction<SettingName>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long settingNameId;

	@In(create = true, value = "settingGroupAction")
	com.oreon.cerebrum.web.action.settings.SettingGroupAction settingGroupAction;

	public void setSettingNameId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setSettingNameIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setSettingGroupId(Long id) {

		if (id != null && id > 0)
			getInstance().setSettingGroup(settingGroupAction.loadFromId(id));

	}

	public Long getSettingGroupId() {
		if (getInstance().getSettingGroup() != null)
			return getInstance().getSettingGroup().getId();
		return 0L;
	}

	public Long getSettingNameId() {
		return (Long) getId();
	}

	public SettingName getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(SettingName t) {
		this.instance = t;
		loadAssociations();
	}

	public SettingName getSettingName() {
		return (SettingName) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('settingName', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('settingName', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected SettingName createInstance() {
		SettingName instance = super.createInstance();

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

		com.oreon.cerebrum.settings.SettingGroup settingGroup = settingGroupAction
				.getDefinedInstance();
		if (settingGroup != null && isNew()) {
			getInstance().setSettingGroup(settingGroup);
		}

	}

	public SettingName getDefinedInstance() {
		return (SettingName) (isIdDefined() ? getInstance() : null);
	}

	public void setSettingName(SettingName t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setSettingNameId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<SettingName> getEntityClass() {
		return SettingName.class;
	}

	public com.oreon.cerebrum.settings.SettingName findByUnqName(String name) {
		return executeSingleResultNamedQuery("settingName.findByUnqName", name);
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getSettingGroup() != null) {
			criteria = criteria.add(Restrictions.eq("settingGroup.id", instance
					.getSettingGroup().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getSettingGroup() != null) {
			settingGroupAction.setInstance(getInstance().getSettingGroup());

			settingGroupAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewSettingName() {
		load(currentEntityId);
		return "viewSettingName";
	}

}
