package com.jboss.sample.redelivery;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.switchyard.component.camel.Route;

@Route(ServiceRoute.class)
public class ServiceRouteBuilder extends RouteBuilder {
//	private static final String namespace = "urn:com.jboss.sample.redelivery:switchyard-redelivery:0.0.1-SNAPSHOT";
	
	public void configure() {
		final RouteDefinition route = from("switchyard://ServiceRoute")
				.log("Received message for 'ServiceRoute' : ${body}");
		
		// retry 5 times with 2 sec delays
		route.onException(Exception.class)
			.to("switchyard://BackOfficeService")
			.redeliveryDelay(1000)
			.maximumRedeliveries(5)
			.handled(true);
		
		route.to("switchyard://InventoryService");
	}
}
