# ibm-mq-spring-boot-jms

This project is used for hands-on workshop.

These demos are  written with SPRING BOOT and JMS. 

You will learn easily about "SPRINGBOOT,JMS,MQAPI,QUEUE,TOPIC,SENDER,RECEIVER,LISTENER" in my project.

## IBM Websphere MQ Server in a Docker container
Just two commands.
```bash
docker run --name mq75 --publish 1415:1415 -idt tony0x00/trial-mq75:v1.0 /bin/bash

docker exec mq75 strmqm ONE.QM
```

## Message Driven POJO, MessageListener Application
### It be configured by Application.yml to set queue and topic destination.
```yaml
servers:
  mq:
    host: 127.0.0.1
    port: 1415
    queue-manager: ONE.QM
    channel: ONE.SVR.CONN
    queue: ONE.QUEUE
    topic: ONE/TOPIC
    timeout: 2000
```
### Run as Java Application, then you will start to listen ONE.QUEUE and ONE/TOPIC.
```java
@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
```
### Run the QueueAndTopicTest and design your scenario.
```java
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





 ```
