package Feature;


import java.util.ArrayList;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class Menu extends ArrayList<String> implements Colour{

    public Menu() {
        super();
    }
    
    public int getChoice(){
        int result = 0;
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        if(this.size()>0){
            for(int i=0; i<this.size(); i++)
                System.out.println("*     "+(i+1)+" - "+this.get(i));
            System.out.println("*************************************************************************");
            System.out.print(TEXT_PURPLE+"Please select an operation: "+TEXT_RESET);
            do{
                try{
                    result = Integer.parseInt(sc.nextLine());
                    if(result<1||result>6) System.out.print(TEXT_RED+"Please enter the number from 1 to 6: "+TEXT_RESET);
                    cont=false;
                }catch(NumberFormatException e){
                    System.out.print(TEXT_RED+"Wrong format. Please enter again: "+TEXT_RESET);
                    cont=true;
                }
            }while(cont==true||result<1||result>6);
            
            
        }
        return result;
    }
}
