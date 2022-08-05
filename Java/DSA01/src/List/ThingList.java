package List;


import Main.Inputter;
import Object.Thing;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;




public class ThingList{
    Node head, tail;

    public ThingList() {
        this.head=this.tail=null;
    }
    
    public boolean isEmpty(){
        return this.head==null;
    }
    
    public void addProductToNode(Thing th){
        if(isEmpty()){
            this.head=this.tail=new Node(th);
            return;
        }
        Node newNode=new Node(th);
        this.tail.setNext(newNode);
        this.tail=newNode;
    }
    
    public void showList(){
        if(isEmpty()){
            System.out.println("List is empty!!!");
            return;
        }
        Node selectedNode = this.head;
        System.out.format("%-15s|%-15s|%-15s|%-15s|%-24s\n" ,"CODE", "NAME", "QUANTITY","SALED","PRICE", "EXPIRATION DATE");
        while(selectedNode!=null){
            System.out.format("%-15s|%-15s|%-15d|%-15d|%-24.4f\n" ,selectedNode.getTh().getPcode(), selectedNode.getTh().getPro_name(), selectedNode.getTh().getQuantity(),
                    selectedNode.getTh().getSale(),selectedNode.getTh().getPrice());
            selectedNode=selectedNode.getNext();
        }
    }
    
    public boolean checkDup(String ccode){
        if(isEmpty()){
            return false;
        }
        Node current = this.head;
        while(current!=null){
            String node = current.getTh().getPcode().replaceAll("[^a-zA-Z0-9?!]","");
            if(node.equals(ccode)){
                System.out.println("Product Code is duplicated. Please enter again.");
                return true;
            }
            current=current.getNext();
        }
        return false;
    }
    
    public void addProduct(){
        System.out.println("--------------------------------------------------");
        String newCode=null, newName;
        int newQty, newSale=0;
        double newPrice;
        boolean q=true, s=true;
        float newWeight;
        boolean idDup=true;
        while(idDup){
            newCode = Inputter.inputStrId("Code: ");
            newCode=newCode.toUpperCase();
            idDup=checkDup(newCode);
        }
        //inputName
        newName = Inputter.inputStr("Name: ");
        newName = newName.toUpperCase();           
        //inputQuantity
        newQty = Inputter.inputInt("Quantity: ", "Quantity");
        //intputSaled
        while(s==true){
            newSale = Inputter.inputInt("Saled: ", "Saled");
            if(newSale<=newQty) s=false;
            else System.out.println("Saled must be lower or equal to Quantity. Please input again.");
        }           
        //inputPrice
        newPrice=Inputter.inputDouble("Price: ", "Price");       
        Thing pro=new Thing(newCode, newName, newQty, newSale, newPrice);
        this.addProductToNode(pro);
        System.out.println("Product has been added");
    }
    
    
    public void writeToFile(String filename) {
        PrintWriter f = null;
        try {
            f = new PrintWriter(filename);
            Node current = this.head;
            while(current!=null){
                f.println(current.th.toString());
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
                if (tmp.length == 5) {
                    String pcode = tmp[0];
                    String pro_name = tmp[1];
                    int qty = Integer.valueOf(tmp[2].replaceAll("[^0-9?!\\.]",""));
                    int saled = Integer.valueOf(tmp[3].replaceAll("[^0-9?!\\.]",""));
                    double price = Double.valueOf(tmp[4].replaceAll("[^0-9?!\\.]",""));
                    Thing th = new Thing(pcode, pro_name, qty, saled, price);
                    this.addProductToNode(th);
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
//   public void writeToFile(String fileName) throws IOException {
//       File file;
//       PrintWriter f = null;
//       FileWriter fr = null;
//       BufferedWriter br = null;
//        try {
//            file = new File(fileName);
//            fr = new FileWriter(file, true);
//            br = new BufferedWriter(fr);
//            f = new PrintWriter(br);
//            Node current = this.head;
//            while(current!=null){
//                //f.println(current.th.toString());
//                f.println(current.th.toString());
//                current=current.getNext();
//            }
//            
//        } catch (FileNotFoundException e) {
//            System.out.println(e);
//        } finally {
//            try {
//                if(f != null){
//                    
//                    //f.close();
//                    f.close();
//                    br.close();
//                    fr.close();
//                }
//            } catch (IOException e) {
//                System.out.println(e);
//            }
//        }
//    }
    
    public Thing searchCode(){
        if(isEmpty()){
            System.out.println("List is Empty!!!");
            return null;
        }
        String scode = Inputter.inputStrId("Enter product code to find: ").toUpperCase();
        boolean isFound = false;
        Node selectedNode = this.head;
        int count = 0;
        while(selectedNode!=null){
            String node = selectedNode.getTh().getPcode().replaceAll("[^a-zA-Z0-9?!]","");
            if(node.equals(scode)){
                System.out.println("FOUND");
                System.out.format("%-15s|%-15s|%-15s|%-15s|%-24s\n" ,"CODE", "NAME", "QUANTITY","SALED","PRICE", "EXPIRATION DATE");
                System.out.format("%-15s|%-15s|%-15d|%-15d|%-24.4f\n" ,selectedNode.getTh().getPcode(), selectedNode.getTh().getPro_name(), selectedNode.getTh().getQuantity(),
                    selectedNode.getTh().getSale(),selectedNode.getTh().getPrice());
                return selectedNode.getTh();
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
        String dcode = Inputter.inputStrId("Enter code to delete: ");
        Node selectedNote = this.head;
        String nodeHead = this.head.getTh().getPcode().replaceAll("[^a-zA-Z0-9?!]","");
        if(nodeHead.equals(dcode)){
            this.head = this.head.getNext();
            System.out.println("DELETED!!!");
            return;
        }
        while(selectedNote!=null){
            String nodeSe = selectedNote.getNext().getTh().getPcode().replaceAll("[^a-zA-Z0-9?!]","");
            if(nodeSe.equals(dcode)){
                selectedNote.setNext(selectedNote.getNext().getNext());
                System.out.println("DELETED!!!");
                return;
            }
            selectedNote = selectedNote.getNext();
        }
        System.out.println(dcode+ " does not exist");
    }
    
    public void sortList() {  
        //Node current will point to head  
        Node current = head, index;  
        Thing temp;  
          
        if(head == null) {  
            return;  
        }  
        else {  
            while(current != null) {  
                //Node index will point to node next to current  
                index = current.next;  
                  
                while(index != null) {  
                    //If current node's data is greater than index's node data, swap the data between them
                    //System.out.println(current.getTh().getPcode().compareTo(index.getTh().getPcode()));
                    if(current.getTh().getPcode().compareTo(index.getTh().getPcode())>0) {  
                        temp = current.getTh();  
                        current.setTh(index.getTh());  
                        index.setTh(temp);  
                    }  
                    index = index.next;  
                }  
                current = current.next;
            }
        }
    }
    
    public int countNode(){
        int count=0;
        for (Node n = head; n != null; n = n.next){
            count++;
        }
        return count;
    }
    
    public Thing addProduct2(){
        System.out.println("--------------------------------------------------");
        String newCode=null, newName;
        int newQty, newSale=0;
        double newPrice;
        boolean q=true, s=true;
        float newWeight;
        boolean idDup=true;
        while(idDup){
            newCode = Inputter.inputStrId("Code: ");
            newCode=newCode.toUpperCase();
            idDup=checkDup(newCode);
        }
        //inputName
        newName = Inputter.inputStr("Name: ");
        newName = newName.toUpperCase();           
        //inputQuantity
        newQty = Inputter.inputInt("Quantity: ", "Quantity");
        //intputSaled
        while(s==true){
            newSale = Inputter.inputInt("Saled: ", "Saled");
            if(newSale<=newQty) s=false;
            else System.out.println("Saled must be lower or equal to Quantity. Please input again.");
        }           
        //inputPrice
        newPrice=Inputter.inputDouble("Price: ", "Price");       
        Thing pro=new Thing(newCode, newName, newQty, newSale, newPrice);
        System.out.println("Product has been added");
        return pro;
    }
    
    public void addAfterK(){
        int position = Inputter.inputInt("Enter the position to add: ", "Position");
        Node node = new Node(addProduct2());
        node.next = null;

    if (this.head == null) {
      //if head is null and position is zero then exit.
      if (position != 0) {
       return;
      } else { //node set to the head.
       this.head = node;
      }
    }

    if (head != null && position == 0) {
      node.next = this.head;
      this.head = node;
      return;
    }

    Node current = this.head;
    Node previous = null;

    int i = 0;

    while (i < position) {
      previous = current;
      current = current.next;

      if (current == null) {
        break;
      }

       i++;
      }

      node.next = current;
      previous.next = node;
    }
    
    public void deleteAfterPcode(){
        if(isEmpty()){
            System.out.println("List is Empty!!!");
            return;
        }
        String dcode = Inputter.inputStrId("Enter code: ");
        Node selectedNote = this.head;
        String nodeHead = this.head.getTh().getPcode().replaceAll("[^a-zA-Z0-9?!]","");
        if(nodeHead.equals(dcode)){
            selectedNote.setNext(selectedNote.getNext().getNext());
            System.out.println("DELETED!!!");
            return;
        }
        String nodeTail = this.tail.getTh().getPcode().replaceAll("[^a-zA-Z0-9?!]","");
        if(nodeTail.equals(dcode)){
            System.out.println("There is no product after "+dcode);
            return;
        }
        
        while(selectedNote!=null){
            String nodeSe = selectedNote.getNext().getTh().getPcode().replaceAll("[^a-zA-Z0-9?!]","");
            if(nodeSe.equals(dcode)){
                    selectedNote = selectedNote.getNext();
                    selectedNote.setNext(selectedNote.getNext().getNext());
                    System.out.println("DELETED!!!");
                    return;
            }
            selectedNote = selectedNote.getNext();
        }
        System.out.println(dcode+ " does not exist");
    }
}
    
