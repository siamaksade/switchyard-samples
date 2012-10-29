package com.jboss.sample.routingslip;

import static org.switchyard.component.camel.SwitchYardRouteDefinition.addNamespaceParameter;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.language.HeaderExpression;
import org.switchyard.component.camel.Route;

@Route(ServiceRoute.class)
public class ServiceRouteBuilder extends RouteBuilder {
	private static final String NAMESPACE = "urn:com.jboss.sample.routingslip:switchyard-routingslip:0.0.1-SNAPSHOT";
	
	public void configure() {
		from("switchyard://ServiceRoute")
			.log("Received message for 'ServiceRoute' : ${body}")
			.setHeader("mySlip").method(ComputeSlip.class)
			.routingSlip(new HeaderExpression("mySlip"));
	}

	public static class ComputeSlip {
		public String compute(String body) {

			if ("1".equals(body)) {
				return addNamespaceParameter("switchyard://InventoryService", NAMESPACE) + "," + 
						addNamespaceParameter("switchyard://BackOfficeService", NAMESPACE) + "," + 
						addNamespaceParameter("switchyard://SupplierService", NAMESPACE);
				
			} else if ("2".equals(body)) {
				return addNamespaceParameter("switchyard://SupplierService", NAMESPACE) + "," + 
						addNamespaceParameter("switchyard://BackOfficeService", NAMESPACE) + "," + 
						addNamespaceParameter("switchyard://InventoryService", NAMESPACE);
			}
			
			return addNamespaceParameter("switchyard://BackOfficeService", NAMESPACE) + "," + 
					addNamespaceParameter("switchyard://SupplierService", NAMESPACE) + "," + 
					addNamespaceParameter("switchyard://InventoryService", NAMESPACE);
		}
	}
}
