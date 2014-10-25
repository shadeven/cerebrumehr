package com.oreon.cerebrum.web.action.facility;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.facility.Ward;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class WardListQueryBase extends BaseQuery<Ward, Long> {

	private static final String EJBQL = "select ward from Ward ward";

	protected Ward ward = new Ward();

	public WardListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Ward getWard() {
		return ward;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Ward getInstance() {
		return getWard();
	}

	@Override
	//@Restrict("#{s:hasPermission('ward', 'view')}")
	public List<Ward> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Ward> getEntityClass() {
		return Ward.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> maxAgeRange = new Range<Integer>();

	public Range<Integer> getMaxAgeRange() {
		return maxAgeRange;
	}
	public void setMaxAge(Range<Integer> maxAgeRange) {
		this.maxAgeRange = maxAgeRange;
	}

	private static final String[] RESTRICTIONS = {
			"ward.id = #{wardList.ward.id}",

			"ward.archived = #{wardList.ward.archived}",

			"ward.facility.id = #{wardList.ward.facility.id}",

			"lower(ward.name) like concat(lower(#{wardList.ward.name}),'%')",

			"ward.gender = #{wardList.ward.gender}",

			"ward.maxAge >= #{wardList.maxAgeRange.begin}",
			"ward.maxAge <= #{wardList.maxAgeRange.end}",

			"ward.dateCreated <= #{wardList.dateCreatedRange.end}",
			"ward.dateCreated >= #{wardList.dateCreatedRange.begin}",};

	/** 
	 * List of all Wards for the given Facility
	 * @param patient
	 * @return 
	 */
	public List<Ward> getAllWardsByFacility(
			final com.oreon.cerebrum.facility.Facility facility) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		ward.setFacility(facility);
		return getResultListTable();
	}

	public LazyDataModel<Ward> getWardsByFacility(
			final com.oreon.cerebrum.facility.Facility facility) {

		EntityLazyDataModel<Ward, Long> wardLazyDataModel = new EntityLazyDataModel<Ward, Long>(
				this) {

			@Override
			public List<Ward> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				ward.setFacility(facility);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return wardLazyDataModel;

	}

	@Observer("archivedWard")
	public void onArchive() {
		refresh();
	}

	public void setFacilityId(Long id) {
		if (ward.getFacility() == null) {
			ward.setFacility(new com.oreon.cerebrum.facility.Facility());
		}
		ward.getFacility().setId(id);
	}

	public Long getFacilityId() {
		return ward.getFacility() == null ? null : ward.getFacility().getId();
	}

}
