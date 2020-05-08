package clientREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class ClientRest {
	
	public static boolean isConnected = true;

	public ClientRest() {
		
		String input;
		
		try {
			javax.ws.rs.client.Client client = javax.ws.rs.client.ClientBuilder.newClient();
    		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    		
    		while(isConnected){
        		
        		System.out.println("Enter your command : ");
        		input = inFromUser.readLine();
        		System.out.println(commandHandler.checkCommand(input, client));
        		
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }
	}
}

