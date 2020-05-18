package sem07;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessageHandler {
	
	public static void sendMessage(Session session, String serverQueue ,String input, javax.jms.Destination replyQueue){
	
		try {
			Queue queue = session.createQueue(serverQueue);
			MessageProducer producer = session.createProducer(queue);
			TextMessage msgS = (TextMessage) session.createTextMessage(MessageHandler.getContent(input));
			msgS.setJMSReplyTo(replyQueue);
            producer.send(msgS);
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
		
	}
	public static void sendMessage(Session session, String serverQueue ,MapMessage input, javax.jms.Destination replyQueue){
		
		try {
			Queue queue = session.createQueue(serverQueue);
			MessageProducer producer = session.createProducer(queue);
			
			input.setJMSReplyTo(replyQueue);
            producer.send(input);
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
		
	}

	public static void sendMessage(Session session, String serverQueue , javax.jms.Destination replyQueue){
		//Exit and Ping Method
		try {
			Queue queue = session.createQueue(serverQueue);
			MessageProducer producer = session.createProducer(queue);
			javax.jms.Message msgS = session.createMessage();
			msgS.setJMSReplyTo(replyQueue);
            producer.send(msgS);
		} catch (JMSException e) {
			
			e.printStackTrace();
		}
		
	}
	public static String getContent(String inFromUser) {
		String temp,finalString;
		
	    temp = inFromUser.substring(inFromUser.indexOf(" "), inFromUser.length());
	    finalString = temp.substring(1, temp.length()).trim();
	    return finalString;
	}
}
