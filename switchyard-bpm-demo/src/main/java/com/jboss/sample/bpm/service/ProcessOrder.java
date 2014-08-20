package com.jboss.sample.bpm.service;

import com.jboss.sample.bpm.model.Order;
import com.jboss.sample.bpm.model.OrderAck;
import com.jboss.sample.bpm.model.OrderStatus;

public interface ProcessOrder {
    OrderAck submitOrder(Order order);
    
    OrderAck updateStatus(OrderStatus orderStatus);
}
