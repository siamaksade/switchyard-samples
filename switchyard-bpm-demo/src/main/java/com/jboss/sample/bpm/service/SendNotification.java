package com.jboss.sample.bpm.service;

import com.jboss.sample.bpm.model.OrderStatus;

public interface SendNotification {
	void send(OrderStatus orderStatus);
}
