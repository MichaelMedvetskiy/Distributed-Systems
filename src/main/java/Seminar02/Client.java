package Seminar02;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.Signature;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Client {
	
    private static final String SERVER_HOST = "0.0.0.0";
    private static final int SERVER_PORT = 151;
    private static boolean available = false;
    
    private Socket clientSocket, updateSocket;
    private Scanner inMessage;
    private PrintWriter outMessage;
    
    public  Client()  {
    	String input;
    	
    	try {
  
    		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
    		clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
    		
    		final DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
    		DataInputStream inFromServer = new DataInputStream(clientSocket.getInputStream());
    		
    		while(!clientSocket.isClosed()){
    		
    		System.out.println("Enter your command : ");
    		input = inFromUser.readLine();
    		
    		commandCheck.checkCommand(input, outToServer, inFromServer, this);
			
    		try {
				ResponseHandler.response(inFromServer);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	
				
    		}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		
    		
    	}
    
    public void sendEcho(String strings, DataOutputStream outToServer) throws IOException {
    	
    	byte [] echoArr = new byte[strings.length()+1];
    	byte [] tempArr = new byte[strings.length()];
    	
		tempArr = strings.getBytes();

    	System.arraycopy(tempArr, 0, echoArr, 1, tempArr.length);
    	echoArr[0] = 3;
    	
    	outToServer.writeInt(echoArr.length);
		outToServer.write(echoArr);
		outToServer.flush();
    	}	
    
    public String getCommandID(String inFromUser) {
    	String[] subStr;
	    String delimeter = " "; 
	    subStr = inFromUser.split(delimeter); 
	    
	    return subStr[0];
    }
    public String getContent(String inFromUser) {
    	String[] subStr;
	    String delimeter = " "; 
	    subStr = inFromUser.split(delimeter);
	    
	    return subStr[1];
    }
    public String[] getLogAndPass(String inFromUser) {
    	String[] logPass;
    	String temp;
	    String delimeter = " "; 
	    temp = inFromUser.substring(inFromUser.indexOf(" "), inFromUser.length()).trim();
	    logPass = temp.split(delimeter);
	    
	    return logPass;
    }
    public void sendPing(DataOutputStream outToServer) throws IOException {
    	byte[] pingArr = new byte[] {1};
    	outToServer.writeInt(pingArr.length);
		outToServer.write(pingArr);
		outToServer.flush();
    
    }   
    private byte[] serialize(Object object, byte ID) throws IOException {
		try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream(); ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {
			objectStream.writeObject(object);
			
			byte[] sendArr = new byte[byteStream.toByteArray().length+1];
			sendArr[0] = ID;
			System.arraycopy(byteStream.toByteArray(), 0, sendArr, 1, byteStream.toByteArray().length);
			
			return sendArr;
			
		}
	}
    @SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] data, int offset, Class<T> clazz) throws ClassNotFoundException, IOException {
		try (ByteArrayInputStream stream = new ByteArrayInputStream(data, offset, data.length - offset);
				ObjectInputStream objectStream = new ObjectInputStream(stream)) {
			return (T) objectStream.readObject();
		}
	}
    public void sendLogin(String[] strings, final DataOutputStream outToServer, byte ID) throws IOException, ClassNotFoundException {
    	
    	outToServer.writeInt(serialize(strings, ID).length);
		outToServer.write(serialize(strings, ID));
		outToServer.flush();
	
    }
    public void sendList(DataOutputStream outToServer) throws IOException {
    	byte[] pingArr = new byte[] {10};
    	outToServer.writeInt(pingArr.length);
		outToServer.write(pingArr);
		outToServer.flush();
    }
    public void exit(DataOutputStream outToServer, DataInputStream inFromServer) throws IOException {
    	
            System.out.println("Client initialize connections suicide ...");
                
            inFromServer.close();
            outToServer.close();

            clientSocket.close();
           
    }
    public void sendMessage(String[] strings, DataOutputStream outToServer, byte ID) throws IOException {
    	outToServer.writeInt(serialize(strings, ID).length);
		outToServer.write(serialize(strings, ID));
		outToServer.flush();
    }
    public void receiveMessage(DataOutputStream updateFromServer, Client client,String input) throws IOException {
    	byte[] pingArr = new byte[] {25};
    	updateFromServer.writeInt(pingArr.length);
    	updateFromServer.write(pingArr);
    	updateFromServer.flush();
    }
	public String[] getSecondPart(String input) {
	String temp,finalString;
	String[] strArr = new String[] {""};
	
    temp = input.substring(input.indexOf(" "), input.length()).trim();
    strArr[0] = temp.substring(temp.indexOf(" "), temp.length()).trim();
	
    return strArr;
}
}



