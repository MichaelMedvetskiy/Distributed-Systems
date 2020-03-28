package Seminar00;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class HelloWorld{

     public static void main(String []args) throws ParseException{
    	 
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        
         
        System.out.println("Hello World!");
        
        Scanner in = new Scanner(System.in);
        
        System.out.print("Please, enter your name: ");
        
        String input = in.nextLine();
        
        System.out.println("Hello, " + input);        
        System.out.println("Your name has " + HelloWorld.lengthName(input) + " letters. " + HelloWorld.lengthName(input) + "! = " + HelloWorld.factor(HelloWorld.lengthName(input)));      
        System.out.print("Please, enter your birth date in format (DD.MM.YYYY): ");
        
        input = in.nextLine();
        
        Date birthDate = dateFormat.parse(input);
        Date currDate = new Date();
        dateFormat.format(currDate);
        
        long milliseconds = currDate.getTime() - birthDate.getTime();
       
        int days = (int) (milliseconds / (24 * 60 * 60 * 1000));
        int year = yearOf(currDate) - yearOf(birthDate);
        System.out.println("Today is " +  dateFormat.format(currDate) + ", you are " + year + " year (" + days + ") old.");
        
        in.close();
     }
     
    public static int lengthName(String name){
         
         return name.length();
         
    }
    static int yearOf(Date date){
         Calendar c = Calendar.getInstance();
         c.setTime(date);
         return c.get(Calendar.YEAR);
     }
    public static int factor(int length){
        int fact = 1;
        for(int i=1;i<=length;i++){    
            fact=fact*i;    
        }    
        return fact;
        }  
    }
     

    
