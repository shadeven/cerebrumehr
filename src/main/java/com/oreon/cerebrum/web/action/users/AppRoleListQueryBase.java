package com.oreon.cerebrum.web.action.users;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

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
