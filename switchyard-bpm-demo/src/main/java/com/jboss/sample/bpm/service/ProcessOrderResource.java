package com.jboss.sample.bpm.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.jboss.sample.bpm.model.Order;
import com.jboss.sample.bpm.model.OrderAck;
import com.jboss.sample.bpm.model.OrderStatus;

@Path("/order")
public interface ProcessOrderResource {
    @POST
    @Path("/submit")
    @Consumes({ "application/xml"})
    public OrderAck submitOrder(Order order);
    
    @POST
    @Path("/update")
    @Consumes({ "application/xml"})
    public OrderAck updateStatus(OrderStatus orderStatus);
}
