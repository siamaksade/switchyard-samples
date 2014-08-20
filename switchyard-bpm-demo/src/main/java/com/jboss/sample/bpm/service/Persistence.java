package com.jboss.sample.bpm.service;

import com.jboss.sample.bpm.model.Order;
import com.jboss.sample.bpm.model.OrderStatus;

public interface Persistence {
    boolean persist(Order order);

	boolean updateStatus(OrderStatus orderStatus);
}
