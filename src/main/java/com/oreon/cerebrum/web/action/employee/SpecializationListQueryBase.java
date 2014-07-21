package com.oreon.cerebrum.web.action.employee;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.employee.Specialization;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class SpecializationListQueryBase
		extends
			BaseQuery<Specialization, Long> {

	private static final String EJBQL = "select specialization from Specialization specialization";

	protected Specialization specialization = new Specialization();

	public SpecializationListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Specialization getInstance() {
		return getSpecialization();
	}

	@Override
	//@Restrict("#{s:hasPermission('specialization', 'view')}")
	public List<Specialization> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Specialization> getEntityClass() {
		return Specialization.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"specialization.id = #{specializationList.specialization.id}",

			"specialization.archived = #{specializationList.specialization.archived}",

			"lower(specialization.name) like concat(lower(#{specializationList.specialization.name}),'%')",

			"specialization.dateCreated <= #{specializationList.dateCreatedRange.end}",
			"specialization.dateCreated >= #{specializationList.dateCreatedRange.begin}",};

	@Observer("archivedSpecialization")
	public void onArchive() {
		refresh();
	}

}
