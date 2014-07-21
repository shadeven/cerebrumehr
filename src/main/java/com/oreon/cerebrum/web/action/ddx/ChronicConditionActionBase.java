package com.oreon.cerebrum.web.action.ddx;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.charts.Chart;
import com.oreon.cerebrum.ddx.ChronicCondition;

//
public abstract class ChronicConditionActionBase
		extends
			BaseAction<ChronicCondition> implements java.io.Serializable {

	@RequestParameter
	protected Long chronicConditionId;

	@In(create = true, value = "chartAction")
	com.oreon.cerebrum.web.action.charts.ChartAction chartsAction;

	public void setChronicConditionId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setChronicConditionIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getChronicConditionId() {
		return (Long) getId();
	}

	public ChronicCondition getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(ChronicCondition t) {
		this.instance = t;
		loadAssociations();
	}

	public ChronicCondition getChronicCondition() {
		return (ChronicCondition) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('chronicCondition', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('chronicCondition', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected ChronicCondition createInstance() {
		ChronicCondition instance = super.createInstance();

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

	public ChronicCondition getDefinedInstance() {
		return (ChronicCondition) (isIdDefined() ? getInstance() : null);
	}

	public void setChronicCondition(ChronicCondition t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setChronicConditionId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<ChronicCondition> getEntityClass() {
		return ChronicCondition.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListCharts();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.charts.Chart> listCharts = new ArrayList<com.oreon.cerebrum.charts.Chart>();

	void initListCharts() {

		if (listCharts.isEmpty())
			listCharts.addAll(getInstance().getCharts());

	}

	public List<com.oreon.cerebrum.charts.Chart> getListCharts() {

		prePopulateListCharts();
		return listCharts;
	}

	public void prePopulateListCharts() {
	}

	public void setListCharts(List<com.oreon.cerebrum.charts.Chart> listCharts) {
		this.listCharts = listCharts;
	}

	public void deleteCharts(int index) {
		listCharts.remove(index);
	}

	@Begin(join = true)
	public void addCharts() {

		initListCharts();
		Chart charts = new Chart();

		charts.setChronicCondition(getInstance());

		getListCharts().add(charts);

	}

	public void updateComposedAssociations() {

		if (listCharts != null) {

			java.util.Set<Chart> items = getInstance().getCharts();
			for (Chart item : items) {
				if (!listCharts.contains(item))
					getEntityManager().remove(item);
			}

			for (Chart item : listCharts) {
				item.setChronicCondition(getInstance());
			}

			getInstance().getCharts().clear();
			getInstance().getCharts().addAll(listCharts);
		}
	}

	public void clearLists() {
		listCharts.clear();

	}

	public String viewChronicCondition() {
		load(currentEntityId);
		return "viewChronicCondition";
	}

}
