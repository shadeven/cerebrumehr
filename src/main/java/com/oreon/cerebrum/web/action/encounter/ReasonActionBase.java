package com.oreon.cerebrum.web.action.encounter;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.encounter.Reason;

//
public abstract class ReasonActionBase extends BaseAction<Reason>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long reasonId;

	@In(create = true, value = "codeAction")
	com.oreon.cerebrum.web.action.codes.CodeAction codeAction;

	public void setReasonId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setReasonIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setCodeId(Long id) {

		if (id != null && id > 0)
			getInstance().setCode(codeAction.loadFromId(id));

	}

	public Long getCodeId() {
		if (getInstance().getCode() != null)
			return getInstance().getCode().getId();
		return 0L;
	}

	public Long getReasonId() {
		return (Long) getId();
	}

	public Reason getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Reason t) {
		this.instance = t;
		loadAssociations();
	}

	public Reason getReason() {
		return (Reason) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('reason', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('reason', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Reason createInstance() {
		Reason instance = super.createInstance();

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

		com.oreon.cerebrum.codes.Code code = codeAction.getDefinedInstance();
		if (code != null && isNew()) {
			getInstance().setCode(code);
		}

	}

	public Reason getDefinedInstance() {
		return (Reason) (isIdDefined() ? getInstance() : null);
	}

	public void setReason(Reason t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setReasonId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Reason> getEntityClass() {
		return Reason.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getCode() != null) {
			criteria = criteria.add(Restrictions.eq("code.id", instance
					.getCode().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getCode() != null) {
			codeAction.setInstance(getInstance().getCode());

			codeAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewReason() {
		load(currentEntityId);
		return "viewReason";
	}

}
