package com.oreon.cerebrum.web.action.ddx;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.ddx.DifferentialDx;
import com.oreon.cerebrum.ddx.Finding;

//
public abstract class FindingActionBase extends BaseAction<Finding>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long findingId;

	public void setFindingId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setFindingIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getFindingId() {
		return (Long) getId();
	}

	public Finding getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Finding t) {
		this.instance = t;
		loadAssociations();
	}

	public Finding getFinding() {
		return (Finding) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('finding', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('finding', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Finding createInstance() {
		Finding instance = super.createInstance();

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

	public Finding getDefinedInstance() {
		return (Finding) (isIdDefined() ? getInstance() : null);
	}

	public void setFinding(Finding t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setFindingId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Finding> getEntityClass() {
		return Finding.class;
	}

	public com.oreon.cerebrum.ddx.Finding findByUnqName(String name) {
		return executeSingleResultNamedQuery("finding.findByUnqName", name);
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListDifferentialDxs();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.ddx.DifferentialDx> listDifferentialDxs = new ArrayList<com.oreon.cerebrum.ddx.DifferentialDx>();

	void initListDifferentialDxs() {

		if (listDifferentialDxs.isEmpty())
			listDifferentialDxs.addAll(getInstance().getDifferentialDxs());

	}

	public List<com.oreon.cerebrum.ddx.DifferentialDx> getListDifferentialDxs() {

		prePopulateListDifferentialDxs();
		return listDifferentialDxs;
	}

	public void prePopulateListDifferentialDxs() {
	}

	public void setListDifferentialDxs(
			List<com.oreon.cerebrum.ddx.DifferentialDx> listDifferentialDxs) {
		this.listDifferentialDxs = listDifferentialDxs;
	}

	public void deleteDifferentialDxs(int index) {
		listDifferentialDxs.remove(index);
	}

	@Begin(join = true)
	public void addDifferentialDxs() {

		initListDifferentialDxs();
		DifferentialDx differentialDxs = new DifferentialDx();

		differentialDxs.setFinding(getInstance());

		getListDifferentialDxs().add(differentialDxs);

	}

	public void updateComposedAssociations() {

		if (listDifferentialDxs != null) {

			java.util.Set<DifferentialDx> items = getInstance()
					.getDifferentialDxs();
			for (DifferentialDx item : items) {
				if (!listDifferentialDxs.contains(item))
					getEntityManager().remove(item);
			}

			for (DifferentialDx item : listDifferentialDxs) {
				item.setFinding(getInstance());
			}

			getInstance().getDifferentialDxs().clear();
			getInstance().getDifferentialDxs().addAll(listDifferentialDxs);
		}
	}

	public void clearLists() {
		listDifferentialDxs.clear();

	}

	public String viewFinding() {
		load(currentEntityId);
		return "viewFinding";
	}

}
