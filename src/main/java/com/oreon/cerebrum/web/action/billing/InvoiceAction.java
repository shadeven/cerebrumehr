
	
package com.oreon.cerebrum.web.action.billing;
	

import org.jboss.seam.annotations.Name;

import com.oreon.cerebrum.billing.InvoiceItem;

	
//@Scope(ScopeType.CONVERSATION)
@Name("invoiceAction")
public class InvoiceAction extends InvoiceActionBase implements java.io.Serializable{
	
	
	
	public void applyPrice(InvoiceItem item){
		item.setAppliedPrice(item.getService().getPrice());	
	}
	
	public void setInvoiceId(Long id) {

		if (id == 0) {
			clearInstance();
			clearLists();
			loadAssociations();
			return;
		}
		refresh();

		setId(id);
		// invoice = loadInstance();
		if (!isPostBack())
			loadAssociations();
	}

	
}
	