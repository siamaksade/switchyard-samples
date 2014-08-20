package com.example.switchyard.wiretrap.threading;

import org.apache.camel.builder.RouteBuilder;

public class CamelServiceRoute extends RouteBuilder {
	public void configure() {
		from("switchyard://MyService")
			.wireTap("switchyard://CommonService")
				.executorService(Utils.newThreadPool(this))
			.log("Received message for 'RouteService' : ${body}");
	}
}
