package List;

import Object.Booking;
import Object.Customer;


public class LLNode {
    Customer cus;
    Booking boo;
    LLNode next;
    
    public LLNode(Booking boo) {
        this.boo = boo;
        this.next = null;
    }
    
    public LLNode(Customer cus) {
        this.cus = cus;
        this.next = null;
    }

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public Booking getBoo() {
        return boo;
    }

    public void setBoo(Booking boo) {
        this.boo = boo;
    }

    public LLNode getNext() {
        return next;
    }

    public void setNext(LLNode next) {
        this.next = next;
    }
    
    
}
