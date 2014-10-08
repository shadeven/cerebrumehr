package com.oreon.cerebrum.web.action.settings;

import com.oreon.cerebrum.settings.Settings;

import org.witchcraft.seam.action.BaseAction;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityQuery;

import org.witchcraft.seam.action.BaseQuery;

import org.witchcraft.base.entity.Range;

import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.EntityLazyDataModel;
import org.primefaces.model.LazyDataModel;
import java.util.Map;

import org.jboss.seam.annotations.Observer;

import java.math.BigDecimal;
import javax.faces.model.DataModel;

import org.jboss.seam.annotations.security.Restrict;

import org.jboss.seam.annotations.In;
import org.jboss.seam.Component;

import com.oreon.cerebrum.settings.Settings;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SettingsListQueryBase extends BaseQuery<Settings, Long> {

	private static final String EJBQL = "select settings from Settings settings";

	protected Settings settings = new Settings();

	public SettingsListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Settings getSettings() {
		return settings;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Settings getInstance() {
		return getSettings();
	}

	@Override
	//@Restrict("#{s:hasPermission('settings', 'view')}")
	public List<Settings> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Settings> getEntityClass() {
		return Settings.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"settings.id = #{settingsList.settings.id}",

			"settings.archived = #{settingsList.settings.archived}",

			"settings.dateCreated <= #{settingsList.dateCreatedRange.end}",
			"settings.dateCreated >= #{settingsList.dateCreatedRange.begin}",};

	@Observer("archivedSettings")
	public void onArchive() {
		refresh();
	}

}
