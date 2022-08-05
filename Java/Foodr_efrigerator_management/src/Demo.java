
import java.text.ParseException;


public class Demo {
    public static void main(String[] args) throws ParseException {
        FoodList listObj = new FoodList();
        Menu menu = new Menu();
        menu.add("Add new Food item");
        menu.add("Search food by name");
        menu.add("Remove food by ID");
        menu.add("Print all the food list");
        menu.add("Quit");
        int choice;
        do{
            System.out.println("\n============================================================");
            System.out.println("FOOD MANAGER - @2021 by <SE161942 - NGUYEN GIANG NAM>");
            choice = menu.getChoice();
            switch(choice){
                case 1:
                    listObj.addFood();
                    break;
                    
                case 2:
                    listObj.searchFood();
                    break;
                    
                case 3:
                    listObj.removeFood();
                    break;
                    
                case 4:
                    listObj.printAll();
                    break;
               
                case 5:
                    System.out.println("Quit !!!");
                    System.exit(0);

            }
        }while((choice>=1)&&choice<6);
        

            
    }
    
   
}
