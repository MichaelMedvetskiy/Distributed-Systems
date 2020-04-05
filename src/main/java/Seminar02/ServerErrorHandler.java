package Seminar02;

public class ServerErrorHandler {

	public static void errorHandler(byte[] message){
		switch(message[0]) {
			case (100):
				System.out.println("Server error");break;
			case (101):
				System.out.println("Wrong Size");break;
			case (102):
				System.out.println("Serialization error");break;
			case (103):
				System.out.println("Unknown command");break;
			case (104):
				System.out.println("Incorrect parameters");break;
			case (110):
				System.out.println("Wrong password");break;
			case (112):
				System.out.println("Not logged in");break;
			case (113):
				System.out.println("Sending failed");break;
		}
	}

}
