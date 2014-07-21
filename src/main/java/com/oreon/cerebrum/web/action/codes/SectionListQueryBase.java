package com.oreon.cerebrum.web.action.codes;

import java.util.List;
import java.util.Map;

import org.jboss.seam.annotations.Observer;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.seam.action.BaseQuery;
import org.witchcraft.seam.action.EntityLazyDataModel;

import com.oreon.cerebrum.codes.Section;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SectionListQueryBase extends BaseQuery<Section, Long> {

	private static final String EJBQL = "select section from Section section";

	protected Section section = new Section();

	public SectionListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Section getSection() {
		return section;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Section getInstance() {
		return getSection();
	}

	@Override
	//@Restrict("#{s:hasPermission('section', 'view')}")
	public List<Section> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Section> getEntityClass() {
		return Section.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"section.id = #{sectionList.section.id}",

			"section.archived = #{sectionList.section.archived}",

			"lower(section.name) like concat(lower(#{sectionList.section.name}),'%')",

			"lower(section.description) like concat(lower(#{sectionList.section.description}),'%')",

			"section.chapter.id = #{sectionList.section.chapter.id}",

			"section.dateCreated <= #{sectionList.dateCreatedRange.end}",
			"section.dateCreated >= #{sectionList.dateCreatedRange.begin}",};

	/** 
	 * List of all Sections for the given Chapter
	 * @param patient
	 * @return 
	 */
	public List<Section> getAllSectionsByChapter(
			final com.oreon.cerebrum.codes.Chapter chapter) {
		setMaxResults(ABSOLUTE_MAX_RECORDS);
		section.setChapter(chapter);
		return getResultListTable();
	}

	public LazyDataModel<Section> getSectionsByChapter(
			final com.oreon.cerebrum.codes.Chapter chapter) {

		EntityLazyDataModel<Section, Long> sectionLazyDataModel = new EntityLazyDataModel<Section, Long>(
				this) {

			@Override
			public List<Section> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				section.setChapter(chapter);
				return super.load(first, pageSize, sortField, sortOrder,
						filters);
			}
		};

		return sectionLazyDataModel;

	}

	@Observer("archivedSection")
	public void onArchive() {
		refresh();
	}

	public void setChapterId(Long id) {
		if (section.getChapter() == null) {
			section.setChapter(new com.oreon.cerebrum.codes.Chapter());
		}
		section.getChapter().setId(id);
	}

	public Long getChapterId() {
		return section.getChapter() == null ? null : section.getChapter()
				.getId();
	}

}
