package com.oreon.cerebrum.web.action.billing;

import org.junit.BeforeClass;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.cerebrum.billing.InvoiceItem;

public class InvoiceItemActionTestBase
		extends
			org.witchcraft.action.test.BaseTest<InvoiceItem> {

	InvoiceItemAction invoiceItemAction = new InvoiceItemAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<InvoiceItem> getAction() {
		return invoiceItemAction;
	}

}
