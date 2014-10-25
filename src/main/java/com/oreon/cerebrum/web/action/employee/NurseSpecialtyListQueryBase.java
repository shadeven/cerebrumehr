package com.oreon.cerebrum.web.action.employee;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.employee.NurseSpecialty;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class NurseSpecialtyListQueryBase
		extends
			BaseQuery<NurseSpecialty, Long> {

	private static final String EJBQL = "select nurseSpecialty from NurseSpecialty nurseSpecialty";

	protected NurseSpecialty nurseSpecialty = new NurseSpecialty();

	public NurseSpecialtyListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public NurseSpecialty getNurseSpecialty() {
		return nurseSpecialty;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public NurseSpecialty getInstance() {
		return getNurseSpecialty();
	}

	@Override
	//@Restrict("#{s:hasPermission('nurseSpecialty', 'view')}")
	public List<NurseSpecialty> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<NurseSpecialty> getEntityClass() {
		return NurseSpecialty.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"nurseSpecialty.id = #{nurseSpecialtyList.nurseSpecialty.id}",

			"nurseSpecialty.archived = #{nurseSpecialtyList.nurseSpecialty.archived}",

			"lower(nurseSpecialty.name) like concat(lower(#{nurseSpecialtyList.nurseSpecialty.name}),'%')",

			"nurseSpecialty.dateCreated <= #{nurseSpecialtyList.dateCreatedRange.end}",
			"nurseSpecialty.dateCreated >= #{nurseSpecialtyList.dateCreatedRange.begin}",};

	@Observer("archivedNurseSpecialty")
	public void onArchive() {
		refresh();
	}

}
