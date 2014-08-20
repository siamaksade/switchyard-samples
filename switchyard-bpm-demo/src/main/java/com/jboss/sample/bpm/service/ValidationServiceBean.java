package com.jboss.sample.bpm.service;

import static java.text.MessageFormat.format;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.switchyard.component.bean.Service;

import com.jboss.sample.bpm.model.Order;

@Service(ValidationService.class)
public class ValidationServiceBean implements ValidationService {
	private static final String CALLBACK = "http://localhost:8080/rest-binding/order/update";

	private static final Logger LOG = Logger.getLogger(ValidationServiceBean.class.getName());

	private static final int VALIDATION_DELAY = 10000;

	private static final List<ValidationObject> scheduledValidations = new LinkedList<ValidationServiceBean.ValidationObject>();

	private static final String RESPONSE_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
			+ "<ns2:orderStatus xmlns:ns2=\"urn:com.jboss.sample.bpm:demo-validation-service:1.0\">"
			+ "<orderId>{0}</orderId>"
			+ "<status>{1}</status>"
			+ "</ns2:orderStatus>";

	private Random random = new Random(System.currentTimeMillis());

	@Override
	public void validate(Order order) {
		scheduledValidations.add(new ValidationObject(order));
	}

	@Override
	public void sendOrderStatusResponse() {
		for (Iterator<ValidationObject> iterator = scheduledValidations
				.iterator(); iterator.hasNext();) {
			ValidationObject validObj = iterator.next();

			if (System.currentTimeMillis() - validObj.getTimestamp() > VALIDATION_DELAY) {
				
				try {
					sendResponse(validObj);
					iterator.remove();
				} catch (Throwable t) {
					LOG.log(Level.SEVERE, "Failed to send response. Rescheduling for retry", t);
					validObj.reschedule();
				}
			}
		}
	}

	private void sendResponse(ValidationObject validObj) {
		try {
			ClientRequest request = new ClientRequest(CALLBACK);
			request.accept("application/xml");
			String responseXml = format(RESPONSE_XML, validObj.getOrderId(), random.nextBoolean() ? "VALID" : "INVALID");
			LOG.info("RESPONSE: " + responseXml);
			request.body("application/xml", responseXml);
			ClientResponse<String> response = request.post(String.class);

			if (response.getStatus() != 200 && response.getStatus() != 204) {
				LOG.severe("Failed request with HTTP status for order "
							+ validObj.getOrderId() + ": " + response.getStatus());
			} else {
				LOG.info("Callback for order " + validObj.getOrderId() + " invoked.");
			}

		} catch (Exception e) {
			LOG.severe(e.getMessage());
		}
		
	}

	private static class ValidationObject {
		private Order order;
		private long timestamp;

		public ValidationObject(Order order) {
			this.order = order;
			this.timestamp = System.currentTimeMillis();
		}

		public void reschedule() {
			this.timestamp = System.currentTimeMillis();
		}

		public long getTimestamp() {
			return timestamp;
		}

		public Order getOrder() {
			return order;
		}

		public String getOrderId() {
			return order == null ? null : order.getOrderId();
		}
	}
}
