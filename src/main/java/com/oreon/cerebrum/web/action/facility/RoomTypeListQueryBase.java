package com.oreon.cerebrum.web.action.facility;

import com.oreon.cerebrum.facility.RoomType;

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

import com.oreon.cerebrum.facility.RoomType;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class RoomTypeListQueryBase extends BaseQuery<RoomType, Long> {

	private static final String EJBQL = "select roomType from RoomType roomType";

	protected RoomType roomType = new RoomType();

	public RoomTypeListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public RoomType getRoomType() {
		return roomType;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public RoomType getInstance() {
		return getRoomType();
	}

	@Override
	//@Restrict("#{s:hasPermission('roomType', 'view')}")
	public List<RoomType> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<RoomType> getEntityClass() {
		return RoomType.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Double> rateRange = new Range<Double>();

	public Range<Double> getRateRange() {
		return rateRange;
	}
	public void setRate(Range<Double> rateRange) {
		this.rateRange = rateRange;
	}

	private Range<Integer> numberOfBedsRange = new Range<Integer>();

	public Range<Integer> getNumberOfBedsRange() {
		return numberOfBedsRange;
	}
	public void setNumberOfBeds(Range<Integer> numberOfBedsRange) {
		this.numberOfBedsRange = numberOfBedsRange;
	}

	private static final String[] RESTRICTIONS = {
			"roomType.id = #{roomTypeList.roomType.id}",

			"roomType.archived = #{roomTypeList.roomType.archived}",

			"lower(roomType.name) like concat(lower(#{roomTypeList.roomType.name}),'%')",

			"lower(roomType.description) like concat(lower(#{roomTypeList.roomType.description}),'%')",

			"roomType.rate >= #{roomTypeList.rateRange.begin}",
			"roomType.rate <= #{roomTypeList.rateRange.end}",

			"roomType.numberOfBeds >= #{roomTypeList.numberOfBedsRange.begin}",
			"roomType.numberOfBeds <= #{roomTypeList.numberOfBedsRange.end}",

			"roomType.dateCreated <= #{roomTypeList.dateCreatedRange.end}",
			"roomType.dateCreated >= #{roomTypeList.dateCreatedRange.begin}",};

	@Observer("archivedRoomType")
	public void onArchive() {
		refresh();
	}

}
