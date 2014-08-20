package com.jboss.sample.bpm.service;

import javax.xml.bind.JAXBElement;

import org.switchyard.annotations.Transformer;

import com.jboss.sample.bpm.model.Order;
import com.jboss.sample.bpm.model.OrderAck;

public class Transformers {
	@Transformer(from = "java:java.lang.Boolean")
    public OrderAck transform(Boolean status) {
		OrderAck ack = new OrderAck();
		ack.setAccepted(status);
		ack.setOrderId("1");
		return ack;
    }
	
	@Transformer
	public Order transform(JAXBElement<Order> element) {
		return element.getValue();
	}
}
