package com.jboss.sample.bpm.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.jboss.sample.bpm.model.Order;
import com.jboss.sample.bpm.model.OrderAggregate;
import com.jboss.sample.bpm.model.OrderStatus;

@ManagedBean
@ApplicationScoped
public class OrderDAO {
	ConcurrentHashMap<String, OrderAggregate> orders = new ConcurrentHashMap<String, OrderAggregate>();
	
    public void persist(Order order) {
    	orders.put(order.getOrderId(), new OrderAggregate(order));
    }
    
	public void updateStatus(OrderStatus orderStatus) {
    	OrderAggregate orderAggregate = orders.get(orderStatus.getOrderId());
    	
    	if (orderAggregate != null) {
    		orderAggregate.setOrderStatus(orderStatus);
    	}
    }
	
	public Collection<OrderAggregate> getOrders() {
		ArrayList<OrderAggregate> list = new ArrayList<OrderAggregate>(orders.values());
		Collections.sort(list, COMPARATOR) ;
		return list;
	}
	
	private static final Comparator<OrderAggregate> COMPARATOR = new Comparator<OrderAggregate>() {
		@Override
		public int compare(OrderAggregate o1, OrderAggregate o2) {
			return o1.getOrder().getOrderId().compareTo(o2.getOrder().getOrderId());
		}
	};
}
