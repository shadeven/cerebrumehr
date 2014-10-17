package com.oreon.cerebrum.web.action.billing;

import org.junit.Test;

import org.witchcraft.seam.action.BaseAction;
import com.oreon.cerebrum.billing.Service;

public class ServiceActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Service> {

	ServiceAction serviceAction = new ServiceAction();

	@Override
	public BaseAction<Service> getAction() {
		return serviceAction;
	}

}
