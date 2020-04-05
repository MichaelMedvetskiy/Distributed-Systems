package Seminar03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import lpi.server.rmi.IServer;
import lpi.server.rmi.IServer.ArgumentException;
import lpi.server.rmi.IServer.FileInfo;
import lpi.server.rmi.IServer.LoginException;
import lpi.server.rmi.IServer.Message;
import lpi.server.rmi.IServer.ServerException;


public class commandHandler {
	static String response;
	static String[] userList;
	static String session_ID;
	
public static String checkCommand(String input, IServer proxy) throws IOException {
		
		switch(commandHandler.getCommandID(input)) {
		case ("exit"):
			try {

			proxy.exit(session_ID);
			ClientRMI.isConnected = false;
			return "Client stopped";
			} catch(ServerException e) {
				e.printStackTrace();
			} catch(ArgumentException e) {
				e.printStackTrace();
			} catch(RemoteException e) {
				e.printStackTrace();
			}
		case ("ping"): 
			try {
			proxy.ping();
			return response = "Ping successful";
			} catch(RemoteException e) {
				e.printStackTrace();
			}
		case ("echo"):
			return response = proxy.echo(commandHandler.getContent(input));
		case ("login"):
			try {
			response = proxy.login(commandHandler.getLogAndPass(input)[0], commandHandler.getLogAndPass(input)[1]);
			session_ID = response;
			break;
			} catch(ServerException e) {
				e.printStackTrace();
			} catch(ArgumentException e) {
				e.printStackTrace();
			} catch(LoginException e) {
				e.printStackTrace();
			}catch(RemoteException e) {
				e.printStackTrace();
			}
		case ("list"):
			try {
			userList = proxy.listUsers(session_ID);
			commandHandler.showUsers(userList);
			break;
			} catch(ServerException e) {
				e.printStackTrace();
			} catch(ArgumentException e) {
				e.printStackTrace();
			}
		case ("msg"):
			try {
				Message msg = new Message(commandHandler.getFirstPart(input), commandHandler.getSecondPart(input));
				proxy.sendMessage(session_ID, msg);break;
			} catch(ServerException e) {
				e.printStackTrace();
			} catch(ArgumentException e) {
				e.printStackTrace();
			} catch(RemoteException e) {
				e.printStackTrace();
			}
		case ("rmsg"):
			try {
				Message rmsg = proxy.receiveMessage(session_ID);
				response = rmsg.getMessage();
				return rmsg.getSender()+ ": " + response;
			} catch(ServerException e) {
				e.printStackTrace();
			} catch(ArgumentException e) {
				e.printStackTrace();
			} catch(RemoteException e) {
				e.printStackTrace();
			}
		case ("file"):
			try {
				File file = new File(commandHandler.getSecondPart(input));
				FileInfo sFile = new FileInfo(commandHandler.getFirstPart(input), file);
				proxy.sendFile(session_ID, sFile);break;
			} catch(ServerException e) {
				e.printStackTrace();
			} catch(ArgumentException e) {
				e.printStackTrace();
			} catch(RemoteException e) {
				e.printStackTrace();
			}
		case ("rfile"):
			try {
				File file = new File(commandHandler.getContent(input));
				FileInfo rFile = proxy.receiveFile(session_ID);
				rFile.saveFileTo(file); break;
				
			} catch(ServerException e) {
				e.printStackTrace();
			} catch(ArgumentException e) {
				e.printStackTrace();
			} catch(RemoteException e) {
				e.printStackTrace();
			}
			
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
public static String[] getLogAndPass(String inFromUser) {
	String[] logPass;
	String temp;
    String delimeter = " "; 
    temp = inFromUser.substring(inFromUser.indexOf(" "), inFromUser.length()).trim();
    logPass = temp.split(delimeter);
    
    return logPass;
}
public static void showUsers(String[] userList) {
	System.out.println("Currently online Users: ");
	for(int i = 0; i < userList.length; i++) {
		
		System.out.print(userList[i]);
		if(i == userList.length-1) {
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
public static String getFirstPart(String input){
	String temp,finalString;
	
    temp = input.substring(input.indexOf(" "), input.length()).trim();
    finalString = temp.substring(0, temp.indexOf(" ")).trim();
    return finalString;
}
}
