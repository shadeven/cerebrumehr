package com.oreon.cerebrum.web.action.charts;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.charts.ChartItem;

public class ChartItemActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ChartItem> {

	ChartItemAction chartItemAction = new ChartItemAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ChartItem> getAction() {
		return chartItemAction;
	}

}
