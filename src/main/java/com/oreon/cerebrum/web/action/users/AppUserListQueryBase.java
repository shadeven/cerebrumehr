package com.oreon.cerebrum.web.action.users;

import com.oreon.cerebrum.users.AppUser;

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

import com.oreon.cerebrum.users.AppUser;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AppUserListQueryBase extends BaseQuery<AppUser, Long> {

	private static final String EJBQL = "select appUser from AppUser appUser";

	protected AppUser appUser = new AppUser();

	public AppUserListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public AppUser getAppUser() {
		return appUser;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	private com.oreon.cerebrum.users.AppRole appRolesToSearch;

	public void setAppRolesToSearch(
			com.oreon.cerebrum.users.AppRole appRoleToSearch) {
		this.appRolesToSearch = appRoleToSearch;
	}

	public com.oreon.cerebrum.users.AppRole getAppRolesToSearch() {
		return appRolesToSearch;
	}

	@Override
	public AppUser getInstance() {
		return getAppUser();
	}

	@Override
	//@Restrict("#{s:hasPermission('appUser', 'view')}")
	public List<AppUser> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<AppUser> getEntityClass() {
		return AppUser.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"appUser.id = #{appUserList.appUser.id}",

			"appUser.archived = #{appUserList.appUser.archived}",

			"lower(appUser.userName) like concat(lower(#{appUserList.appUser.userName}),'%')",

			"appUser.enabled = #{appUserList.appUser.enabled}",

			"#{appUserList.appRolesToSearch} in elements(appUser.appRoles)",

			"appUser.dateCreated <= #{appUserList.dateCreatedRange.end}",
			"appUser.dateCreated >= #{appUserList.dateCreatedRange.begin}",};

	@Observer("archivedAppUser")
	public void onArchive() {
		refresh();
	}

}
