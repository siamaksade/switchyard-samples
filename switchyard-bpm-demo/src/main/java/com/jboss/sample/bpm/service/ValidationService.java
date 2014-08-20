package com.jboss.sample.bpm.service;

import com.jboss.sample.bpm.model.Order;

public interface ValidationService {
	public void validate(Order order);
	
	public void sendOrderStatusResponse();
}
