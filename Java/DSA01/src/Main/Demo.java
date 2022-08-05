package Main;


import List.CustomerList;
import List.OrderList;
import Menu.Menu;
import List.ThingList;
import Menu.MenuCustomer;
import Menu.MenuOrder;
import Menu.MenuProduct;
import Object.Thing;
import java.io.IOException;



public class Demo {
    public static void main(String[] args) throws IOException {
        ThingList listObj = new ThingList();
        CustomerList listCus = new CustomerList();
        OrderList listOr = new OrderList();
        Menu menu = new Menu();
        MenuProduct menu1 = new MenuProduct();
        MenuCustomer menu2 = new MenuCustomer();
        MenuOrder menu3 = new MenuOrder();
        menu.add("Product");
        menu.add("Customer");
        menu.add("Oder");
        menu.add("Quit");
        ///////////////
        menu1.add("Load data from file");
        menu1.add("Add Product");
        menu1.add("Display data");
        menu1.add("Save list to file");
        menu1.add("Search by code");
        menu1.add("Delete by code");
        menu1.add("Sort by code");
        menu1.add("Add after position k");
        menu1.add("Delete the node after the node having code = xCode");
        menu1.add("Exit");
        ////////////////
        menu2.add("Load data from file");
        menu2.add("Add Customer");
        menu2.add("Display data");
        menu2.add("Save list to file");
        menu2.add("Search by code");
        menu2.add("Delete by code");
        menu2.add("Exit");
        ///////////////
        menu3.add("Input");
        menu3.add("Display data");
        menu3.add("Sort by Product code and Customer code");
        menu3.add("Exit");
        
       
        int choice, choice1, choice2, choice3;
        do{
            System.out.println("\n============================================================");
            System.out.println("SALE MANAGERMENT SYSTEM - @2021 by <SE161942 - NGUYEN GIANG NAM>");
            choice = menu.getChoice();
            switch(choice){
                case 1:
                    do{
                        System.out.println("\n============================================================");
                        System.out.println("PRODUCT MANAGERMENT SYSTEM");
                        choice1 = menu1.getChoice();
                        switch(choice1){
                            case 1:
                                listObj.readFromFile("product.txt");
                                break;
                            case 2:
                                listObj.addProduct();
                                break;
                            case 3:
                                listObj.showList();
                                break;
                            case 4:
                                listObj.writeToFile("product.txt");
                                break;
                            case 5:
                                listObj.searchCode();
                                break;     
                            case 6:
                                listObj.deleteCode();
                                break;     
                            case 7:
                                listObj.sortList();
                                break;     
                            case 8:
                                listObj.addAfterK();
                                break;     
                            case 9:
                                listObj.deleteAfterPcode();
                                break;
                            case 10:
                                choice1=11;
                                break;    
                        }
                    }while((choice1>=1)&&choice1<11);
                    break;
                    
                case 2:
                    do{
                        System.out.println("\n============================================================");
                        System.out.println("CUSTOMER MANAGERMENT SYSTEM");
                        choice2 = menu2.getChoice();
                        switch(choice2){
                            case 1:
                                listCus.readFromFile("customer.txt");
                                break;
                            case 2:
                                listCus.addCustomer();
                                break;
                            case 3:
                                listCus.showList();
                                break;
                            case 4:
                                listCus.writeToFile("customer.txt");
                                break;    
                            case 5:
                                listCus.searchCode();
                                break;    
                            case 6:
                                listCus.deleteCode();
                                break; 
                            case 7:
                                choice2=10;
                                break; 
                        }
                    }while((choice2>=1)&&choice2<8);
                    break;
                    
                case 3:
                    do{
                        System.out.println("\n============================================================");
                        System.out.println("ORDER MANAGERMENT SYSTEM");
                        choice3 = menu3.getChoice();
                        switch(choice3){
                            case 1:
                                boolean check = true;
                                Thing a;
                                String pcode, ccode;
                                int qtyint;
                                int sale;
                                int want=0;
                                try {
//                                    if(listObj.searchCode().getPcode()==null){
//                                        break;
//                                    }
                                    a = listObj.searchCode();
                                    pcode = a.getPcode();
                                    qtyint = a.getQuantity();
                                    sale = a.getSale();
                                    
                                }
                                catch(NullPointerException e) {
                                    System.out.println("Order cannot be accepted.");
                                    break;
                                }
                                
                                while(check){
                                    int qtyaft = qtyint - sale;
                                    want = Inputter.inputInt("Enter quantity: ", "Quantity");
                                    if(qtyaft==0){
                                        System.out.println("There no product left.");
                                        break;
                                    }
                                    if(want>qtyaft){
                                        System.out.println("There are only "+qtyaft+" left.");
                                    }
                                    else check = false;
                                }
                                try{
                                    ccode = listCus.searchCode().getCcode();
                                }catch(NullPointerException e){
                                    System.out.println("Order cannot be accepted.");
                                    break;
                                }
                                
                                
                                if(pcode == null || ccode == null){
                                    System.out.println("Order cannot be accepted.");
                                    break;
                                }
                                else listOr.addOrder(pcode, ccode, want);
                                break;
                            case 2:
                                listOr.showList();
                                break;
                            case 3:
                                listOr.sortList();
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
