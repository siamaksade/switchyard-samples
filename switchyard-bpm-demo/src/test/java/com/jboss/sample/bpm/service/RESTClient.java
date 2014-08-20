package com.jboss.sample.bpm.service;

import java.text.MessageFormat;
import java.util.Random;

import org.switchyard.component.test.mixins.http.HTTPMixIn;

public class RESTClient {
    private static String BASE_URL = "http://localhost:8080/rest-binding";
    
    
    private static final String ORDER_REQUEST = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><ns2:order xmlns:ns2=\"urn:com.jboss.sample.bpm:demo-validation-service:1.0\">"
    										+ "<orderId>{0}</orderId>"
    										+ "<itemId>cowbell</itemId>"
    										+ "<quantity>{1}</quantity>"
    										+ "</ns2:order>";
    
    @SuppressWarnings("unused")
	private static final String STATUS_UPDATE = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
    										+ "<ns2:orderStatus xmlns:ns2=\"urn:com.jboss.sample.bpm:demo-validation-service:1.0\">"
    										+ "<orderId>{0}</orderId>"
    										+ "<status>VALID</status>"
    										+ "</ns2:orderStatus>";
    
    @SuppressWarnings("unused")
	public static void main(String[] args) {
    	int count = 1;
    	
        HTTPMixIn httpMixIn = new HTTPMixIn();
        httpMixIn.initialize();
        httpMixIn.setContentType("application/xml");
        String response = null;
        String request = null;
		Random rand = new Random(System.currentTimeMillis());
		String prefix = System.currentTimeMillis() + "";
		for (int i = 1; i <= count; i++) {
			request = MessageFormat.format(ORDER_REQUEST, "order-" + prefix + "-" + i, rand.nextInt(100));
			try {
				Thread.sleep(1000);
				response = httpMixIn.sendString(BASE_URL + "/order/submit", request, HTTPMixIn.HTTP_POST);
			} catch (Exception e) {
			}
		}
		
		System.out.println("Done");
	}
}
