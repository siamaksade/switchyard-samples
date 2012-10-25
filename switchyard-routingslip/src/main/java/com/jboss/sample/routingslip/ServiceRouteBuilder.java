package com.jboss.sample.routingslip;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.language.HeaderExpression;
import org.switchyard.component.camel.Route;

@Route(ServiceRoute.class)
public class ServiceRouteBuilder extends RouteBuilder {
	public void configure() {
		from("switchyard://ServiceRoute")
			.log("Received message for 'ServiceRoute' : ${body}")
			.setHeader("mySlip").method(ComputeSlip.class)
			.routingSlip(new HeaderExpression("mySlip"));
	}

	public static class ComputeSlip {
		public String compute(String body) {
			String namespace = "urn:com.jboss.sample.routingslip:switchyard-routingslip:0.0.1-SNAPSHOT";

			if ("1".equals(body)) {
				return "switchyard://InventoryService?namespace=" + namespace + "," + 
						"switchyard://BackOfficeService?namespace=" + namespace + "," + 
						"switchyard://SupplierService?namespace=" + namespace + ",";
				
			} else if ("2".equals(body)) {
				return "switchyard://SupplierService?namespace=" + namespace + "," + 
						"switchyard://BackOfficeService?namespace=" + namespace + "," + 
						"switchyard://InventoryService?namespace=" + namespace + ",";
			}
			
			return "switchyard://BackOfficeService?namespace=" + namespace + "," + 
					"switchyard://SupplierService?namespace=" + namespace + "," + 
					"switchyard://InventoryService?namespace=" + namespace + ",";
		}
	}
}
