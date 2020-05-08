package Seminar04;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import client.IChatServer;
import client.LoginFault;
import client.Message;
import client.ServerFault;

import client.ArgumentFault;
import client.FileInfo;



public class commandHandler {

	static String response;
	static List<String> userList = new ArrayList<String>();
	static String session_ID;
	
public static String checkCommand(String input, IChatServer serverProxy) throws IOException {
		
		switch(commandHandler.getCommandID(input)) {
		case ("exit"):
			try {

			serverProxy.exit(session_ID);;
			ClientSOAP.isConnected = false;
			return "Client stopped";
			} catch(ServerFault e) {
				e.printStackTrace();
			} catch(ArgumentFault e) {
				e.printStackTrace();
			}
		case ("ping"): 
			serverProxy.ping();
			return response = "Ping successful";
		case ("echo"):
			return response = serverProxy.echo(commandHandler.getEcho(input));
		case ("login"):
			try {
			response = serverProxy.login(commandHandler.getLogAndPass(input)[0], commandHandler.getLogAndPass(input)[1]);
			session_ID = response;
			return "Your session ID - " + session_ID;
			} catch(ServerFault e) {
				e.printStackTrace();
			} catch(ArgumentFault e) {
				e.printStackTrace();
			} catch(LoginFault e) {
				e.printStackTrace();
			}
		case ("list"):
			try {
			userList = serverProxy.listUsers(session_ID);
			commandHandler.showUsers(userList);
			break;
			} catch(ServerFault e) {
				e.printStackTrace();
			} catch(ArgumentFault e) {
				e.printStackTrace();
			}
		case ("msg"):
			try {
				Message msg = new Message();
				msg.setReceiver(commandHandler.getReceiver(input));
				serverProxy.sendMessage(session_ID, msg);break;
			} catch(ServerFault e) {
				e.printStackTrace();
			} catch(ArgumentFault e) {
				e.printStackTrace();
			} 
		case ("rmsg"):
			try {
				Message rmsg = serverProxy.receiveMessage(session_ID);
				response = rmsg.getMessage();
				return response;
			} catch(ServerFault e) {
				e.printStackTrace();
			} catch(ArgumentFault e) {
				e.printStackTrace();
			}  
		default:
			return "Wrong command!"; 
	}
		return "";
}
public static String getCommandID(String inFromUser) {
	String[] subStr;
    String delimeter = " "; 
    subStr = inFromUser.split(delimeter); 
    
    return subStr[0];
}
public static String getEcho(String inFromUser) {
	String[] subStr;
    String delimeter = " "; 
    subStr = inFromUser.split(delimeter);
    
    return subStr[1];
}
public static String[] getLogAndPass(String inFromUser) {
	String[] logPass;
	String temp;
    String delimeter = " "; 
    temp = inFromUser.substring(inFromUser.indexOf(" "), inFromUser.length()).trim();
    logPass = temp.split(delimeter);
    
    return logPass;
}
public static void showUsers(List<String> userList2) {
	System.out.println("Currently online Users: ");
	for(int i = 0; i < userList2.size(); i++) {
		
		System.out.print(userList2.get(i));
		if(i == userList2.size()-1) {
			continue;
		}else System.out.println(",");
		
	}
}
public static String getSecondPart(String input) {
	String temp,finalString;
	
    temp = input.substring(input.indexOf(" "), input.length()).trim();
    finalString = temp.substring(temp.indexOf(" "), temp.length()).trim();
    return finalString;
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
public static byte[] serialize(Object object) throws IOException {
	try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream(); ObjectOutputStream objectStream = new ObjectOutputStream(byteStream)) {
		objectStream.writeObject(object);
		
		byte[] sendArr = new byte[byteStream.toByteArray().length+1];
		//sendArr[0] = ID;
		//System.arraycopy(byteStream.toByteArray(), 0, sendArr, 1, byteStream.toByteArray().length);
		
		return sendArr;
		
	}
}

}
