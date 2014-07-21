package com.oreon.cerebrum.web.action.billing;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("invoiceList")
@Scope(ScopeType.CONVERSATION)
public class InvoiceListQuery extends InvoiceListQueryBase
		implements
			java.io.Serializable {

}
