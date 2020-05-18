package sem07;

import java.io.IOException;

import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

public class commandHandler {
	
	private static final String DESTINATION_NAME = "chat.messages";
	
public static void checkCommand(String input, Session session, Session sessionRM, javax.jms.Destination replyQueue, MessageConsumer consumer) throws IOException {
	
	try {
	
	switch(commandHandler.getCommandID(input)) {
	case ("exit"):
		MessageHandler.sendMessage(session, "chat.exit", replyQueue);
		receiveHandler.responesExit(consumer); break;
	case ("ping"): 
		MessageHandler.sendMessage(session, "chat.diag.ping", replyQueue);
		receiveHandler.responesPing(consumer);break;
	case ("echo"):
		MessageHandler.sendMessage(session, "chat.diag.echo", input, replyQueue);
		//receiveHandler.receive(consumer, "chat.diag.echo");break;
	    receiveHandler.responesEcho(consumer);break;
	case ("login"):
		javax.jms.MapMessage requestL = session.createMapMessage();
		requestL.setString("login", commandHandler.getLog(input));
		requestL.setString("password", commandHandler.getPas(input));
		MessageHandler.sendMessage(session, "chat.login", requestL, replyQueue);
		receiveHandler.responesLogin(consumer);break;
	case ("list"):
		MessageHandler.sendMessage(session, "chat.listUsers", replyQueue);
	    receiveHandler.responesList(consumer);break;
	case ("msg"):
		javax.jms.MapMessage requestM = session.createMapMessage();
		requestM.setString("receiver", commandHandler.getLog(input));
		requestM.setString("message", commandHandler.getPas(input));
		MessageHandler.sendMessage(session, "chat.sendMessage", requestM, replyQueue);
		receiveHandler.responesSMessage(consumer);break;
	case ("rmsg"):
		Destination queue = sessionRM.createQueue("chat.messages");
	    MessageConsumer consumerRM = sessionRM.createConsumer(queue);
	    MessageHandler.sendMessage(session, "chat.messages", replyQueue);
		receiveHandler.responesRMessage(consumerRM);break;
	case ("file"):
		System.out.println("This method is unimplemented");break;
	case ("rfile"):
		System.out.println("This method is unimplemented");break;
	
}
	
} catch (Exception e) {
	e.printStackTrace();
}
}
private static String getPas(String input) {
	String[] logPass;
	String temp;
    String delimeter = " "; 
    temp = input.substring(input.indexOf(" "), input.length()).trim();
    logPass = temp.split(delimeter);
    
    return logPass[1];
}
private static String getLog(String input) {
	String[] logPass;
	String temp;
    String delimeter = " "; 
    temp = input.substring(input.indexOf(" "), input.length()).trim();
    logPass = temp.split(delimeter);
    
    return logPass[0];
}
public static String getCommandID(String inFromUser) {
String[] subStr;
String delimeter = " "; 
subStr = inFromUser.split(delimeter); 

return subStr[0];
}
public static String getReceiver(String input){
	String temp,finalString;
	
    temp = input.substring(input.indexOf(" "), input.length()).trim();
    finalString = temp.substring(0, temp.length()).trim();
    return finalString;
}
public static String getMessageContent(String input){
	String temp,finalString;
	
    temp = input.substring(input.indexOf(" ")+1, input.length());
    finalString = temp.substring(1, temp.length()).trim();
    return finalString;
}

}

