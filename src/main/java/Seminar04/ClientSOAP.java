package Seminar04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.ws.WebServiceException;
import client.ChatServer;
import client.IChatServer;


public class ClientSOAP {
	
    public static boolean isConnected = true;

	public ClientSOAP() {
		String input;
		
		try {
			URL url = new URL("http://localhost:153/chat");
    		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    		
    	    ChatServer serverWrapper = new ChatServer(url); 
    	    IChatServer serverProxy = serverWrapper.getChatServerProxy();
    	    
    	    
    		while(isConnected){
        		
        		System.out.println("Enter your command : ");
        		input = inFromUser.readLine();
        		
        		System.out.println(commandHandler.checkCommand(input, serverProxy));
        		
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (WebServiceException e) {
			e.printStackTrace();
        	System.out.println("Server is currently offline");
        }
		
	}

}
