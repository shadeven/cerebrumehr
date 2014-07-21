package com.oreon.cerebrum.web.action.patient;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.patient.Allergen;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class AllergenListQueryBase extends BaseQuery<Allergen, Long> {

	private static final String EJBQL = "select allergen from Allergen allergen";

	protected Allergen allergen = new Allergen();

	public AllergenListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Allergen getAllergen() {
		return allergen;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Allergen getInstance() {
		return getAllergen();
	}

	@Override
	//@Restrict("#{s:hasPermission('allergen', 'view')}")
	public List<Allergen> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Allergen> getEntityClass() {
		return Allergen.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private static final String[] RESTRICTIONS = {
			"allergen.id = #{allergenList.allergen.id}",

			"allergen.archived = #{allergenList.allergen.archived}",

			"lower(allergen.name) like concat(lower(#{allergenList.allergen.name}),'%')",

			"allergen.dateCreated <= #{allergenList.dateCreatedRange.end}",
			"allergen.dateCreated >= #{allergenList.dateCreatedRange.begin}",};

	@Observer("archivedAllergen")
	public void onArchive() {
		refresh();
	}

}
