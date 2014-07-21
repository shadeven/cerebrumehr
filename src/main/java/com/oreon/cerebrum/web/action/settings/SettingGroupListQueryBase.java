package com.oreon.cerebrum.web.action.settings;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.settings.SettingGroup;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SettingGroupListQueryBase
		extends
			BaseQuery<SettingGroup, Long> {

	private static final String EJBQL = "select settingGroup from SettingGroup settingGroup";

	protected SettingGroup settingGroup = new SettingGroup();

	public SettingGroupListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public SettingGroup getSettingGroup() {
		return settingGroup;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public SettingGroup getInstance() {
		return getSettingGroup();
	}

	@Override
	//@Restrict("#{s:hasPermission('settingGroup', 'view')}")
	public List<SettingGroup> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<SettingGroup> getEntityClass() {
		return SettingGroup.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"settingGroup.id = #{settingGroupList.settingGroup.id}",

			"settingGroup.archived = #{settingGroupList.settingGroup.archived}",

			"lower(settingGroup.name) like concat(lower(#{settingGroupList.settingGroup.name}),'%')",

			"settingGroup.dateCreated <= #{settingGroupList.dateCreatedRange.end}",
			"settingGroup.dateCreated >= #{settingGroupList.dateCreatedRange.begin}",};

	@Observer("archivedSettingGroup")
	public void onArchive() {
		refresh();
	}

}
