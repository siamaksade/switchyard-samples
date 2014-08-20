package com.jboss.sample.bpm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.switchyard.Context;
import org.switchyard.Property;

public class Utils {
	private static final Logger LOG = LoggerFactory.getLogger(Utils.class);
	
	public static void dump(Context context) {
		for (Property property : context.getProperties()) {
			LOG.info("context property> " + dump(property));
		}
	}

	private static String dump(Property property) {
		return "Property[name = " + property.getName() 
				+ ", value= " + property.getValue()
				+ ", scope= " + property.getScope()
				+ "]";
	}
}
