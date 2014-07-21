package com.oreon.cerebrum.web.action.prescription;

import java.util.List;

import org.jboss.seam.annotations.Observer;
import org.witchcraft.base.entity.Range;
import org.witchcraft.seam.action.BaseQuery;

import com.oreon.cerebrum.prescription.Frequency;

/**
 * 
 * @author WitchcraftMDA Seam Cartridge - 
 *
 */
public abstract class FrequencyListQueryBase extends BaseQuery<Frequency, Long> {

	private static final String EJBQL = "select frequency from Frequency frequency";

	protected Frequency frequency = new Frequency();

	public FrequencyListQueryBase() {
		super();
		setOrderColumn("id");
		setOrderDirection("desc");
	}

	public Frequency getFrequency() {
		return frequency;
	}

	@Override
	protected String getql() {
		return EJBQL;
	}

	@Override
	public Frequency getInstance() {
		return getFrequency();
	}

	@Override
	//@Restrict("#{s:hasPermission('frequency', 'view')}")
	public List<Frequency> getResultList() {
		return super.getResultList();
	}

	@Override
	public Class<Frequency> getEntityClass() {
		return Frequency.class;
	}

	@Override
	public String[] getEntityRestrictions() {
		return RESTRICTIONS;
	}

	private Range<Integer> qtyPerDayRange = new Range<Integer>();

	public Range<Integer> getQtyPerDayRange() {
		return qtyPerDayRange;
	}
	public void setQtyPerDay(Range<Integer> qtyPerDayRange) {
		this.qtyPerDayRange = qtyPerDayRange;
	}

	private static final String[] RESTRICTIONS = {
			"frequency.id = #{frequencyList.frequency.id}",

			"frequency.archived = #{frequencyList.frequency.archived}",

			"lower(frequency.name) like concat(lower(#{frequencyList.frequency.name}),'%')",

			"frequency.qtyPerDay >= #{frequencyList.qtyPerDayRange.begin}",
			"frequency.qtyPerDay <= #{frequencyList.qtyPerDayRange.end}",

			"frequency.dateCreated <= #{frequencyList.dateCreatedRange.end}",
			"frequency.dateCreated >= #{frequencyList.dateCreatedRange.begin}",};

	@Observer("archivedFrequency")
	public void onArchive() {
		refresh();
	}

}
