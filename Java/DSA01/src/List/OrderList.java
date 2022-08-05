
package List;

import Object.Order;

public class OrderList {
    Node head, tail;

    public OrderList() {
        this.head=this.tail=null;
    }
    
    public boolean isEmpty(){
        return this.head==null;
    }
    
    public void addOrderToNode(Order or){
        if(isEmpty()){
            this.head=this.tail=new Node(or);
            return;
        }
        Node newNode=new Node(or);
        this.tail.setNext(newNode);
        this.tail=newNode;
    }
    
    public void addOrder(String pcode, String ccode, int qty){
        Order ord = new Order(pcode, ccode, qty);
        this.addOrderToNode(ord);
        System.out.println("ORDER HAS BEEN ADDED");
    }
    
    public void showList(){
        if(isEmpty()){
            System.out.println("List is empty!!!");
            return;
        }
        Node selectedNode = this.head;
        System.out.format("%-15s|%-15s|%-15s\n" ,"PRODUCT CODE", "CUSTOMER CODE", "QUANTITY");
        while(selectedNode!=null){
            System.out.format("%-15s|%-15s|%-15s\n" ,selectedNode.getOr().getPcode(), selectedNode.getOr().getCcode(), selectedNode.getOr().getQuantity());
            selectedNode=selectedNode.getNext();
        }
    }
    
    public void sortList() {  
        //Node current will point to head  
        Node current = head, index;  
        Order temp;  
          
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
                    if(current.getOr().getPcode().compareTo(index.getOr().getPcode())>0) {  
                        temp = current.getOr();  
                        current.setOr(index.getOr());  
                        index.setOr(temp);  
                    }
                    else  if(current.getOr().getPcode().compareTo(index.getOr().getPcode())==0){
                        if(current.getOr().getCcode().compareTo(index.getOr().getCcode())>0) {  
                            temp = current.getOr();  
                            current.setOr(index.getOr());  
                            index.setOr(temp);  
                        }
                    }
                    index = index.next;  
                }  
                current = current.next;
            }
        }
    }
   
}
