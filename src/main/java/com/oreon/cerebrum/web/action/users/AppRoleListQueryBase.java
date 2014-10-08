package com.oreon.cerebrum.web.action.users;

import com.oreon.cerebrum.users.AppRole;

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

import com.oreon.cerebrum.users.AppRole;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AppRoleListQueryBase extends BaseQuery<AppRole, Long> {

	private static final String EJBQL = "select appRole from AppRole appRole";

	protected AppRole appRole = new AppRole();

	public AppRoleListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public AppRole getAppRole() {
		return appRole;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	private com.oreon.cerebrum.users.AppUser appUsersToSearch;

	public void setAppUsersToSearch(
			com.oreon.cerebrum.users.AppUser appUserToSearch) {
		this.appUsersToSearch = appUserToSearch;
	}

	public com.oreon.cerebrum.users.AppUser getAppUsersToSearch() {
		return appUsersToSearch;
	}

	@Override
	public AppRole getInstance() {
		return getAppRole();
	}

	@Override
	//@Restrict("#{s:hasPermission('appRole', 'view')}")
	public List<AppRole> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<AppRole> getEntityClass() {
		return AppRole.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"appRole.id = #{appRoleList.appRole.id}",

			"appRole.archived = #{appRoleList.appRole.archived}",

			"lower(appRole.name) like concat(lower(#{appRoleList.appRole.name}),'%')",

			"#{appRoleList.appUsersToSearch} in elements(appRole.appUsers)",

			"appRole.dateCreated <= #{appRoleList.dateCreatedRange.end}",
			"appRole.dateCreated >= #{appRoleList.dateCreatedRange.begin}",};

	@Observer("archivedAppRole")
	public void onArchive() {
		refresh();
	}

}
