package com.tongyy.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ContainerChecker {
	@Autowired
	SimpleMessageListenerContainer  queueContainer;
	@Autowired
	SimpleMessageListenerContainer  topicContainer;
	
    @Scheduled(fixedRate = 60000)
    public void reportContainerStatus() throws Exception {
    	if(!queueContainer.isActive() || !topicContainer.isActive())
    		throw new Exception("STATUS ERROR");
    }
}
