package GUI;


import DTO.InjectionList;
import Feature.Colour;
import Feature.Menu;
import java.text.ParseException;

public class Demo implements Colour{
    public static void main(String[] args) throws ParseException {
        InjectionList listObj = new InjectionList();
        listObj.readFromFileInjection();
        listObj.readFromFileStudent();
        listObj.readFromFileVaccine();
        listObj.readFromFilePlace();
        listObj.addRead();
        
        Menu menu = new Menu();
        menu.add("Show all injected student                                     *");
        menu.add("Add new injection information                                 *");
        menu.add("Update injection information                                  *");
        menu.add("Delete injection information                                  *");
        menu.add("Search for injection information by Vaccine Name                *");
        menu.add("Quit                                                          *");
        int choice;
        do{
//           System.out.println(TEXT_RED + "This text is red!" + TEXT_RESET);
//           System.out.println(TEXT_BLUE + "This text is red!" + TEXT_RESET);
//            System.out.println((char)27 + "[31m" + "ERROR MESSAGE IN RED");
            System.out.println("\n");
            System.out.println("*************************************************************************");
            System.out.println("*      "+TEXT_BLUE+"VACCINE MANAGEMENT - @2021 by <SE161942 - NGUYEN GIANG NAM>"+TEXT_RESET+"      *");
            System.out.println("*************************************************************************");
            choice = menu.getChoice();
            switch(choice){
                case 1:
                    listObj.printInjectionList();
                    break;
                    
                case 2:
                    listObj.addInject();
                    break;
                    
                case 3:
                    listObj.updateInject();
                    break;
                    
                case 4:
                    listObj.deleteInject();
                    break;
                    
                case 5:
                    listObj.searchInjectInfoVaccine();
                    break;
                    
                case 6:
                    System.out.println("Quit !!!");
                    System.exit(0);

            }
        }while((choice>=1)&&choice<7);
        

            
    }
    
   
}