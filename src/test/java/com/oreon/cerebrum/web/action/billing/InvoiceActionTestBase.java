package com.oreon.cerebrum.web.action.billing;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.billing.Invoice;

public class InvoiceActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<Invoice> {

	InvoiceAction invoiceAction = new InvoiceAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<Invoice> getAction() {
		return invoiceAction;
	}

}
