package com.oreon.cerebrum.web.action.charts;

import com.oreon.cerebrum.charts.ChartProcedure;

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

//
public abstract class ChartProcedureActionBase
		extends
			BaseAction<ChartProcedure> implements java.io.Serializable {

	@RequestParameter
	protected Long chartProcedureId;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	@In(create = true, value = "chartItemAction")
	com.oreon.cerebrum.web.action.charts.ChartItemAction chartItemAction;

	public void setChartProcedureId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setChartProcedureIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setPatientId(Long id) {

		if (id != null && id > 0)
			getInstance().setPatient(patientAction.loadFromId(id));

	}

	public Long getPatientId() {
		if (getInstance().getPatient() != null)
			return getInstance().getPatient().getId();
		return 0L;
	}

	public void setChartItemId(Long id) {

		if (id != null && id > 0)
			getInstance().setChartItem(chartItemAction.loadFromId(id));

	}

	public Long getChartItemId() {
		if (getInstance().getChartItem() != null)
			return getInstance().getChartItem().getId();
		return 0L;
	}

	public Long getChartProcedureId() {
		return (Long) getId();
	}

	public ChartProcedure getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(ChartProcedure t) {
		this.instance = t;
		loadAssociations();
	}

	public ChartProcedure getChartProcedure() {
		return (ChartProcedure) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('chartProcedure', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('chartProcedure', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected ChartProcedure createInstance() {
		ChartProcedure instance = super.createInstance();

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

		com.oreon.cerebrum.patient.Patient patient = patientAction
				.getDefinedInstance();
		if (patient != null && isNew()) {
			getInstance().setPatient(patient);
		}

		com.oreon.cerebrum.charts.ChartItem chartItem = chartItemAction
				.getDefinedInstance();
		if (chartItem != null && isNew()) {
			getInstance().setChartItem(chartItem);
		}

	}

	public ChartProcedure getDefinedInstance() {
		return (ChartProcedure) (isIdDefined() ? getInstance() : null);
	}

	public void setChartProcedure(ChartProcedure t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setChartProcedureId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<ChartProcedure> getEntityClass() {
		return ChartProcedure.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getPatient() != null) {
			criteria = criteria.add(Restrictions.eq("patient.id", instance
					.getPatient().getId()));
		}

		if (instance.getChartItem() != null) {
			criteria = criteria.add(Restrictions.eq("chartItem.id", instance
					.getChartItem().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getPatient() != null) {
			patientAction.setInstance(getInstance().getPatient());

			patientAction.loadAssociations();

		}

		if (getInstance().getChartItem() != null) {
			chartItemAction.setInstance(getInstance().getChartItem());

			chartItemAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewChartProcedure() {
		load(currentEntityId);
		return "viewChartProcedure";
	}

}
