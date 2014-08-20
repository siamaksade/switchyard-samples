package com.jboss.sample.bpm.util;

import org.switchyard.Context;
import org.switchyard.Scope;
import org.switchyard.component.bpm.BPMConstants;
import org.switchyard.component.resteasy.composer.RESTEasyBindingData;
import org.switchyard.component.resteasy.composer.RESTEasyContextMapper;

import com.jboss.sample.bpm.model.Order;
import com.jboss.sample.bpm.model.OrderStatus;

public class ProductIDCorrelationContextMapper extends RESTEasyContextMapper {

	@Override
	public void mapFrom(RESTEasyBindingData source, Context ctx)
			throws Exception {
		super.mapFrom(source, ctx);

		// set correlation id
		Object param = source.getParameters()[0];
		String correlationKey = null;
		
		if (param instanceof Order) {
			correlationKey = ((Order)param).getOrderId();
		} else if (param instanceof OrderStatus) {
			correlationKey = ((OrderStatus)param).getOrderId();
		}

		if (correlationKey != null) {
			ctx.setProperty(BPMConstants.CORRELATION_KEY_PROPERTY, correlationKey, Scope.EXCHANGE);
		}
	}
}
