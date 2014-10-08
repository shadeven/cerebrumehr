package com.oreon.cerebrum.web.action.facility;

import com.oreon.cerebrum.facility.Facility;

import org.witchcraft.seam.action.BaseAction;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;
import org.jboss.seam.security.Identity;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import org.witchcraft.base.entity.FileAttachment;

import org.apache.commons.io.FileUtils;

import org.primefaces.model.DualListModel;

import org.witchcraft.seam.action.BaseAction;
import org.witchcraft.base.entity.BaseEntity;

import com.oreon.cerebrum.facility.Ward;

//
public abstract class FacilityActionBase extends BaseAction<Facility>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long facilityId;

	public void setFacilityId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setFacilityIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getFacilityId() {
		return (Long) getId();
	}

	public Facility getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Facility t) {
		this.instance = t;
		loadAssociations();
	}

	public Facility getFacility() {
		return (Facility) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('facility', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('facility', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Facility createInstance() {
		Facility instance = super.createInstance();

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

	public Facility getDefinedInstance() {
		return (Facility) (isIdDefined() ? getInstance() : null);
	}

	public void setFacility(Facility t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setFacilityId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Facility> getEntityClass() {
		return Facility.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListWards();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.facility.Ward> listWards = new ArrayList<com.oreon.cerebrum.facility.Ward>();

	void initListWards() {

		if (listWards.isEmpty())
			listWards.addAll(getInstance().getWards());

	}

	public List<com.oreon.cerebrum.facility.Ward> getListWards() {

		prePopulateListWards();
		return listWards;
	}

	public void prePopulateListWards() {
	}

	public void setListWards(List<com.oreon.cerebrum.facility.Ward> listWards) {
		this.listWards = listWards;
	}

	public void deleteWards(int index) {
		listWards.remove(index);
	}

	@Begin(join = true)
	public void addWards() {

		initListWards();
		Ward wards = new Ward();

		wards.setFacility(getInstance());

		getListWards().add(wards);

	}

	public void updateComposedAssociations() {

		if (listWards != null) {

			java.util.Set<Ward> items = getInstance().getWards();
			for (Ward item : items) {
				if (!listWards.contains(item))
					getEntityManager().remove(item);
			}

			for (Ward item : listWards) {
				item.setFacility(getInstance());
			}

			getInstance().getWards().clear();
			getInstance().getWards().addAll(listWards);
		}
	}

	public void clearLists() {
		listWards.clear();

	}

	public String viewFacility() {
		load(currentEntityId);
		return "viewFacility";
	}

}
