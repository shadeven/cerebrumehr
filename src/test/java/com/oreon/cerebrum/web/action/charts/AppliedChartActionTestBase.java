package com.oreon.cerebrum.web.action.charts;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.charts.AppliedChart;

public class AppliedChartActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AppliedChart> {

	AppliedChartAction appliedChartAction = new AppliedChartAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AppliedChart> getAction() {
		return appliedChartAction;
	}

}
