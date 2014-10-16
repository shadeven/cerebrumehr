package com.oreon.cerebrum.web.action.charts;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.charts.ChartProcedure;

public class ChartProcedureActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<ChartProcedure> {

	ChartProcedureAction chartProcedureAction = new ChartProcedureAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<ChartProcedure> getAction() {
		return chartProcedureAction;
	}

}
