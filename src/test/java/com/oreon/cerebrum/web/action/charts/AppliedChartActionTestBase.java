package com.oreon.cerebrum.web.action.charts;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.charts.AppliedChart;

public class AppliedChartActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<AppliedChart> {

	AppliedChartAction appliedChartAction = new AppliedChartAction();

	@Override
	public BaseAction<AppliedChart> getAction() {
		return appliedChartAction;
	}

}
