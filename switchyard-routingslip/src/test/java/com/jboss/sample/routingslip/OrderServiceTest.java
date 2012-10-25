package com.jboss.sample.routingslip;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.test.Invoker;
import org.switchyard.test.ServiceOperation;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.mixins.CDIMixIn;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(mixins = CDIMixIn.class, config = SwitchYardTestCaseConfig.SWITCHYARD_XML)
public class OrderServiceTest {

	@ServiceOperation("OrderService")
	private Invoker service;

	@Test
	public void testOrder() throws Exception {
		String result = service.operation("order").sendInOut("1").getContent(String.class);
		assertEquals("1:Inventory:BackOffice:Suplier", result);

		result = service.operation("order").sendInOut("2").getContent(String.class);
		assertEquals("2:Suplier:BackOffice:Inventory", result);

		result = service.operation("order").sendInOut("3").getContent(String.class);
		assertEquals("3:BackOffice:Suplier:Inventory", result);
	}
}
