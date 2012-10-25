package com.jboss.sample.routingslip;

import org.switchyard.component.bean.Service;

@Service(InventoryService.class)
public class InventoryServiceBean implements InventoryService {
	@Override
	public String inStore(String productId) {
		return productId + ":Inventory";
	}
}
