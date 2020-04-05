package Seminar03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import lpi.server.rmi.IServer;


public class ClientRMI {
	
	private static final String SERVER_HOST = "0.0.0.0";
    private static final int SERVER_PORT = 152;
    public static boolean isConnected = true;

	public ClientRMI() throws NotBoundException {
	String input;
    	
    	try {
    		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    		Registry registry = LocateRegistry.getRegistry(SERVER_HOST,SERVER_PORT);
    	    System.out.println(registry);
    	    IServer proxy = (IServer)registry.lookup(IServer.RMI_SERVER_NAME);
    		while(isConnected){
        		
        		System.out.println("Enter your command : ");
        		input = inFromUser.readLine();
        		
        		System.out.println(commandHandler.checkCommand(input, proxy));
        		
        	}
        } catch (IOException e) {
        			e.printStackTrace();
        }
	}
	
}
