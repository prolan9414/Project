
package List;

import Object.Booking;

public class BookingList {
    LLNode head, tail;

    public BookingList() {
        this.head=this.tail=null;
    }
    
    public boolean isEmpty(){
        return this.head==null;
    }
    
    public void addBookingToNode(Booking boo){
        if(isEmpty()){
            this.head=this.tail=new LLNode(boo);
            return;
        }
        LLNode newNode=new LLNode(boo);
        this.tail.setNext(newNode);
        this.tail=newNode;
    }
    
    public void addBooking(String tcode, String ccode, int booked){
        Booking ord = new Booking(tcode, ccode, booked);
        this.addBookingToNode(ord);
        System.out.println("BOOKING HAS BEEN ADDED");
    }
    
    public void showList(){
        if(isEmpty()){
            System.out.println("List is empty!!!");
            return;
        }
        LLNode selectedNode = this.head;
        System.out.println("|===============================================|");
        System.out.format("|%-15s|%-15s|%-15s|\n" ,"TRAIN CODE", "CUSTOMER CODE", "BOOKED SEAT");
        System.out.println("|===============================================|");
        while(selectedNode!=null){
            System.out.format("|%-15s|%-15s|%-15s|\n" ,selectedNode.getBoo().getTcode(), selectedNode.getBoo().getCcode(), selectedNode.getBoo().getSeat());
            selectedNode=selectedNode.getNext();
        }
        System.out.println("|===============================================|");
    }
    
    public void sortList() {  
        //Node current will point to head  
        LLNode current = head, index;  
        Booking temp;  
          
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
                    if(current.getBoo().getTcode().compareTo(index.getBoo().getTcode())>0) {  
                        temp = current.getBoo();  
                        current.setBoo(index.getBoo());  
                        index.setBoo(temp);  
                    }
                    else  if(current.getBoo().getTcode().compareTo(index.getBoo().getTcode())==0){
                        if(current.getBoo().getCcode().compareTo(index.getBoo().getCcode())>0) {  
                            temp = current.getBoo();  
                            current.setBoo(index.getBoo());  
                            index.setBoo(temp);  
                        }
                    }
                    index = index.next;  
                }  
                current = current.next;
            }
        }
    }
}
