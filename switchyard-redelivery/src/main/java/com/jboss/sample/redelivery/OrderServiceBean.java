package com.jboss.sample.redelivery;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

@Service(OrderService.class)
public class OrderServiceBean implements OrderService {
	@Inject
	@Reference
	private ServiceRoute serviceRoute;
	
	@Override
	public String order(String productId) {
		return serviceRoute.route(productId);
	}
}
