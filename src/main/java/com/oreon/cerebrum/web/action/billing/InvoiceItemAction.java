
	
package com.oreon.cerebrum.web.action.billing;
	

import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.billing.InvoiceItem;

	
//@Scope(ScopeType.CONVERSATION)
@Name("invoiceItemAction")
public class InvoiceItemAction extends InvoiceItemActionBase implements java.io.Serializable{
	
	public void onChangeService(InvoiceItem item){
		item.setAppliedPrice(item.getService().getPrice());	
	}
	
}
	