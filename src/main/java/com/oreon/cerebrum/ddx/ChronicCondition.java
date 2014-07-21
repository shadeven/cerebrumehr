
/**
 * This file can be modified
 *
 */

package com.oreon.cerebrum.ddx;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

@Entity
@Table(name = "chroniccondition")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class ChronicCondition extends ChronicConditionBase
		implements
			java.io.Serializable {
	private static final long serialVersionUID = 1358837921L;
}
