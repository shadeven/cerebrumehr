package com.oreon.cerebrum.users.dto;

import java.util.HashSet;
import java.util.Set;

import org.witchcraft.base.entity.BaseEntity;

public class AppUserDto extends BaseEntity {

	protected String userName;

	protected String password;

	protected Boolean enabled;

	private Set<AppRoleDto> appRolesDto = new HashSet<AppRoleDto>();

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setAppRoles(Set<AppRoleDto> appRolesDto) {
		this.appRolesDto = appRolesDto;
	}
	public Set<AppRoleDto> getAppRoles() {
		return appRolesDto;
	}

}
