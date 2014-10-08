package com.oreon.cerebrum.web.action.settings;

import com.oreon.cerebrum.settings.SettingName;

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

import com.oreon.cerebrum.settings.SettingName;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SettingNameListQueryBase
		extends
			BaseQuery<SettingName, Long> {

	private static final String EJBQL = "select settingName from SettingName settingName";

	protected SettingName settingName = new SettingName();

	public SettingNameListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public SettingName getSettingName() {
		return settingName;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public SettingName getInstance() {
		return getSettingName();
	}

	@Override
	//@Restrict("#{s:hasPermission('settingName', 'view')}")
	public List<SettingName> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<SettingName> getEntityClass() {
		return SettingName.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"settingName.id = #{settingNameList.settingName.id}",

			"settingName.archived = #{settingNameList.settingName.archived}",

			"lower(settingName.name) like concat(lower(#{settingNameList.settingName.name}),'%')",

			"settingName.settingGroup.id = #{settingNameList.settingName.settingGroup.id}",

			"lower(settingName.defaultValue) like concat(lower(#{settingNameList.settingName.defaultValue}),'%')",

			"settingName.dateCreated <= #{settingNameList.dateCreatedRange.end}",
			"settingName.dateCreated >= #{settingNameList.dateCreatedRange.begin}",};

	/** 
	 * List of all SettingNames for the given SettingGroup
	 * @param patient
	 * @return 
	 */
	public List<SettingName> getAllSettingNamesBySettingGroup(
			final com.oreon.cerebrum.settings.SettingGroup settingGroup) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		settingName.setSettingGroup(settingGroup);
		return getResultListTable();
	}

	public LazyDataModel<SettingName> getSettingNamesBySettingGroup(
			final com.oreon.cerebrum.settings.SettingGroup settingGroup) {

		EntityLazyDataModel<SettingName, Long> settingNameLazyDataModel = new EntityLazyDataModel<SettingName, Long>(
				this) {

			@Override
			public List<SettingName> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				settingName.setSettingGroup(settingGroup);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return settingNameLazyDataModel;

	}

	@Observer("archivedSettingName")
	public void onArchive() {
		refresh();
	}

	public void setSettingGroupId(Long id) {
		if (settingName.getSettingGroup() == null) {
			settingName
					.setSettingGroup(new com.oreon.cerebrum.settings.SettingGroup());
		}
		settingName.getSettingGroup().setId(id);
	}

	public Long getSettingGroupId() {
		return settingName.getSettingGroup() == null ? null : settingName
				.getSettingGroup().getId();
	}

}
