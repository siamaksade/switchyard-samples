package com.jboss.sample.routingslip;

import org.switchyard.component.bean.Service;

@Service(BackOfficeService.class)
public class BackOfficeServiceBean implements BackOfficeService {
	@Override
	public String getStatus(String productId) {
		return productId + ":BackOffice";
	}
}
