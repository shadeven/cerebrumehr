package com.oreon.cerebrum.web.action.charts;

import com.oreon.cerebrum.charts.AppliedChart;

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
public abstract class AppliedChartActionBase extends BaseAction<AppliedChart>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long appliedChartId;

	@In(create = true, value = "patientAction")
	com.oreon.cerebrum.web.action.patient.PatientAction patientAction;

	@In(create = true, value = "chartAction")
	com.oreon.cerebrum.web.action.charts.ChartAction chartAction;

	public void setAppliedChartId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setAppliedChartIdForModalDlg(Long id) {
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

	public void setChartId(Long id) {

		if (id != null && id > 0)
			getInstance().setChart(chartAction.loadFromId(id));

	}

	public Long getChartId() {
		if (getInstance().getChart() != null)
			return getInstance().getChart().getId();
		return 0L;
	}

	public Long getAppliedChartId() {
		return (Long) getId();
	}

	public AppliedChart getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(AppliedChart t) {
		this.instance = t;
		loadAssociations();
	}

	public AppliedChart getAppliedChart() {
		return (AppliedChart) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('appliedChart', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('appliedChart', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected AppliedChart createInstance() {
		AppliedChart instance = super.createInstance();

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

		com.oreon.cerebrum.charts.Chart chart = chartAction
				.getDefinedInstance();
		if (chart != null && isNew()) {
			getInstance().setChart(chart);
		}

	}

	public AppliedChart getDefinedInstance() {
		return (AppliedChart) (isIdDefined() ? getInstance() : null);
	}

	public void setAppliedChart(AppliedChart t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setAppliedChartId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<AppliedChart> getEntityClass() {
		return AppliedChart.class;
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

		if (instance.getChart() != null) {
			criteria = criteria.add(Restrictions.eq("chart.id", instance
					.getChart().getId()));
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

		if (getInstance().getChart() != null) {
			chartAction.setInstance(getInstance().getChart());

			chartAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewAppliedChart() {
		load(currentEntityId);
		return "viewAppliedChart";
	}

}
