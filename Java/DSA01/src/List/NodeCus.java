
package List;

import Object.Customer;

public class NodeCus {
    Customer cus;
    Node next;

    public NodeCus(Customer cus) {
        this.cus = cus;
        this.next = null;
    }

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
}
