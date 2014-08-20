package com.example.switchyard.wiretrap.threading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.component.bean.Service;

@Service(CommonService.class)
public class CommonServiceBean implements CommonService {
	private static final Logger LOG = LoggerFactory.getLogger(CommonServiceBean.class);
	
	public void trace(String payload) {
		LOG.info("trace: {}", payload);
	}
}
