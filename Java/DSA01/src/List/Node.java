package List;


import Object.Customer;
import Object.Order;
import Object.Thing;


public class Node {
    Thing th;
    Customer cus;
    Order or;
    Node next;
    
    
    
    public Node(Thing th) {
        this.th = th;
        this.next = null;
    }
    
    public Node(Customer cus) {
        this.cus = cus;
        this.next = null;
    }
    
    public Node(Order or) {
        this.or = or;
        this.next = null;
    }

    public Order getOr() {
        return or;
    }

    public void setOr(Order or) {
        this.or = or;
    }

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }
    
    public Thing getTh() {
        return th;
    }

    public void setTh(Thing th) {
        this.th = th;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    } 
    
     
}
