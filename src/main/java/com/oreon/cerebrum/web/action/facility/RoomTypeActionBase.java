package com.oreon.cerebrum.web.action.facility;

import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.facility.RoomType;

//
public abstract class RoomTypeActionBase extends BaseAction<RoomType>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long roomTypeId;

	public void setRoomTypeId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setRoomTypeIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getRoomTypeId() {
		return (Long) getId();
	}

	public RoomType getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(RoomType t) {
		this.instance = t;
		loadAssociations();
	}

	public RoomType getRoomType() {
		return (RoomType) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('roomType', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('roomType', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected RoomType createInstance() {
		RoomType instance = super.createInstance();

		return instance;
	}

	/**
	 * Adds the contained associations that should be available for a newly created object e.g. 
	 * An order should always have at least one order item . Marked in uml with 1..* multiplicity
	 */
	private void addDefaultAssociations() {
		instance = getInstance();

	}

	public void wire() {
		getInstance();

	}

	public RoomType getDefinedInstance() {
		return (RoomType) (isIdDefined() ? getInstance() : null);
	}

	public void setRoomType(RoomType t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setRoomTypeId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<RoomType> getEntityClass() {
		return RoomType.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewRoomType() {
		load(currentEntityId);
		return "viewRoomType";
	}

}
