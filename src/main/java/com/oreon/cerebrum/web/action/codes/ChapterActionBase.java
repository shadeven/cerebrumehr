package com.oreon.cerebrum.web.action.codes;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.annotations.web.RequestParameter;

import com.oreon.cerebrum.codes.Chapter;
import com.oreon.cerebrum.codes.Section;

//
public abstract class ChapterActionBase
		extends
			com.oreon.cerebrum.web.action.codes.AbstractCodeAction<Chapter>
		implements
			java.io.Serializable {

	@RequestParameter
	protected Long chapterId;

	public void setChapterId(Long id) {
		setEntityId(id);
	}

	/** for modal dlg we need to load associaitons regardless of postback
	 * @param id
	 */
	public void setChapterIdForModalDlg(Long id) {
		setEntityIdForModalDlg(id);
	}

	public Long getChapterId() {
		return (Long) getId();
	}

	public Chapter getEntity() {
		return instance;
	}

	//@Override
	public void setEntity(Chapter t) {
		this.instance = t;
		loadAssociations();
	}

	public Chapter getChapter() {
		return (Chapter) getInstance();
	}

	@Override
	@Restrict("#{s:hasPermission('chapter', 'edit')}")
	public String save(boolean endconv) {
		return super.save(endconv);
	}

	@Override
	@Restrict("#{s:hasPermission('chapter', 'delete')}")
	public void archiveById() {
		super.archiveById();
	}

	@Override
	protected Chapter createInstance() {
		Chapter instance = super.createInstance();

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

	public Chapter getDefinedInstance() {
		return (Chapter) (isIdDefined() ? getInstance() : null);
	}

	public void setChapter(Chapter t) {
		this.instance = t;
		if (getInstance() != null && t != null) {
			setChapterId(t.getId());
			loadAssociations();
		}
	}

	@Override
	public Class<Chapter> getEntityClass() {
		return Chapter.class;
	}

	/** This function is responsible for loading associations for the given entity e.g. when viewing an order, we load the customer so
	 * that customer can be shown on the customer tab within viewOrder.xhtml
	 * @see org.witchcraft.seam.action.BaseAction#loadAssociations()
	 */
	public void loadAssociations() {

		initListSections();

		addDefaultAssociations();
	}

	public void updateAssociations() {

	}

	protected List<com.oreon.cerebrum.codes.Section> listSections = new ArrayList<com.oreon.cerebrum.codes.Section>();

	void initListSections() {

		if (listSections.isEmpty())
			listSections.addAll(getInstance().getSections());

	}

	public List<com.oreon.cerebrum.codes.Section> getListSections() {

		prePopulateListSections();
		return listSections;
	}

	public void prePopulateListSections() {
	}

	public void setListSections(
			List<com.oreon.cerebrum.codes.Section> listSections) {
		this.listSections = listSections;
	}

	public void deleteSections(int index) {
		listSections.remove(index);
	}

	@Begin(join = true)
	public void addSections() {

		initListSections();
		Section sections = new Section();

		sections.setChapter(getInstance());

		getListSections().add(sections);

	}

	public void updateComposedAssociations() {

		if (listSections != null) {

			java.util.Set<Section> items = getInstance().getSections();
			for (Section item : items) {
				if (!listSections.contains(item))
					getEntityManager().remove(item);
			}

			for (Section item : listSections) {
				item.setChapter(getInstance());
			}

			getInstance().getSections().clear();
			getInstance().getSections().addAll(listSections);
		}
	}

	public void clearLists() {
		listSections.clear();

	}

	public String viewChapter() {
		load(currentEntityId);
		return "viewChapter";
	}

}