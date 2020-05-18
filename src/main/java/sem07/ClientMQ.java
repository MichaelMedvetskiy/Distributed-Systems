package sem07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.Arrays;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import org.apache.activemq.ActiveMQConnectionFactory;

public class ClientMQ {
	 public static boolean isConnected = true;
	 
    public ClientMQ() {
    	
    	String input;
    	
        
        try {
        	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            // Producer
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    "tcp://0.0.0.0:61616");
            
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            Session sessionRM = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
           
            javax.jms.Destination replyQueue = session.createTemporaryQueue();
            MessageConsumer consumer = session.createConsumer(replyQueue);
            
           
            while(isConnected){
        		
        		System.out.println("Enter your command : ");
        		input = inFromUser.readLine();
        		
        		commandHandler.checkCommand(input, session, sessionRM,replyQueue, consumer);
        		
        	}
            session.close();
            connection.close();
            
            
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
    }
}