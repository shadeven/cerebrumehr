package com.oreon.cerebrum.web.action.codes;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.codes.Chapter;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class ChapterListQueryBase extends BaseQuery<Chapter, Long> {

	private static final String EJBQL = "select chapter from Chapter chapter";

	protected Chapter chapter = new Chapter();

	public ChapterListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Chapter getChapter() {
		return chapter;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Chapter getInstance() {
		return getChapter();
	}

	@Override
	//@Restrict("#{s:hasPermission('chapter', 'view')}")
	public List<Chapter> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Chapter> getEntityClass() {
		return Chapter.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"chapter.id = #{chapterList.chapter.id}",

			"chapter.archived = #{chapterList.chapter.archived}",

			"lower(chapter.name) like concat(lower(#{chapterList.chapter.name}),'%')",

			"lower(chapter.description) like concat(lower(#{chapterList.chapter.description}),'%')",

			"chapter.dateCreated <= #{chapterList.dateCreatedRange.end}",
			"chapter.dateCreated >= #{chapterList.dateCreatedRange.begin}",};

	@Observer("archivedChapter")
	public void onArchive() {
		refresh();
	}

}
