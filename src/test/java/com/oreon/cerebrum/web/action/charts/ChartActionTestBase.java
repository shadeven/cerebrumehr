package com.oreon.cerebrum.web.action.charts;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.charts.Chart;

public class ChartActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Chart> {

	ChartAction chartAction = new ChartAction();

	@Override
	public BaseAction<Chart> getAction() {
		return chartAction;
	}

}
