package com.oreon.cerebrum.users.dto;

import java.util.HashSet;
import java.util.Set;

import org.witchcraft.base.entity.BaseEntity;

public class AppRoleDto extends BaseEntity {

	protected String name;

	private Set<AppUserDto> appUsersDto = new HashSet<AppUserDto>();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAppUsers(Set<AppUserDto> appUsersDto) {
		this.appUsersDto = appUsersDto;
	}
	public Set<AppUserDto> getAppUsers() {
		return appUsersDto;
	}

}
