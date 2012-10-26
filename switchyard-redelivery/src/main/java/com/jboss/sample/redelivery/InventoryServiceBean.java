package com.jboss.sample.redelivery;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.switchyard.component.bean.Service;

@Service(InventoryService.class)
public class InventoryServiceBean implements InventoryService {
	private final Logger LOG = Logger.getLogger(InventoryServiceBean.class);
	
	private static final AtomicInteger counter = new AtomicInteger();
	
	@Override
	public String inStore(String productId) {
		LOG.info("# inventory check for product " + productId + " [" + counter.addAndGet(1) + "]");
		throw new RuntimeException("OOpS!");
	}
}
