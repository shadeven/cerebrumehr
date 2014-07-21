package com.oreon.cerebrum.web.action.codes;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import com.oreon.cerebrum.codes.Code;

//
public abstract class CodeActionBase
		extends
			com.oreon.cerebrum.web.action.codes.AbstractCodeAction<Code>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long codeId;

	@In(create = true, value = "sectionAction")
	com.oreon.cerebrum.web.action.codes.SectionAction sectionAction;

	public void setCodeId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setCodeIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public void setSectionId(Long id) {

		if (id != null && id > 0)
			getInstance().setSection(sectionAction.loadFromId(id));

	}

	public Long getSectionId() {
		if (getInstance().getSection() != null)
			return getInstance().getSection().getId();
		return 0L;
	}

	public Long getCodeId() {
		return (Long) getId();
	}

	public Code getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Code t) {
		this.instance = t;
		loadAssociations();
	}

	public Code getCode() {
		return (Code) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('code', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('code', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Code createInstance() {
		Code instance = super.createInstance();

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

		com.oreon.cerebrum.codes.Section section = sectionAction
				.getDefinedInstance();
		if (section != null && isNew()) {
			getInstance().setSection(section);
		}

	}

	public Code getDefinedInstance() {
		return (Code) (isIdDefined() ? getInstance() : null);
	}

	public void setCode(Code t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setCodeId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Code> getEntityClass() {
		return Code.class;
	}

	/** This function adds associated entities to an example criterion
	 * @see org.witchcraft.model.support.dao.BaseAction#createExampleCriteria(java.lang.Object)
	 */
	@Override
	public void addAssociations(Criteria criteria) {

		if (instance.getSection() != null) {
			criteria = criteria.add(Restrictions.eq("section.id", instance
					.getSection().getId()));
		}

	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		if (getInstance().getSection() != null) {
			sectionAction.setInstance(getInstance().getSection());

			sectionAction.loadAssociations();

		}

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	public void updateComposedAssociations() {
	}

	public void clearLists() {

	}

	public String viewCode() {
		load(currentEntityId);
		return "viewCode";
	}

}
