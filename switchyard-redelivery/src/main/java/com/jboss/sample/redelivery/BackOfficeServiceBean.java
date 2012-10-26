package com.jboss.sample.redelivery;

import org.apache.log4j.Logger;
import org.switchyard.component.bean.Service;

@Service(BackOfficeService.class)
public class BackOfficeServiceBean implements BackOfficeService {
	private final Logger LOG = Logger.getLogger(BackOfficeServiceBean.class);
	
	@Override
	public String getStatus(String productId) {
		LOG.info("# backoffice for product " + productId);
		return productId + ":BackOffice";
	}
}
