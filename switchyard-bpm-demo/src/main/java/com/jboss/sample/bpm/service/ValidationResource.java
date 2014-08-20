package com.jboss.sample.bpm.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.jboss.sample.bpm.model.Order;

@Path("/")
public interface ValidationResource {
    @POST
    @Path("/xml")
    @Consumes({ "application/xml"})
    public void validate(Order order);
}
