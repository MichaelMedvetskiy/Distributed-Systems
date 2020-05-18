package sem07;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

public class receiveHandler {

	static void responesExit(MessageConsumer consumer) throws IOException, JMSException {
		Message msg = consumer.receive(5000);
		if(msg instanceof Message) { 
            System.out.println("Closing connection"); 
            ClientMQ.isConnected=false;
        } else { 
            	throw new IOException("Unexpected message type: " + msg.getClass());
        }
	}
	static void responesPing(MessageConsumer consumer) throws JMSException, IOException {
		Message msg = consumer.receive(5000);
		if(msg instanceof Message) { 
            System.out.println("Ping success"); 
        } else { 
            	throw new IOException("Unexpected message type: " + msg.getClass());
        }
	}
	static void responesEcho(MessageConsumer consumer) throws IOException, JMSException {
		String content = null;
		TextMessage msg = (TextMessage) consumer.receive(5000);
		if(msg instanceof TextMessage) { 
			content = ((TextMessage)msg).getText();
			System.out.println(content);
        } else { 
            	throw new IOException("Unexpected message type: " + msg.getClass());
        }
	}
	static void responesLogin(MessageConsumer consumer) throws JMSException, IOException {
		MapMessage msg = (MapMessage) consumer.receive(5000);
		if(msg.getBoolean("success")) {
			System.out.println(msg.getString("message"));
		} else {
			throw new IOException("Unexpected message type: " + msg.getClass());
		}
	}
	static void responesSMessage(MessageConsumer consumer) throws JMSException, IOException {
		MapMessage msg = (MapMessage) consumer.receive(5000);
		if(msg.getBoolean("success")) {
			System.out.println(msg.getString("message"));
		} else {
			throw new IOException("Unexpected message type: " + msg.getClass());
		}
	}
	static void responesList(MessageConsumer consumer) throws JMSException, IOException {
		ObjectMessage msg = (ObjectMessage) consumer.receive(5000);
		Serializable obj = ((ObjectMessage)msg).getObject();
		if (obj != null && obj instanceof String[]) {
		String[] users = (String[])obj;
		System.out.println(Arrays.toString(users) + "; ");
		} else { 
		throw new IOException("Unexpected content: "+ obj);
		}
	}
	static void responesRMessage(MessageConsumer consumer) throws JMSException, IOException {
		consumer.setMessageListener(new MessageReceiver());
	}
	static void responesSFile() {
		//Unimplemented method
	}
	static void responesRFile() {
		//Unimplemented method
	}

	
}
