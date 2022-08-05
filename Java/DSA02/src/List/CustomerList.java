
package List;

import Main.Inputter;
import Object.Customer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CustomerList {
    
    LLNode head, tail;
    
    public CustomerList() {
        this.head=this.tail=null;
    }
    
    public boolean isEmpty(){
        return this.head==null;
    }
    
    //////////////////////////Continue function//////////////////////////
    public boolean continueYN(String nn){
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------------------------\n");
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
                    System.out.print("Please enter 1 or 2: ");  
                }
            }catch(NumberFormatException e){
                System.out.print("Wrong format. Please enter again: ");
            }
        }
        System.out.println("\n");
        return choice==1;
    }
    
    
    public void addCustomerToNode(Customer cus){
        if(isEmpty()){
            this.head=this.tail=new LLNode(cus);
            return;
        }
        LLNode newNode=new LLNode(cus);
        this.tail.setNext(newNode);
        this.tail=newNode;
    }
    
    public void showList(){
        if(isEmpty()){
            System.out.println("List is empty!!!");
            return;
        }
        LLNode selectedNode = this.head;
        System.out.println("|===============================================|");
        System.out.format("|%-15s|%-15s|%-15s|\n" ,"CUSTOMER CODE", "NAME", "PHONE");
        System.out.println("|===============================================|");
        while(selectedNode!=null){
            System.out.format("|%-15s|%-15s|%-15s|\n" ,selectedNode.getCus().getCcode(), selectedNode.getCus().getCus_name(), selectedNode.getCus().getPhone());
            selectedNode=selectedNode.getNext();
        }
        System.out.println("|===============================================|");
    }
    
    public boolean checkDup(String ccode){
        if(isEmpty()){
            return false;
        }
        LLNode current = this.head;
        while(current!=null){
            String node = current.getCus().getCcode().replaceAll("[^a-zA-Z0-9?!]","");
            if(node.equals(ccode)){
                System.out.println("Customer Code is duplicated. Please enter again.");
                return true;
            }
            current=current.getNext();
        }
        return false;
    }
    
    public void addCustomer(){
        System.out.println("--------------------------------------------------");
        String newCode=null, newName, newPhone;
        boolean q=true, s=true;
        boolean idDup=true;
        while(idDup){
            newCode = Inputter.inputStrId("Code: ");
            newCode=newCode.toUpperCase();
            idDup=checkDup(newCode);
        }
        //inputName
        newName = Inputter.inputStr("Name: ");
        newName = newName.toUpperCase();           
        //inputPhone
        newPhone = Inputter.inputStr("Phone: ");
        
        Customer pro=new Customer(newCode, newName, newPhone);
        this.addCustomerToNode(pro);
        System.out.println("Customer has been added");
        writeToFile();
        if(continueYN("continue to add more customer information")){            
            addCustomer();          
        }
        
    }
    
    public Customer searchCode(){
        if(isEmpty()){
            System.out.println("List is Empty!!!");
            return null;
        }
        String scode = Inputter.inputStrId("Enter customer code to find: ").toUpperCase();
        boolean isFound = false;
        LLNode selectedNode = this.head;
        int count = 0;
        while(selectedNode!=null){
            //System.out.println(selectedNode.getCus().getCcode()+".");
            String node = selectedNode.getCus().getCcode().replaceAll("[^a-zA-Z0-9?!]","");
            if(node.equals(scode)){
                System.out.println("\n\t-----RECORD FOUND-----");
                System.out.println("|===============================================|");
                System.out.format("|%-15s|%-15s|%-15s|\n" ,"CODE", "NAME", "PHONE");
                System.out.println("|===============================================|");
                System.out.format("|%-15s|%-15s|%-15s|\n" ,selectedNode.getCus().getCcode(), selectedNode.getCus().getCus_name(), selectedNode.getCus().getPhone());
                System.out.println("|===============================================|");
                return selectedNode.getCus();
            }
            selectedNode=selectedNode.getNext();
        }
        System.out.println(scode+ " does not exist");
        return null;
    }
    
    public void deleteCode(){
        if(isEmpty()){
            System.out.println("List is Empty!!!");
            return;
        }
        LLNode selectedNote = head;
        String dcode = Inputter.inputStrId("Enter code to delete: ").toUpperCase();
        String nodeHead = this.head.getCus().getCcode().replaceAll("[^a-zA-Z0-9?!]","");
        if(nodeHead.equals(dcode)){
            if(continueYN("continue to delete customer information")){            
                this.head = this.head.getNext();
                writeToFile();
                System.out.println("DELETED!!!"); 
                return;
            }
            else return;
        }
        while(selectedNote!=null){
            if(selectedNote.getNext()==null){
                System.out.println(dcode+ " does not exist");
                return;
            }
            String nodeSe = selectedNote.getNext().getCus().getCcode().replaceAll("[^a-zA-Z0-9?!]","");
            
            if(nodeSe.equals(dcode)){
                if(continueYN("continue to delete customer information")){
                    selectedNote.setNext(selectedNote.getNext().getNext());
                    writeToFile();
                    System.out.println("DELETED!!!");
                    return;
                }else return;
            }
            selectedNote = selectedNote.getNext();
        }
    }
    
    public void readFromFile(){
        String filename = "Customer.txt";
        File file = new File(filename);
        FileReader f = null;
        BufferedReader bf = null;
        
        String line;
        try {
            if (file.exists() == false) {
                file.createNewFile();
            }
            f = new FileReader(file);
            bf = new BufferedReader(f); 
            while ((line = bf.readLine()) != null){ 
                String[] tmp = line.split("\\|");
                if (tmp.length == 3) {
                    String ccode = tmp[0];
                    String cus_name = tmp[1];
                    String phone = tmp[2];
                    Customer cus = new Customer(ccode, cus_name, phone);
                    this.addCustomerToNode(cus);
                }
            }       
        } catch (IOException e) {
            System.out.println(e);
        }
        finally {
            try {
                if (f != null) f.close();
                if (bf!= null) bf.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
    public void writeToFile() {
        String filename = "Customer.txt";
        PrintWriter f = null;
        try {
            f = new PrintWriter(filename);
            LLNode current = this.head;
            while(current!=null){
                f.println(current.cus.toString());
                current=current.getNext();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            try {
                if(f != null) f.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
