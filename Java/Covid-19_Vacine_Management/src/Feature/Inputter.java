package Feature;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Inputter implements Colour {
    public static Scanner sc=new Scanner(System.in);
    
    public static String inputStrId(String msg){
        String data="";
        System.out.print(msg);
        boolean check=true;
        boolean isSpecial=true;
        while(check || isSpecial){
            data = sc.nextLine().trim();
            check = data.isEmpty();
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(data);
            isSpecial = matcher.find();
            
            if(check || isSpecial) {
                if(isSpecial) System.out.println(TEXT_RED+"Contain special character. Please enter again: "+TEXT_RESET);
                if(check) System.out.println(TEXT_RED+"Empty input. Please enter again: "+TEXT_RESET);
            }
        }
        
        return data;
    }
    
    public static String inputPattern(String msg, String pattern){
        String data=null;
        boolean check=true;
        while(check){
            System.out.print(msg);
            data = sc.nextLine().trim().toUpperCase();
            if(!data.matches(pattern))
                System.out.println(TEXT_RED+"Wrong format!!!(Format: INJxxxxxx, x is number)"+TEXT_RESET);
            else check=false;
        }
        return data;
    }
    
    public static String inputStr(String msg){
        String data="";
        System.out.print(msg);
        boolean check=true;
        while(check){
            data = sc.nextLine().trim();
            check = data.isEmpty();
            
            if(check) {
                if(check) System.out.print(TEXT_RED+"Empty input. Please enter again: "+TEXT_RESET);
            }
        }
        
        return data;
    }
    
}
