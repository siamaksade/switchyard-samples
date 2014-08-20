package com.example.switchyard.wiretrap.threading;

import java.util.concurrent.ExecutorService;

import org.apache.camel.ThreadPoolRejectedPolicy;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.ThreadPoolProfile;

public class Utils {
	public static ExecutorService newThreadPool(RouteBuilder routeBuilder) {
		ThreadPoolProfile profile = new ThreadPoolProfile();
        profile.setPoolSize(30);
        profile.setMaxPoolSize(30);
        profile.setKeepAliveTime(25L);
        profile.setMaxQueueSize(1000);
        profile.setRejectedPolicy(ThreadPoolRejectedPolicy.Abort);
        
        return routeBuilder.getContext().getExecutorServiceManager().newThreadPool(routeBuilder, "mywiretap", profile);
	}
}
