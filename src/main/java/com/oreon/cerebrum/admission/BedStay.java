
/**
 * This file can be modified
 *
 */

package com.oreon.cerebrum.admission;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

@Entity
@Table(name = "bedstay")
@Filters({@Filter(name = "archiveFilterDef"), @Filter(name = "tenantFilterDef")})
@Cache(usage = CacheConcurrencyStrategy.NONE)
@XmlRootElement
public class BedStay extends BedStayBase implements java.io.Serializable {
	private static final long serialVersionUID = 1866971223L;
}
