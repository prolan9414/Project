
package List;
import Main.Inputter;
import Object.Customer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;





public class CustomerList {
    Node head, tail;

    public CustomerList() {
        this.head=this.tail=null;
    }
    
    public boolean isEmpty(){
        return this.head==null;
    }
    
    public void addCustomerToNode(Customer cus){
        if(isEmpty()){
            this.head=this.tail=new Node(cus);
            return;
        }
        Node newNode=new Node(cus);
        this.tail.setNext(newNode);
        this.tail=newNode;
    }
    
    public void showList(){
        if(isEmpty()){
            System.out.println("List is empty!!!");
            return;
        }
        Node selectedNode = this.head;
        System.out.format("%-15s|%-15s|%-15s\n" ,"CODE", "NAME", "PHONE");
        while(selectedNode!=null){
            System.out.format("%-15s|%-15s|%-15s\n" ,selectedNode.getCus().getCcode(), selectedNode.getCus().getCus_name(), selectedNode.getCus().getPhone());
            selectedNode=selectedNode.getNext();
        }
    }
    
    public boolean checkDup(String ccode){
        if(isEmpty()){
            return false;
        }
        Node current = this.head;
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
    }
    
    public void writeToFile(String filename) {
        PrintWriter f = null;
        try {
            f = new PrintWriter(filename);
            Node current = this.head;
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
        System.out.println("SAVE!!!");
    }
    
    public void readFromFile(String filename) {
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
        System.out.println("READ!!!");
    }
    
    public Customer searchCode(){
        if(isEmpty()){
            System.out.println("List is Empty!!!");
            return null;
        }
        String scode = Inputter.inputStrId("Enter customer code to find: ").toUpperCase();
        boolean isFound = false;
        Node selectedNode = this.head;
        int count = 0;
        while(selectedNode!=null){
            //System.out.println(selectedNode.getCus().getCcode()+".");
            String node = selectedNode.getCus().getCcode().replaceAll("[^a-zA-Z0-9?!]","");
            if(node.equals(scode)){
                System.out.println("FOUND");
                System.out.format("%-15s|%-15s|%-15s\n" ,"CODE", "NAME", "PHONE");
                System.out.format("%-15s|%-15s|%-15s\n" ,selectedNode.getCus().getCcode(), selectedNode.getCus().getCus_name(), selectedNode.getCus().getPhone());
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
        Node selectedNote = head;
        String dcode = Inputter.inputStrId("Enter code to delete: ").toUpperCase();
        String nodeHead = this.head.getCus().getCcode().replaceAll("[^a-zA-Z0-9?!]","");
        if(nodeHead.equals(dcode)){
            this.head = this.head.getNext();
            System.out.println("DELETED!!!");
            return;
        }
        while(selectedNote!=null){
            if(selectedNote.getNext()==null){
                System.out.println(dcode+ " does not exist");
                return;
            }
//            System.out.println(head);
//            System.out.println(selectedNote);
//            System.out.println(selectedNote.getNext());
//            System.out.println(dcode);
            String nodeSe = selectedNote.getNext().getCus().getCcode().replaceAll("[^a-zA-Z0-9?!]","");
//            System.out.println(nodeSe);
//            System.out.println(nodeSe.equals(dcode));
            
            if(nodeSe.equals(dcode)){
                selectedNote.setNext(selectedNote.getNext().getNext());
                System.out.println("DELETED!!!");
                return;
            }
            selectedNote = selectedNote.getNext();
        }
        
//        for(selectedNote=head; selectedNote!=null; selectedNote=selectedNote.getNext()){
//            if(selectedNote.getNext()==null) return;
//            System.out.println(head);
//            System.out.println(selectedNote);
//            System.out.println(selectedNote.getNext());
//            System.out.println(dcode);
//            String nodeSe = selectedNote.getNext().getCus().getCcode().replaceAll("[^a-zA-Z0-9?!]","");
//            System.out.println(nodeSe);
//            System.out.println(nodeSe.equals(dcode));
//            
//            if(nodeSe.equals(dcode)){
//                selectedNote.setNext(selectedNote.getNext().getNext());
//                System.out.println("DELETED!!!");
//                return;
//            }
//        }
    }
    
    
}


