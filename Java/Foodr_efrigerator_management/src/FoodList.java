
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class FoodList extends ArrayList<Food>{
    static Scanner sc=new Scanner(System.in);

    public FoodList() {
        super();
    }
    
    public boolean continueYN(String nn){
        System.out.println("--------------------------------------------------");
        System.out.println("Do you want to " +nn+" ?");
        System.out.println("1. Yes\n2. No");
        System.out.print("Input: ");
        boolean check=true;
        int choice=0;
        while(check){
            try{
                choice = Integer.parseInt(sc.nextLine());
                if(choice==1||choice==2){
                    check=false;
                }
                else{
                    System.out.println("Please enter 1 or 2.");  
                }
            }catch(NumberFormatException e){
                System.out.println("Wrong format. Please enter again.");
            }
        }
        return choice==1;
    }
    
    public Food search(String ID){
        ID=ID.trim().toUpperCase();
        for (Food aThi : this) {
            if (aThi.getID().equals(ID)) {
                return aThi;
            }
        }
        return null;
    }
    
    
    
    private boolean isIdDupplicated(String ID){
        ID = ID.trim().toUpperCase();
        return search(ID) != null;
    }
    
    public void addFood() throws ParseException{
        System.out.println("--------------------------------------------------");
        String newID, newName, newType, newPlace;
        boolean q=true;
        float newWeight;
        Date expirationDate = null;
        int newsoluong;
        boolean idDup;
        do{
            newID = Inputter.inputStrId("ID: ");
            newID=newID.toUpperCase();
            idDup=isIdDupplicated(newID);
            if(idDup) System.out.println("ID is Dupplicated. Please enter again.");
        }while(idDup==true);        
        //inputName
        newName = Inputter.inputStr("Name: ");
        newName = newName.toUpperCase().replaceAll("\\s{2,}"," ");           
        //inputWeight
        newWeight = Inputter.inputFloat("Weight(grams): ", 10000);       
        //intputType
        newType = Inputter.inputStr("Type: ").replaceAll("\\s{2,}"," ").toLowerCase();       
        //inputPlace
        newPlace = Inputter.inputStr("Place: ").replaceAll("\\s{2,}"," ").toLowerCase();       
        //inputDate
        Date today=new Date();
        SimpleDateFormat to=new SimpleDateFormat("dd/MM/yyyy");
        while(q){
            try{
                SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
                System.out.println("The expired date(dd/MM/yyyy): ");
                String dateInput = sc.nextLine();
                df.setLenient(false);
                expirationDate=df.parse(dateInput);
                if(today.before(expirationDate)){
                    q=false;
                }
                else{
                    System.out.println("The date input must be after "+to.format(today));
                    q=true;
                }
            }catch(ParseException e){
                System.out.println("Wrong day format. Please enter again.");
            }
        }    
        
        newsoluong = Inputter.inputInt("Enter so luong: ", 20);
        Food foood=new Food(newID, newName, newWeight, newType, newPlace, expirationDate, newsoluong);
        this.add(foood);
        System.out.println("New food has been added");
        saveToFile();
        if(continueYN("continue to add more food")){            
            addFood();          
        }
    }
    
    
    
    public void searchFood(){
        int count=0;
        if(this.isEmpty())
            System.out.println("Empty list. No search can be performed!");
        else{
            System.out.println("--------------------------------------------------");
            String sName = Inputter.inputStr("Input food name for search: ");  
            sName=sName.trim().toUpperCase().replaceAll("\\s{2,}"," ");
            for(int i=0; i<this.size(); i++){
                if(this.get(i).getName().equals(sName)){
                    if(count==0) System.out.format("%-15s|%-15s|%-5s|%-15s|%-24s|%-15s\n" ,"ID", "NAME", "WEIGHT(GRAMS)","TYPE","PLACE", "EXPIRATION DATE");
                    Food st =  this.get(i);
                    System.out.println(st);
                    count++;
                }
            }
            if(count==0) System.out.println("Food "+sName+" doesn't existed!");
            
            if(continueYN("continue to search")){
                searchFood();
            }
        }              
    }
    
    
    public void removeFood(){
        if(this.isEmpty()){
            System.out.println("Empty list. Remove option cannot be performed!");
            return;
        }
            
        else{
            System.out.println("--------------------------------------------------"); 
            String sId = Inputter.inputStrId("Input ID of removed food:");
            Food st = this.search(sId);
            if(st==null){
                System.out.println("Food's ID "+sId+" doesn't existed!");
                return;
            }
                
            else{
                if(continueYN("remove the food")){
                    this.remove(st);
                    System.out.println("Food "+sId+" has been removed.");
                }
            }
        }
        if(!this.isEmpty()){
           saveToFile();
           printAll(); 
        }
        
    }
    
      
    public void sortFood(){
        sort((t, t1) -> {
                 if ((t.expirationDate).compareTo(t1.expirationDate) > 0 ) {
                return -1;
            } else if ((t.expirationDate).compareTo(t1.expirationDate) < 0){
                return 1;
            } else {
                if (t.getID().compareTo(t1.getID()) > 0) {
                    return 1;
                } else if (t.getID().compareTo(t1.getID()) < 0) {
                    return -1;
                }
            }
                return 0;
            });
    }
      
    public void saveToFile(){
        if(this.isEmpty()){
            System.out.println("Empty list.");
            return;
        }
        try{
            String filename = "FoodList.dat";
            FileOutputStream f = new FileOutputStream(filename);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for(Food b: this) fo.writeObject(b);
            fo.close(); f.close(); 
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    
    public void printAll(){
        if(this.isEmpty())
            System.out.println("Empty list!");
        else{
            System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.format("%-15s|%-15s|%-5s|%-15s|%-24s|%-15s|%-15s\n" ,"ID", "NAME", "WEIGHT(GRAMS)","TYPE","PLACE", "EXPIRATION DATE", "SO LUONG");
            sortFood(); 
            for(Food st: this){
                System.out.println(st);
            }
            System.out.println("Total: "+this.size()+" food(s).");
            System.out.println("-------------------------------------------------------------------------------------------------------");
        }
    }
    
}
