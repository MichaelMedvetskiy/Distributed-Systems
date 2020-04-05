package Seminar02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.TimerTask;

public class commandCheck {
	
	

	public static void checkCommand(String input, DataOutputStream outToServer, DataInputStream inFromServer, Client client) throws IOException {
		
		switch(client.getCommandID(input)) {
		case ("exit"):
			client.exit(outToServer, inFromServer);break;
		case ("ping"): 
			client.sendPing(outToServer);break;
		case ("echo"):
			client.sendEcho(client.getContent(input), outToServer);break;
		case ("login"):
		try {
			client.sendLogin(client.getLogAndPass(input), outToServer, (byte) 5);break;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		case ("list"):
			client.sendList(outToServer); break;
		case ("msg"):
			client.sendMessage(client.getSecondPart(input), outToServer, (byte) 15);
		case ("rmsg"):
			client.receiveMessage(outToServer, client, input);
	}
		
	}
	

}
