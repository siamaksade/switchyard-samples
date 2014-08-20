package com.jboss.sample.bpm.model;

public class OrderAggregate {
	private Order order;
	private OrderStatus orderStatus;
	
	public OrderAggregate(Order order) {
		this.order = order;
	}
	
	public Order getOrder() {
		return order;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}
