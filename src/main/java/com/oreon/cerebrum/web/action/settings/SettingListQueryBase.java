package com.oreon.cerebrum.web.action.settings;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.settings.Setting;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SettingListQueryBase extends BaseQuery<Setting, Long> {

	private static final String EJBQL = "select setting from Setting setting";

	protected Setting setting = new Setting();

	public SettingListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Setting getSetting() {
		return setting;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Setting getInstance() {
		return getSetting();
	}

	@Override
	//@Restrict("#{s:hasPermission('setting', 'view')}")
	public List<Setting> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Setting> getEntityClass() {
		return Setting.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"setting.id = #{settingList.setting.id}",

			"setting.archived = #{settingList.setting.archived}",

			"lower(setting.value) like concat(lower(#{settingList.setting.value}),'%')",

			"setting.settingName.id = #{settingList.setting.settingName.id}",

			"setting.dateCreated <= #{settingList.dateCreatedRange.end}",
			"setting.dateCreated >= #{settingList.dateCreatedRange.begin}",};

	@Observer("archivedSetting")
	public void onArchive() {
		refresh();
	}

	public void setSettingNameId(Long id) {
		if (setting.getSettingName() == null) {
			setting
					.setSettingName(new com.oreon.cerebrum.settings.SettingName());
		}
		setting.getSettingName().setId(id);
	}

	public Long getSettingNameId() {
		return setting.getSettingName() == null ? null : setting
				.getSettingName().getId();
	}

}
