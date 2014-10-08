package com.oreon.cerebrum.web.action.settings;

import com.oreon.cerebrum.settings.SettingGroup;

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

import com.oreon.cerebrum.settings.SettingName;

//
public abstract class SettingGroupActionBase extends BaseAction<SettingGroup>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long settingGroupId;

	public void setSettingGroupId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setSettingGroupIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getSettingGroupId() {
		return (Long) getId();
	}

	public SettingGroup getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(SettingGroup t) {
		this.instance = t;
		loadAssociations();
	}

	public SettingGroup getSettingGroup() {
		return (SettingGroup) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('settingGroup', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('settingGroup', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected SettingGroup createInstance() {
		SettingGroup instance = super.createInstance();

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

	public SettingGroup getDefinedInstance() {
		return (SettingGroup) (isIdDefined() ? getInstance() : null);
	}

	public void setSettingGroup(SettingGroup t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setSettingGroupId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<SettingGroup> getEntityClass() {
		return SettingGroup.class;
	}

	public com.oreon.cerebrum.settings.SettingGroup findByUnqName(String name) {
		return executeSingleResultNamedQuery("settingGroup.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListSettingNames();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.settings.SettingName> listSettingNames = new ArrayList<com.oreon.cerebrum.settings.SettingName>();

	void initListSettingNames() {

		if (listSettingNames.isEmpty())
			listSettingNames.addAll(getInstance().getSettingNames());

	}

	public List<com.oreon.cerebrum.settings.SettingName> getListSettingNames() {

		prePopulateListSettingNames();
		return listSettingNames;
	}

	public void prePopulateListSettingNames() {
	}

	public void setListSettingNames(
			List<com.oreon.cerebrum.settings.SettingName> listSettingNames) {
		this.listSettingNames = listSettingNames;
	}

	public void deleteSettingNames(int index) {
		listSettingNames.remove(index);
	}

	@Begin(join = true)
	public void addSettingNames() {

		initListSettingNames();
		SettingName settingNames = new SettingName();

		settingNames.setSettingGroup(getInstance());

		getListSettingNames().add(settingNames);

	}

	public void updateComposedAssociations() {

		if (listSettingNames != null) {

			java.util.Set<SettingName> items = getInstance().getSettingNames();
			for (SettingName item : items) {
				if (!listSettingNames.contains(item))
					getEntityManager().remove(item);
			}

			for (SettingName item : listSettingNames) {
				item.setSettingGroup(getInstance());
			}

			getInstance().getSettingNames().clear();
			getInstance().getSettingNames().addAll(listSettingNames);
		}
	}

	public void clearLists() {
		listSettingNames.clear();

	}

	public String viewSettingGroup() {
		load(currentEntityId);
		return "viewSettingGroup";
	}

}
