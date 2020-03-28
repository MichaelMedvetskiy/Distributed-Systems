package Seminar01;

import java.util.Scanner;

public class terminalInterpreter {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please, enter command: ");
        
        String input = in.nextLine();
        
        terminalInterpreter.parser(input);
             	
	}
		private static int tabCount(String input) {
			int count = 0;
			for(int i = 0; i < input.length(); i++) {
				if(input.charAt(i) == ' ') {
					
					count++;
				} else continue;
			}
			return count;
		}

	public static void parser(String input) {
		
		String[] subStr;
	    String delimeter = " "; 
	    subStr = input.split(delimeter); 
	    System.out.print("You entered = <" + subStr[0] + ">");
	    for(int i = 1; i < subStr.length; i++) {
	    	System.out.print(", paramentrs = [" + subStr[i] + "]");
	    }
	}
}
