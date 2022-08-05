package Main;


import List.BSTree;
import List.BookingList;
import List.CustomerList;
import Menu.Menu;
import Menu.MenuBooking;
import Menu.MenuCustomer;
import Menu.MenuTrain;
import Object.Train;
import java.io.IOException;



public class Demo {
    public static void main(String[] args) throws IOException {
        CustomerList listCus = new CustomerList();
        BookingList listBoo = new BookingList();
        BSTree t = new BSTree();
        Menu menu = new Menu();
        MenuTrain menu1 = new MenuTrain();
        MenuCustomer menu2 = new MenuCustomer();
        MenuBooking menu3 = new MenuBooking();
/////////////////////LOAD FROM FILE/////////////////////
        t.loadFile();
        listCus.readFromFile();
/////////////////////MAIN MENU/////////////////////
        menu.add("Train");
        menu.add("Customer");
        menu.add("Booking");
        menu.add("Quit");
/////////////////////TRAIN MENU/////////////////////
        menu1.add("Input Train information");
        menu1.add("Display data In-order traverse");
        menu1.add("Display data Breadth-first traverse ");
        menu1.add("Search by train code");
        menu1.add("Delete by train code (copying method)");
        menu1.add("Simply balancing binary seach tree");
        menu1.add("Save data in-order traverse to file ");
        menu1.add("Exit");
/////////////////////CUSTOMER MENU/////////////////////
        menu2.add("Add Customer");
        menu2.add("Display data");
        menu2.add("Search by code");
        menu2.add("Delete by code");
        menu2.add("Exit");
/////////////////////BOOKING MENU/////////////////////
        menu3.add("Input");
        menu3.add("Display data");
        menu3.add("Sort by Train code and Customer code");
        menu3.add("Exit");
        
       
        int choice, choice1, choice2, choice3;
        do{
            System.out.println("\n============================================================");
            System.out.println("TRAIN BOOKING MANAGERMENT SYSTEM - @2021 by <SE161942 - NGUYEN GIANG NAM>");
            choice = menu.getChoice();
            switch(choice){
                case 1:
                    do{
                        System.out.println("\n============================================================");
                        System.out.println("TRAIN MANAGERMENT SYSTEM");
                        choice1 = menu1.getChoice();
                        switch(choice1){
                            case 1:
                                t.input();
                                break;
                            case 2:
                                t.displayInorder();
                                t.print();
                                break;
                            case 3:
                                t.displayBreadthF();
                                t.print();
                                break;
                            case 4:
                                t.search();
                                break;     
                            case 5:
                                t.delete();
                                break;     
                            case 6:
                                t.bal();
                                break;   
                            case 7:
                                t.saveFileInorder();
                                break;
                            case 8:
                                choice1=11;
                                break; 
                        }
                    }while((choice1>=1)&&choice1<9);
                    break;
                    
                case 2:
                    do{
                        System.out.println("\n============================================================");
                        System.out.println("CUSTOMER MANAGERMENT SYSTEM");
                        choice2 = menu2.getChoice();
                        switch(choice2){
                            case 1:
                                listCus.addCustomer();
                                break;
                            case 2:
                                listCus.showList();
                                break;
                            case 3:
                                listCus.searchCode();
                                break;
                            case 4:
                                listCus.deleteCode();
                                break;    
                            case 5:
                                choice2=10;
                                break;
                                 
                        }
                    }while((choice2>=1)&&choice2<6);
                    break;
                    
                case 3:
                    do{
                        System.out.println("\n============================================================");
                        System.out.println("ORDER MANAGERMENT SYSTEM");
                        choice3 = menu3.getChoice();
                        switch(choice3){
                            case 1:
                                Train a;
                                String tcode, ccode;
                                int booked;
                                t.displayInorder();
                                try {
                                    
                                    a = t.search2();
                                    tcode = a.getTcode();
                                    booked = a.getBooked();
                                    
                                }
                                catch(NullPointerException e) {
                                    System.out.println("Booking cannot be accepted.");
                                    break;
                                }
                                System.out.println("\n\n");
                                listCus.showList();
                                try{
                                    ccode = listCus.searchCode().getCcode();
                                }catch(NullPointerException e){
                                    System.out.println("Order cannot be accepted.");
                                    break;
                                }
                                
                                
                                if(tcode == null || ccode == null){
                                    System.out.println("Order cannot be accepted.");
                                    break;
                                }
                                else listBoo.addBooking(tcode, ccode, booked);
                                break;
                            case 2:
                                listBoo.showList();
                                break;
                            case 3:
                                listBoo.sortList();
                                break;
                            case 4:
                                choice3=10;
                                break; 
                        }
                    }while((choice3>=1)&&choice3<5);
                    break;
                    
                case 4:
                    System.out.println("Quit !!!");
                    System.exit(0);
            }
        }while((choice>=1)&&choice<5);
        

            
    }
    
   
}
