package com.jboss.sample.routingslip;

import org.switchyard.component.bean.Service;

@Service(SupplierService.class)
public class SupplierServiceBean implements SupplierService {
	@Override
	public String canSupply(String productId) {
		return productId + ":Suplier";
	}
}
