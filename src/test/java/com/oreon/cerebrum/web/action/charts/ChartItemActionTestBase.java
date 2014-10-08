package com.oreon.cerebrum.web.action.charts;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
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
