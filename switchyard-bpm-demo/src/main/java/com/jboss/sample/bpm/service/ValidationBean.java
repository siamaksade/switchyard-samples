package com.jboss.sample.bpm.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.Context;
import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import com.jboss.sample.bpm.model.Order;

@Service(Validation.class)
public class ValidationBean implements Validation {
	private static final Logger LOG = LoggerFactory.getLogger(ValidationBean.class);
	
	@Inject
	@Reference
	private ValidationResource validationResource;
	
    @Override
    public void validate(Order order) {
    	LOG.info("Validating " + order);
		validationResource.validate(order);
    }
}
