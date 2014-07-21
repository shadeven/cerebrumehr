package com.oreon.cerebrum.web.action.settings;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import com.oreon.cerebrum.settings.Setting;

@Scope(ScopeType.SESSION)
@Name("settingsAction")
public class SettingsAction extends SettingsActionBase
		implements
			java.io.Serializable {
	
	public String getSettingValue(String name){
		String qry = "Select s from Setting s where s.settingName.name = ?";
		Setting setting =  executeSingleResultQuery(qry, name);
		return (setting.getValue() == null )?setting.getValue() : setting.getSettingName().getDefaultValue();
	}
	
	public String getStartHour(){
		return getSettingValue("Hour Clinc Starts");
	}
	
	public String getSlotLength(){
		return getSettingValue("Length Of Appointment Unit");
	}

}
