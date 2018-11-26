package okcash.gamer;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import okcash.gamer.teamviewer.TeamViewerStarter;

public class OperateMessageListener implements ExceptionListener {

	private static final String COMMAND_QUEUE = "command-queue";
	private Thread playingGame = new Thread();

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

			while (true) {
				// Wait for a message
				Message message = consumer.receive(60000);
				
				if (message == null) {
					System.out.print(".");
					continue;
				}
					
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String command = textMessage.getText();
					System.out.println("command : " + command);
					
					long publishedTime = System.currentTimeMillis() - message.getJMSTimestamp();
					if (publishedTime > 10000) {
						System.out.println("discard old message. publishedTime=" + publishedTime);
						continue;
					}

					if ("remote".equalsIgnoreCase(command)) {
						new TeamViewerStarter().start();
						continue;
						
					} else if ("center".equalsIgnoreCase(command)) {
						new TeamViewerStarter().mouseCenterPress();
						continue;
						
					} else if ("exit".equalsIgnoreCase(command)) {
						break;
					}

					Runnable runnable = GameType.getOkGame(command);
					
					if (runnable == null) {
						System.out.println("Not a Registrated Game");
					} else {
						if (playingGame.isAlive()) {
							playingGame.interrupt();
							System.out.println("playingGame.interrupt()");
						}
						
						playingGame = new Thread(runnable);
						playingGame.start();
					}
					
				} else {
					System.out.println("Not a TextMessage");
				}
			}

			System.out.println("consumer close");
	        consumer.close();
	        
	        System.out.println("session close");
	        session.close();
	        
	        System.out.println("connection close");
	        connection.close();
	        
		} catch (JMSException jmsException) {
			jmsException.printStackTrace();
			
		}
	}

	public synchronized void onException(JMSException exception) {
		System.out.println("JMS Exception occured.  Shutting down client.");
	}
}
