package com.jboss.sample.bpm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

import com.jboss.sample.bpm.model.OrderStatus;

@Service(SendNotification.class)
public class SendNotificationBean implements SendNotification {
	private static final Logger LOG = LoggerFactory.getLogger(PersistenceBean.class);

	@Override
	public void send(OrderStatus orderStatus) {
		LOG.info("Sending notification: " + orderStatus);
	}
}
