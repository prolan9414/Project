package Main;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class Inputter {
    public static Scanner sc=new Scanner(System.in);
    
    public static String inputStr(String msg){
        String data="";
        System.out.print(msg);
        boolean check=true;
        boolean isSpecial=true;
        while(check || isSpecial){
            data = sc.nextLine().trim();
            check = data.isEmpty();
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]+$");
            Matcher matcher = pattern.matcher(data);
            isSpecial = matcher.find();
            
            if(check || isSpecial) {
                if(isSpecial) System.out.println("Contain special character. Please enter again: ");
                if(check) System.out.println("Empty input");
                System.out.println("Please enter again: ");
            }
        }
        
        return data;
    }
    
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
                if(isSpecial) System.out.println("Contain special character. Please enter again: ");
                if(check) System.out.println("Empty input");
                System.out.println("Please enter again: ");
            }
        }
        
        return data;
    }
    
    public static int inputInt(String msg, String name){
        boolean check=true;
        System.out.print(msg);
        int data=0;
        while(check){
            try{
                data=Integer.parseInt(sc.nextLine());
                check=false;
                if(data<0){
                    System.out.println(name+" cannot be under 0. Please enter again");
                    check=true;
                }
            }catch(NumberFormatException e){
                System.out.println("Wrong format!!! Please enter again.");
            }
            
        }        
        return data;
    }
    
    public static double inputDouble(String msg, String name){
        boolean check=true;
        System.out.print(msg);
        double data=0;
        while(check){
            try{
                data=Double.parseDouble(sc.nextLine());
                check=false;
                if(data<0){
                    System.out.println(name+" cannot be under 0. Please enter again");
                    check=true;
                }               
            }catch(NumberFormatException e){
                System.out.println("Wrong format!!! Please enter again.");
            }
            
        }
        
        return data;
    }
}
