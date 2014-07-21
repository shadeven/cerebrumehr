
/**
 * This file can be modified
 *
 */

package com.oreon.cerebrum.settings;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

@Entity
@Table(name = "settinggroup")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class SettingGroup extends SettingGroupBase
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 1752499712L;
}
