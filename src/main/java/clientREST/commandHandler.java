package clientREST;

import java.io.IOException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class commandHandler {
	static String response;
	static String[] userList;
	static String session_ID;
	
public static String checkCommand(String input, javax.ws.rs.client.Client client) throws IOException {
		
		switch(commandHandler.getCommandID(input)) {
		case ("exit"):
			client.close();
			ClientRest.isConnected = false;
			return "Client stopped";
		case ("ping"): 
			String responsePing = client.target("http://localhost:8080/chat/server/ping") 
					.request(MediaType.TEXT_PLAIN_TYPE)
					.get(String.class);
			return "Ping successful";
		case ("echo"):
			String	responseEcho = client.target("http://localhost:8080/chat/server/echo") 
			.request(MediaType.TEXT_PLAIN_TYPE) 
			.post(Entity.text(commandHandler.getContent(input)), String.class);
			return responseEcho;	
	}
		return "";		
}
public static String getCommandID(String inFromUser) {
	String[] subStr;
    String delimeter = " "; 
    subStr = inFromUser.split(delimeter); 
    
    return subStr[0];
}
public static String getContent(String inFromUser) {
	String[] subStr;
    String delimeter = " "; 
    subStr = inFromUser.split(delimeter);
    
    return subStr[1];
	}
}
