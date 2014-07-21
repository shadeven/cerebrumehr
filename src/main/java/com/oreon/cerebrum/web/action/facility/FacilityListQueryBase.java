package com.oreon.cerebrum.web.action.facility;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.facility.Facility;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FacilityListQueryBase extends BaseQuery<Facility, Long> {

	private static final String EJBQL = "select facility from Facility facility";

	protected Facility facility = new Facility();

	public FacilityListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Facility getFacility() {
		return facility;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Facility getInstance() {
		return getFacility();
	}

	@Override
	//@Restrict("#{s:hasPermission('facility', 'view')}")
	public List<Facility> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Facility> getEntityClass() {
		return Facility.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"facility.id = #{facilityList.facility.id}",

			"facility.archived = #{facilityList.facility.archived}",

			"lower(facility.name) like concat(lower(#{facilityList.facility.name}),'%')",

			"facility.isResidential = #{facilityList.facility.isResidential}",

			"facility.dateCreated <= #{facilityList.dateCreatedRange.end}",
			"facility.dateCreated >= #{facilityList.dateCreatedRange.begin}",};

	@Observer("archivedFacility")
	public void onArchive() {
		refresh();
	}

}
