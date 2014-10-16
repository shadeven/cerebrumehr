package com.oreon.cerebrum.web.action.charts;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.charts.Chart;

public class ChartActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Chart> {

	ChartAction chartAction = new ChartAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Chart> getAction() {
		return chartAction;
	}

}
