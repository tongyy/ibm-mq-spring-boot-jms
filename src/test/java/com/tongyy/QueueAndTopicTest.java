package com.tongyy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueAndTopicTest {

    @Autowired
    private JmsTemplate queueTemplate;
    @Autowired
    private JmsTemplate topicTemplate;
    
    private String queue = "ONE.REQ";
    private String topic = "ONE/TOPIC";
    
    @Test
    public void sendMessageToQueue() {
        
    	queueTemplate.convertAndSend(queue, "hello world");
    }
    @Test
    public void receiveMessageFromQueue() {
   
    	queueTemplate.receiveAndConvert(queue);
    }
    
    @Test
    public void sendMessageToQueueWithSelector() {
        
    	queueTemplate.convertAndSend(queue, "hello world",
    			(MessagePostProcessor)(message)-> {
    		{
                message.setStringProperty("name", "tony");
                return message;
            }
        });
    }
    @Test
    public void receiveMessageFromQueueWithSelector() {
   
    	queueTemplate.receiveSelected(queue, "name='tony'");
    }
   
    @Test
    public void publishMessageToTopic() {
    	
    	topicTemplate.convertAndSend(topic, "hello world");
    }



}
