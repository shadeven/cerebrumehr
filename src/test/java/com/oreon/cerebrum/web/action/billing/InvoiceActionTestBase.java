package com.oreon.cerebrum.web.action.billing;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.*;
import org.hibernate.annotations.Filter;

import org.testng.annotations.BeforeClass;
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
