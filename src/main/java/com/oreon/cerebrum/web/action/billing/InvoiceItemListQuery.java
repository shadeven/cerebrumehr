package com.oreon.cerebrum.web.action.billing;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("invoiceItemList")
@Scope(ScopeType.CONVERSATION)
public class InvoiceItemListQuery extends InvoiceItemListQueryBase
		implements
			java.io.Serializable {

}
