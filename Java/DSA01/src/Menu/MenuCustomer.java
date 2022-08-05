
package Menu;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuCustomer extends ArrayList<String>{
    public MenuCustomer() {
        super();
    }
    
    public int getChoice(){
        int result = 0;
        boolean cont = true;
        Scanner sc = new Scanner(System.in);
        if(this.size()>0){
            for(int i=0; i<this.size(); i++)
                System.out.println((i+1)+" - "+this.get(i));
            System.out.println("============================================================\n");
            System.out.println("Please select an operation: ");
            do{
                try{
                    result = Integer.parseInt(sc.nextLine());
                    if(result<0||result>7) System.out.println("Please enter the number from 1 to 7");
                    cont=false;
                }catch(NumberFormatException e){
                    System.out.println("Please enter the number from 1 to 7");
                    cont=true;
                }
            }while(cont==true||result<0||result>7);
            
            
        }
        return result;
    }
}
