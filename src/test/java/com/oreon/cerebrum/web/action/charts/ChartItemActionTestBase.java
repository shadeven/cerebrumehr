package com.oreon.cerebrum.web.action.charts;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.charts.ChartItem;

public class ChartItemActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ChartItem> {

	ChartItemAction chartItemAction = new ChartItemAction();

	@Override
	public BaseAction<ChartItem> getAction() {
		return chartItemAction;
	}

}
