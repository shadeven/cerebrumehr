package com.oreon.cerebrum.web.action.facility;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.facility.Room;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class RoomListQueryBase extends BaseQuery<Room, Long> {

	private static final String EJBQL = "select room from Room room";

	protected Room room = new Room();

	public RoomListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Room getRoom() {
		return room;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Room getInstance() {
		return getRoom();
	}

	@Override
	//@Restrict("#{s:hasPermission('room', 'view')}")
	public List<Room> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Room> getEntityClass() {
		return Room.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"room.id = #{roomList.room.id}",

			"room.archived = #{roomList.room.archived}",

			"lower(room.name) like concat(lower(#{roomList.room.name}),'%')",

			"room.roomType.id = #{roomList.room.roomType.id}",

			"room.ward.id = #{roomList.room.ward.id}",

			"room.dateCreated <= #{roomList.dateCreatedRange.end}",
			"room.dateCreated >= #{roomList.dateCreatedRange.begin}",};

	/** 
	 * List of all Rooms for the given Ward
	 * @param patient
	 * @return 
	 */
	public List<Room> getAllRoomsByWard(
			final com.oreon.cerebrum.facility.Ward ward) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		room.setWard(ward);
		return getResultListTable();
	}

	public LazyDataModel<Room> getRoomsByWard(
			final com.oreon.cerebrum.facility.Ward ward) {

		EntityLazyDataModel<Room, Long> roomLazyDataModel = new EntityLazyDataModel<Room, Long>(
				this) {

			@Override
			public List<Room> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, Object> filters) {

				room.setWard(ward);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return roomLazyDataModel;

	}

	@Observer("archivedRoom")
	public void onArchive() {
		refresh();
	}

	public void setRoomTypeId(Long id) {
		if (room.getRoomType() == null) {
			room.setRoomType(new com.oreon.cerebrum.facility.RoomType());
		}
		room.getRoomType().setId(id);
	}

	public Long getRoomTypeId() {
		return room.getRoomType() == null ? null : room.getRoomType().getId();
	}

	public void setWardId(Long id) {
		if (room.getWard() == null) {
			room.setWard(new com.oreon.cerebrum.facility.Ward());
		}
		room.getWard().setId(id);
	}

	public Long getWardId() {
		return room.getWard() == null ? null : room.getWard().getId();
	}

}
