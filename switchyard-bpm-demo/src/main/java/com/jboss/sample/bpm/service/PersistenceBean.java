package com.jboss.sample.bpm.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

import com.jboss.sample.bpm.model.Order;
import com.jboss.sample.bpm.model.OrderStatus;

@Service(Persistence.class) 
public class PersistenceBean implements Persistence {
	private static final Logger LOG = LoggerFactory.getLogger(PersistenceBean.class);

	@Inject
	private OrderDAO orderDAO;
	
    @Override
    public boolean persist(Order order) {
    	LOG.info("Storing order " + order);
    	orderDAO.persist(order);
    	return true;
    }
    
    @Override
	public boolean updateStatus(OrderStatus orderStatus) {
    	orderDAO.updateStatus(orderStatus);
    	return true;
    }
}
