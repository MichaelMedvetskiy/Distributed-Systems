package Seminar02;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ResponseHandler {

	public static void response(DataInputStream inFromServer) throws IOException, ClassNotFoundException {
		int contentSize = inFromServer.readInt();
    	
		if(contentSize == 1) {
			byte[] message = new byte[contentSize];
			inFromServer.readFully(message);
			
			switch(message[0]) {
				case (2):
					System.out.println("Ping test is successful");break;
				case (6):
					System.out.println("New user created");break;
				case (7):
					System.out.println("Login is successful");break;
				case (16):
					System.out.println("Message sent");
				default:
					ServerErrorHandler.errorHandler(message);
			}
		}else {
			
			
			ResponseHandler.readResponse(inFromServer, contentSize);
		
		}
	}
	
	
	public static void readResponse(DataInputStream inFromServer, int contentSize) throws IOException {
		byte[] message = new byte[contentSize];
		inFromServer.readFully(message);
		
		String[] str = null;
		try {
			str = Client.deserialize(message, 0, String[].class);
			
	    	for(int i = 0; i < str.length; i++) {
	    		System.out.print(str[i] + " ");
	    	}
		} catch (StreamCorruptedException e ) {
			Arrays.toString(message);
			String test = new String(message, Charset.forName("UTF-8"));
			System.out.println(test);
		} catch (ClassNotFoundException e) {
			
		} 
	}
}
