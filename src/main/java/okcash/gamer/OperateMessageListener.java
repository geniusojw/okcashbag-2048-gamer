package okcash.gamer;

import java.awt.AWTException;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import okcash.gamer.ok2048.Ok2048Gamer;

public class OperateMessageListener implements ExceptionListener {

	private static final String COMMAND_QUEUE = "command-queue";

	public void listen() {

		// Create a ConnectionFactory
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		try {
			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();
			connection.setExceptionListener(this);


			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue(COMMAND_QUEUE);

			// Create a MessageConsumer from the Session to the Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);

			boolean loop = true;
			while (loop) {
				// Wait for a message
				Message message = consumer.receive(2000);
				
				if (message != null) {
					System.out.println("Received: " + message);
					
					if (message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						String commandType = textMessage.getText();
						
						System.out.println("Command Type: " + commandType);
						
						if ("exit".equals(commandType)) {
							System.out.println("Exit");
							loop = false;
							
						} else if ("ok2048".equals(commandType)) {
							System.out.println("OK 2048");
							Ok2048Gamer.start();
							
						} else {
							System.out.println("Not a Registrated Command Type");
						}
						
					} else {
						System.out.println("Not a TextMessage");
					}
				} else {
					System.out.print(".");
				}
			}

			
	        consumer.close();
	        session.close();
	        connection.close();
	        
		} catch (JMSException jmsException) {
			jmsException.printStackTrace();
			
		} catch (AWTException awtException) {
			awtException.printStackTrace();
		}
	}

	@Override
	public synchronized void onException(JMSException exception) {
		System.out.println("JMS Exception occured.  Shutting down client.");
	}
}
