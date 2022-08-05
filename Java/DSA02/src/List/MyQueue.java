
package List;

import java.util.LinkedList;

public class MyQueue{
    LinkedList<BSTNode> t;
    public MyQueue(){
        t=new LinkedList<>();  
    }
    public void clear() {
        t.clear();  
    }
    public boolean isEmpty(){
        return(t.isEmpty());  
    }
    public void enqueue(BSTNode p) {
        t.addLast(p);
    }
    public BSTNode dequeue(){
        if(isEmpty()) return(null);
        return(t.removeFirst());
    }
    public BSTNode front(){
        if(isEmpty()) return(null);
        return(t.getFirst());    
    }
}
